package user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import user.model.vo.Dog;
import user.model.vo.User;

public class UserDAO {
	public UserDAO() {}
	
	public User selectOneUser(Connection conn, String userId, String userPw) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM USERTBL WHERE USER_ID ='" + userId + "'AND USER_PW = '" + userPw + "'";
		User user = null;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				user = new User();
				user.setUserId(rset.getString("USER_ID"));
				user.setUserPw(rset.getString("USER_PW"));
				user.setUserNick(rset.getString("USER_NICK"));
				user.setUserName(rset.getString("USER_NAME"));
				user.setPhone(rset.getString("PHONE"));
				user.setEmail(rset.getString("EMAIL"));
				user.setUserBirth(rset.getString("USER_BIRTH"));
				user.setUserAddr(rset.getString("USER_ADDR"));
				user.setDogCheck(rset.getString("DOG_CHECK").charAt(0));
				user.setEnrollDate(rset.getDate("ENROLL_DATE"));
				user.setAdminCheck(rset.getString("ADMIN_CHECK").charAt(0));
				user.setUserPhoto(rset.getString("USER_PHOTO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return user;
	}
	
	public ArrayList<User> selectAllUserList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<User> list = null;
		String query = "SELECT * FROM USERTBL";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<User>();
			while(rset.next()) {
				User user = new User();
				user = new User();
				user.setUserId(rset.getString("USER_ID"));
				user.setUserPw(rset.getString("USER_PW"));
				user.setUserNick(rset.getString("USER_NICK"));
				user.setUserName(rset.getString("USER_NAME"));
				user.setPhone(rset.getString("PHONE"));
				user.setEmail(rset.getString("EMAIL"));
				user.setUserBirth(rset.getString("USER_BIRTH"));
				user.setUserAddr(rset.getString("USER_ADDR"));
				user.setDogCheck(rset.getString("DOG_CHECK").charAt(0));
				user.setEnrollDate(rset.getDate("ENROLL_DATE"));
				user.setAdminCheck(rset.getString("ADMIN_CHECK").charAt(0));
				user.setUserPhoto(rset.getString("USER_PHOTO"));
				list.add(user);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return list;
	}
	
	// 회원가입 (강아지 없음)
	public int insertUser(Connection conn, User user) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		char dogCheck = 0;
//		String query = "INSERT INTO USERTBL VALUES(?,?,?,?,?,?,?,?,?,SYSDATE,?,?)";
//		String query2 = "INSERT INTO USERTBL VALUES('user2', 'ASDFASDF', '이름', '닉네임', '01024243232', 'user2@naver.com', '001224', '서울시 종로구','N', SYSDATE, 'N', null);";
//		try {
//			
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, user.getUserId());
//			pstmt.setString(2, user.getUserPw());
//			pstmt.setString(3, user.getUserNick());
//			pstmt.setString(4, user.getUserName());
//			pstmt.setString(5, user.getPhone());
//			pstmt.setString(6, user.getEmail());
//			pstmt.setString(7, user.getUserAddr());
//			pstmt.setString(8, user.getUserBirth());
//			pstmt.setString(9, String.valueOf(user.getDogCheck()));
//			pstmt.setString(10, 'N' + "");
//			pstmt.setString(11, "NULL");
//			result = pstmt.executeUpdate();
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query2 = "INSERT INTO USERTBL VALUES(?,?,?,?,?,?,?,?,'N', SYSDATE, 'N', null)";
		try {
			pstmt = conn.prepareStatement(query2);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserNick());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getEmail());
			pstmt.setString(7, user.getUserBirth());
			pstmt.setString(8, user.getUserAddr());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	// 회원가입 (강아지 있음)
	public int insertDog(Connection conn, User user, Dog dog) {
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		int result = 0;
		char dogCheck = 0;
		String query1 = "INSERT INTO USERTBL VALUES(?,?,?,?,?,?,?,?,'Y', SYSDATE, 'N', null)";
		String query2 = "INSERT INTO DOG VALUES(SEQ_DOGCODE.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt1 = conn.prepareStatement(query1);
			pstmt1.setString(1, user.getUserId());
			pstmt1.setString(2, user.getUserPw());
			pstmt1.setString(3, user.getUserName());
			pstmt1.setString(4, user.getUserNick());
			pstmt1.setString(5, user.getPhone());
			pstmt1.setString(6, user.getEmail());
			pstmt1.setString(7, user.getUserBirth());
			pstmt1.setString(8, user.getUserAddr());
			
			pstmt2 = conn.prepareStatement(query2);
			pstmt2.setString(1, dog.getDogName());
			pstmt2.setString(2, dog.getDogBreed());
			pstmt2.setString(3, dog.getDogGender()+ "");
			pstmt2.setInt(4, dog.getDogAge());
			pstmt2.setFloat(5, dog.getDogWeight());
			pstmt2.setString(6, dog.getDogId());
			result = pstmt1.executeUpdate();
			result = pstmt2.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt1);
		}
		return result;
	}
	
 	
	public int deleteUser(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM USERTBL WHERE USER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
//	public int updateUser(Connection conn, User user) {
//		 PreparedStatement pstmt = null;
//	      int result = 0;
//	      String query = "UPDATE USERTBL SET USER_PW=?, USER_NICK=?, PHONE=?, EMAIL=?, DOG_CHECK=?, DOG_NAME=?, DOG_BREED=?, DOG_GENDER=?, DOG_AGE=?, DOG_WEIGHT=?  WHERE USER_ID=?";
//	      
//	      try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, user.getUserPw());
//			pstmt.setString(2, user.getUserNick());
//			pstmt.setString(3, user.getPhone());
//			pstmt.setString(4, user.getEmail());
//			pstmt.setString(5, user.getUserPw());
//			pstmt.setString(6, String.valueOf(user.getDogCheck()));
//			pstmt.setString(7, user.getDogName());
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	      return 0;
//	}
	
//	public User findUser(Connection conn, String userId) { // findUserId - findUserPwd 분리로 주석처리
//		return null;
//	}

	public User findUserId(Connection conn, String userName, String userEmail) {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM USERTBL  WHERE USER_NAME = ? AND EMAIL = ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			rset = pstmt.executeQuery();
			System.out.println(rset + "테스트");
			
			
			if(rset.next()) {
				user = new User();
				user.setUserId(rset.getString("USER_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return user;
	}

	public User findUserPwd(Connection conn, String userName, String userId, String userEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User user = null;
		String query = "SELECT * FROM USERTBL  WHERE USER_NAME = ? AND EMAIL = ? AND USER_ID = ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			pstmt.setString(3, userId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				user = new User();
				user.setUserPw(rset.getString("USER_PW"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return user;
	}

	
}
