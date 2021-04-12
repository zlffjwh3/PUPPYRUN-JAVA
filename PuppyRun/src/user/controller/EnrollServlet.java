package user.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.vo.Dog;
import user.model.vo.User;


@WebServlet("/user/enroll")
public class EnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EnrollServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		Dog dog = new Dog();
		String birth = request.getParameter("user-birth-year") + request.getParameter("user-birth-month") + request.getParameter("user-birth-day");
		String address = request.getParameter("zip") + request.getParameter("addr1") + request.getParameter("addr2");
		user.setUserId(request.getParameter("user-id"));
		user.setUserPw(request.getParameter("user-pwd"));
		user.setUserNick(request.getParameter("user-nickname"));
		user.setUserName(request.getParameter("user-name"));
		user.setPhone(request.getParameter("user-phone"));
		user.setEmail(request.getParameter("user-email"));
		user.setUserBirth(birth);
		user.setUserAddr(address);
		user.setDogCheck(request.getParameter("pet-select").charAt(0));
		
		dog.setDogName(request.getParameter("dog-name"));
		dog.setDogBreed(request.getParameter("dog-kind"));
		dog.setDogGender(request.getParameter("dog-gender").charAt(0));
		dog.setDogAge(Integer.parseInt(request.getParameter("dog-weight")));
		
	}

}
