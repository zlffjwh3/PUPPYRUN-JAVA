package petdiary.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.vo.Notice;
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
					diary.setDiaryMap(rset.getString("DIARY_MAP"));
					diary.setDiaryDate(rset.getString("DIARY_DATE"));
					diary.setDiaryPhoto(rset.getString("DIARY_PHOTO"));
					
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
	
	public String getPageNavi(Connection conn, int currentPage) {
		return null;
	}
	
	public int totalCount(Connection conn) {
		return 0;
	}
	
	public PetDiary selectOneDiary(Connection conn, int diaryNo) {
		return null;
	}
	
	public int insertDiary(Connection conn, PetDiary petDiary) {
		return 0;
	}
	
	public int updateDiary(Connection conn, PetDiary petDiary) {
		return 0;
	}
	
	public int deleteDiary(Connection conn, int diaryNo) {
		return 0;
	}
	
	public int addReadCount(Connection conn, int diaryNo) {
		return 0;
	}
}
