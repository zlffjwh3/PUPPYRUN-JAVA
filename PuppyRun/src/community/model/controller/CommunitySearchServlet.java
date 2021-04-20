package community.model.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import user.model.service.UserService;
import user.model.vo.User;

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
		
		// 회원정보 모두 가져오기 (닉네임 옆에 사진때매)
		ArrayList<User> uList = new UserService().selectAllUserList2();
		request.setAttribute("uList", uList);
		
		if(!cList.isEmpty()) {
			request.setAttribute("cList", cList);
			request.setAttribute("pageNavi", pageNavi);
			request.setAttribute("cnt", cnt);
			request.setAttribute("cntLike", cntLike);
			request.getRequestDispatcher("/WEB-INF/views/community/communitySearch.jsp").forward(request, response);
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('검색어가 존재하지 않습니다!'); location.href='/community/list';</script>");
			out.flush();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
