package matching.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import matching.model.service.MatchingChatService;
import matching.model.service.MatchingService;
import matching.model.vo.MatchingChat;
import photo.model.service.PhotoService;
import user.model.vo.User;

@WebServlet("/matching/delete")
public class MatchingDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MatchingDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();
		int matNo = Integer.parseInt(request.getParameter("matNo"));
		String matPhoto = new MatchingService().printOneMatching(matNo).getMatPhoto();
		
		// 글 삭제
		int result = new MatchingService().deleteMatching(matNo);
		
		// 메세지 삭제
		ArrayList<MatchingChat> matChat = new MatchingChatService().viewMsg(matNo);
		int chatResult = 0;
		if(!matChat.isEmpty()) {
			chatResult = new MatchingChatService().deleteMsg(matNo);
		} else {
			chatResult = 1;
		}
		
		// 사진 삭제
		int photoResult = 0;
		if(matPhoto != null) {
			String photoPath = new PhotoService().selectPhoto(matPhoto, userId);
			photoResult = new PhotoService().removePhoto(matPhoto, userId);
			File file = new File(photoPath);
			file.delete(); //upload 폴더 안 파일 삭제
		} else {
			photoResult = 1;
		}
		
		if(result > 0 && chatResult > 0 && photoResult > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시글이 삭제되었습니다.'); location.href='/matching/list'; </script>");
		}else {
			request.getRequestDispatcher("/WEB-INF/views/matching/matchingError.html").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

