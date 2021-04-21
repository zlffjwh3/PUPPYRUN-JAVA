package community.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.LikeService;
import community.model.vo.Like;


@WebServlet("/like/change")
public class LikeChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LikeChange() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		int comNo = Integer.parseInt(request.getParameter("comNo"));
		String check = request.getParameter("check");
	
		// 좋아요를 누른 적이 없을 경우 (등록하기)
		if(check.equals("null")) {
			String likeStatus = "Y";
			int eroll = new LikeService().registerStatus(comNo, userId, likeStatus);
			
			// 위 메소드가 성공하면
			if(eroll > 0) { 
				response.sendRedirect("/community/detail?comNo=" + comNo); 
			}else {
				System.out.println("좋아요 등록 오류");
			}
		// 좋아요를 누른 적이 있을 경우 (삭제하기)
		}else if(check.equals("Y")){
			int eroll = new LikeService().cancelStatus(comNo, userId);
			
			if(eroll > 0) {
				response.sendRedirect("/community/detail?comNo=" + comNo);
			}else {
				System.out.println("좋아요 삭제 오류");
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
