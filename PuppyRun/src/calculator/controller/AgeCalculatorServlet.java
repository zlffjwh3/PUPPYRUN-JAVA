package calculator.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AgeCalculatorServler
 */
@WebServlet("/calculator/age")
public class AgeCalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgeCalculatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String birth = request.getParameter("dog_date");
		int widthChoice = Integer.parseInt(request.getParameter("dog_type"));
	      Calendar getToday = Calendar.getInstance();
	      getToday.setTime(new Date()); //금일 날짜
	      
	      String s_date = "2017-01-21";
	      Date date;
	      Calendar cmpDate = Calendar.getInstance();

	      try {
	         date = new SimpleDateFormat("yyyy-MM-dd").parse(s_date);
	         cmpDate.setTime(date); //특정 일자
	      } catch (ParseException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	         
	      long diffSec = (getToday.getTimeInMillis() - cmpDate.getTimeInMillis()) / 1000;
	      long diffDays = diffSec / (24*60*60); //일자수 차이

	      System.out.println(diffDays);
	      long diffyear = diffDays/365;
	      System.out.println("년 : " + diffyear);
	      
	      
	      long result = 0;
	      
	      if(widthChoice == 1) {
	         if(diffyear > 2) {
	            result = (2*12);
	            result = result + ((diffyear - 2) * 4);
	         }else {
	            result = diffyear*12;
	         }
	      }else if(widthChoice == 2) {
	         if(diffyear > 2) {
	            result = (2*12);
	            result = result + ((diffyear - 2) * 4);
	         }else {
	            result = diffyear*12;
	         }
	      }else if(widthChoice == 3) {
	         if(diffyear > 2) {
	            result = (2*12);
	            result = result + ((diffyear - 2) * 4);
	         }else {
	            result = diffyear*12;
	         }
	      }
	      
	       request.setAttribute("result", result);
	        request.getRequestDispatcher("WEB-INF/views/calculator/age.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
