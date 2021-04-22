package user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.model.service.CommentService;
import community.model.service.CommunityService;
import community.model.service.LikeService;
import matching.model.service.MatchingChatService;
import matching.model.service.MatchingService;
import notice.model.service.NoticeService;
import petdiary.model.service.GoalService;
import petdiary.model.service.PetDiaryService;
import user.model.service.UserService;
import user.model.vo.User;

@WebServlet("/user/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		char dogCheck = request.getParameter("dogCheck").charAt(0);
		System.out.println(dogCheck);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();
		int userResult = 0;
		int dogResult = 0;
		int communityResult = 0;
		int commentResult = 0;
		int likeResult = 0;
		int goalResult = 0;
		int petDiaryResult = 0;
		int matchingResult = 0;
		int messageResult = 0;
		int noticeResult = 0;
		
		System.out.println("유저아이디 : " + userId);
		
		if(user.getDogCheck() == 'N') { // 나는 반려견이 없습니다
			
			goalResult = new GoalService().deleteGoal(userId);
			petDiaryResult = new PetDiaryService().deleteDiary(userId);
			messageResult = new MatchingChatService().deleteChat(userId);
			matchingResult = new MatchingService().deleteMatching(userId);
			likeResult = new LikeService().deleteLike(userId);
			commentResult = new CommentService().deleteCommunity(userId); 
			communityResult = new CommunityService().deleteCommunity(userId);
			dogResult = new UserService().deleteDog(userId);
			userResult = new UserService().deleteUser(userId);
			
			if(userResult > 0) {
				session.invalidate(); // session 파괴
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('퍼피런 회원탈퇴가 완료되었습니다.'); location.href='/index.jsp';</script>");
				out.flush();
			} else {
				request.getRequestDispatcher("/WEB-INF/views/user/error.html").forward(request, response);
			}
			
		} else if(user.getDogCheck() == 'Y') { // 나는 반려견이 있습니다
			
			goalResult = new GoalService().deleteGoal(userId);
			petDiaryResult = new PetDiaryService().deleteDiary(userId);
			messageResult = new MatchingChatService().deleteChat(userId);
			matchingResult = new MatchingService().deleteMatching(userId);
			likeResult = new LikeService().deleteLike(userId);
			commentResult = new CommentService().deleteCommunity(userId); 
			communityResult = new CommunityService().deleteCommunity(userId);
			dogResult = new UserService().deleteDog(userId);
			userResult = new UserService().deleteUser(userId);
			
			if(userResult > 0 && dogResult > 0) {
				session.invalidate(); // session 파괴
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('퍼피런 회원탈퇴가 완료되었습니다.'); location.href='/index.jsp';</script>");
				out.flush();
			} else {
				request.getRequestDispatcher("/WEB-INF/views/user/error.html").forward(request, response);
			}
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 어드민 회원 강제 탈퇴
		String userId = request.getParameter("userId");
		String noticeNo = request.getParameter("noticeNo");
		
		int userResult = 0;
		int dogResult = 0;
		int communityResult = 0;
		int commentResult = 0;
		int likeResult = 0;
		int goalResult = 0;
		int petDiaryResult = 0;
		int matchingResult = 0;
		int messageResult = 0;
		int noticeResult = 0;
		
		System.out.println(noticeNo);
		if(noticeNo != null) {
			int noticeNo1 = Integer.parseInt(noticeNo);
			int result = new NoticeService().deleteNotice(noticeNo1);
			
			if(result > 0) {
				response.sendRedirect("/user/list?dogCheck=all");
			}
			
			// 여기서 삭제 하나 더 해줘야함
		}
		
		
		System.out.println("userId 받아왔니 : " + userId);
		User user = new UserService().selectOneUserIdOnly(userId);
		if(noticeNo == null && userId != null) {
			if(user.getDogCheck() == 'N') {
				// 회원 삭제할 때, 게시물과 댓글, 좋아요도 삭제
				
				
				goalResult = new GoalService().deleteGoal(userId);
				petDiaryResult = new PetDiaryService().deleteDiary(userId);
				messageResult = new MatchingChatService().deleteChat(userId);
				matchingResult = new MatchingService().deleteMatching(userId);
				likeResult = new LikeService().deleteLike(userId);
				commentResult = new CommentService().deleteCommunity(userId); 
				communityResult = new CommunityService().deleteCommunity(userId);
				dogResult = new UserService().deleteDog(userId);
				userResult = new UserService().deleteUser(userId);
			
				if(userResult > 0 ) {
					response.sendRedirect("/user/list?dogCheck=all");
				}else {
					System.out.println("관리자 메뉴에서 회원 삭제 오류 발생 (DogCheck == N)");
				}
			
			}else if(user.getDogCheck() == 'Y'){
				
				goalResult = new GoalService().deleteGoal(userId);
				petDiaryResult = new PetDiaryService().deleteDiary(userId);
				messageResult = new MatchingChatService().deleteChat(userId);
				matchingResult = new MatchingService().deleteMatching(userId);
				likeResult = new LikeService().deleteLike(userId);
				commentResult = new CommentService().deleteCommunity(userId); 
				communityResult = new CommunityService().deleteCommunity(userId);
				dogResult = new UserService().deleteDog(userId);
				userResult = new UserService().deleteUser(userId);
			
			
				if(userResult > 0 && dogResult > 0) {
					response.sendRedirect("/user/list?dogCheck=all");
				}else {
					System.out.println("관리자 메뉴에서 회원 삭제 오류 발생 (DogCheck == Y)");
					
				}
			}else {
				System.out.println("관리자 메뉴에서 회원 삭제 오류 발생 (DogCheck 값 가져올 수 없음!!");
			}
		}

	}

}
