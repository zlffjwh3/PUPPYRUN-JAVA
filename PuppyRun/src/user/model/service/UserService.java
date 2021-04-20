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
import user.model.vo.UserPage;

public class UserService {
	
	private JDBCTemplate factory;
	
	public UserService() {
		factory = JDBCTemplate.getConnection();
	}
	
	// 유저 한명 정보 가져오기
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
	
	// 유저 한명 강아지 정보 가져오기
	public Dog selectOneDog(String userId) {
		Dog dog = null;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			dog = new UserDAO().selectOneDog(conn, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return dog;
	}
	
	// 유저 전체 정보 가져오기
	public UserPage selectAllUserList(int currentPage) {
		UserPage up = null;
		Connection conn = null;
		
		try {
			up = new UserPage();
			conn = factory.createConnection();
			up.setuList(new UserDAO().selectAllUserList(conn, currentPage));
			up.setPageNavi(new UserDAO().getPageNavi(conn, currentPage));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return up;
	}
	
	// 유저 전체 -2
	public ArrayList<User> selectAllUserList2() {
		ArrayList<User> uList = null;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			uList = new UserDAO().selectAllUserList2(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return uList;
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
	
	public int insertDog(User user, Dog dog) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new UserDAO().insertDog(conn, user, dog);
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
	
	// 회원 탈퇴
	public int deleteUser(String userId) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
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
	

	// 유저ID찾기
	public User findUserId(String userName, String userEmail) {
		User user = null;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			user = new UserDAO().findUserId(conn, userName, userEmail);
			System.out.println("에러 : " + user);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return user;
	}

	// 유저Pwd찾기
	public User finduserPwd(String userName, String userId, String userEmail) {
		User user = null;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			user = new UserDAO().findUserPwd(conn, userName, userId, userEmail);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return user;
	}
	
	// 회원정보 수정할 때 사용
	public User selectOneUserIdOnly(String userId) {
		User user = null;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			user = new UserDAO().selectOneUserIdOnly(conn, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return user;
	}
	// 회원정보수정
	public int updateUser(User user) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			result = new UserDAO().updateUser(conn, user);
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
	
	public int updateDog(Dog dog) { 
		int result = 0;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			result = new UserDAO().updateDog(conn, dog);
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


	public UserPage selectSearchUserList(String search, int currentPage) {
		Connection conn = null;
		UserPage userPage = new UserPage();
		
		try {
			conn = factory.createConnection();
			userPage.setuList(new UserDAO().selectSearchJUserList(conn, search, currentPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return userPage;
	}



	
}
