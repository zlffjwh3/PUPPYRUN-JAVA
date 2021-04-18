package petdiary.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import petdiary.model.service.GoalService;
import petdiary.model.vo.Goal;
import user.model.vo.User;

@WebServlet("/goal/write")
public class GoalWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GoalWriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD
		// saveUserGoal 부르는 서블릿 - 결과는 int result 
		// SELECT * FROM GOAL WHERE '210417' >= TO_CHAR(GOAL_DATE, 'YYMMDD') AND '210417' < (TO_CHAR(GOAL_DATE + 7, 'YYMMDD'));
		int result = 0;
=======
		HttpSession session = request.getSession(); 
		
		User user = (User)session.getAttribute("user");
		Goal goal = new Goal();
		
		
		String goalId = user.getUserId();
		String goalDate = request.getParameter("goal-date");
		int goalDis = Integer.parseInt(request.getParameter("walk-dis"));
		int goalTime = Integer.parseInt(request.getParameter("walk-time"));
		
		goal.setGoalId(goalId);
		goal.setGoalDate(goalDate);
		goal.setGoalDis(goalDis);
		goal.setGoalTime(goalTime);
		
		int result = new GoalService().saveUserGoal(goal);
		
>>>>>>> branch 'main' of https://github.com/Dog-Rice-Team/PUPPYRUN-JAVA.git
		
		if(result > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('목표가 등록되었습니다.'); location.href='/petdiary/list';</script>");
			out.flush();
		} else {
			System.out.println("골 넣는거 실패해따!!!!!!!!!!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
