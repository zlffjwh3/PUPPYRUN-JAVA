package petdiary.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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

import petdiary.model.service.GoalService;
import petdiary.model.service.PetDiaryService;
import petdiary.model.vo.Goal;
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
		String year = request.getParameter("year");
	 	String month = request.getParameter("month");
	 	if(month.length() == 1) {
	 		month = "0" + month;
	 	}
	 	String date = request.getParameter("date");
	 	if(date.length() == 1) {
	 		date = "0" + date;
	 	}
	 	String diaryDate = year + "/" + month + "/" + date;
	 	String goalDate = year.substring(2, 4) + month + date;
		
		request.setAttribute("diaryDate", diaryDate);
		request.setAttribute("goalDate", goalDate);
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
		
		String diaryId = user.getUserId();
		String diaryDate = multi.getParameter("diaryDate");
		String goalDate = multi.getParameter("goalDate");
		String petdiaryTitle = multi.getParameter("title");
		String petdiaryContent = multi.getParameter("content");
		int goaldis = Integer.parseInt(multi.getParameter("distance"));
		int goalwalk = Integer.parseInt(multi.getParameter("walkkTime"));
		
		PetDiary petdiary = new PetDiary();
		petdiary.setDiaryId(diaryId);
		petdiary.setDiaryTitle(petdiaryTitle);
		petdiary.setDiaryContent(petdiaryContent);
		petdiary.setDiaryDate(diaryDate);
		petdiary.setDiaryDis(goaldis);
		petdiary.setDiaryTime(goalwalk);
		
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
		
		} else {
			photoResult = 1;
		}

		int diaryResult = new PetDiaryService().insertDiary(petdiary);
		
		Goal goal = new GoalService().weekGoal(diaryId, goalDate);
		int goalResult = 0;
		if(goal != null) {
			goalResult = new GoalService().addGoalData(petdiary, goalDate);
		} else {
			goalResult = 1;
		}
		
		// 결과 확인
		if(diaryResult > 0 && photoResult > 0 && goalResult > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시글이 등록되었습니다.'); location.href='/petdiary/list';</script>");
			out.flush();
		}else {
			request.getRequestDispatcher("/WEB-INF/views/pet-diary/diaryError.html").forward(request, response);
		}

	}

}