package community.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import community.model.vo.Comment;

public class CommentDAO {
	public CommentDAO() {}
	
	public ArrayList<Comment> selectAllCommunity(Connection conn, int currentPage) {
		return null;
	}
	
	public int totalCount(Connection conn) {
		return 0;
	}
	
	public Comment selectOneCommunity(Connection conn, int commentNo) {
		return null;
	}
	
	public int insertComment(Connection conn, Comment comment) {
		return 0;
	}
	
	public int updateComment(Connection conn, Comment comment) {
		return 0;
	}
	
	public int deleteComment(Connection conn, int commentNo) {
		return 0;
	}
	
	public void addReadCount(Connection conn, int commentNo) {}
}
