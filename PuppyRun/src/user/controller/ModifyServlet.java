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
		
		int userResult = 0; // 결과값을 받아온다
		int dogResult = 0; // "
		String userId = request.getParameter("userId"); // 마이페이지 넘어올 때 userId 받아옴
		char modifyDogCheck = request.getParameter("dogCheck").charAt(0); // 수정할때 반려견 체크
		User userBefore = new UserService().selectOneUserIdOnly(userId); // 수정하기 전 기본정보
		
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
		
		
		// 수정할 때 반려견 있음 표시!!! (기존 반려견 있는 사람도 포함)
		if(modifyDogCheck == 'Y') {
			if(userBefore.getDogCheck() == 'Y') { // 나는 원래 강아지가 있었어요!!! 내 강아지를 수정해요
				String dogName = request.getParameter("dog-name");
				String dogBreed = request.getParameter("dog-kind");
				char dogGender = request.getParameter("dog-gender").charAt(0);
				int dogAge = Integer.parseInt(request.getParameter("dog-age"));
				float dogWeight = Float.parseFloat(request.getParameter("dog-weight"));
				
				User user = new User();
				String birth = year + month + day;
				String address = zip + "/" + addr1 + "/" + addr2;
				user.setUserId(userId);
				user.setUserPw(pwd);
				user.setPhone(phone);
				user.setEmail(email);
				user.setUserBirth(birth);
				user.setUserAddr(address);
				user.setDogCheck('Y');
				
				Dog dog = new Dog();
				dog.setDogName(dogName);
				dog.setDogBreed(dogBreed);
				dog.setDogGender(dogGender);
				dog.setDogAge(dogAge);
				dog.setDogWeight(dogWeight);
				dog.setDogId(userId);
				System.out.println("도그아이디 : " + dog.getDogId() + "도그이름" + dog.getDogName() +
						"개성별" + dog.getDogGender() + "개나이 " + dog.getDogAge() + "도그무게 " + dog.getDogWeight());
				
				userResult = new UserService().updateUser(user);
				dogResult = new UserService().updateDog(dog);
				System.out.println("유저값" + userResult);
				System.out.println("도그리저트" + dogResult);
				
			} else if(userBefore.getDogCheck() == 'N') { // 반려견이 없었는데 나타났어요!!
				String dogName = request.getParameter("dog-name");
				String dogBreed = request.getParameter("dog-kind");
				char dogGender = request.getParameter("dog-gender").charAt(0);
				int dogAge = Integer.parseInt(request.getParameter("dog-age"));
				float dogWeight = Float.parseFloat(request.getParameter("dog-weight"));
				
				User user = new User();
				String birth = year + month + day;
				String address = zip + "/" + addr1 + "/" + addr2;
				user.setUserId(userId);
				user.setUserPw(pwd);
				user.setPhone(phone);
				user.setEmail(email);
				user.setUserBirth(birth);
				user.setUserAddr(address);
				user.setDogCheck('Y');
				
				Dog dog = new Dog();
				dog.setDogName(dogName);
				dog.setDogBreed(dogBreed);
				dog.setDogGender(dogGender);
				dog.setDogAge(dogAge);
				dog.setDogWeight(dogWeight);
				dog.setDogId(userId);
				
				userResult = new UserService().updateUser(user);
				dogResult = new UserService().insertDog(dog);
				System.out.println("유저값" + userResult);
				System.out.println("도그리저트" + dogResult);
				
				
			}
			
			if(userResult > 0 && dogResult > 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('회원정보가 수정되었습니다.'); location.href='/user/myInfo'; </script>");
			} else {
				response.sendRedirect("/views/user/error.html");
			}
			
			// 나는 강아지가 없습니다!!
		} else if(modifyDogCheck == 'N') {
			
			User user = new User();
			String birth = request.getParameter("user-birth-year") + request.getParameter("user-birth-month") + request.getParameter("user-birth-day");
			String address = request.getParameter("zip") + "/" + request.getParameter("addr1") + "/" + request.getParameter("addr2");
			user.setUserId(request.getParameter("user-id"));
			user.setUserPw(request.getParameter("user-pwd"));
			user.setPhone(request.getParameter("user-phone"));
			user.setEmail(request.getParameter("user-email"));
			user.setUserBirth(birth);
			user.setUserAddr(address);
			user.setDogCheck('N');
			
			userResult = new UserService().updateUser(user);
			
			if(userResult > 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('회원정보가 수정되었습니다.'); location.href='/user/myInfo'; </script>");
			} else if (userResult == 0) {
				
			}else {
				response.sendRedirect("/views/user/error.html");
			}
		}
		
		
		
		
	}

}