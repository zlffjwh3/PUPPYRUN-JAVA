package petdiary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/goal/write")
public class GoalWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GoalWriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// saveUserGoal 부르는 서블릿 - 결과는 int result 
		int result = 0;
		
		if(result > 0) {
			// 목표 설정 했는지 여부 보내줘야함
		} else {
			System.out.println("골 넣는거 실패해따!!!!!!!!!!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
