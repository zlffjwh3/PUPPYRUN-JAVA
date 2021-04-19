package index.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import matching.model.service.MatchingService;
import matching.model.vo.Matching;
import matching.model.vo.MatchingPage;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import notice.model.vo.NoticePage;
import community.model.service.CommunityService;
import community.model.vo.Community;
import community.model.vo.CommunityPage;
import petdiary.model.service.GoalService;
import petdiary.model.vo.Goal;
import user.model.service.UserService;
import user.model.vo.User;

@WebServlet("/PuppyRun")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		int currentPage = 1;
		
		// 회원정보 모두 가져오기 (닉네임 옆에 사진때매)
		ArrayList<User> uList = new UserService().selectAllUserList2();
		request.setAttribute("uList", uList);
		
		// 공지사항 가져오기 (최신순 3개)
		NoticePage np = new NoticeService().selectAllNotice(currentPage);
		ArrayList<Notice> nList = np.getnList();
		request.setAttribute("nList", nList);
		
		// 멍멍이야기 게시글 가져오기
		CommunityPage communityPage = new CommunityService().selectAllCommunity(currentPage);
		ArrayList<Community> cList = communityPage.getcList();
		request.setAttribute("cList", cList);
		
	    // 산책짝꿍 게시글 가져오기
		MatchingPage mp = new MatchingService().printAllMatching(currentPage);
		ArrayList<Matching> mList = mp.getmList();
		request.setAttribute("mList", mList);
	    
		if(user != null) {
			String userId = user.getUserId();
			
			// 산책기록 가져오기
			Date today = new Date();
			SimpleDateFormat sdformat = new SimpleDateFormat("yyMMdd");
		    String todayString = sdformat.format(today);
		    Goal weekGoal = new GoalService().weekGoal(userId, todayString);
		    
		    request.setAttribute("weekGoal", weekGoal); // 산책기록
			request.getRequestDispatcher("/puppyrun.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/puppyrun.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
