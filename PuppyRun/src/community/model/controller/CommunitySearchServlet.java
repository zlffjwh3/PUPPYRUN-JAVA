package community.model.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.PageData;

import community.model.service.CommentService;
import community.model.service.CommunityService;
import community.model.service.LikeService;
import community.model.vo.Community;
import community.model.vo.CommunityPage;

@WebServlet("/community/search")
public class CommunitySearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommunitySearchServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int currentPage = 0;
		
		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		String search = request.getParameter("searchKeyword");
		CommunityPage communityPage = new CommunityService().printSearchList(search, currentPage);
		ArrayList<Community> cList = communityPage.getcList();
		String pageNavi = communityPage.getPageNavi();
		
		// 댓글 수 구하는 객체
		ArrayList<int[]> cnt = new CommentService().cnt();
					
		// 좋아요 수 구하는 객체
		ArrayList<int[]> cntLike = new LikeService().cnt();
		
		
		if(!cList.isEmpty()) {
			request.setAttribute("cList", cList);
			request.setAttribute("pageNavi", pageNavi);
			request.setAttribute("cnt", cnt);
			request.setAttribute("cntLike", cntLike);
			request.getRequestDispatcher("/WEB-INF/views/community/communitySearch.jsp").forward(request, response);
		}else {
			System.out.println("검색 서블릿/cList 객체 없음");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
