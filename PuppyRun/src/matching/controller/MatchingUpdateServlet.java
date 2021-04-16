package matching.controller;

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

import matching.model.service.MatchingService;
import matching.model.vo.Matching;
import photo.model.service.PhotoService;
import photo.model.vo.Photo;

@WebServlet("/matching/modify")
public class MatchingUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MatchingUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정할 정보 불러오기
		int matchingNo = Integer.parseInt(request.getParameter("matNo"));
		Matching matching = new MatchingService().printOneMatching(matchingNo);
		
		if(matching != null) {
			request.setAttribute("matching", matching);
			// 수정 페이지로 가기
			request.getRequestDispatcher("/WEB-INF/views/notice/matchingUpdate.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/notice/matchingError.html").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정한거 보내기
		request.setCharacterEncoding("UTF-8");
		
		char photoCheck = request.getParameter("photoCheck").charAt(0);
		int matchingNo = Integer.parseInt(request.getParameter("matNo"));
		// 기존 정보 가져오기
		Matching matchingBefore = new MatchingService().printOneMatching(matchingNo);
		
		// 기존에 있던 파일 수정한 경우 기존 파일 삭제
		if(photoCheck == 'Y') {
			String photoPathBefore = new PhotoService().selectPhoto(matchingBefore.getMatPhoto(), matchingBefore.getMatId());
			new File(photoPathBefore).delete();
		}
		
		// 새 파일 저장(실제 upload 폴더 경로에 저장)
		String uploadFilePath = request.getServletContext().getRealPath("upload");
		int uploadFileSizeLimit = 5*1024*1024;
		String encType = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
		
		int photoResult = 0;
		String matchingPhoto = multi.getFilesystemName("upFile");
		if(matchingPhoto == null && photoCheck == 'Y' ) {
			// 위에서 실제로 삭제한 파일을 DB에서도 삭제
			photoResult = new PhotoService().removePhoto(matchingBefore.getMatPhoto(), matchingBefore.getMatId());
		} else if(matchingPhoto != null) {
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
	    	photo.setPhotoId(matchingBefore.getMatId());
	    	photo.setUploadTime(uploadTime);
	    	photo.setBoardType('M');
	    	
	    	if(photoCheck != 'Y' ) {
	    		photoResult = new PhotoService().registerPhotoInfo(photo);
	    	}else {
	    		photoResult = new PhotoService().updatePhoto(photo, matchingBefore.getMatPhoto());
	    	}
		} else {
			photoResult = 1;
		}
		
		// Matching DB 업데이트
		String matchingTitle = multi.getParameter("title");
		String matchingContent = multi.getParameter("content");
		
		Matching matching = new Matching();
		matching.setMatNo(matchingNo);
		matching.setMatTitle(matchingTitle);
		matching.setMatContent(matchingContent);
		matching.setMatPhoto(matchingPhoto);
		// 조회수랑 업로드 날짜는 기존꺼 그대로 유지
		
		int matchingResult = new MatchingService().modifyMatching(matching);
		
		if(matchingResult > 0 && photoResult > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시글이 수정되었습니다.'); location.href='/matching/detail?matNo=" + matchingNo + "'; </script>");
		} else {
			request.getRequestDispatcher("/WEB-INF/views/matching/matchingError.html").forward(request, response);
		}
	}
}