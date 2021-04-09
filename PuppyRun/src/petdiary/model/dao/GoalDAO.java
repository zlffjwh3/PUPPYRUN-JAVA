package petdiary.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import petdiary.model.vo.Goal;

public class GoalDAO {
	// 목표 설정하면 저장하는 메소드
	public int saveUserGoal(Connection conn, Goal goal) {
		int result = 0;
		return result;
	}
	
	// 일주일 기록 불러옴
	public Goal weekGoal(Connection conn, String goalId) {
		return null;
	}
	
	// 일주일 기록 계산해서 하루랑 일주일 저장하는 메소드
	public int saveGoalData(Connection conn, String goalId, Goal goal) {
		// 오늘꺼 받은 거 그대로 저장
		// 일주일 받은거에 더해줘서 저장
		
		return 0;
	}
	
	// 전체 기록 불러오는 메소드
	public ArrayList<Goal> goalList(Connection conn, String goalId) {
		return null;
	}
	
	// 아이디별로 목표 달성 몇개했는지 카운트해서 가져오는 메소드
	public int countGoal(Connection conn, String goalId) {
		return 0;
	}
	
	// 달성 스탬프 찍기
	public int addStamp(Connection conn, String goalId) {
		return 0;
	}
}
