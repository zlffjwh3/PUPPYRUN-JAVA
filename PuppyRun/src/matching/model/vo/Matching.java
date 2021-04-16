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
	private int msgNo;
	private String rcvId;
	private String sendId;
	private String content;
	
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
	
	

	public int getMsgNo() {
		return msgNo;
	}

	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}

	public String getRcvId() {
		return rcvId;
	}

	public void setRcvId(String rcvId) {
		this.rcvId = rcvId;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Matching [matNo=" + matNo + ", matId=" + matId + ", matTitle=" + matTitle + ", matContent=" + matContent
				+ ", matAddr=" + matAddr + ", matDate=" + matDate + ", matCheck=" + matCheck + ", matPhoto=" + matPhoto
				+ ", userNick=" + userNick + ", msgNo=" + msgNo + ", rcvId=" + rcvId + ", sendId=" + sendId
				+ ", content=" + content + "]";
	}

	

	
}
