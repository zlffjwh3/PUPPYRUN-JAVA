package user.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import user.model.vo.User;

public class UserDAO {
	public UserDAO() {}
	
	public User loginCheck(Connection conn, String userId, String userPw) {
		return null;
	}
	
	public User selectOneUser(Connection conn, String userId) {
		return null;
	}
	
	public ArrayList<User> selectAllUserList(Connection conn, String userId) {
		return null;
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
