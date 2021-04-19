package petdiary.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
					new GoalDAO().addStamp(conn, goal.getGoalNo());
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
		public int addStamp(Connection conn, int goalNo) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "UPDATE GOAL SET GOAL_CHECK = 'Y' WHERE GOAL_NO = ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, goalNo);
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
	
	// 전체 기록
	public ArrayList<Goal> goalList(Connection conn, String goalId, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY GOAL_DATE ASC) AS NUM, GOAL_NO, GOAL_DIS, GOAL_TIME, GOAL_CHECK, GOAL_DATE, GOAL_ID, WEEK_DIS, WEEK_TIME FROM GOAL WHERE GOAL_ID = ?) WHERE NUM BETWEEN ? AND ?";
		ArrayList<Goal> gList = null;
		
		int recordCountPage = 9;
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, goalId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			
			if(rset != null) {
				gList = new ArrayList<Goal>();
				
				while(rset.next()) {
					Goal goal = new Goal();
					
					goal.setGoalNo(rset.getInt("GOAL_NO"));
					goal.setGoalDis(rset.getInt("GOAL_DIS"));
					goal.setGoalTime(rset.getInt("GOAL_TIME"));
					goal.setGoalCheck(rset.getString("GOAL_CHECK").charAt(0));
					goal.setGoalDate(rset.getString("GOAL_DATE"));
					goal.setGoalId(rset.getString("GOAL_ID"));
					goal.setWeekDis(rset.getInt("WEEK_DIS"));
					goal.setWeekTime(rset.getInt("WEEK_TIME"));
					
					gList.add(goal);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return gList;
	}
	
	// 전체 기록 - 페이지 네비게이션
	public String getPageNavi(Connection conn, String goalId, int currentPage) {
		int recordTotalCount = totalCount(conn, goalId);
		int recordCountPerPage = 9;
		int pageTotalCount = 0;
		
		if((recordTotalCount % recordCountPerPage) > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		// 오류방지 코드
		if(currentPage < 1) {
			currentPage = 1;
		} else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int naviCountPerPage = 10;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		
		// 오류방지 코드
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		if(needPrev) {
			sb.append("<a href='/goal/stmap?currentPage=" + (startNavi - 1) + "' id='page-prev'> < </a>");
		}
		for(int i=startNavi; i<=endNavi; i++) {
			sb.append("<a href='/goal/stamp?currentPage=" + i + "'>" + i + "</a>");
		}
		if(needNext) {
			sb.append("<a href='/goal/stmap?currentPage=" + (endNavi + 1) + "' id='page-next'> > </a>");
		}
		
		return sb.toString();
	}
	
	// 전체 기록 - 총 게시물 수 계산
	public int totalCount(Connection conn, String goalId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM GOAL WHERE GOAL_ID = ?";
		
		int recordTotalCount = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, goalId);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return recordTotalCount;
	}
}
