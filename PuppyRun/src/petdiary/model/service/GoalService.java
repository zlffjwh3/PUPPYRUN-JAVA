package petdiary.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import petdiary.model.dao.GoalDAO;
import petdiary.model.vo.Goal;

public class GoalService {
	private JDBCTemplate factory;
	
	public GoalService() {
		factory = JDBCTemplate.getConnection();
	}
	
	// 목표 설정하면 저장하는 메소드 - GoalWriteServlet
	public int saveUserGoal(Goal goal) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new GoalDAO().saveUserGoal(conn, goal);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	// 오늘 기록 가져오는 메소드 - PetDiaryWriteServlet / PetDiaryUpdateServlet
	public Goal weekGoal(String goalId ,String goalDate) {
		Connection conn = null;
		Goal goal = new Goal();
		
		try {
			conn = factory.createConnection();
			goal = new GoalDAO().weekGoal(conn, goalId, goalDate);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(conn);
		}
		return goal;
	}
	
	// 기록 저장하는 메소드 - PetDiaryWriteServlet / PetDiaryUpdateServlet
	public int saveTodayGoal(String goalId, Goal goal) {
		int result = 0;
		return result;
	}
	
	// 전체 기록 불러오는 메소드 - GoalStampServlet
	public ArrayList<Goal> goalList(String goalId) {
		return null;
	}
	
	// 아이디별로 목표 달성 몇개했는지 카운트해서 가져오는 메소드 - GoalStampServlet
	public int countGoal(String goalId) {
		return 0;
	}
	
	// 달성 스탬프 찍기
	public int addStamp(String goalId) {
		return 0;
	}
}
