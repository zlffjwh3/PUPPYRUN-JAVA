package petdiary.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.dao.NoticeDAO;
import notice.model.vo.NoticePage;
import petdiary.model.dao.PetDiaryDAO;
import petdiary.model.vo.PetDiary;

public class PetDiaryService {
	private JDBCTemplate factory;
	
	public PetDiaryService() {
		factory = JDBCTemplate.getConnection();
	}
	
	public ArrayList<PetDiary> selectAllDiary(String userId) {
		Connection conn = null;
		ArrayList<PetDiary> pList = null;
		
		try {
			conn = factory.createConnection();
			pList = new PetDiaryDAO().selectAllDiary(conn, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return pList;
	}
	
	public PetDiary selectOneDiary(Date diaryDate, String diaryId) {
		Connection conn = null;
		PetDiary petDiary = null;
		
		try {
			conn = factory.createConnection();
			petDiary = new PetDiaryDAO().selectOneDiary(conn, diaryDate, diaryId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return petDiary;
	}
	
	public int insertDiary(PetDiary petDiary) {
		return 0;
	}
	
	public int updateDiary(PetDiary petDiary) {
		return 0;
	}
	
	public int deleteDiary(int DiaryNo) {
		return 0;
	}
	
	public int addReadCount(int noticeNo) {
		return 0;
	}
}
