package community.model.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.CommunityService;
import community.model.vo.Community;
import photo.model.service.PhotoService;

@WebServlet("/community/delete")
public class CommunityDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommunityDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int communityNo = Integer.parseInt(request.getParameter("communityNo"));
		Community community = new CommunityService( ).selectOneCommunity(communityNo);
		String communityPhoto = community.getComPhoto();
		String communityUserId = community.getComId();
		int result = 0;
		
		result = new CommunityService().deleteCommunity(communityNo);
		
		int photoResult = 1;
		if(communityPhoto != null) {
			String photoPath = new PhotoService().selectPhoto(communityPhoto, communityUserId);
			photoResult = new PhotoService().removePhoto(communityPhoto, communityUserId);
			File file = new File(photoPath);
			file.delete(); //위에 가져온 파일 경로와 동일한 파일 삭제
		}
		
		if(result > 0 && photoResult > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시글이 삭제되었습니다.'); location.href='/community/list';</script>");
			out.flush();
		}else {
			System.out.println("삭제 오류");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
