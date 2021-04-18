package petdiary.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import petdiary.model.service.GoalService;
import petdiary.model.service.PetDiaryService;
import petdiary.model.vo.Goal;
import petdiary.model.vo.PetDiary;
import user.model.vo.User;

@WebServlet("/petdiary/list")
public class PetDiaryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PetDiaryListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();
		
		// 일기 전체 데이터 가져오기
		ArrayList<PetDiary> pList = new PetDiaryService().selectAllDiary(userId);
		
		// 오늘에 해당되는 목표 가져오기
		Date today = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyMMdd");
	    String todayString = sdformat.format(today);
	    Goal weekGoal = new GoalService().weekGoal(userId, todayString);
		
		request.setAttribute("pList", pList);
		request.setAttribute("weekGoal", weekGoal);
		request.getRequestDispatcher("/WEB-INF/views/pet-diary/pet-diary.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
