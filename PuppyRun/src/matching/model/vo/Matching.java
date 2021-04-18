package matching.model.vo;

import java.util.Date;

public class Matching {
	private int matNo;
	private String matId;
	private String matTitle;
	private String matContent;
	private String matAddr;
	private Date matDate;
	private char matCheck;
	private String matPhoto;
	private String userNick;
	
	
	public Matching() {}
	
	// getter/setter
	public int getMatNo() {
		return matNo;
	}

	public void setMatNo(int matNo) {
		this.matNo = matNo;
	}

	public String getMatId() {
		return matId;
	}

	public void setMatId(String matId) {
		this.matId = matId;
	}

	public String getMatTitle() {
		return matTitle;
	}

	public void setMatTitle(String matTitle) {
		this.matTitle = matTitle;
	}

	public String getMatContent() {
		return matContent;
	}

	public void setMatContent(String matContent) {
		this.matContent = matContent;
	}

	public String getMatAddr() {
		return matAddr;
	}

	public void setMatAddr(String matAddr) {
		this.matAddr = matAddr;
	}

	
	
	public Date getMatDate() {
		return matDate;
	}

	public void setMatDate(Date matDate) {
		this.matDate = matDate;
	}

	public char getMatCheck() {
		return matCheck;
	}

	public void setMatCheck(char matCheck) {
		this.matCheck = matCheck;
	}

	public String getMatPhoto() {
		return matPhoto;
	}

	public void setMatPhoto(String matPhoto) {
		this.matPhoto = matPhoto;
	}
	
	

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	@Override
	public String toString() {
		return "Matching [matNo=" + matNo + ", matId=" + matId + ", matTitle=" + matTitle + ", matContent=" + matContent
				+ ", matAddr=" + matAddr + ", matDate=" + matDate + ", matCheck=" + matCheck + ", matPhoto=" + matPhoto
				+ ", userNick=" + userNick + "]";
	}
	
}
