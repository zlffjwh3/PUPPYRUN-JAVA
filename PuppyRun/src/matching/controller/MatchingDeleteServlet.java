package matching.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import matching.model.service.MatchingService;

@WebServlet("/matching/delete")
public class MatchingDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MatchingDeleteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int matNo = Integer.parseInt(request.getParameter("matNo"));
		int result = new MatchingService().deleteMatching(matNo);
		if(result > 0) {
			response.sendRedirect("/matching/list");
			
		}else {
			request.getRequestDispatcher("/WEB-INF/views/matching/matchingError.html").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

