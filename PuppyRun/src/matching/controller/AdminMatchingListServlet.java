package matching.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import matching.model.service.MatchingService;
import matching.model.vo.Matching;
import matching.model.vo.MatchingPage;


@WebServlet("/admin/matching")
public class AdminMatchingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminMatchingListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 페이지에서 산책짝꿍 검색
		String matchingChoice = request.getParameter("matchingChoice");
		String matchingSearch = request.getParameter("searchKeyword");
		
		if(matchingChoice.equals("matchingTitle")) {
			matchingChoice = "MAT_TITLE";
		} else {
			matchingChoice = "MAT_CONTENT";
		}
		MatchingPage matchingPage = new MatchingService().selectSearchMatchingList(matchingSearch, matchingChoice);
		ArrayList<Matching> matchingList = matchingPage.getmList();
		
		if(!matchingList.isEmpty()) {
			request.setAttribute("matchingList", matchingList);
			request.getRequestDispatcher("/user/list").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
