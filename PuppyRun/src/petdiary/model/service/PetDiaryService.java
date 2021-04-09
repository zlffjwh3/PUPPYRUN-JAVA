package petdiary.model.service;

import common.JDBCTemplate;
import petdiary.model.vo.PetDiary;
import petdiary.model.vo.PetDiaryPage;

public class PetDiaryService {
	private JDBCTemplate factory;
	
	public PetDiaryService() {
		factory = JDBCTemplate.getConnection();
	}
	
	public PetDiaryPage selectAllDiary(int currentPage) {
		return null;
	}
	
	public PetDiary selectOneDiary(int petDiaryNo) {
		return null;
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
