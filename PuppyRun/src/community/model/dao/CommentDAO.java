package community.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import community.model.vo.Comment;

public class CommentDAO {
	public CommentDAO() {}
	
	// 모든 댓글 가져오는 메소드
	public ArrayList<Comment> selectAllCommunity(Connection conn, int comNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM COMMENTTBL WHERE COM_NO = ? ORDER BY COMMENT_NO ASC";
		ArrayList<Comment> cList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comNo);
			rset = pstmt.executeQuery();
			
			if(rset != null) {
				cList = new ArrayList<Comment>();
				
				while(rset.next()) {
					Comment comment = new Comment();
					comment.setCommentNo(rset.getInt("COMMENT_NO"));
					comment.setComNo(rset.getInt("COM_NO"));
					comment.setCommentId(rset.getString("COMMENT_ID"));
					comment.setCommentContents(rset.getString("COMMENT_CONTENTS"));
					comment.setCommentDate(rset.getDate("COMMENT_DATE"));
					comment.setUserNick(rset.getString("COMMENT_USER_NICK"));
					
					cList.add(comment);
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
	
	// 댓글 등록하는 메소드
	public int insertComment(Connection conn, Comment comment) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO COMMENTTBL VALUES(SEQ_COMMENTNO.NEXTVAL, ?, ?, ?, SYSDATE, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comment.getComNo());
			pstmt.setString(2, comment.getCommentId());
			pstmt.setString(3, comment.getCommentContents());
			pstmt.setString(4, comment.getUserNick());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	// 댓글 삭제하는 메소드
	public int deleteComment(Connection conn, int commentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM COMMENTTBL WHERE COMMENT_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	// 게시물 삭제하면 댓글도 삭제되도록하는 메소드
	public int deleteComment2(Connection conn, int communityNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM COMMENTTBL WHERE COM_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, communityNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	// 댓글 수 구해주는 메소드
	public ArrayList<int[]> addReadCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<int[]> cnt = null;
		String query = "SELECT A.COM_NO, (SELECT COUNT(*) FROM COMMENTTBL B WHERE B.COM_NO = A.COM_NO) as CNT FROM COMMUNITY A ORDER BY A.COM_NO DESC";
		
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
	
	// 아이디로 댓글 전부 가져오기
	public ArrayList<Comment> printUserComment(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM COMMENTTBL WHERE COMMENT_ID = ? ORDER BY COMMENT_NO DESC";
		ArrayList<Comment> cList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset != null) {
				cList = new ArrayList<Comment>();
				
				while(rset.next()) {
					Comment comment = new Comment();
					comment.setCommentNo(rset.getInt("COMMENT_NO"));
					comment.setComNo(rset.getInt("COM_NO"));
					comment.setCommentId(rset.getString("COMMENT_ID"));
					comment.setCommentContents(rset.getString("COMMENT_CONTENTS"));
					comment.setCommentDate(rset.getDate("COMMENT_DATE"));
					comment.setUserNick(rset.getString("COMMENT_USER_NICK"));
					
					cList.add(comment);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return cList;
	}

	public int deleteComment(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM COMMENTTBL WHERE COMMENT_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}
