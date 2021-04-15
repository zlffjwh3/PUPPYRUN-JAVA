package notice.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import photo.model.service.PhotoService;
import photo.model.vo.Photo;

@WebServlet("/notice/update")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정할 정보 불러오기
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		Notice notice = new NoticeService().selectOneNotice(noticeNo);
		
		if(notice != null) {
			request.setAttribute("notice", notice);
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeUpdate.jsp").forward(request, response);
			// 수정하는 페이지 보여주기
		} else {
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정사항 수정해서 보내기
		request.setCharacterEncoding("UTF-8");

		char photoCheck = request.getParameter("photoCheck").charAt(0);
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		Notice noticeBefore = new NoticeService().selectOneNotice(noticeNo); // 기존 정보 가져오기
		
		// 기존에 파일 있었는데 수정한 경우 기존 파일 삭제
		if(photoCheck == 'Y') {
			String photoPathBefore = new PhotoService().selectPhoto(noticeBefore.getNoticePhoto(), "admin");
			new File(photoPathBefore).delete(); // 기존 파일 upload 폴더에서 삭제
		}
		
		// 새 사진파일 저장 (실제 upload 폴더 경로에 저장)
		String uploadFilePath = request.getServletContext().getRealPath("upload");
		int uploadFileSizeLimit = 5*1024*1024; // 5MB
		String encType = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());

		int photoResult = 0;
		String noticePhoto = multi.getFilesystemName("upFile");
		if(noticePhoto == null && photoCheck == 'Y') {
			// 파일 삭제
			photoResult = new PhotoService().removePhoto(noticeBefore.getNoticePhoto(), "admin");
		} else if(noticePhoto != null) {
			// 파일 추가 또는 수정
			File uploadFile = multi.getFile("upFile");
			
			String photoName = multi.getFilesystemName("upFile");
			String photoPath = uploadFile.getPath();
			long photoSize = uploadFile.length();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS"); // 날짜데이터를 내가 원하는 형태로 바꿔줌
			Timestamp uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
			
			Photo photo = new Photo();
			photo.setPhotoName(photoName);
			photo.setPhotoPath(photoPath);
			photo.setPhotoSize(photoSize);
			photo.setPhotoId("admin");
			photo.setUploadTime(uploadTime);
			photo.setBoardType('N');
			
			if(photoCheck != 'Y') {
				photoResult = new PhotoService().registerPhotoInfo(photo); // 파일 추가
			} else {
				photoResult = new PhotoService().updatePhoto(photo, noticeBefore.getNoticePhoto()); // 파일 수정
			}
		} else {
			photoResult = 1;
		}
		
		// NOTICE DB 업데이트
		String noticeTitle = multi.getParameter("title");
		String noticeContent = multi.getParameter("content");
		
		Notice notice = new Notice();
		notice.setNoticeNo(noticeNo);
		notice.setNoticeTitle(noticeTitle);
		notice.setNoticeContent(noticeContent);
		notice.setNoticePhoto(noticePhoto);
			// 조회수랑 업로드 날짜는 그대로 두는 걸로
		int noticeResult = new NoticeService().updateNotice(notice);
		
		// 결과 확인 ---------------------------------------------------------------------------------------------------
		if(noticeResult > 0 && photoResult > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시글이 수정되었습니다.'); location.href='/notice/detail?noticeNo=" + noticeNo  +"';</script>");
			out.flush();
		} else {
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html").forward(request, response);
		}
	}
}
