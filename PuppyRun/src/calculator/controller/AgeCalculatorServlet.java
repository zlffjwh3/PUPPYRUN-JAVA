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
		String calc_type = request.getParameter("calc_type");
		if("2".equals(calc_type)) {
			request.getRequestDispatcher("/WEB-INF/views/calculator/calculatorCalorie.jsp").forward(request, response);
		}else if("3".equals(calc_type)) {
			request.getRequestDispatcher("/WEB-INF/views/calculator/calculatorObesity.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/views/calculator/calculatorAge.jsp").forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
