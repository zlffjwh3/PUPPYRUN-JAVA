package user.controller;

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
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import photo.model.service.PhotoService;
import photo.model.vo.Photo;
import user.model.service.UserService;
import user.model.vo.User;

@WebServlet("/user/photo")
public class UserPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserPhotoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user"); // 유저를 받아옴
		User beforeUser = new UserService().selectOneUserIdOnly(user.getUserId()); // 기존정보
		 
		
		
		if(beforeUser.getUserPhoto() != null) { // 기존 파일이 있는 경우 ... 기존 파일은 삭제한다
			System.out.println("널널...");
			String photoPathBefore = new PhotoService().selectPhoto(beforeUser.getUserPhoto(), beforeUser.getUserId());
			new File(photoPathBefore).delete(); // 기존 파일 폴더에서 삭제
		}
		
		
		String uploadFilePath = request.getServletContext().getRealPath("upload");
		int uploadFileSizeLimit = 5*1024*1024; // 5MB
		String encType = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
		System.out.println("멀티에 문제가 있나 : " + multi);
		int photoResult = 0;
		
		
		
		// Photo DB에 저장
		File uploadFile = multi.getFile("upFile");
		System.out.println("파일을 받아왔나 : " + uploadFile); // 안받아짐******
		
		String photoName = multi.getFilesystemName("upFile");
		String photoPath = uploadFile.getPath(); 
		
		
		System.out.println("주소 받아와지나 " + photoPath);
		
		long photoSize = uploadFile.length();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS"); // 날짜데이터를 내가 원하는 형태로 바꿈
		Timestamp uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
		
		Photo photo = new Photo();
		photo.setPhotoName(photoName);
		photo.setPhotoPath(photoPath);
		photo.setPhotoSize(photoSize);
		photo.setPhotoId(beforeUser.getUserId());
		photo.setUploadTime(uploadTime);
		photo.setBoardType('P');
		
		if(user.getUserPhoto() == null) { // 유저포토가 원래 없었음
			photoResult = new PhotoService().registerPhotoInfo(photo);
		} else {
			photoResult = new PhotoService().updatePhoto(photo, beforeUser.getUserPhoto());
		}
		
		// 유저에서 USER_PHOTO 업데이트
		String userPhoto = multi.getFilesystemName("upFile");
		beforeUser.setUserPhoto(userPhoto);
		
		int userResult = new UserService().updatePhoto(beforeUser);
		
		System.out.println("유저 : " + userResult);
		System.out.println("사진 : " + photoResult);
		
		if(userResult > 0 && photoResult > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('프로필 사진이 설정되었습니다'); location.href='/user/myInfo';</script>");
			out.flush();
		} else {
			request.getRequestDispatcher("/WEB-INF/views/user/error.html").forward(request, response);
		}
	}

}
