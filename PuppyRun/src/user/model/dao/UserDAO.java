package user.model.dao;

import java.sql.Connection;
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
	
	public int insertUser(Connection conn, User user) {
		return 0;
	}
	
	public int deleteUser(Connection conn, String userId) {
		return 0;
	}
	
	public int updateUser(Connection conn, User user) {
		return 0;
	}
	
	public User findUser(Connection conn, String userId) {
		return null;
	}
}
