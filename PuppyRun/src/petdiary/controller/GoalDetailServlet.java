package petdiary.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import petdiary.model.service.GoalService;
import petdiary.model.vo.Goal;
import user.model.vo.User;

@WebServlet("/goal/detail")
public class GoalDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GoalDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String goalId = user.getUserId();
		
		// 현재 목표 가져오기
		Date today = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyMMdd");
	    String todayString = sdformat.format(today);
	    Goal weekGoal = new GoalService().weekGoal(goalId, todayString);
		
		// 하루 데이터 가져오기
		Goal goal = null;
		String date = request.getParameter("goalDate");
		String year = date.substring(2, 4);
		String month = date.substring(5, 7);
		String day = date.substring(8, 10);
		String goalDate = year + month + day;
		goal = new GoalService().weekGoal(goalId, goalDate);
		
		if(goal != null) {
			request.setAttribute("weekGoal", weekGoal);
			request.setAttribute("goal", goal);
			request.getRequestDispatcher("/WEB-INF/views/pet-diary/walking-log-detail.jsp").forward(request, response);
		} else {
			System.out.println("왜 널값이 나오깡");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
