package community.model.vo;

import java.sql.Date;

public class Comment {
	private int commentNo;
	private int comNo;
	private String userNick;
	private String commentContents;
	private String commentId;
	private Date commentDate;
	
	public Comment() {}
	
	// getter/setter
	public int getCommentNo() {
		return commentNo;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getComNo() {
		return comNo;
	}

	public void setComNo(int comNo) {
		this.comNo = comNo;
	}

	public String getCommentContents() {
		return commentContents;
	}

	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", comNo=" + comNo + ", commentContents=" + commentContents
				+ ", commentId=" + commentId + ", commentDate=" + commentDate + "]";
	}
}
