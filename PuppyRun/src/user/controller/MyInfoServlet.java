package user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.model.service.CommentService;
import community.model.service.CommunityService;
import community.model.vo.Comment;
import community.model.vo.Community;
import user.model.service.UserService;
import user.model.vo.Dog;
import user.model.vo.User;

@WebServlet("/user/myInfo")
public class MyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String userId = user.getUserId();
		
		// 강아지정보 가져오기
		Dog dog = null;
		if(user.getDogCheck() == 'Y') {
			dog = new UserService().selectOneDog(userId);
		}
		
		// 커뮤니티 게시판 글 가져오기
		/*
		 * ArrayList<Community> cList = new CommunityService(); ArrayList<Comment>
		 * comList = new CommentService()
		 */
		
		// 산책일기 글 가져오기
		
		
		request.setAttribute("dog", dog);
		request.getRequestDispatcher("/WEB-INF/views/user/myInfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
