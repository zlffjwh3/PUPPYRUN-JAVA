package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.vo.Notice;

public class NoticeDAO {
	public NoticeDAO() {}
	
	// 공지사항 리스트
	public ArrayList<Notice> selectAllNotice(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY NOTICE_NO DESC) AS NUM, NOTICE_NO, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_VIEW, NOTICE_DATE, NOTICE_PHOTO FROM NOTICE) WHERE NUM BETWEEN ? AND ?";
		ArrayList<Notice> nList = null;
		
		int recordCountPage = 9;
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			if(rset != null) {
				nList = new ArrayList<Notice>();
				
				while(rset.next()) {
					Notice notice = new Notice();
					
					notice.setNoticeNo(rset.getInt("NOTICE_NO"));
					notice.setNoticeTitle(rset.getString("NOTICE_TITLE"));
					notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
					notice.setNoticeView(rset.getInt("NOTICE_VIEW"));
					notice.setNoticeDate(rset.getDate("NOTICE_DATE"));
					notice.setNoticePhoto(rset.getString("NOTICE_PHOTO"));
					
					nList.add(notice);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return nList;
	}
	
	// 공지사항 리스트 - 페이지 네비게이션
	public String getPageNavi(Connection conn, int currentPage) {
		int recordTotalCount = totalCount(conn);
		int recordCountPerPage = 9;
		int pageTotalCount = 0;
		
		if((recordTotalCount % recordCountPerPage) > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		// 오류방지 코드
		if(currentPage < 1) {
			currentPage = 1;
		} else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int naviCountPerPage = 10;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		
		// 오류방지 코드
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		if(needPrev) {
			sb.append("<a href='/notice/list?currentPage=" + (startNavi - 1) + "' id='page-prev'> < </a>");
		}
		for(int i=startNavi; i<=endNavi; i++) {
			sb.append("<a href='/notice/list?currentPage=" + i + "'>" + i + "</a>");
		}
		if(needNext) {
			sb.append("<a href='/notice/list?currentPage=" + (endNavi + 1) + "' id='page-next'> > </a>");
		}
		
		return sb.toString();
	}
	
	// 공지사항 리스트 - 총 게시물 수 계산
	public int totalCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM NOTICE";
		
		int recordTotalCount = 0;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if(rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return recordTotalCount;
	}
	
	
	// 공지사항 조회
	public Notice selectOneNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null;
		String query = "SELECT * FROM NOTICE WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				notice = new Notice();
				notice.setNoticeNo(noticeNo);
				notice.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				notice.setNoticeView(rset.getInt("NOTICE_VIEW"));
				notice.setNoticeDate(rset.getDate("NOTICE_DATE"));
				notice.setNoticePhoto(rset.getString("NOTICE_PHOTO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return notice;
	}
	
	// 공지사항 조회 - 조회수 추가
	public int addReadCount(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE NOTICE SET NOTICE_VIEW = NOTICE_VIEW + 1 WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	// 공지사항 추가
	public int insertNotice(Connection conn, Notice notice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO NOTICE VALUES(SEQ_NOTICENO.NEXTVAL, ?, ?, 0, SYSDATE, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setString(3, notice.getNoticePhoto());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	// 공지사항 수정
	public int updateNotice(Connection conn, Notice notice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE NOTICE SET NOTICE_TITLE=?, NOTICE_CONTENT=?, NOTICE_PHOTO=? WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setString(3, notice.getNoticePhoto());
			pstmt.setInt(4, notice.getNoticeNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	// 공지사항 삭제
	public int deleteNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM NOTICE WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Notice> selectSearchNoticeList(Connection conn, String noticeSearch, String contentChoice) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Notice> nList = null;
		String query = "SELECT NOTICE_NO, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_VIEW, NOTICE_DATE FROM NOTICE WHERE " + contentChoice + " LIKE '%" + noticeSearch + "%' ORDER BY NOTICE_DATE DESC"; 
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset != null) {
				nList = new ArrayList<Notice>();
				
				while(rset.next()) {
					Notice notice = new Notice();
					notice.setNoticeNo(rset.getInt("NOTICE_NO"));
					notice.setNoticeTitle(rset.getString("NOTICE_TITLE"));
					notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
					notice.setNoticeView(rset.getInt("NOTICE_VIEW"));
					notice.setNoticeDate(rset.getDate("NOTICE_DATE"));
					nList.add(notice);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return nList;
	}
}
