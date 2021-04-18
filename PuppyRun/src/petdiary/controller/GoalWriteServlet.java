package petdiary.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import petdiary.model.service.GoalService;
import petdiary.model.service.PetDiaryService;
import petdiary.model.vo.Goal;
import petdiary.model.vo.PetDiary;
import user.model.vo.User;

@WebServlet("/goal/write")
public class GoalWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GoalWriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		User user = (User)session.getAttribute("user");
		Goal goal = new Goal();
		
		String goalId = user.getUserId();
		String year = request.getParameter("year").substring(2,4);
		String month = request.getParameter("month");
		if(month.length() == 1) {
			month = "0" + month;
		}
		String date = request.getParameter("date");
		if(date.length() == 1) {
			date = "0" + date;
		}
		String goalDate = year + "/" + month + "/" + date;
		int goalDis = Integer.parseInt(request.getParameter("walk-dis"));
		int goalTime = Integer.parseInt(request.getParameter("walk-time"));
		
		goal.setGoalId(goalId);
		goal.setGoalDate(goalDate);
		goal.setGoalDis(goalDis);
		goal.setGoalTime(goalTime);
		
		// 목표 새로 생성하기
		int result = new GoalService().saveUserGoal(goal);
		
		// 일요일 데이터는 이미 등록되었으므로 가져와서 데이터 더해주기
		PetDiary petDiary = new PetDiaryService().selectOneDiary(goalDate, goalId);
		String goalDate2 = year + month + date;
		int sunResult = new GoalService().addGoalData(petDiary, goalDate2);
		
		if(result > 0 && sunResult > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('목표가 등록되었습니다.'); location.href='/petdiary/list';</script>");
			out.flush();
		} else {
			System.out.println("골 넣는거 실패해따!!!!!!!!!!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
