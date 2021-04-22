package petdiary.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import petdiary.model.vo.PetDiary;

public class PetDiaryDAO {
	public PetDiaryDAO() {}
	
	public ArrayList<PetDiary> selectAllDiary(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM DIARY WHERE DIARY_ID = ?";
		ArrayList<PetDiary> pList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset != null) {
				pList = new ArrayList<PetDiary>();
				
				while(rset.next()) {
					PetDiary diary = new PetDiary();
					
					diary.setDiaryNo(rset.getInt("DIARY_NO"));
					diary.setDiaryTitle(rset.getString("DIARY_TITLE"));
					diary.setDiaryContent(rset.getString("DIARY_CONTENT"));
					diary.setDiaryId(rset.getString("DIARY_ID"));
					diary.setDiaryDate(rset.getString("DIARY_DATE"));
					diary.setDiaryPhoto(rset.getString("DIARY_PHOTO"));
					diary.setDiaryDis(rset.getInt("DIARY_DIS"));
					diary.setDiaryTime(rset.getInt("DIARY_TIME"));
					
					pList.add(diary);
				} 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return pList;
	}
	
	public PetDiary selectOneDiary(Connection conn, String diaryDate, String diaryId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM DIARY WHERE DIARY_ID = ? AND TO_CHAR(DIARY_DATE, 'YY/MM/DD') = ?";
		PetDiary petDiary = new PetDiary();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, diaryId); 
			pstmt.setString(2, diaryDate); 
			rset = pstmt.executeQuery();
			if(rset.next()) {
				petDiary.setDiaryNo(rset.getInt("DIARY_NO"));
				petDiary.setDiaryTitle(rset.getString("DIARY_TITLE"));
				petDiary.setDiaryContent(rset.getString("DIARY_CONTENT"));
				petDiary.setDiaryId(rset.getString("DIARY_ID"));
				petDiary.setDiaryDate(rset.getString("DIARY_DATE"));
				petDiary.setDiaryPhoto(rset.getString("DIARY_PHOTO"));
				petDiary.setDiaryDis(rset.getInt("DIARY_DIS"));
				petDiary.setDiaryTime(rset.getInt("DIARY_TIME"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return petDiary;
	}
	
	public int insertDiary(Connection conn, PetDiary petDiary) {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO DIARY VALUES(SEQ_DIARYNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		int result = 0;													
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, petDiary.getDiaryTitle());
			pstmt.setString(2, petDiary.getDiaryContent());
			pstmt.setString(3, petDiary.getDiaryId());
			pstmt.setString(4, petDiary.getDiaryDate());
			pstmt.setString(5, petDiary.getDiaryPhoto());
			pstmt.setInt(6, petDiary.getDiaryDis());
			pstmt.setInt(7, petDiary.getDiaryTime());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int updateDiary(Connection conn, PetDiary petDiary) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE DIARY SET DIARY_TITLE=?, DIARY_CONTENT=?, DIARY_PHOTO=?, DIARY_DIS=?, DIARY_TIME=? WHERE DIARY_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, petDiary.getDiaryTitle());
			pstmt.setString(2, petDiary.getDiaryContent());
			pstmt.setString(3, petDiary.getDiaryPhoto());
			pstmt.setInt(4, petDiary.getDiaryDis());
			pstmt.setInt(5, petDiary.getDiaryTime());
			pstmt.setInt(6, petDiary.getDiaryNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int deleteDiary(Connection conn, int diaryNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = " DELETE FROM DIARY WHERE DIARY_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, diaryNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteDiary(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM DIARY WHERE DIARY_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
}
