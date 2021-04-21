package community.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import community.model.dao.CommentDAO;
import community.model.dao.LikeDAO;
import community.model.vo.Community;
import community.model.vo.Like;

public class LikeService {
	
	private JDBCTemplate factory;
	
	public LikeService() {
		factory = JDBCTemplate.getConnection();
	}
	
	// 좋아요 상태 받아오는 메소드
	public Like likeStatus(int comNo, String userId) {
		Connection conn = null;
		Like like = null;
		
		try {
			conn = factory.createConnection();
			like = new LikeDAO().likeStatus(conn, comNo, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.commit(conn);
		}
		
		return like;
	}
	
	// 좋아요 등록
	public int registerStatus(int comNo, String userId, String likeStatus) {
		Connection conn = null;
		int eroll = 0;
		
		try {
			conn = factory.createConnection();
			eroll = new LikeDAO().insertStatus(conn, comNo, userId, likeStatus);
			
			if(eroll > 0) {
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
		
		return eroll;
	}
	
	// 좋아요 취소
	public int cancelStatus(int comNo, String userId) {
		Connection conn = null;
		int eroll = 0;
		
		try {
			conn = factory.createConnection();
			eroll = new LikeDAO().deleteStatus(conn, comNo, userId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return eroll;
	}
	
	// 게시물 내에서 좋아요 수 출력해주는 메소드
	public int countLike(int comNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new LikeDAO().countStatus(conn, comNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	// 게시물 리스트에서 좋아요 수 출력해주는 메소드
	public ArrayList<int[]> cnt() {
		Connection conn = null;
		ArrayList<int[]> cnt = null;
		
		try {
			conn = factory.createConnection();
			cnt = new LikeDAO().addReadCount(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return cnt;
	}
}
