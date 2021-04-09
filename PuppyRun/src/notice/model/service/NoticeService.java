package notice.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import common.JDBCTemplate;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;
import notice.model.vo.NoticePage;

public class NoticeService {
	private JDBCTemplate factory;
	
	public NoticeService() {
		factory = JDBCTemplate.getConnection();
	}
	
	// 공지사항 리스트
	public NoticePage selectAllNotice(int currentPage) {
		Connection conn = null;
		NoticePage np = new NoticePage();
		
		try {
			conn = factory.createConnection();
			
			np.setnList(new NoticeDAO().selectAllNotice(conn, currentPage));
			np.setPageNavi(new NoticeDAO().getPageNavi(conn, currentPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return np;
	}
	
	// 공지사항 조회
	public Notice selectOneNotice(int noticeNo) {
		return null;
	}
	
	// 공지사항 조회 - 조회수 추가
	public int addReadCount(int noticeNo) {
		return 0;
	}
	
	// 공지사항 추가
	public int insertNotice(Notice notice) {
		return 0;
	}
	
	// 공지사항 수정
	public int updateNotice(Notice notice) {
		return 0;
	}
	
	// 공지사항 삭제
	public int deleteNotice(int noticeNo) {
		return 0;
	}
}
