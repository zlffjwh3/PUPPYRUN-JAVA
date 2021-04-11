package user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
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
				user.setUserBirth(rset.getDate("USER_BIRTH"));
				user.setUserAddr(rset.getString("USER_ADDR"));
				user.setDogCheck(rset.getString("DOG_CHECK").charAt(0));
				user.setEnrollDate(rset.getDate("ENROLL_DATE"));
				user.setAdminCheck(rset.getString("ADMIN_CHECK").charAt(0));
				user.setUserPhoto(rset.getInt("USER_PHOTO"));
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
				user.setUserBirth(rset.getDate("USER_BIRTH"));
				user.setUserAddr(rset.getString("USER_ADDR"));
				user.setDogCheck(rset.getString("DOG_CHECK").charAt(0));
				user.setEnrollDate(rset.getDate("ENROLL_DATE"));
				user.setAdminCheck(rset.getString("ADMIN_CHECK").charAt(0));
				user.setUserPhoto(rset.getInt("USER_PHOTO"));
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
	
	// 회원가입 (강아지 없음에 클릭 시 )
	public int insertUser(Connection conn, User user) {
		PreparedStatement pstmt = null;
		int result = 0;
		char dogCheck = 0;
		String query = "INSERT INTO USERTBL VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserNick());
			pstmt.setString(4, user.getUserName());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getEmail());
			pstmt.setString(7, user.getUserAddr());
			pstmt.setDate(8, user.getUserBirth());
			pstmt.setString(9, String.valueOf(user.getDogCheck()));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
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
	
	public User findUser(Connection conn, String userId) {
		return null;
	}
}
