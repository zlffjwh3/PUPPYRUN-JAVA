package matching.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import matching.model.vo.Matching;

public class MatchingDAO {
	public ArrayList<Matching> selectAllList(Connection conn, int currentPage) {
		return null;
	}
	
	public String getPageNavi(Connection conn, int currentPage) { // currentPage는 사용자가 페이지 클릭한 번호
		return null;
	}
	
	// 전체 게시물의 개수를 가져오는 메소드
	public int totalCount(Connection conn) {
		return 0;
	}

	public int insertMatching(Connection conn, Matching nmatching) {
		return 0;
	}

	public int deleteMatching(Connection conn, int matNo) {
		return 0;
	}

	public int updateMatching(Connection conn, Matching matching) {
		return 0;
	}

}
