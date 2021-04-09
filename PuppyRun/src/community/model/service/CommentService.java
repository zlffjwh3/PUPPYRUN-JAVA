package community.model.service;

import java.util.ArrayList;

import common.JDBCTemplate;
import community.model.vo.Comment;

public class CommentService {
	private JDBCTemplate factory;
	
	public CommentService() {
		factory = JDBCTemplate.getConnection();
	}
	
	public ArrayList<Comment> selectAllComment(int currentPage) {
		return null;
	}
	
	public Comment selectOneComment(int commentNo) {
		return null;
	}
	
	public int insertCommunity(Comment comment) {
		return 0;
	}
	
	public int updateCommunity(Comment comment) {
		return 0;
	}
	
	public int deleteCommunity(int commentNo) {
		return 0;
	}
}
