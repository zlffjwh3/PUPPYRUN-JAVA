package community.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.CommentService;
import community.model.vo.Comment;

@WebServlet("/comment/write")
public class CommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글 작성
		String commentContent = request.getParameter("comment");
		String commentUserId = request.getParameter("userId");
		String commentUserNick = request.getParameter("userNick");
		int communityNo = Integer.parseInt(request.getParameter("comNo"));
		Comment comment = new Comment();
		
		comment.setCommentContents(commentContent);
		comment.setCommentId(commentUserId);
		comment.setComNo(communityNo);
		comment.setUserNick(commentUserNick);
		
		int result = 0;
		result = new CommentService().insertCommunity(comment);
		
		if(result > 0) {
			response.sendRedirect("/community/detail?comNo=" + communityNo);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
