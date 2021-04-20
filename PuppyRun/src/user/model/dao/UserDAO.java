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
	
	// 유저 한명 정보 가져오기
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
	
	// 유저 한명 강아지 정보 가져오기
	public Dog selectOneDog(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM DOG WHERE DOG_ID = ?";
		Dog dog = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				dog = new Dog();
				dog.setDogCode(rset.getInt("DOG_CODE"));
				dog.setDogName(rset.getString("DOG_NAME"));
				dog.setDogBreed(rset.getString("DOG_BREED"));
				dog.setDogGender(rset.getString("DOG_GENDER").charAt(0));
				dog.setDogAge(rset.getInt("DOG_AGE"));
				dog.setDogWeight(rset.getFloat("DOG_WEIGHT"));
				dog.setDogId(userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return dog;
	}
	
	// 유저 리스트 전부 가져오기
	public ArrayList<User> selectAllUserList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<User> list = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY ENROLL_DATE DESC) AS NUM, USER_ID, USER_PW, USER_NICK, USER_NAME, PHONE, EMAIL, USER_BIRTH, USER_ADDR, DOG_CHECK, ENROLL_DATE, ADMIN_CHECK, USER_PHOTO FROM USERTBL) WHERE NUM BETWEEN ? AND ?";
		
		int recordCountPage = 9;
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
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
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	// 회원 리스트 - 네비
	public String getPageNavi(Connection conn, int currentPage) {
		int recordTotalCount = totalCount(conn);
		int recordCountPerPage = 9;
		int pageTotalCount = 0;
		
		if((recordTotalCount % recordCountPerPage) > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		// 오류방지 코드
		if(currentPage < 1) {
			currentPage = 1;
		} else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int naviCountPerPage = 10;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		
		// 오류방지 코드
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		if(needPrev) {
			sb.append("<a href='/user/list?currentPage=" + (startNavi - 1) + "' id='page-prev'> < </a>");
		}
		for(int i=startNavi; i<=endNavi; i++) {
			sb.append("<a href='/user/list?currentPage=" + i + "'>" + i + "</a>");
		}
		if(needNext) {
			sb.append("<a href='/user/list?currentPage=" + (endNavi + 1) + "' id='page-next'> > </a>");
		}
		
		return sb.toString();
	}
	
	// 회원 리스트 - 총 숫자 계산
	public int totalCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM USERTBL";
		
		int recordTotalCount = 0;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if(rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return recordTotalCount;
	}
	
	// 회원 리스트 2 - 페이지 없는 버전
	public ArrayList<User> selectAllUserList2(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<User> list = null;
		String query = "SELECT * FROM USERTBL";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
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
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	// 회원가입 (강아지 없음)
	public int insertUser(Connection conn, User user) {
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
	
 	// 회원 탈퇴
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

	// 회원정보 수정 (강아지 없음)
	public int updateUser(Connection conn, User user) {
		// 반려견 있음 -> 없음
		// 현재 유저의 이전 정보를 받은 다음, 강아지 유무체크를 먼저 확인
		User beforeUser = selectOneUserIdOnly(conn, user.getUserId());
		
		int result = 0;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String query1 = "UPDATE USERTBL SET USER_PW = ?, PHONE = ?, EMAIL = ?, USER_BIRTH = ?, USER_ADDR = ? WHERE USER_ID= ?"; // 원래 없음
		String query2 = "UPDATE USERTBL SET USER_PW = ?, PHONE = ?, EMAIL = ?, USER_BIRTH = ?, USER_ADDR = ? DOG_CHECK='N' WHERE USER_ID= ?"; // 유->무
		String query3 = "delete dog where DOG_ID = ?;"; // 반려견 제거
		
		if(beforeUser.getDogCheck() == 'Y') { // 강아지가 있다가 없음
			try {
				pstmt2 = conn.prepareStatement(query2);
				pstmt2.setString(1, user.getUserPw());
				pstmt2.setString(2, user.getPhone());
				pstmt2.setString(3, user.getEmail());
				pstmt2.setString(4, user.getUserBirth());
				pstmt2.setString(5, user.getUserAddr());
				pstmt2.setNString(6, user.getUserId());
				
				pstmt3 = conn.prepareStatement(query3);
				pstmt3.setString(1, user.getUserId());
				result = pstmt2.executeUpdate();
				result = pstmt3.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt3);
			}
			
		} else if(beforeUser.getDogCheck() == 'N') { // 강아지 원래부터 없었음!
			try {
				pstmt1 = conn.prepareStatement(query1);
				pstmt1.setString(1, user.getUserPw());
				pstmt1.setString(2, user.getPhone());
				pstmt1.setString(3, user.getEmail());
				pstmt1.setString(4, user.getUserBirth());
				pstmt1.setString(5, user.getUserAddr());
				pstmt1.setNString(6, user.getUserId());
				result = pstmt1.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt1);
			}
			
		}
		
		
		return result;
	}

	// 회원가입 정보 수정하기 전 조회
	public User selectOneUserIdOnly(Connection conn, String userId) {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM USERTBL WHERE USER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
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
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return user;
	}
	
	// 강아지 있음 수정!!
	public int updateDog(Connection conn, User user, Dog dog) {
		int result = 0;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String query1 = "UPDATE USERTBL SET USER_PW = ?, PHONE = ?, EMAIL = ?, USER_BIRTH = ?, USER_ADDR = ?, DOG_CHECK = ? WHERE USER_ID= ?";
		String query2 = "UPDATE DOG SET DOG_NAME = ?, DOG_BREED = ?, DOG_GENDER = ?, DOG_AGE = ?, DOG_WEIGHT = ? WHERE DOG_ID= ?";
		
		try {
			pstmt1 = conn.prepareStatement(query1);
			pstmt1.setString(1, user.getUserPw());
			pstmt1.setString(2, user.getPhone());
			pstmt1.setString(3, user.getEmail());
			pstmt1.setString(4, user.getUserBirth());
			pstmt1.setString(5, user.getUserAddr());
			pstmt1.setString(6, "Y");
			pstmt1.setNString(7, user.getUserId());
			
			
			pstmt2 = conn.prepareStatement(query2);
			pstmt2.setString(1, dog.getDogName());
			pstmt2.setString(2, dog.getDogBreed());
			pstmt2.setString(3, String.valueOf(dog.getDogGender()));
			pstmt2.setInt(4, dog.getDogAge());
			pstmt2.setFloat(5, dog.getDogWeight());
			pstmt2.setString(6, user.getUserId());
			
			result = pstmt1.executeUpdate();
			System.out.println("제대로 오나? : " + result);
			result = pstmt2.executeUpdate();
			System.out.println("제대로 오나? 2 : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt1);
		}
		return result;
	}


	public ArrayList<User> selectSearchJUserList(Connection conn, String search, int currentPage) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT USER_ID=?, USER_NAME=?, USER_NICK=?, PHONE=?, EMAIL=?, USER_BIRTH=?, USER_ADDR=?, DOG_CHECK=?, ENROLL_DATE=? FROM USERTBL WHERE USER_ID LIKE ?";
		ArrayList<User> userList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + search + "%");
			userList = new ArrayList<User>();
			while(rset.next()) {
				User user = new User();
				user.setUserId(rset.getString("USERID"));
				user.setUserName(rset.getString("USERNAME"));
				user.setUserNick(rset.getString("USERNICK"));
				user.setPhone(rset.getString("PHONE"));
				user.setEmail(rset.getString("EMAIL"));
				user.setUserBirth(rset.getString("USERBIRTH"));
				user.setUserAddr(rset.getString("USERADDR"));
				user.setDogCheck(rset.getString("DOGCHECK").charAt(0));
				user.setEnrollDate(rset.getDate("ENROLLDATE"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return userList;
	}

}
