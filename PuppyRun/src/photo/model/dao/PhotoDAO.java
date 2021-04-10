package photo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import photo.model.vo.Photo;

public class PhotoDAO {
	// 사진정보 등록
	public int insertPhotoInfo(Connection conn, Photo photo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO PHOTO VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, photo.getPhotoName());
			pstmt.setString(2, photo.getPhotoPath());
			pstmt.setLong(3, photo.getPhotoSize());
			pstmt.setString(4, photo.getPhotoId());
			pstmt.setTimestamp(5, photo.getUploadTime());
			pstmt.setString(6, photo.getBoardType() + "");
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	// 사진정보 수정
	public int updatePhoto(Connection conn, Photo photo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE PHOTO SET PHOTO_NAME=?, PHOTO_PATH=?, PHOTO_SIZE=?, UPLOAD_TIME=? WHERE PHOTO_ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, photo.getPhotoName());
			pstmt.setString(2, photo.getPhotoPath());
			pstmt.setLong(3, photo.getPhotoSize());
			pstmt.setTimestamp(4, photo.getUploadTime());
			pstmt.setString(5, photo.getPhotoId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	// 사진정보 삭제
	public int deletePhoto(Connection conn, String photoPath, String userId) {
		return 0;
	}
}