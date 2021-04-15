package matching.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import matching.model.service.MatchingService;
import matching.model.vo.Matching;

@WebServlet("/matching/detail")
public class MatchingDetailServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    public MatchingDetailServlet() {
        super();
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int matchingNo = Integer.parseInt(request.getParameter("matNo"));
      
      
      Matching matching = new MatchingService().printOneMatching(matchingNo);
      
      if(matching != null) {
    	  request.setAttribute("matching", matching);
    	  request.getRequestDispatcher("/WEB-INF/views/matching/petMateChat.jsp").forward(request, response);
      } else {
    	  request.getRequestDispatcher("/WEB-INF/views/matching/matchingError.html").forward(request, response);
      }
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
   }

}
