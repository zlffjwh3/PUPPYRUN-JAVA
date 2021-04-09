package community.model.vo;

public class Like {
	private int comNo;
	private int likeId;
	private char likeStatus;
	
	public Like() {}
	
	// getter/setter
	public int getComNo() {
		return comNo;
	}

	public void setComNo(int comNo) {
		this.comNo = comNo;
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public char getLikeStatus() {
		return likeStatus;
	}

	public void setLikeStatus(char likeStatus) {
		this.likeStatus = likeStatus;
	}

	@Override
	public String toString() {
		return "Like [comNo=" + comNo + ", likeId=" + likeId + ", likeStatus=" + likeStatus + "]";
	}
}
