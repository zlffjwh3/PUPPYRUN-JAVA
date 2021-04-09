package petdiary.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import petdiary.model.vo.Goal;

public class GoalService {
	private JDBCTemplate factory;
	
	public GoalService() {
		factory = JDBCTemplate.getConnection();
	}
	
	// 목표 설정하면 저장하는 메소드
	public int saveUserGoal(Goal goal) {
		int result = 0;
		return result;
	}
	
	// 기록 가져오는 메소드
	public Goal weekGoal(String goalId) {
		return null;
	}
	
	// 기록 저장하는 메소드
	public int saveTodayGoal(String goalId, Goal goal) {
		int result = 0;
		return result;
	}
	
	// 오늘 전체 기록 불러오는 메소드
	public ArrayList<Goal> goalList(String goalId) {
		return null;
	}
	
	// 아이디별로 목표 달성 몇개했는지 카운트해서 가져오는 메소드
	public int countGoal(String goalId) {
		return 0;
	}
	
	// 달성 스탬프 찍기
	public int addStamp(String goalId) {
		return 0;
	}
}
