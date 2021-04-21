package community.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import community.model.vo.Community;
import community.model.vo.Like;

public class LikeDAO {
	public LikeDAO() {}

	// 특정 유저의 좋아요 정보 가져오는 메소드
	public Like likeStatus(Connection conn, int comNo, String userId) {
		// select해서 좋아요 상태 가져오기
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Like like = new Like();
		String query = "SELECT * FROM LIKETBL WHERE COM_NO = ? AND LIKE_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comNo);
			pstmt.setString(2, userId);
			rset = pstmt.executeQuery();
			
			if(rset != null) {
				while(rset.next()) {
					like.setComNo(rset.getInt("COM_NO"));
					like.setLikeId(rset.getString("LIKE_ID"));
					like.setLikeStatus(rset.getString("LIKE_STATUS"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return like;
	}

	// 좋아요 등록
	public int insertStatus(Connection conn, int comNo, String userId, String after) {
		PreparedStatement pstmt = null;
		int eroll = 0;
		String query = "INSERT INTO LIKETBL VALUES(?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comNo);
			pstmt.setString(2, userId);
			pstmt.setString(3, after);
			eroll = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return eroll;
	}

	// 좋아요 상태 변경(좋아요 취소)
	public int deleteStatus(Connection conn, int comNo, String userId) {
		PreparedStatement pstmt = null;
		int eroll = 0;
		String query = "DELETE FROM LIKETBL WHERE COM_NO = ? AND LIKE_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comNo);
			pstmt.setString(2, userId);
			eroll = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return eroll;
	}

	// 게시물 내에서 좋아요 총 갯수 보여주는 메소드
	public int countStatus(Connection conn, int comNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		String query = "SELECT COUNT(*) as COUNT FROM LIKETBL WHERE COM_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comNo);
			rset = pstmt.executeQuery();
			
			if(rset != null) {
				while(rset.next()) {
					count = rset.getInt("COUNT");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		return count;
	}
	
	// 커뮤니티 리스트에서 좋아요 갯수 보여주는 메소드
	public ArrayList<int[]> addReadCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<int[]> cnt = null;
		String query = "SELECT A.COM_NO, (SELECT COUNT(*) FROM LIKETBL B WHERE COM_NO = A.COM_NO) as CNT FROM COMMUNITY A ORDER BY A.COM_NO DESC";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if(rset != null) {
				cnt = new ArrayList<int[]>();
				
				while(rset.next()) {
					int[] result = new int[2];
					result[0] = rset.getInt("COM_NO");
					result[1] = rset.getInt("CNT");
					
					cnt.add(result);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return cnt;
	}
	
	// 아이디별로 좋아요한거 가져오는 메소드
	public ArrayList<Like> printUserLikes(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM LIKETBL WHERE LIKE_ID = ? AND LIKE_STATUS = 'Y'";
		ArrayList<Like> lList = new ArrayList<Like>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Like like = new Like();
				like.setComNo(rset.getInt("COM_NO"));
				like.setLikeId(rset.getString("LIKE_ID"));
				like.setLikeStatus(rset.getString("LIKE_STATUS"));
				lList.add(like);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return lList;
	}
}
