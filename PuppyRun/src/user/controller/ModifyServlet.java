package user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.model.service.UserService;
import user.model.vo.Dog;
import user.model.vo.User;

@WebServlet("/user/modify")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		User user = new UserService().selectOneUser(userId, userPw);
		Dog dog = new UserService().selectOneDog(userId); // 강아지 정보 가져옴
		
		if(user != null) {
			request.setAttribute("dog", dog); // user값은 session에서
			request.getRequestDispatcher("/WEB-INF/views/user/myInfoModify.jsp").forward(request, response);;
		} else {
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html").forward(request, response);
		}
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		User userBefore = new UserService().selectOneUser(userId, userPw);
		System.out.println("테스트 : " + userBefore);
		
		
		User user = new User(); // 정보 수정할 때 데이터 넘어옴
		user.setUserPw(request.getParameter("user-pwd"));
		user.setEmail(request.getParameter("user-email"));
		user.setPhone(request.getParameter("user-phone"));
		String year = request.getParameter("user-birth-year");
		String month = request.getParameter("user-birth-month");
		String day = request.getParameter("user-birth-day");
		user.setUserBirth(year + month + day);
//		user.setUserAddr(request.getParameter(name)); // 주소
		Dog dog = new Dog();
		dog.setDogName(request.getParameter("dog-name"));
		dog.setDogBreed(request.getParameter("dog-kind"));
		dog.setDogGender(request.getParameter("dog-gender").charAt(0));
		dog.setDogAge(Integer.parseInt(request.getParameter("dog-age")));
		dog.setDogWeight(Integer.parseInt("dog-weight"));
		
		int result = new UserService().updateUser(user);
		if(result > 0) {
			response.sendRedirect("/index.jsp");
		} else {
			response.sendRedirect("/views/user/error.html");
		}
		
		
	}

}
