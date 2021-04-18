package user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.Dog;
import user.model.vo.User;


@WebServlet("/user/enroll")
public class EnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EnrollServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		User user = new User();
//		Dog dog = new Dog();
		String birth = request.getParameter("user-birth-year") + request.getParameter("user-birth-month") + request.getParameter("user-birth-day");
		String address = request.getParameter("zip") + "/" + request.getParameter("addr1") + "/" + request.getParameter("addr2");
		
		user.setUserId(request.getParameter("user-id"));
		user.setUserPw(request.getParameter("user-pwd"));
		user.setUserNick(request.getParameter("user-nickname"));
		user.setUserName(request.getParameter("user-name"));
		user.setPhone(request.getParameter("user-phone"));
		user.setEmail(request.getParameter("user-email"));
		user.setUserBirth(birth);
		user.setUserAddr(address);
//		user.setDogCheck(request.getParameter("pet-select").charAt(0));
		
		
		/*
		 * dog.setDogName(request.getParameter("dog-name"));
		 * dog.setDogBreed(request.getParameter("dog-kind"));
		 * dog.setDogGender(request.getParameter("dog-gender").charAt(0));
		 * dog.setDogAge(Integer.parseInt(request.getParameter("dog-age")));
		 * dog.setDogWeight(Float.parseFloat(request.getParameter("dog-weight")));
		 * dog.setDogId(request.getParameter("user-id"));
		 * 
		 * user.setDog(dog);
		 */
				
		int userResult = new UserService().insertUser(user);
		if(userResult > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('퍼피런 회원 등록이 완료되었습니다!'); location.href='/index.jsp';</script>");
			out.flush();
		}else {
			request.getRequestDispatcher("/WEB-INF/views/user/error.html").forward(request, response);
		}
		/*
		 * if(request.getParameter("pet-select").charAt(0) == 'Y') { int dogResult = new
		 * UserService().insertDog(dog);
		 * 
		 * if(userResult > 0 && dogResult > 0) { response.sendRedirect("/join.jsp");
		 * }else { response.sendRedirect("/WEB-INF/views/user/error.html"); }
		 * 
		 * }else { int userResult = new UserService().insertUser(user);
		 * 
		 * }
		 */
		
	}

}
