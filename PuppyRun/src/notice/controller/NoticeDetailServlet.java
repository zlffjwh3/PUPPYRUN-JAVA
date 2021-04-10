package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

@WebServlet("/notice/detail")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));

		int result = new NoticeService().addReadCount(noticeNo); // 조회수 추가
		if(result > 0) {
			Notice notice = new NoticeService().selectOneNotice(noticeNo); // 데이터 가져오기
			
			if(notice != null) {
				request.setAttribute("notice", notice);
				request.getRequestDispatcher("/WEB-INF/views/notice/noticeDetail.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
