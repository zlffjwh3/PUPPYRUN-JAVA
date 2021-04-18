package petdiary.model.vo;

import java.sql.Date;

public class Goal {
	private int goalNo;
	private int goalDis;
	private int goalTime;
	private int weekDis;
	private int weekTime;
	private char goalCheck;
	private int goalId;
	private Date goalDate;
	
	public Goal() {}
	
	public int getGoalNo() {
		return goalNo;
	}
	public void setGoalNo(int goalNo) {
		this.goalNo = goalNo;
	}
	public int getGoalDis() {
		return goalDis;
	}
	public void setGoalDis(int goalDis) {
		this.goalDis = goalDis;
	}
	public int getGoalTime() {
		return goalTime;
	}
	public void setGoalTime(int goalTime) {
		this.goalTime = goalTime;
	}
	public int getWeekDis() {
		return weekDis;
	}

	public void setWeekDis(int weekDis) {
		this.weekDis = weekDis;
	}

	public int getWeekTime() {
		return weekTime;
	}

	public void setWeekTime(int weekTime) {
		this.weekTime = weekTime;
	}

	public char getGoalCheck() {
		return goalCheck;
	}
	public void setGoalCheck(char goalCheck) {
		this.goalCheck = goalCheck;
	}
	public int getGoalId() {
		return goalId;
	}
	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}
	public Date getGoalDate() {
		return goalDate;
	}
	public void setGoalDate(Date goalDate) {
		this.goalDate = goalDate;
	}

	@Override
	public String toString() {
		return "Goal [goalNo=" + goalNo + ", goalDis=" + goalDis + ", goalTime=" + goalTime + ", weekDis=" + weekDis + ", weekTime=" + weekTime + ", goalCheck="
				+ goalCheck + ", goalId=" + goalId + ", goalDate=" + goalDate + "]";
	}
}
