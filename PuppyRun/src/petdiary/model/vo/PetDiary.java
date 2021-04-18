package petdiary.model.vo;

import java.util.Date;
import java.util.ArrayList;

public class PetDiary {
	private int diaryNo;
	private String diaryTitle;
	private String diaryContent;
	private String diaryId;
	private String diaryDate;
	private String diaryPhoto;
	private int diaryDis;
	private int diaryTime;
	
	
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

	public String getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}

	public String getDiaryDate() {
		return diaryDate;
	}

	public void setDiaryDate(String diaryDate) {
		this.diaryDate = diaryDate;
	}

	public String getDiaryPhoto() {
		return diaryPhoto;
	}

	public void setDiaryPhoto(String diaryPhoto) {
		this.diaryPhoto = diaryPhoto;
	}

	public int getDiaryDis() {
		return diaryDis;
	}

	public void setDiaryDis(int diaryDis) {
		this.diaryDis = diaryDis;
	}

	public int getDiaryTime() {
		return diaryTime;
	}

	public void setDiaryTime(int diaryTime) {
		this.diaryTime = diaryTime;
	}

	@Override
	public String toString() {
		return "PetDiary [diaryNo=" + diaryNo + ", diaryTitle=" + diaryTitle + ", diaryContent=" + diaryContent
				+ ", diaryId=" + diaryId + ", diaryDate=" + diaryDate + ", diaryPhoto="
				+ diaryPhoto + "]";
	}
}
