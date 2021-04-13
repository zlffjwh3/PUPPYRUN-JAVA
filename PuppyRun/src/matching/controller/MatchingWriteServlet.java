package matching.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import matching.model.vo.Matching;
import user.model.vo.User;

@WebServlet("/matching/write")
public class MatchingWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MatchingWriteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 사진을 저장할 실제 폴더 경로
		String uploadFilePath = request.getServletContext().getRealPath("upload");
		int uploadFileSizeLimit = 5*1024*1024;
		String encType = "UTF-8";
		MultipartRequest multi  = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
		
		// 로그인 했을 때의 세션 정보 가져오기 (User 객체)
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		// 작성 완료한 게시물 정보 가져오기
		String mAddr = "addr1" + "addr2";
		String matchingId = user.getUserId();
		String matchingAddr = multi.getParameter(mAddr);
		String matchingTitle = multi.getParameter("title");
		String matchingContent = multi.getParameter("content");
		String matchingNickName = user.getUserNick();
		
		// 위에 가져온 값들을 Matching 객체에 저장하기
		Matching matching = new Matching();
		matching.setMatId(matchingId);
		
	}

}