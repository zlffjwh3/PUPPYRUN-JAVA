package photo.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import photo.model.vo.Photo;

public class PhotoDAO {

	public int insertPhotoInfo(Connection conn, Photo photo) {
		return 0;
	}

	public ArrayList<Photo> selectPhotoList(Connection conn, String userId) {
		return null;
	}

	public int deletePhoto(Connection conn, String photoPath, String photoUser) {
		return 0;
	}
}