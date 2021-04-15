package user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.User;
import user.model.vo.UserPage;

@WebServlet("/user/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 전체정보 가져오기
		int currentPage = 0;
		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		UserPage up = new UserService().selectAllUserList(currentPage);
		ArrayList<User> uList = up.getuList();
		String pageNavi = up.getPageNavi();
		
		// 게시글 전체정보 가져오기 - 산책짝꿍 / 멍멍이야기
		
		request.setAttribute("uList", uList);
		request.setAttribute("pageNavi", pageNavi);
		request.getRequestDispatcher("/WEB-INF/views/user/myInfo-m.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
