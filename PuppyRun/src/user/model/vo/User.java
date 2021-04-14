package user.model.vo;

import java.sql.Date;
import java.util.ArrayList;

public class User {
	private String userId;
	private String userPw;
	private String userNick;
	private String userName;
	private String phone;
	private String email;
	private String userBirth;
	private String userAddr;
	private Date enrollDate;
	private char adminCheck;
	private char dogCheck;
	private String userPhoto;
	
	public User() {}

	// getter / setter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public char getAdminCheck() {
		return adminCheck;
	}

	public void setAdminCheck(char adminCheck) {
		this.adminCheck = adminCheck;
	}

	public char getDogCheck() {
		return dogCheck;
	}

	public void setDogCheck(char dogCheck) {
		this.dogCheck = dogCheck;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPw=" + userPw + ", userNick=" + userNick + ", userName=" + userName
				+ ", phone=" + phone + ", email=" + email + ", userBirth=" + userBirth + ", userAddr=" + userAddr
				+ ", enrollDate=" + enrollDate + ", adminCheck=" + adminCheck + ", dogCheck=" + dogCheck
				+ ", userPhoto=" + userPhoto + "]";
	}

	
}
