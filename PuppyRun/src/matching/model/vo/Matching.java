package matching.model.vo;

public class Matching {
	private int matNo;
	private String matId;
	private String matTitle;
	private String matContent;
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
		return "Matching [matNo=" + matNo + ", matId=" + matId + ", matTitle=" + matTitle + ", matContent="
				+ matContent + ", matAddr=" + matAddr + ", matCheck=" + matCheck + ", matPhoto=" + matPhoto + "]";
	}
}
