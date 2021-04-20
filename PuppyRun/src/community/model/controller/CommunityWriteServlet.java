package community.model.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import community.model.service.CommunityService;
import community.model.vo.Community;
import photo.model.service.PhotoService;
import photo.model.vo.Photo;
import user.model.vo.User;

@WebServlet("/community/write")
public class CommunityWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommunityWriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/community/communityWrite.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 사진을 저장할 실제 폴더 경로
		String uploadFilePath = request.getServletContext().getRealPath("upload");
		int uploadFileSizeLimit = 5*1024*1024; //최대 파일 크기 (5MB)
		String encType = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
		
		// 로그인 했을 때의 세션 정보 가져오기 (User객체)
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		// 작성 완료한 게시물 정보 가져오기
		String communityId = user.getUserId();
		int communityTagNo = Integer.parseInt(multi.getParameter("tags"));
		String communityTitle = multi.getParameter("title");
		String communityContent = multi.getParameter("content");
		String communityUserName = user.getUserNick();
		// 위에 가져온 값들을 Community 객체에 저장하기
		Community community = new Community();
		community.setComId(communityId);
		community.setTagNo(communityTagNo);
		community.setComTitle(communityTitle);
		community.setComContent(communityContent);
		community.setUserNick(communityUserName);
		
		// 파일 업로드
		int photoResult = 0;
		// 작성한 게시물에 File이 존재하면
		if(multi.getFilesystemName("upFile") != null) {
			// File의 이름 저장
			String communityPhoto = multi.getFilesystemName("upFile");
			// File의 이름을 community 객체에 저장
			community.setComPhoto(communityPhoto);
		
			// 이제 Photo DB에 저장
			File uploadFile = multi.getFile("upFile");
			// File의 이름 가져오기
			String photoName = multi.getFilesystemName("upFile");
			// File의 파일 경로 가져오기
			String photoPath = uploadFile.getPath();
			// File의 크기 가져오기
			long photoSize = uploadFile.length();
			// 올린 날짜 설정 및 포맷
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Timestamp uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
			
			// 위에 가져온 값들을 Photo 객체에 저장
			Photo photo = new Photo();
			photo.setPhotoName(photoName);
			photo.setPhotoPath(photoPath);
			photo.setPhotoSize(photoSize);
			photo.setPhotoId(user.getUserId());
			photo.setUploadTime(uploadTime);
			photo.setBoardType('C');
			
			photoResult = new PhotoService().registerPhotoInfo(photo);
		
		}
		// File이 없다면   
		int communityResult = new CommunityService().insertCommunity(community);
		
		// 결과 확인 (File 업로드 안하면 무조건 오류뜨게 해놨음.. 나중에 수정할 부분)
		if(communityResult > 0) {
			response.sendRedirect("/community/list");
		}else {
			request.getRequestDispatcher("/WEB-INF/views/commuity/commuityError.html").forward(request, response);
		}

	}

}
