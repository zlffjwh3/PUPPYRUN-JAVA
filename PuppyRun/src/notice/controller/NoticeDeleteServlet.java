package notice.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import photo.model.service.PhotoService;

@WebServlet("/notice/delete")
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeDeleteServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String noticePhoto = new NoticeService().selectOneNotice(noticeNo).getNoticePhoto();
		String userId = "admin";
		
		int noticeResult = new NoticeService().deleteNotice(noticeNo);
		
		int photoResult = 1;
		if(noticePhoto != null) {
			String photoPath = new PhotoService().selectPhoto(noticePhoto, userId);
			photoResult = new PhotoService().removePhoto(noticePhoto, userId);
			File file = new File(photoPath);
			file.delete(); //upload 폴더 안 파일 삭제
		}
		
		if(noticeResult > 0 && photoResult > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시글이 삭제되었습니다.'); location.href='/notice/list';</script>");
			out.flush();
		} else {
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
