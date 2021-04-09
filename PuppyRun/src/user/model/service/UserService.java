package user.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import user.model.vo.User;

public class UserService {
	
	private JDBCTemplate factory;
	
	public UserService() {
		factory = JDBCTemplate.getConnection();
	}
	
	public User loginCheck(String userId, String userPw) {
		return null;
	}
	
	public User selectOneUser(String userId) {
		return null;
	}
	
	public ArrayList<User> selectAllUserList(String userId) {
		return null;
	}
	
	public int insertUser(User user) {
		return 0;
	}
	
	public int deleteUser(String userId) {
		return 0;
	}
	
	public int updateUser(User user) {
		return 0;
	}
	
	public User findUser(String userId) {
		return null;
	}
}
