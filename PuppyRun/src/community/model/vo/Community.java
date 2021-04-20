package community.model.vo;

import java.sql.Date;

public class Community {
	private int comNo;
	private String comId;
	private int tagNo;
	private String comTitle;
	private String comContent;
	private int comview;
	private Date comDate;
	private String comPhoto;
	/* private int likeCount; */
	/* private ArrayList<Comment> comment; */
	private String userNick;
	
	public Community() {}
	
	// getter/setter
	public int getComNo() {
		return comNo;
	}
	public void setComNo(int comNo) {
		this.comNo = comNo;
	}
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public int getTagNo() {
		return tagNo;
	}
	public void setTagNo(int tagNo) {
		this.tagNo = tagNo;
	}
	public String getComTitle() {
		return comTitle;
	}
	public void setComTitle(String comTitle) {
		this.comTitle = comTitle;
	}
	public String getComContent() {
		return comContent;
	}
	public void setComContent(String comContent) {
		this.comContent = comContent;
	}
	public int getComview() {
		return comview;
	}
	public void setComview(int comview) {
		this.comview = comview;
	}
	public Date getComDate() {
		return comDate;
	}
	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}
	public String getComPhoto() {
		return comPhoto;
	}
	public void setComPhoto(String comPhoto) {
		this.comPhoto = comPhoto;
	}
	/*
	 * public int getLikeCount() { return likeCount; }
	 * 
	 * public void setLikeCount(int likeCount) { this.likeCount = likeCount; }
	 */

	/*
	 * public ArrayList<Comment> getComment() { return comment; } public void
	 * setComment(ArrayList<Comment> comment) { this.comment = comment; }
	 */

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

}
