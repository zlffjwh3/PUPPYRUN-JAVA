package petdiary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/goal/stamp")
public class GoalStampServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GoalStampServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 목표달성 몇개 했는지 / 스탬프 보여주기
		
		request.getRequestDispatcher("/WEB-INF/views/pet-diary/walking-log.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
