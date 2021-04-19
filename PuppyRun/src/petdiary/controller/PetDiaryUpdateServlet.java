package petdiary.controller;

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

import petdiary.model.service.GoalService;
import petdiary.model.service.PetDiaryService;
import petdiary.model.vo.Goal;
import petdiary.model.vo.PetDiary;
import photo.model.service.PhotoService;
import photo.model.vo.Photo;
import user.model.vo.User;

@WebServlet("/petdiary/update")
public class PetDiaryUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PetDiaryUpdateServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String diaryId = user.getUserId();
		
		String date = request.getParameter("diaryDate");
		String year = date.substring(2, 4);
		String month = date.substring(5, 7);
		String day = date.substring(8, 10);
		String diaryDate = year + "/" + month + "/" + day;
		
		PetDiary petDiary = new PetDiaryService().selectOneDiary(diaryDate, diaryId);
		
		if(petDiary != null) {
			request.setAttribute("petDiary", petDiary);
			request.getRequestDispatcher("/WEB-INF/views/pet-diary/diaryUpdate.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/pet-diary/diaryError.html").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정사항 수정해서 보내기
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String diaryId = user.getUserId();
		
		char photoCheck = request.getParameter("photoCheck").charAt(0);
		String diaryDate = request.getParameter("diaryDate");
		PetDiary diaryBefore = new PetDiaryService().selectOneDiary(diaryDate, diaryId); // 기존정보 가져오기
		
		// 기존에 파일 있었는데 수정한 경우 기존 파일 삭제
		if(photoCheck == 'Y') {
			String photoPathBefore = new PhotoService().selectPhoto(diaryBefore.getDiaryPhoto(), diaryId);
			new File(photoPathBefore).delete(); // 기존 파일 upload 폴더에서 삭제
		}
		
		// 새 사진파일 저장 (실제 upload 폴더 경로에 저장)
		String uploadFilePath = request.getServletContext().getRealPath("upload");
		int uploadFileSizeLimit = 5*1024*1024; // 5MB
		String encType = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());

		int photoResult = 0;
		String diaryPhoto = multi.getFilesystemName("upFile");
		if(diaryPhoto == null && photoCheck == 'Y') {
			// 파일 삭제
			photoResult = new PhotoService().removePhoto(diaryBefore.getDiaryPhoto(), diaryId);
		} else if(diaryPhoto != null) {
			// 파일 추가 또는 수정
			File uploadFile = multi.getFile("upFile");
			
			String photoName = multi.getFilesystemName("upFile");
			String photoPath = uploadFile.getPath();
			long photoSize = uploadFile.length();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS"); // 날짜데이터를 내가 원하는 형태로 바꿔줌
			Timestamp uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
			
			Photo photo = new Photo();
			photo.setPhotoName(photoName);
			photo.setPhotoPath(photoPath);
			photo.setPhotoSize(photoSize);
			photo.setPhotoId(diaryId);
			photo.setUploadTime(uploadTime);
			photo.setBoardType('D');
			
			if(photoCheck != 'Y') {
				photoResult = new PhotoService().registerPhotoInfo(photo); // 파일 추가
			} else {
				photoResult = new PhotoService().updatePhoto(photo, diaryBefore.getDiaryPhoto()); // 파일 수정
			}
		} else {
			photoResult = 1;
			diaryPhoto = diaryBefore.getDiaryPhoto();
		}
		
		// DIARY DB 업데이트
		String diaryTitle = multi.getParameter("title");
		String diaryContent = multi.getParameter("content");
		String tempDis = multi.getParameter("distance");
		int diaryDis = 0;
		if(!tempDis.equals("")) {
			diaryDis = Integer.parseInt(tempDis);
		}
		String tempTime = multi.getParameter("walkkTime");
		int diaryTime = 0;
		if(!tempTime.equals("")) {
			diaryTime = Integer.parseInt(tempTime);
		}
		
		PetDiary petDiary = new PetDiary();
		petDiary.setDiaryNo(diaryBefore.getDiaryNo());
		petDiary.setDiaryTitle(diaryTitle);
		petDiary.setDiaryContent(diaryContent);
		petDiary.setDiaryPhoto(diaryPhoto);
		petDiary.setDiaryDis(diaryDis);
		petDiary.setDiaryTime(diaryTime);
		
		int diaryResult = new PetDiaryService().updateDiary(petDiary);
		
		String goalDate = diaryDate.substring(0, 2) + diaryDate.substring(3, 5) + diaryDate.substring(6, 8);
		Goal goal = new GoalService().weekGoal(diaryId, goalDate);
		int goalResult = 0;
		if(goal != null) {
			int newDis = diaryDis - diaryBefore.getDiaryDis();
			int newTime = diaryTime - diaryBefore.getDiaryTime();
			
			PetDiary diaryGoal = new PetDiary();
			diaryGoal.setDiaryDis(newDis);
			diaryGoal.setDiaryTime(newTime);
			diaryGoal.setDiaryId(diaryId);
			
			goalResult = new GoalService().addGoalData(diaryGoal, goalDate);
		} else {
			goalResult = 1;
		}
		
		// 결과 확인 ---------------------------------------------------------------------------------------------------
		if(diaryResult > 0 && photoResult > 0 && goalResult > 0) {
			String date = diaryBefore.getDiaryDate();
			String year = date.substring(0, 4);
			String month = date.substring(5, 7);
			String day = date.substring(8, 10);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시글이 수정되었습니다.'); location.href='/petdiary/detail?year=" + year +"&month=" + month + "&date=" + day + "';</script>");
			out.flush();
		} else {
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html").forward(request, response);
		}
	}

}
