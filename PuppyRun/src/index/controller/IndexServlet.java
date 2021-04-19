package index.controller;


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

@WebServlet("/PuppyRun")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(user != null) {
			String userId = user.getUserId();
			
			// 공지사항 가져오기
			
			// 산책기록 가져오기
			Date today = new Date();
			SimpleDateFormat sdformat = new SimpleDateFormat("yyMMdd");
		    String todayString = sdformat.format(today);
		    Goal weekGoal = new GoalService().weekGoal(userId, todayString);

		    // 멍멍이야기 게시글 가져오기
		    
		    // 산책짝꿍 게시글 가져오기
		    
		    
		    // 데이터 전부 보내주기 -> 밑에 else에도 적어줘야댕!!!!! 게시물은 다 보여야 하니까
		    request.setAttribute("weekGoal", weekGoal); // 산책기록
			request.getRequestDispatcher("/puppyrun.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/puppyrun.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
