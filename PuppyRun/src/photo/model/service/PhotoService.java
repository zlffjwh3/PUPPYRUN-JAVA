package photo.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import photo.model.dao.PhotoDAO;
import photo.model.vo.Photo;

public class PhotoService {
	
	private JDBCTemplate factory;

	public PhotoService() {
		factory = JDBCTemplate.getConnection();
	}

	// 사진정보 등록
	public int registerPhotoInfo(Photo photo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new PhotoDAO().insertPhotoInfo(conn, photo);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	// 사진정보 수정
	public int updatePhoto(Photo photo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new PhotoDAO().updatePhoto(conn, photo);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	// 사진정보 삭제
	public int removePhoto(String photoPath, String userId) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new PhotoDAO().deletePhoto(conn, photoPath, userId);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
}
