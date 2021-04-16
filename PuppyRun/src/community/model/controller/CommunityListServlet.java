package community.model.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.CommentService;
import community.model.service.CommunityService;
import community.model.vo.Comment;
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
		int tag = 5;
		int currentPage = 0; // getparameter로 받아오기
		
		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		if(request.getParameter("tagNo") == null) {
			tag = 5;
		}else {
			tag = Integer.parseInt(request.getParameter("tagNo"));
		}
		
		if(tag == 0 || tag == 1 || tag == 2 || tag == 3) {
			// 특정 태그
			CommunityPage communityPage = new CommunityService().selectTagList(currentPage, tag);
			ArrayList<Community> cList = communityPage.getcList();
			String pageNavi = communityPage.getPageNavi();
			
			if(!cList.isEmpty()) {
				request.setAttribute("cList", cList);
				request.setAttribute("pageNavi", pageNavi);
					
				request.getRequestDispatcher("/WEB-INF/views/community/community.jsp").forward(request, response);
			} else {
				System.out.println("list 태그 오류다");
			}
		}else {
			//전체
			CommunityPage communityPage = new CommunityService().selectAllCommunity(currentPage);
			ArrayList<Community> cList = communityPage.getcList();
			String pageNavi = communityPage.getPageNavi();
			
			if(!cList.isEmpty()) {
				request.setAttribute("cList", cList);
				request.setAttribute("pageNavi", pageNavi);
				request.getRequestDispatcher("/WEB-INF/views/community/community.jsp").forward(request, response);
				
			} else {
				System.out.println("List 오류다");
			}
		}
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
