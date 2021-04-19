package community.model.vo;

public class Like {
	private int comNo;
	private String likeId;
	private String likeStatus;
	
	public Like() {}
	
	// getter/setter
	public int getComNo() {
		return comNo;
	}

	public void setComNo(int comNo) {
		this.comNo = comNo;
	}

	public String getLikeId() {
		return likeId;
	}

	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}

	public String getLikeStatus() {
		return likeStatus;
	}

	public void setLikeStatus(String likeStatus) {
		this.likeStatus = likeStatus;
	}

	@Override
	public String toString() {
		return "Like [comNo=" + comNo + ", likeId=" + likeId + ", likeStatus=" + likeStatus + "]";
	}
}
