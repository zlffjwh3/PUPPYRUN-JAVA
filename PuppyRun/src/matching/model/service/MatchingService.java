package matching.model.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
	public Matching printOneMatching(int matchingNo) {
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

	// 산책짝꿍 수정
	public int modifyMatching(Matching matching) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new MatchingDAO().updateMatching(conn, matching);
			
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
	
	// 산책짝꿍 삭제
	public int deleteMatching(int matNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection ();
			result = new MatchingDAO().deleteMatching(conn, matNo);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	// 채팅 했는지 여부 보여주기
	public int changeCheck(int matchingNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection ();
			result = new MatchingDAO().changeCheck(conn, matchingNo);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	// 관리자 페이지에서 산책짝꿍 검색
	public MatchingPage selectSearchMatchingList(String matchingSearch, String matchingChoice) {
		Connection conn = null;
		MatchingPage matchingPage = new MatchingPage();
		
		try {
			conn = factory.createConnection();
			matchingPage.setmList(new MatchingDAO(). selectSearchMatchingList(conn, matchingSearch, matchingChoice));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return matchingPage;
	}
	
	// 아이디별로 산책짝꿍 게시글 보내주기
	public ArrayList<Matching> printUserMatching(String userId) {
		Connection conn = null;
		ArrayList<Matching> mList = null;
		
		try {
			conn = factory.createConnection();
			mList = new MatchingDAO().printUserMatching(conn, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mList;
	}
}
