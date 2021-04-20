package matching.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import matching.model.service.MatchingService;
import matching.model.vo.MatchingChat;
import user.model.vo.User;

@WebServlet("/mychatting/list")
public class MyChattingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyChattingListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();
		
		ArrayList<MatchingChat> matChat = new MatchingService().viewList(userId);
		
		request.setAttribute("matChat", matChat);
		request.getRequestDispatcher("/WEB-INF/views/matching/myChatting.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
