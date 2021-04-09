package petdiary.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import petdiary.model.vo.PetDiary;

public class PetDiaryDAO {
	public PetDiaryDAO() {}
	
	public ArrayList<PetDiary> selectAllDiary(Connection conn, int currentPage) {
		return null;
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
