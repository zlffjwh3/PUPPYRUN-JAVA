package matching.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import matching.model.vo.Matching;

public class MatchingDAO {
	
	public MatchingDAO() {}
	
	// 전체 게시글 목록 리스트
	public ArrayList<Matching> selectAllMatching(Connection conn, int currentPage) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY MAT_NO DESC) AS NUM, MAT_NO, MAT_ID, MAT_TITLE, MAT_CONTENT, MAT_ADDR, MAT_DATE, MAT_CHECK, MAT_PHOTO, USER_NICK FROM MATCHING) WHERE NUM BETWEEN ? AND ?";
		ArrayList<Matching> mList = null;
		
		// 한 페이지에 보여줄 게시물의 수
		int recordCountPage = 6;
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			if(rset != null) {
				mList = new ArrayList<Matching>();
				
				while(rset.next()) {
					Matching matching = new Matching();
					
					matching.setMatNo(rset.getInt("MAT_NO"));
					matching.setMatId(rset.getString("MAT_ID"));
					matching.setMatTitle(rset.getString("MAT_TITLE"));
					matching.setMatContent(rset.getString("MAT_CONTENT"));
					matching.setMatAddr(rset.getString("MAT_ADDR"));
					matching.setMatDate(rset.getDate("MAT_DATE"));
					matching.setMatCheck(rset.getString("MAT_CHECK").charAt(0));
					matching.setMatPhoto(rset.getString("MAT_PHOTO"));
					matching.setUserNick(rset.getNString("USER_NICK"));
					
					mList.add(matching);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return mList;
	}
	
	// 페이징
	public String getPageNavi(Connection conn, int currentPage) { // currentPage는 사용자가 페이지 클릭한 번호
		int recordTotalCount = totalCount(conn);
		int recordCountPerPage = 6;
		int pageTotalCount = 0;
		
		if((recordTotalCount % recordCountPerPage) > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		// 오류방지 코드
		if(currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
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
			sb.append("<a href='/matching/list?currentPage=" + (startNavi - 1) + "' id='page-prev'> < </a>");
		}
		for(int i=startNavi; i<=endNavi; i++) {
			sb.append("<a href='/matching/list?currentPage=" + i + "'>" + i + "</a>");
		}
		if(needNext) {
			sb.append("<a href='/matching/list?currentPage=" + (endNavi + 1) + "' id='page-next'> > </a>");
		}
		
		return sb.toString();
	}
	
	
	// 전체 게시물의 개수를 가져오는 메소드
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
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return recordTotalCount;
		
	}
	
	// 게시글 한개 보기
	public Matching printOneMatching(Connection conn, int matchingNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Matching matching = null;
		String query = "SELECT * FROM MATCHING WHERE MAT_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, matchingNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				matching = new Matching();
				matching.setMatNo(rset.getInt("MAT_NO"));
				matching.setMatId(rset.getString("MAT_ID"));
				matching.setMatTitle(rset.getString("MAT_TITLE"));
				matching.setMatContent(rset.getString("MAT_CONTENT"));
				matching.setMatAddr(rset.getString("MAT_ADDR"));
				matching.setMatDate(rset.getDate("MAT_DATE"));
				matching.setMatCheck(rset.getString("MAT_CHECK").charAt(0));
				matching.setMatPhoto(rset.getString("MAT_PHOTO"));
				matching.setUserNick(rset.getNString("USER_NICK"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return matching;
	}

	// 산책짝꿍 게시글 추가
	public int insertMatching(Connection conn, Matching matching) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO MATCHING VALUES(SEQ_MATNO.NEXTVAL,?,?,?,?,SYSDATE,'N',?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, matching.getMatId());
			pstmt.setString(2, matching.getMatTitle());
			pstmt.setString(3, matching.getMatContent());
			pstmt.setString(4, matching.getMatAddr());
			pstmt.setString(5, matching.getMatPhoto());
			pstmt.setString(6, matching.getUserNick());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);  
		}
		return result;
	}

	public int updateMatching(Connection conn, Matching matching) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE MATCHING SET MAT_ADDR=?, MAT_TITLE=?, MAT_CONTENT=?, MAT_PHOTO=? WHERE MAT_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, matching.getMatAddr());
			pstmt.setString(2, matching.getMatTitle());
			pstmt.setString(3, matching.getMatContent());
			pstmt.setString(4, matching.getMatPhoto());
			pstmt.setInt(5, matching.getMatNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int deleteMatching(Connection conn, int matNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM MATCHING WHERE MAT_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, matNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}


}
