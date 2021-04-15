package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.model.service.UserService;
import user.model.vo.User;

@WebServlet("/user/findId")
public class FindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FindIdServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("e-user-name");
		String userEmail = request.getParameter("e-user-email");
		
		User user = new UserService().findUserId(userName, userEmail);
		System.out.println(user);
		
		if(user != null) {
			request.setAttribute("userId", user.getUserId());
			request.setAttribute("userName", userName);
			RequestDispatcher view = request.getRequestDispatcher("/id-pw-inquiry.jsp");
			view.forward(request, response);
			
		} else {
			request.getRequestDispatcher("/id-pw-inquiry.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
 
}
