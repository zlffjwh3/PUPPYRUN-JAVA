package community.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import community.model.dao.CommentDAO;
import community.model.vo.Comment;

public class CommentService {
	private JDBCTemplate factory;
	
	public CommentService() {
		factory = JDBCTemplate.getConnection();
	}
	
	public ArrayList<Comment> selectAllComment(int comNo) {
		Connection conn = null;
		ArrayList<Comment> cList = null;
		
		try {
			conn = factory.createConnection();
			cList = new CommentDAO().selectAllCommunity(conn, comNo);
			
			if(cList != null) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return cList;
	}
	
	public Comment selectComment(int commentNo) {
		return null;
	}
	
	public int insertCommunity(Comment comment) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new CommentDAO().insertComment(conn, comment);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public int updateCommunity(Comment comment) {
		return 0;
	}
	
	public int deleteCommunity(int commentNo) {
		return 0;
	}
}
