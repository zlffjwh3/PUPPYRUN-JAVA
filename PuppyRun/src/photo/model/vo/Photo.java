package photo.model.vo;

import java.sql.Timestamp;

public class Photo {
	private int photoNo;
	private String photoName;
	private String photoPath;
	private long photoSize;
	private String photoId;
	private Timestamp uploadTime;
	private char boardType;
	
	public Photo() {}
	
	public int getPhotoNo() {
		return photoNo;
	}
	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public long getPhotoSize() {
		return photoSize;
	}
	public void setPhotoSize(long photoSize) {
		this.photoSize = photoSize;
	}
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
	public char getBoardType() {
		return boardType;
	}
	public void setBoardType(char boardType) {
		this.boardType = boardType;
	}
	
	@Override
	public String toString() {
		return "Photo [photoNo=" + photoNo + ", photoName=" + photoName + ", photoPath=" + photoPath + ", photoSize="
				+ photoSize + ", photoId=" + photoId + ", uploadTime=" + uploadTime + ", boardType=" + boardType + "]";
	}
}
