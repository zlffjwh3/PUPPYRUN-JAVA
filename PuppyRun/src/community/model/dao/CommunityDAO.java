package community.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import community.model.vo.Community;

public class CommunityDAO {
	public CommunityDAO() {}
	
	// 전체 게시글 목록 리스트
	public ArrayList<Community> selectAllCommunity(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY COM_NO DESC) AS NUM, COM_NO, COM_ID, TAG_NO, COM_TITLE, COM_CONTENT, COM_VIEW, COM_DATE, COM_PHOTO, LIKE_COUNT, USER_NICK FROM COMMUNITY) WHERE NUM BETWEEN ? AND ?";
		ArrayList<Community> cList = null;
		
		// 한 페이지에 보여줄 게시물의 수
		int recordCountPage = 10;
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		System.out.println("DAO완료");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			if(rset != null) {
				cList = new ArrayList<Community>();
				
				while(rset.next()) {
					Community community = new Community();
					community.setComNo(rset.getInt("COM_NO"));
					community.setComId(rset.getString("COM_ID"));
					community.setTagNo(rset.getInt("TAG_NO"));
					community.setComTitle(rset.getString("COM_TITLE"));
					community.setComContent(rset.getString("COM_CONTENT"));
					community.setComview(rset.getInt("COM_VIEW"));
					community.setComDate(rset.getDate("COM_DATE"));
					community.setComPhoto(rset.getString("COM_PHOTO"));
					community.setLikeCount(rset.getInt("LIKE_COUNT"));
					community.setUserNick(rset.getString("USER_NICK"));
					
					cList.add(community);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return cList;
	}
	
	// 페이지 네비게이션
	public String getPageNavi(Connection conn, int currentPage) {
		int recordTotalCount = totalCount(conn);
		int recordCountPerPage = 10;
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
			sb.append("<a href='/community/list?currentPage=" + (startNavi - 1) + "' id='page-prev'> < </a>");
		}
		for(int i=startNavi; i<=endNavi; i++) {
			sb.append("<a href='/community/list?currentPage=" + i + "'>" + i + "</a>");
		}
		if(needNext) {
			sb.append("<a href='/community/list?currentPage=" + (endNavi + 1) + "' id='page-next'> > </a>");
		}
		
		return sb.toString();
	}
	
		// 총 게시물 수 계산
		public int totalCount(Connection conn) {
			Statement stmt = null;
			ResultSet rset = null;
			String query = "SELECT COUNT(*) AS TOTALCOUNT FROM COMMUNITY";
			
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
		
	public ArrayList<Community> selectTagList(Connection conn, int currentPage) {
		return null;
	}
	
	// 특정 게시물 보기
	public Community selectOneCommunity(Connection conn, int comNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM COMMUNITY WHERE COM_NO = ?";
		Community community = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				community = new Community();
				community.setComNo(rset.getInt("COM_NO"));
				community.setComId(rset.getString("COM_ID"));
				community.setTagNo(rset.getInt("TAG_NO"));
				community.setComTitle(rset.getString("COM_TITLE"));
				community.setComContent(rset.getString("COM_CONTENT"));
				community.setComview(rset.getInt("COM_VIEW"));
				community.setComDate(rset.getDate("COM_DATE"));
				community.setComPhoto(rset.getString("COM_PHOTO"));
				community.setLikeCount(rset.getInt("LIKE_COUNT"));
				community.setUserNick(rset.getString("USER_NICK"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return community;
	}
	
	// 글 등록
	public int insertCommunity(Connection conn, Community community) {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO COMMUNITY VALUES(SEQ_COMNO.NEXTVAL, ?, ?, ?, ?, 0, SYSDATE, ?, 0, ?)";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, community.getComId());
			pstmt.setInt(2, community.getTagNo());
			pstmt.setString(3, community.getComTitle());
			pstmt.setString(4, community.getComContent());
			pstmt.setString(5, community.getComPhoto());
			pstmt.setString(6, community.getUserNick());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int updateCommunity(Connection conn, Community community) {
		return 0;
	}
	
	public int deleteNotice(Connection conn, int comNo) {
		return 0;
	}
	
	public int addReadCount(Connection conn, int comNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE COMMUNITY SET COM_VIEW = COM_VIEW + 1 WHERE COM_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<Community> selectSearchList(Connection conn, String search, int currentPage) {
		return null;
	}

	public String selectPageNavi(Connection conn, String search, int currentPage) {
		searchTotalCount(conn, search);
		return null;
	}

	public int searchTotalCount(Connection conn, String search) {
		return 0;
	}
}
