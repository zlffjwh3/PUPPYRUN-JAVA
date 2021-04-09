package photo.model.service;

import java.util.ArrayList;

import common.JDBCTemplate;
import photo.model.vo.Photo;

public class PhotoService {
	
	private JDBCTemplate factory;

	public PhotoService() {
		factory = JDBCTemplate.getConnection();
	}

	public int registerFileInfo(Photo photoData) {
		return 0;
	}
	
	public ArrayList<Photo> printPhotoList(String userId) {
		return null;
	}

	public int removePhoto(String photoPath, String photoUser) {
		return 0;
	}
}
