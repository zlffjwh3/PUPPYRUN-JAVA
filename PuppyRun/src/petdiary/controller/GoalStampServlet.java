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
import petdiary.model.vo.Goal;
import petdiary.model.vo.GoalPage;
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
		
		int currentPage = 0;
		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 현재 목표 가져오기
		Date today = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyMMdd");
	    String todayString = sdformat.format(today);
	    Goal weekGoal = new GoalService().weekGoal(goalId, todayString);
	    
	    // 지난 목표 전부 가져오기
	    GoalPage goalPage = new GoalService().goalList(goalId, currentPage);
	    ArrayList<Goal> gList = goalPage.getgList();
	    String pageNavi = goalPage.getPageNavi();
	    
	    // 데이터 보내기 ------------------------------------------------------------------
		request.setAttribute("weekGoal", weekGoal);
	    request.setAttribute("gList", gList);
	    request.setAttribute("pageNavi", pageNavi);
	    request.getRequestDispatcher("/WEB-INF/views/pet-diary/walking-log.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
