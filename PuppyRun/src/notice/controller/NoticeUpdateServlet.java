package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

@WebServlet("/notice/update")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정할 정보 불러오기
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		Notice notice = new NoticeService().selectOneNotice(noticeNo);
		
		// 수정할 수 있는 아이디인지 확인해야 하나????????????
		if(notice != null) {
			request.setAttribute("notice", notice);
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeUpdate.jsp").forward(request, response);
			// 수정하는 페이지 보여주기
		} else {
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정사항 수정해서 보내기 -- 파일부분 수정하고 난 다음에 수정할 것!!!!!!!!!! (writeServlet 참고)
		// 사진파일 없으면 사진정보에서 삭제하는 과정 필요
		request.setCharacterEncoding("UTF-8");
		
//		String subject = request.getParameter("subject");
//		String content = request.getParameter("content");
//		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
//		
//		Notice notice = new Notice();
//		notice.setSubject(subject);
//		notice.setContents(content);
//		notice.setNoticeNo(noticeNo);
//		
//		int result = new NoticeService().updateNotice(notice);
//		if(result > 0) {
//			response.sendRedirect("/notice/list");
//		} else {
//			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html").forward(request, response);
//		}
	}

}
