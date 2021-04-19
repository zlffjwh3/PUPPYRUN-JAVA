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
import community.model.vo.Like;
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
		String userId = request.getParameter("userId");
		
		// ----------- 좋아요 기능
		
		// 좋아요 버튼을 누른 적이 있는지 확인하는 메소드
		Like beforeLike  = new LikeService().likeStatus(comNo, userId);
		int countLike = new LikeService().countLike(comNo);
		
		request.setAttribute("beforeLike", beforeLike);
		request.setAttribute("countLike", countLike);
	
		// -----------------------------
		if(result > 0) {
			// 특정 게시물 불러오기
			Community community = new CommunityService().selectOneCommunity(comNo);
			
			// 댓글 전체 불러오기
			ArrayList<Comment> cList = new CommentService().selectAllComment(comNo);
			
			if(community != null) {
				request.setAttribute("community", community);
				if(cList != null) {
					request.setAttribute("cList", cList);
					request.getRequestDispatcher("/WEB-INF/views/community/communityDetail.jsp").forward(request, response);	
				}else {
					request.getRequestDispatcher("/WEB-INF/views/community/communityDetail.jsp").forward(request, response);
				}
			}else {
				System.out.println("게시물 오류다!");
			}
		} else {
			System.out.println("조회수 오류다!");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	
	}

}
