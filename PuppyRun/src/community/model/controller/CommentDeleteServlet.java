package community.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.CommentService;

@WebServlet("/comment/delete")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int communityNo = Integer.parseInt(request.getParameter("comNo"));
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		
		int result = 0;
		
		result = new CommentService().deleteCommunity(commentNo);
		
		if(result > 0) {
			response.sendRedirect("/community/detail?comNo="+ communityNo);
		}else {
			System.out.println("댓글 삭제 오류다!");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
