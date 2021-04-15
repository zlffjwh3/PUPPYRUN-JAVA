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
import notice.model.vo.NoticePage;

@WebServlet("/matching/list")
public class MatchingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MatchingListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 0;
		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		MatchingPage np = new MatchingService().printAllMatching(currentPage);
		ArrayList<Matching> mList = np.getmList();
		String pageNavi = np.getPageNavi();
		
		if(!mList.isEmpty()) {
			request.setAttribute("mList", mList);
			request.setAttribute("pageNavi", pageNavi);
			request.getRequestDispatcher("/WEB-INF/views/matching/petMate.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/matching/matchingError.html").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

