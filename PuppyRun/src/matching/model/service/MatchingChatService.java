package matching.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import matching.model.dao.MatchingChatDAO;
import matching.model.dao.MatchingDAO;
import matching.model.vo.MatchingChat;

public class MatchingChatService {
	
	private JDBCTemplate factory;
	
	public MatchingChatService() {
		factory = JDBCTemplate.getConnection();
	}
	
	// 메세지 등록
		public int sendMsg(MatchingChat matChat) {
			Connection conn = null;
			int result = 0;
			try {
				conn = factory.createConnection ();
				result = new MatchingChatDAO().insertMsg(conn, matChat);
				if(result > 0) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(conn);
			}
			return result;
			
		}

		// 메세지 보기
		public ArrayList<MatchingChat> viewMsg(int matchingNo) {
			Connection conn = null;
			ArrayList<MatchingChat> matChat = null;
			try {
				conn = factory.createConnection ();
				matChat = new MatchingChatDAO().selectAllMsg(conn, matchingNo);
				if(matChat != null) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(conn);
			}
			return matChat;
		}
		
		// 메세지 삭제
		public int deleteMsg(int matNo) {
			Connection conn = null;
			int result = 0;
			try {
				conn = factory.createConnection ();
				result = new MatchingChatDAO().deleteMsg(conn, matNo);
				if(result > 0) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(conn);
			}
			return result;
			
		}
		
		// 아이디별로 채팅 리스트 가져오기
		public ArrayList<MatchingChat> viewList(String userId) {
			Connection conn = null;
			ArrayList<MatchingChat> matChat = null;
			
			try {
				conn = factory.createConnection();
				matChat = new MatchingChatDAO().viewList(conn, userId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return matChat;
		}
}
