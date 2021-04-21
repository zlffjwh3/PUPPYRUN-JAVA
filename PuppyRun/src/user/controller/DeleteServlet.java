package user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.model.service.UserService;
import user.model.vo.User;

@WebServlet("/user/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		char dogCheck = request.getParameter("dogCheck").charAt(0);
		System.out.println(dogCheck);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();
		int userResult = 0;
		int dogResult = 0;
		
		System.out.println("유저아이디 : " + userId);
		
		if(user.getDogCheck() == 'N') { // 나는 반려견이 없습니다
			userResult = new UserService().deleteUser(userId);
			if(userResult > 0) {
				session.invalidate(); // session 파괴
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('퍼피런 회원탈퇴가 완료되었습니다.'); location.href='/index.jsp';</script>");
				out.flush();
			} else {
				request.getRequestDispatcher("/WEB-INF/views/user/error.html").forward(request, response);
			}
			
		} else if(user.getDogCheck() == 'Y') { // 나는 반려견이 있습니다
			dogResult = new UserService().deleteDog(userId);
			userResult = new UserService().deleteUser(userId);
			
			if(userResult > 0 && dogResult > 0) {
				session.invalidate(); // session 파괴
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('퍼피런 회원탈퇴가 완료되었습니다.'); location.href='/index.jsp';</script>");
				out.flush();
			} else {
				request.getRequestDispatcher("/WEB-INF/views/user/error.html").forward(request, response);
			}
			
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
