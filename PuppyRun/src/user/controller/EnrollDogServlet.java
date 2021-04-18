package user.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.Dog;
import user.model.vo.User;


@WebServlet("/user/enrollDog")
public class EnrollDogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EnrollDogServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		User user = new User();
		Dog dog = new Dog();
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
		user.setDogCheck('Y');
		
		 dog.setDogName(request.getParameter("dog-name"));
		 dog.setDogBreed(request.getParameter("dog-kind"));
		 dog.setDogGender(request.getParameter("dog-gender").charAt(0));
		 dog.setDogAge(Integer.parseInt(request.getParameter("dog-age")));
		 dog.setDogWeight(Float.parseFloat(request.getParameter("dog-weight")));
		 dog.setDogId(request.getParameter("user-id"));
		  
//		 user.setDog(dog);
		 
				
		int userResult = new UserService().insertDog(user, dog);
		System.out.println("값이 나오나 테스트 : " + userResult);
		if(userResult > 0) {
			response.sendRedirect("/join.jsp");
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
