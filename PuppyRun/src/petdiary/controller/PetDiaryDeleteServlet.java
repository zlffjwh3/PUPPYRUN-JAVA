package petdiary.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import petdiary.model.service.PetDiaryService;
import photo.model.service.PhotoService;
import user.model.vo.User;

@WebServlet("/petdiary/delete")
public class PetDiaryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PetDiaryDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date diaryDate = Date.valueOf(request.getParameter("diaryDate"));
		System.out.println(diaryDate);////////////////////////힝 ㅜㅠ
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String diaryId = user.getUserId();
		int diaryNo = Integer.parseInt(request.getParameter("diaryNo"));
		
		String diaryPhoto = new PetDiaryService().selectOneDiary(diaryDate, diaryId).getDiaryPhoto();
		
		int diaryResult = new PetDiaryService().deleteDiary(diaryNo);
		System.out.println(diaryResult);
		
		int photoResult = 1;
		if(diaryPhoto != null) {
			String photoPath = new PhotoService().selectPhoto(diaryPhoto, diaryId);
			photoResult = new PhotoService().removePhoto(diaryPhoto, diaryId);
			File file = new File(photoPath);
			file.delete(); //upload 폴더 안 파일 삭제
		}
		
		if(diaryResult > 0 && photoResult > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시글이 삭제되었습니다.'); location.href='/petdiary/list';</script>");
			out.flush();
		} else {
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
