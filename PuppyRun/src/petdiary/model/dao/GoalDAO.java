package petdiary.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import petdiary.model.vo.Goal;
import petdiary.model.vo.PetDiary;

public class GoalDAO {
	// 목표 설정하면 저장하는 메소드
	public int saveUserGoal(Connection conn, Goal goal) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO GOAL VALUES(SEQ_GOALNO.NEXTVAL, ?, ?, 'N', ?, ?, 0, 0)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, goal.getGoalDis());
			pstmt.setInt(2, goal.getGoalTime());
			pstmt.setString(3, goal.getGoalDate());
			pstmt.setString(4, goal.getGoalId());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	// 일주일 기록 불러옴
	public Goal weekGoal(Connection conn, String goalId, String goalDate) {
		Goal goal = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM GOAL WHERE ? >= TO_CHAR(GOAL_DATE, 'YYMMDD') AND ? < TO_CHAR(GOAL_DATE + 7, 'YYMMDD') AND GOAL_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, goalDate);
			pstmt.setString(2, goalDate);
			pstmt.setString(3, goalId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				goal = new Goal();
				goal.setGoalNo(rset.getInt("GOAL_NO"));
				goal.setGoalDis(rset.getInt("GOAL_DIS"));
				goal.setGoalTime(rset.getInt("GOAL_TIME"));
				goal.setGoalCheck(rset.getString("GOAL_CHECK").charAt(0));
				goal.setGoalDate(rset.getString("GOAL_DATE"));
				goal.setGoalId(rset.getString("GOAL_ID"));
				goal.setWeekDis(rset.getInt("WEEK_DIS"));
				goal.setWeekTime(rset.getInt("WEEK_TIME"));
				
				// 가져올 때마다 목표 넘었는지 체크해서 기록해주기
				int weekDis = goal.getWeekDis();
				int weekTime = goal.getWeekTime();
				int goalDis = goal.getGoalDis();
				int goalTime = goal.getGoalTime();
				char goalCheck = goal.getGoalCheck();
				
				if(weekDis >= goalDis && weekTime >= goalTime && goalCheck == 'N') {
					new GoalDAO().addStamp(conn, goal.getGoalId());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return goal;
	}
	
		// 달성 스탬프 찍기
		public int addStamp(Connection conn, String goalId) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "UPDATE GOAL SET GOAL_CHECK = 'Y' WHERE GOAL_ID = ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, goalId);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		}
	
	// 일주일 기록 계산해서 일주일 저장하는 메소드
	public int addGoalData(Connection conn, PetDiary petDiary, String goalDate) {
		// 일주일 받은거에 더해줘서 저장
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE GOAL SET WEEK_DIS = WEEK_DIS + ?, WEEK_TIME = WEEK_TIME + ? WHERE ? >= TO_CHAR(GOAL_DATE, 'YYMMDD') AND ? < TO_CHAR(GOAL_DATE + 7, 'YYMMDD') AND GOAL_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, petDiary.getDiaryDis());
			pstmt.setInt(2, petDiary.getDiaryTime());
			pstmt.setString(3, goalDate);
			pstmt.setString(4, goalDate);
			pstmt.setString(5, petDiary.getDiaryId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	// 전체 기록 불러오는 메소드
	// -- 그냥 전체 불러와서 확인할 수 있도록 할까나,,,?
	public ArrayList<Goal> goalList(Connection conn, String goalId) {
		return null;
	}
	
	// 아이디별로 목표 달성 몇개했는지 카운트해서 가져오는 메소드
	public int countGoal(Connection conn, String goalId) {
		return 0;
	}
}
