package petdiary.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import community.model.dao.CommunityDAO;
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
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new PetDiaryDAO().insertDiary(conn, petDiary);
			System.out.println(result); // 오류
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	public int updateDiary(PetDiary petDiary) {
		return 0;
	}
	
	public int deleteDiary(int diaryNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new PetDiaryDAO().deleteDiary(conn, diaryNo);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public int addReadCount(int noticeNo) {
		return 0;
	}
}
