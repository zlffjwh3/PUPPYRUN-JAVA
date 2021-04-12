package petdiary.model.vo;

import java.sql.Date;
import java.util.ArrayList;

public class PetDiary {
	private int diaryNo;
	private String diaryTitle;
	private String diaryContent;
	private String diaryMap;
	private int diaryId;
	private Date diaryDate;
	private String diaryPhoto;
	private ArrayList<Goal> goal;
	
	public PetDiary() {}
	
	// getter/ setter
	public int getDiaryNo() {
		return diaryNo;
	}

	public void setDiaryNo(int diaryNo) {
		this.diaryNo = diaryNo;
	}

	public String getDiaryTitle() {
		return diaryTitle;
	}

	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}

	public String getDiaryContent() {
		return diaryContent;
	}

	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}

	public String getDiaryMap() {
		return diaryMap;
	}

	public void setDiaryMap(String diaryMap) {
		this.diaryMap = diaryMap;
	}

	public int getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}

	public Date getDiaryDate() {
		return diaryDate;
	}

	public void setDiaryDate(Date diaryDate) {
		this.diaryDate = diaryDate;
	}

	public String getDiaryPhoto() {
		return diaryPhoto;
	}

	public void setDiaryPhoto(String diaryPhoto) {
		this.diaryPhoto = diaryPhoto;
	}

	public ArrayList<Goal> getGoal() {
		return goal;
	}

	public void setGoal(ArrayList<Goal> goal) {
		this.goal = goal;
	}

	@Override
	public String toString() {
		return "PetDiary [diaryNo=" + diaryNo + ", diaryTitle=" + diaryTitle + ", diaryContent=" + diaryContent
				+ ", diaryMap=" + diaryMap + ", diaryId=" + diaryId + ", diaryDate=" + diaryDate + ", diaryPhoto="
				+ diaryPhoto + ", goal=" + goal + "]";
	}
}
