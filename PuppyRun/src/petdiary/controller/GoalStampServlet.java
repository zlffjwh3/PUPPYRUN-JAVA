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

@WebServlet("/goal/stamp")
public class GoalStampServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GoalStampServlet() {
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
	    
	    // 지난 목표 전부 가져오기
	    
	    
	    // 스탬프 개수 가져오기
		
		request.getRequestDispatcher("/WEB-INF/views/pet-diary/walking-log.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
