package community.model.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import community.model.service.CommunityService;
import community.model.vo.Community;
import photo.model.service.PhotoService;
import photo.model.vo.Photo;

@WebServlet("/community/update")
public class CommunityUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CommunityUpdateServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정할 정보 불러오기
		int communityNo = Integer.parseInt(request.getParameter("communityNo"));
		int communutyTagNo = Integer.parseInt(request.getParameter("communityTagNo"));
		
		// 게시물 상세보기 (Detail)에서 쓰인 메소드 가져와 재사용하기
		// (해당 게시물번호에 맞는 정보 가져오기) 
		Community community = new CommunityService().selectOneCommunity(communityNo);
		
		if(community != null) {
			// 위에 가져온 값들을 Update 페이지로 보내줌
			request.setAttribute("community", community);
			request.getRequestDispatcher("/WEB-INF/views/community/communityUpdate.jsp").forward(request, response);
		}else {
			System.out.println("update 서블릿에서 오류다!");
		}
		
		// 위 doGet 메소드는  List > Detail > 수정버튼 눌렀을 때 작동함
		// 아래 doPost 메소드는 Update > 등록(수정) 버튼 눌렀을 때 작동함
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet에서 받아온 값 수정해서 저장하기
		request.setCharacterEncoding("UTF-8");
		
		// js 파일에서 해당 값 계속 변경해줌 (Y/N)
		char photoCheck = request.getParameter("photoCheck").charAt(0);
		int communityNo = Integer.parseInt(request.getParameter("communityNo"));
		// 기존 정보 가져오기
		Community communityBefore = new CommunityService().selectOneCommunity(communityNo);
		
		// 기존에 파일이 있었는데 수정한 경우, 기본 파일 삭제
		if(photoCheck == 'Y') {
			// 기존 파일, 기존 닉네임
			String photoPathBefore = new PhotoService().selectPhoto(communityBefore.getComPhoto(), communityBefore.getComId());
			// 기존 파일을 upload 폴더에서 삭제
			new File(photoPathBefore).delete();
		}
		
		// 새 파일 저장 (실제 upload 폴더 경로에 저장)
		String uploadFilePath = request.getServletContext().getRealPath("upload");
		int uploadFileSizeLimit = 5*1024*1024; // 5mb
	    String encType = "UTF-8";
	    MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
	    
	    int photoResult = 0;
	    String communityPhoto = multi.getFilesystemName("upFile");
	    if(communityPhoto == null && photoCheck == 'Y') {
	    	// 위에서 실제로 삭제한 파일을 DB에서도 삭제
	    	photoResult = new PhotoService().removePhoto(communityBefore.getComPhoto(), communityBefore.getComId());
	    }else if(communityPhoto != null) {
	    	// 파일 추가 또는 수정
	    	File uploadFile = multi.getFile("upFile");
	    	
	    	String photoName = multi.getFilesystemName("upFile");
	    	String photoPath = uploadFile.getPath();
	    	long photoSize = uploadFile.length();
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS"); 
	    	Timestamp uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
	    	
	    	Photo photo = new Photo();
	    	photo.setPhotoName(photoName);
	    	photo.setPhotoPath(photoPath);
	    	photo.setPhotoSize(photoSize);
	    	photo.setPhotoId(communityBefore.getComId());
	    	photo.setUploadTime(uploadTime);
	    	photo.setBoardType('C');
	    	
	    	if(photoCheck != 'Y') {
	    		photoResult = new PhotoService().registerPhotoInfo(photo);
	    	}else {
	    		photoResult = new PhotoService().updatePhoto(photo, communityBefore.getComPhoto());
	    	}
	    }else {
	    	photoResult = 1;
	    }
	    
	    // Community DB 업데이트
	    String communityTitle = multi.getParameter("title");
	    String communityContent = multi.getParameter("content");
	    int communityTagNo = Integer.parseInt(multi.getParameter("tags"));
	    
	    Community community = new Community();
	    community.setComNo(communityNo);
	    community.setTagNo(communityTagNo);
	    community.setComTitle(communityTitle);
	    community.setComContent(communityContent);
	    community.setComPhoto(communityPhoto);
	    // 조회수랑 업로드 날짜는 기존꺼 그대로 유지
	    
	    int communityResult = new CommunityService().updateCommunity(community);
	    
	    if(communityResult > 0 && photoResult > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시글이 수정되었습니다.'); location.href='/community/detail?comNo=" + communityNo +"';</script>");
			out.flush();
		} else {
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html").forward(request, response);
		}
	}
}
