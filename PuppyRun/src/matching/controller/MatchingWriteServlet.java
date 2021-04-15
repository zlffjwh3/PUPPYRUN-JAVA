package matching.controller;

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

import matching.model.service.MatchingService;
import matching.model.vo.Matching;
import photo.model.service.PhotoService;
import photo.model.vo.Photo;
import user.model.vo.User;

@WebServlet("/matching/write")
public class MatchingWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MatchingWriteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/matching/petMateWrite.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 사진을 저장할 실제 폴더 경로
		String uploadFilePath = request.getServletContext().getRealPath("upload");
		int uploadFileSizeLimit = 5*1024*1024;
		String encType = "UTF-8";
		MultipartRequest multi  = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
		
		// 로그인 했을 때의 세션 정보 가져오기 (User 객체)
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		// 작성 완료한 게시물 정보 가져오기
		String matchingAddr = multi.getParameter("addr1") + multi.getParameter("addr2");
		String matchingId = user.getUserId();
		String matchingTitle = multi.getParameter("title");
		String matchingContent = multi.getParameter("content");
		String matchingNickName = user.getUserNick();
		
		// 위에 가져온 값들을 Matching 객체에 저장하기
		Matching matching = new Matching();
		matching.setMatId(matchingId);
		matching.setMatAddr(matchingAddr);
		matching.setMatTitle(matchingTitle);
		matching.setMatContent(matchingContent);
		matching.setUserNick(matchingNickName);
		
		// 파일 업로드
		int photoResult = 0;
		// 작성한 게시물에 File이 존재하면
		if(multi.getFilesystemName("upFile") != null) {
			// File의 이름 저장
			String matchingPhoto = multi.getFilesystemName("upFile");
			// File의 이름을 matching 객체에 저장
			matching.setMatPhoto(matchingPhoto);
			
			// 이제 photo DB에 저장
			File uploadFile = multi.getFile("upFile");
			// File의 이름 가져오기
			String photoName = multi.getFilesystemName("upFile");
			// File의 경로 가져오기
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
			photo.setBoardType('M');
			
			photoResult = new PhotoService().registerPhotoInfo(photo);
		}
		
		// File이 없다면
		int matchingResult = new MatchingService().registerMatching(matching);
		System.out.println(photoResult);
		System.out.println(matchingResult);
		// 결과 확인 (File 업로드 안하면 무조건 오류 뜸 // 나중에 수정할거야~~!)
		if(matchingResult > 0 && photoResult > 0) {
			response.sendRedirect("/matching/list");
		} else {
			request.getRequestDispatcher("/WEB-INF/views/matching/matchingError.html").forward(request, response);
		}
	}

}