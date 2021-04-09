package community.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import community.model.dao.LikeDAO;
import community.model.vo.Community;

public class LikeService {
	
	private JDBCTemplate factory;
	
	public LikeService() {
		factory = JDBCTemplate.getConnection();
	}
	
	// 좋아요 상태 받아오는 메소드
	public char likeStatus(int comNo, String userId) {
		Connection conn = null;
		char origin = 0;
		
		try {
			conn = factory.createConnection();
			origin = new LikeDAO().likeStatus(conn, comNo, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return origin;
	}
	
	// 좋아요 버튼 누르면 상태 바꿔주는 메소드
	public int changeStatus(int comNo, String userId, char after) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new LikeDAO().changeStatus(conn, comNo, userId, after);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 좋아요한 게시글 리스트
	public ArrayList<Community> likeList(String userId) {
		Connection conn = null;
		ArrayList<Community> userLikes = null;
		
		try {
			conn = factory.createConnection();
			userLikes = new LikeDAO().likeList(conn, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userLikes;
	}
}
