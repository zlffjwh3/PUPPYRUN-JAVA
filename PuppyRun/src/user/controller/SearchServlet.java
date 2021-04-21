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

@WebServlet("/admin/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		/*
		 * int currentPage = 0; if(request.getParameter("currentPage") == null) {
		 * currentPage = 1; } else { currentPage =
		 * Integer.parseInt(request.getParameter("currentPage")); }
		 */
		
		String search = request.getParameter("searchKeyword");
		String userChoice = request.getParameter("userChoice");
		System.out.println(userChoice);
		
		if(userChoice.equals("userId")) {
			userChoice = "USER_ID";
		}else {
			userChoice = "USER_NAME";
		}
		
		UserPage userPage = new UserService().selectSearchUserList(search, userChoice);
		ArrayList<User> userList = userPage.getuList();
		/* String pageNavi = userPage.getPageNavi(); */
		if(!userList.isEmpty()) {
			request.setAttribute("uList", userList);
			/* request.setAttribute("pageNavi", pageNavi); */
			request.getRequestDispatcher("/WEB-INF/views/user/myinfo-m-search.jsp").forward(request, response);
		} else {
			System.out.println("검색 에러다..쉬불..");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
