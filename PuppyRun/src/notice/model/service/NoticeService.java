package notice.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	// 공지사항 리스트2 - 최신순으로 3개만 출력 (배너용)
	public ArrayList<Notice> selectThreeNotice() {
		Connection conn = null;
		ArrayList<Notice> nList = null;
		
		try {
			conn = factory.createConnection();
			nList = new NoticeDAO().selectThreeNotice(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return nList;
	}
	
	// 공지사항 조회
	public Notice selectOneNotice(int noticeNo) {
		Connection conn = null;
		Notice notice = null;
		
		try {
			conn = factory.createConnection();
			notice = new NoticeDAO().selectOneNotice(conn, noticeNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return notice;
	}
	
	// 공지사항 조회 - 조회수 추가
	public int addReadCount(int noticeNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new NoticeDAO().addReadCount(conn, noticeNo);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	// 공지사항 추가
	public int insertNotice(Notice notice) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new NoticeDAO().insertNotice(conn, notice);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	// 공지사항 수정
	public int updateNotice(Notice notice) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new NoticeDAO().updateNotice(conn, notice);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	// 공지사항 삭제
	public int deleteNotice(int noticeNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new NoticeDAO().deleteNotice(conn, noticeNo);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
}
