package community.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.LikeService;

@WebServlet("/like/change")
public class LikeChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LikeChangeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = null; // 세션에서 가져옴
		int comNo = 0; // communityDetail.jsp에서 가져옴
		
		char before = new LikeService().likeStatus(comNo, userId);
		char after = 0;
		if(after == 'Y') {
			after = 'N';
		} else {
			after = 'Y';
		}
		
		int result = new LikeService().changeStatus(comNo, userId, after);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
