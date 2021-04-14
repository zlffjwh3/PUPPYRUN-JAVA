package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.User;

@WebServlet("/user/findPwd")
public class FindPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FindPwdServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("p-user-name");
		String userId = request.getParameter("p-user-id");
		String userEmail = request.getParameter("p-user-email");
		
		User user = new UserService().finduserPwd(userName, userId, userEmail);
		if(user != null) {
			request.setAttribute("userPwd", user.getUserPw());
			request.setAttribute("PuserId", userId);
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
