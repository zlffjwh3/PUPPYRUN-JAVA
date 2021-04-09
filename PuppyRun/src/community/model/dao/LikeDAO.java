package community.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import community.model.vo.Community;

public class LikeDAO {
	public LikeDAO() {}

	public char likeStatus(Connection conn, int comNo, String userId) {
		// select해서 좋아요 상태 가져오기
		return 0;
	}

	public int changeStatus(Connection conn, int comNo, String userId, char after) {
		// 좋아요 상태 반대로 바꾸기
		countLike(conn, comNo, after);
		return 0;
	}

	public void countLike(Connection conn, int comNo, char after) {
		// select count(*)써서 개수 가져오기
		// 알아서 저장
		
		if(after == 'Y') {
			// +
		} else {
			// -
		}
	}
	
	// 좋아요 수 가져오는 메소드 추가

	public ArrayList<Community> likeList(Connection conn, String userId) {
		return null;
	}
}
