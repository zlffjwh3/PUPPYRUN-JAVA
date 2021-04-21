package matching.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import matching.model.vo.MatchingChat;

public class MatchingChatDAO {
	
	// 메세지 입력
	public int insertMsg(Connection conn, MatchingChat matChat) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO MESSAGE VALUES(SEQ_MSGNO.NEXTVAL, ?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, matChat.getMatNo());
			pstmt.setString(2, matChat.getRcvId());
			pstmt.setString(3, matChat.getSendId());
			pstmt.setString(4, matChat.getContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}

	public ArrayList<MatchingChat> selectAllMsg(Connection conn, int matchingNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MESSAGE WHERE MAT_NO=? ORDER BY MSG_NO ASC";
		ArrayList<MatchingChat> matChatList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, matchingNo);
			rset = pstmt.executeQuery();
			if(rset != null) {
				matChatList = new ArrayList<MatchingChat>();
				while(rset.next()) {
					MatchingChat matChat = new MatchingChat();
					matChat.setMatNo(rset.getInt("MAT_NO"));
					matChat.setMsgNo(rset.getInt("MSG_NO"));
					matChat.setRcvId(rset.getString("RCV_ID"));
					matChat.setSendId(rset.getString("SEND_ID"));
					matChat.setContent(rset.getString("MSG_CONTENT"));
					matChatList.add(matChat);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return matChatList;
		
	}

	// 아이디별로 채팅 리스트 전부 보내주기
	public ArrayList<MatchingChat> viewList(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MESSAGE WHERE RCV_ID=? ORDER BY MSG_NO DESC";
		ArrayList<MatchingChat> matChatList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if(rset != null) {
				matChatList = new ArrayList<MatchingChat>();
				while(rset.next()) {
					MatchingChat matChat = new MatchingChat();
					matChat.setMatNo(rset.getInt("MAT_NO"));
					matChat.setMsgNo(rset.getInt("MSG_NO"));
					matChat.setRcvId(rset.getString("RCV_ID"));
					matChat.setSendId(rset.getString("SEND_ID"));
					matChat.setContent(rset.getString("MSG_CONTENT"));
					matChatList.add(matChat);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return matChatList;
	}

	// 메세지 삭제
	public int deleteMsg(Connection conn, int matNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM MESSAGE WHERE MAT_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, matNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
