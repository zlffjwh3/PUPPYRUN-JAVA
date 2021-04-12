package petdiary.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import petdiary.model.service.PetDiaryService;
import petdiary.model.vo.PetDiary;
import user.model.vo.User;

@WebServlet("/petdiary/list")
public class PetDiaryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PetDiaryListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();
		
		ArrayList<PetDiary> pList = new PetDiaryService().selectAllDiary(userId);
		
		if(pList.isEmpty()) {
			request.setAttribute("pList", pList);
			request.getRequestDispatcher("/WEB-INF/views/pet-diary/pet-diary.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/pet-diary/diary.html").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
