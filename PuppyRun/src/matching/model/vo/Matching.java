package matching.model.vo;

public class Matching {
	private int matNo;
	private int userId;
	private String matTitle;
	private String matComment;
	private String matAddr;
	private char matCheck;
	private int matPhoto;
	private String userNick;
	
	public Matching() {}
	
	// getter/setter
	public int getMatNo() {
		return matNo;
	}

	public void setMatNo(int matNo) {
		this.matNo = matNo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMatTitle() {
		return matTitle;
	}

	public void setMatTitle(String matTitle) {
		this.matTitle = matTitle;
	}

	public String getMatComment() {
		return matComment;
	}

	public void setMatComment(String matComment) {
		this.matComment = matComment;
	}

	public String getMatAddr() {
		return matAddr;
	}

	public void setMatAddr(String matAddr) {
		this.matAddr = matAddr;
	}

	public char getMatCheck() {
		return matCheck;
	}

	public void setMatCheck(char matCheck) {
		this.matCheck = matCheck;
	}

	public int getMatPhoto() {
		return matPhoto;
	}

	public void setMatPhoto(int matPhoto) {
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
		return "Matching [matNo=" + matNo + ", userId=" + userId + ", matTitle=" + matTitle + ", matComment="
				+ matComment + ", matAddr=" + matAddr + ", matCheck=" + matCheck + ", matPhoto=" + matPhoto + "]";
	}
}
