package user.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		User user = new UserService().selectOneUserIdOnly(userId);
		Dog dog = new UserService().selectOneDog(userId); // 강아지 정보 가져옴
		
		if(user != null) {
			request.setAttribute("user", user);
			request.setAttribute("dog", dog); // user값은 session에서
			request.getRequestDispatcher("/WEB-INF/views/user/userUpdate.jsp").forward(request, response);;
		} else {
			request.getRequestDispatcher("/WEB-INF/views/user/error.html").forward(request, response);
		}
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId") ;
		User userBefore = new UserService().selectOneUserIdOnly(userId); // 기존정보
		
		// DB 업데이트
		String pwd = request.getParameter("user-pwd");
		String phone = request.getParameter("user-phone");
		String email = request.getParameter("user-email");
		String year = request.getParameter("user-birth-year");
		String month = request.getParameter("user-birth-month");
		String day = request.getParameter("user-birth-day");
		String zip = request.getParameter("zip");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		
		
		
		
		User user = new User(); // 정보 수정할 때 데이터 넘어옴
		user.setUserId(userId);
		user.setUserPw(pwd);
		user.setPhone(phone);
		user.setEmail(email);
		user.setUserBirth(year + month + day);
		user.setUserAddr(zip + "/" + addr1 + "/" + addr2);
		
		// 반려견 이름이 입력되어 있다면...
		// 수정예정 (강아지 없다가 생김 / 강아지 있다가 없어짐 )---------------------------------------------------------------------------!!
		int dogResult = 0;
		if(request.getParameter("dog-name") != "") {
			String dogName = request.getParameter("dog-name");
			String dogBreed = request.getParameter("dog-kind");
			String dogGender = request.getParameter("dog-gender");
			String dogAge = request.getParameter("dog-age");
			String dogWeight = request.getParameter("dog-weight");
			
			System.out.println("ㅎㅎㅎ"); // 테스트
			Dog dog = new Dog();
			dog.setDogName(request.getParameter("dog-name"));
			dog.setDogBreed(request.getParameter("dog-kind"));
			dog.setDogGender(request.getParameter("dog-gender").charAt(0));
			dog.setDogAge(Integer.parseInt(request.getParameter("dog-age")));
			dog.setDogWeight(Float.parseFloat(request.getParameter("dog-weight")));
			
			dogResult = new UserService().updateDog(user, dog);
		}
		
		int result = new UserService().updateUser(user);
		if(result > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원정보가 수정되었습니다.'); location.href='/user/myInfo'; </script>");
		} else if (result == 0) {
			
		}else {
			response.sendRedirect("/views/user/error.html");
		}
		
		
	}

}
