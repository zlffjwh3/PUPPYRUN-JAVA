package petdiary.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import petdiary.model.service.PetDiaryService;
import petdiary.model.vo.PetDiary;
import user.model.vo.User;

@WebServlet("/petdiary/detail")
public class PetDiaryDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PetDiaryDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String diaryId = user.getUserId();
		String year = request.getParameter("year");
	 	String month = request.getParameter("month");
	 	if(month.length() == 1) {
	 		month = "0" + month;
	 	}
	 	String date = request.getParameter("date");
	 	if(date.length() == 1) {
	 		date = "0" + date;
	 	}
	 	String tempDate = year+"-"+month+"-"+date;
	 	Date diaryDate = Date.valueOf(tempDate);
	 	
	 	PetDiary petDiary = new  PetDiaryService().selectOneDiary(diaryDate, diaryId);
	 	ArrayList<PetDiary> pList = new PetDiaryService().selectAllDiary(diaryId);
	 	
	 	
	 	if(petDiary != null && !pList.isEmpty() ) {
	 		request.setAttribute("petDiary", petDiary);
	 		request.setAttribute("pList", pList);
	 		request.getRequestDispatcher("/WEB-INF/views/pet-diary/pet-diary.jsp").forward(request, response);
	 	}else {
	 		request.getRequestDispatcher("/WEB-INF/views/pet-diary/diaryError.html").forward(request, response);
	 	}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
