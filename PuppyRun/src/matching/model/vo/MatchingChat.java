package matching.model.vo;

public class MatchingChat {
	
	private int matNo;
	private int msgNo;
	private String rcvId;
	private String sendId;
	private String content;
	public int getMatNo() {
		return matNo;
	}
	public void setMatNo(int matNo) {
		this.matNo = matNo;
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
		return "MatchingChat [matNo=" + matNo + ", msgNo=" + msgNo + ", rcvId=" + rcvId + ", sendId=" + sendId
				+ ", content=" + content + "]";
	}
	
	
	
	
	

}
