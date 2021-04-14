package user.model.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import user.model.dao.UserDAO;
import user.model.vo.Dog;
import user.model.vo.User;

public class UserService {
	
	private JDBCTemplate factory;
	
	public UserService() {
		factory = JDBCTemplate.getConnection();
	}
	
	
	public User selectOneUser(String userId, String userPw) {
		User user = null;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			user = new UserDAO().selectOneUser(conn, userId, userPw);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return user;
		
	}
	
	public ArrayList<User> selectAllUserList(String userId) {
		ArrayList<User> list = null;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			list = new UserDAO().selectAllUserList(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return list;
	}
	
	// 회원가입 (강아지 없음)
	public int insertUser(User user) {
		int result = 0; // 이 result는 DAO에서 나오는 결과 값을 받아서 servlet으로 보내기 위해 사용
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			result = new UserDAO().insertUser(conn, user);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	// 회원가입 (강아지 있음)
	public int insertDog(Dog dog) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new UserDAO().insertDog(conn, dog);
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
	
	public int deleteUser(String userId) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = factory.createConnection(); // 연결 생성해서 DAO에 넘겨주는 역할
			result = new UserDAO().deleteUser(conn, userId);
			
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
	
//	public int updateUser(User user) {
//		int result = 0;
//		Connection conn = null;
//		
//		try {
//			conn = factory.createConnection();
//			result = new UserDAO().updateUser(conn, user);
//			System.out.println("결과값 : " + result);
//			if(result > 0) {
//				JDBCTemplate.commit(conn);
//			} else {
//				JDBCTemplate.rollback(conn);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//	         JDBCTemplate.close(conn);
//	      }
//		return result;
//	}
	
//	public User findUser(String userId) { // findUserId / findUserPwd 분리하는게 나을것같아서 주석
//		return null;
//	}


	// 유저ID찾기
	public User findUserId(String userName, String userEmail) {
		User user = null;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			user = new UserDAO().findUserId(conn, userName, userEmail);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return user;
	}

	
}
