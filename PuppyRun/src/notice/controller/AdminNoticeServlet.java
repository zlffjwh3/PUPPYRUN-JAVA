package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import notice.model.vo.NoticePage;

@WebServlet("/admin/notice")
public class AdminNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminNoticeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 페이지에서 퍼피런이야기 검색
		String contentChoice = request.getParameter("contentChoice");
		String noticeSearch = request.getParameter("searchKeyword");
		
		if(contentChoice.equals("puppyTitle")) {
			contentChoice = "NOTICE_TITLE";
		} else {
			contentChoice = "NOTICE_CONTENT";
		}
		NoticePage noticePage = new NoticeService().selectSearchNoticeList(noticeSearch, contentChoice);
		ArrayList<Notice> noticeList = noticePage.getnList();
		
		if(!noticeList.isEmpty()) {
			request.setAttribute("noticeList", noticeList);
			request.getRequestDispatcher("/user/list").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
