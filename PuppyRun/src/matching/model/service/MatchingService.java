package matching.model.service;


import java.sql.Connection;
import java.sql.SQLException;

import common.JDBCTemplate;
import matching.model.dao.MatchingDAO;
import matching.model.vo.Matching;
import matching.model.vo.MatchingPage;

public class MatchingService {
	private JDBCTemplate factory;
	
	public MatchingService() {
		factory = JDBCTemplate.getConnection();
	}

	// 전체 게시글 리스트
	public MatchingPage printAllMatching(int currentPage) {
		Connection conn = null;
		MatchingPage mp = new MatchingPage();
		
		try {
			conn = factory.createConnection();
			// 게시글 목록 리스트
			mp.setmList(new MatchingDAO().selectAllMatching(conn, currentPage));
			// 게시글 페이지
			mp.setPageNavi(new MatchingDAO().getPageNavi(conn, currentPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return mp;
	}

	// 산책짝궁 게시글 한개 보기
	public Matching printOne(int matchingNo) {
		Connection conn = null;
		Matching matching = null;
		
		try {
			conn = factory.createConnection();
			matching = new MatchingDAO().printOneMatching(conn, matchingNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return matching;
	}

	// 산책짝꿍 게시글 등록
	public int registerMatching(Matching matching) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new MatchingDAO().insertMatching(conn, matching);
			System.out.println(result);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public int deleteMatching(int matNo) {
		return 0;
	}

	public int modifyMatching(Matching matching) {
		return 0;
	}
}
