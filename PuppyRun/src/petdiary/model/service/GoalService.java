package petdiary.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import petdiary.model.dao.GoalDAO;
import petdiary.model.vo.Goal;
import petdiary.model.vo.GoalPage;
import petdiary.model.vo.PetDiary;

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
	
	// 기록 저장하는 메소드
	public int addGoalData(PetDiary petDiary, String goalDate) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new GoalDAO().addGoalData(conn, petDiary, goalDate);
			
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
	
	// 전체 기록 불러오는 메소드 - GoalStampServlet
	public GoalPage goalList(String goalId, int currentPage) {
		Connection conn = null;
		GoalPage gp = new GoalPage();
		
		try {
			conn = factory.createConnection();
			
			gp.setgList(new GoalDAO().goalList(conn, goalId, currentPage));
			gp.setPageNavi(new GoalDAO().getPageNavi(conn, goalId, currentPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return gp;
	}

	public int deleteGoal(String userId) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new GoalDAO().deleteGoal(conn, userId);
			
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
}
