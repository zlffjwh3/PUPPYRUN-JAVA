package community.model.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.model.service.CommunityService;
import community.model.vo.Community;
import community.model.vo.CommunityPage;

@WebServlet("/community/list")
public class CommunityListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommunityListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = null; // 세션에서 받아오기
		int tag = 0; // getparameter로 받아오기
		int currentPage = 0; // getparameter로 받아오기
		
		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		CommunityPage communityPage = new CommunityService().selectAllCommunity(currentPage);
		ArrayList<Community> cList = communityPage.getcList();
		String pageNavi = communityPage.getPageNavi();
		
		if(!cList.isEmpty()) {
			request.setAttribute("cList", cList);
			request.setAttribute("pageNavi", pageNavi);
			request.getRequestDispatcher("/WEB-INF/views/community/community.jsp").forward(request, response);
			
		} else {
			request.getRequestDispatcher("/WEB-INF/views/community/communutyError.html").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
