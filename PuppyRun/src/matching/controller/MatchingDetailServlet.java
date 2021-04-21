package matching.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import matching.model.service.MatchingChatService;
import matching.model.service.MatchingService;
import matching.model.vo.Matching;
import matching.model.vo.MatchingChat;
import user.model.service.UserService;
import user.model.vo.User;

@WebServlet("/matching/detail")
public class MatchingDetailServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    public MatchingDetailServlet() {
        super();
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int matchingNo = Integer.parseInt(request.getParameter("matNo"));
      
      
      Matching matching = new MatchingService().printOneMatching(matchingNo);
      ArrayList<MatchingChat> matChat = new MatchingChatService().viewMsg(matchingNo);
      // 프로필 사진 가져와야 함
   	  ArrayList<User> uList = new UserService().selectAllUserList2();
      
      if(matching != null) {
    	  request.setAttribute("matching", matching);
    	  request.setAttribute("uList", uList);
    	  if(matChat != null) {
    		  request.setAttribute("matChat", matChat);
    		  request.getRequestDispatcher("/WEB-INF/views/matching/petMateChat.jsp").forward(request, response);
    	  }else {
    		  request.getRequestDispatcher("/WEB-INF/views/matching/petMateChat.jsp").forward(request, response);
    	  }
    	  
      } else {
    	  request.getRequestDispatcher("/WEB-INF/views/matching/matchingError.html").forward(request, response);
      }
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.setCharacterEncoding("UTF-8");
	   int matchingNo = Integer.parseInt(request.getParameter("matching-no"));
	   String sendId = request.getParameter("send-id");
	   String rcvId = request.getParameter("rcv-id");
	   String matchingContent = request.getParameter("chat-content");
	   
	   // 채팅 새로 등록
	   MatchingChat matChat = new MatchingChat();
	   
	   matChat.setMatNo(matchingNo);
	   matChat.setSendId(sendId);
	   matChat.setRcvId(rcvId);
	   matChat.setContent(matchingContent);
	   
	   // 채팅 등록되었다고 알리기
	   Matching matching = new MatchingService().printOneMatching(matchingNo);
	   int matResult = 0;
	   if(matching.getMatCheck() == 'N') {
		   matResult = new MatchingService().changeCheck(matchingNo);
	   } else {
		   matResult = 1;
	   }
	   
	   int result = new MatchingChatService().sendMsg(matChat);
	   
	   
	   System.out.println(result);///////////////////////////
	   System.out.println(matResult);///////////////////
	   
	   if(result > 0 && matResult > 0) {
		   response.sendRedirect("/matching/detail?matNo=" + matchingNo);
	   } else {
		   System.out.println("오류다 피해 !!!!!!!!!!!!!!!!!!!!!!!!!!");
	   }
   }

}
