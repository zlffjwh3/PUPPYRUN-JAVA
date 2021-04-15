package community.model.controller;

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
import community.model.service.LikeService;
import community.model.vo.Comment;
import community.model.vo.Community;
import user.model.vo.User;

@WebServlet("/community/detail")
public class CommunityDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommunityDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int comNo = Integer.parseInt(request.getParameter("comNo"));
		
		int result = new CommunityService().addReadCount(comNo);
		System.out.println("result : " + result);
		if(result > 0) {
			Community community = new CommunityService().selectOneCommunity(comNo);
		
			System.out.println("community : " + community);
			if(community != null) {
				request.setAttribute("community", community);
				request.getRequestDispatcher("/WEB-INF/views/community/communityDetail.jsp").forward(request, response);
			}else {
				System.out.println("게시물 오류다!");
			}
		} else {
			System.out.println("조회수 오류다!");
		}
		// comment
		ArrayList<Comment> cCommentList = new CommentService().selectAllComment(comNo);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
