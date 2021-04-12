package community.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.CommunityService;
import community.model.service.LikeService;
import community.model.vo.CommunityPage;

@WebServlet("/community/list")
public class CommunityListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommunityListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = null; // 세션에서 받아오기
		int currentPage = 0; // getparameter로 받아오기
		String tag = null; // getparameter로 받아오기
		
		// if문을 돌려서 tag별로 다르게 보이게 하기
		CommunityPage community = new CommunityService().selectAllCommunity(currentPage);
		
		request.getRequestDispatcher("/WEB-INF/views/community/community.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
