package petdiary.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import petdiary.model.service.PetDiaryService;
import petdiary.model.vo.PetDiary;
import photo.model.service.PhotoService;
import photo.model.vo.Photo;
import user.model.vo.User;

@WebServlet("/petdiary/write")
public class PetDiaryWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PetDiaryWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/pet-diary/diaryWrite.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		request.setCharacterEncoding("UTF-8");
		
		
		String uploadFilePath = request.getServletContext().getRealPath("upload");
		int uploadFileSizeLimit = 5*1024*1024; //파일 사이즈
		String encType = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
		
		String petdiaryId = user.getUserId();
		String petdiaryTitle = multi.getParameter("title");
		String petdiaryContent = multi.getParameter("content");
		String petdiaryMap = multi.getParameter("map");
		//지도 테스트
		int goaldis = Integer.parseInt(multi.getParameter("distance"));
		int goalwak = Integer.parseInt(multi.getParameter("walkkTime"));
		
		PetDiary petdiary = new PetDiary();
		petdiary.setDiaryId(petdiaryId);
		petdiary.setDiaryTitle(petdiaryTitle);
		petdiary.setDiaryContent(petdiaryContent);
		petdiary.setDiaryMap(petdiaryMap);
		
		//지도 테스트
		System.out.println(goaldis);
		System.out.println(goalwak);
		
		int photoResult = 0;
		if(multi.getFilesystemName("upFile") != null) {
			String petdiaryPhoto = multi.getFilesystemName("upFile");
			petdiary.setDiaryPhoto(petdiaryPhoto);
		
			File uploadFile = multi.getFile("upFile");
			String photoName = multi.getFilesystemName("upFile");
			String photoPath = uploadFile.getPath();
			long photoSize = uploadFile.length();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Timestamp uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
			
			// 위에 가져온 값들을 Photo 객체에 저장
			Photo photo = new Photo();
			photo.setPhotoName(photoName);
			photo.setPhotoPath(photoPath);
			photo.setPhotoSize(photoSize);
			photo.setPhotoId(user.getUserId());
			photo.setUploadTime(uploadTime);
			photo.setBoardType('D');
			
			photoResult = new PhotoService().registerPhotoInfo(photo);
		
		}
		// File이 없다면   
		int diaryResult = new PetDiaryService().insertDiary(petdiary);
		
		// 결과 확인 (File 업로드 안하면 무조건 오류뜨게 해놨음.. 나중에 수정할 부분)
		if(diaryResult > 0 && multi.getFilesystemName("upFile") != null && photoResult > 0) {
			response.sendRedirect("/petdiary/list");
		}else {
			request.getRequestDispatcher("/WEB-INF/views/pet-diary/diaryError.html").forward(request, response);
		}

	}

}