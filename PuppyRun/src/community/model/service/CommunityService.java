package community.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import community.model.dao.CommunityDAO;
import community.model.vo.Community;
import community.model.vo.CommunityPage;

public class CommunityService {
	private JDBCTemplate factory;
	
	public CommunityService() {
		factory = JDBCTemplate.getConnection();
	}
	
	// 전체 게시글 리스트
	public CommunityPage selectAllCommunity(int currentPage) {
		Connection conn = null;
		CommunityPage cp = new CommunityPage();
		
		try {
			conn = factory.createConnection();
			// 게시글 목록 리스트
			cp.setcList(new CommunityDAO().selectAllCommunity(conn, currentPage));
			// 게시글 페이지
			cp.setPageNavi(new CommunityDAO().getPageNavi(conn, currentPage));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return cp;
	}
	
	// 전체 게시물 페이지 없음 버전
	public ArrayList<Community> selectAllCommunity2() {
		Connection conn = null;
		ArrayList<Community> allCList = null;
		
		try {
			conn = factory.createConnection();
			allCList = new CommunityDAO().selectAllCommunity2(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return allCList;
	}
	
	// 태그별로 다르게 보이게 해주는 메소드
	public CommunityPage selectTagList(int currentPage, int tag) {
		Connection conn = null;
		CommunityPage cp = new CommunityPage();
		
		try {
			conn = factory.createConnection();
			cp.setcList(new CommunityDAO().selectTagList(conn, currentPage, tag));
			cp.setPageNavi(new CommunityDAO().getPageNavi(conn, currentPage, tag));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return cp;
	}
	
	// 게시물 보기 (Detail) 메소드
	public Community selectOneCommunity(int communityNo) {
		Connection conn = null;
		Community community = null;
		
		try {
			conn = factory.createConnection();
			community = new CommunityDAO().selectOneCommunity(conn, communityNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return community;
	}
	
	// 게시물 등록하는 메소드
	public int insertCommunity(Community community) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new CommunityDAO().insertCommunity(conn, community);
			System.out.println(result);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	
	public int updateCommunity(Community community) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new CommunityDAO().updateCommunity(conn, community);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public int deleteCommunity(int communityNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new CommunityDAO().deleteCommunity(conn, communityNo);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public int addReadCount(int communityNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new CommunityDAO().addReadCount(conn, communityNo);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public CommunityPage printSearchList(String search, int currentPage) {
		Connection conn = null;
		CommunityPage communityPage = new CommunityPage();
		
		try {
			conn = factory.createConnection();
			communityPage.setcList(new CommunityDAO().selectSearchList(conn, search, currentPage));
			communityPage.setPageNavi(new CommunityDAO().getSearchPageNavi(conn, search, currentPage));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return communityPage;
	}
	
	// 유저당 커뮤니티 게시물 가져오기
	public ArrayList<Community> printUserCommunity(String userId) {
		Connection conn = null;
		ArrayList<Community> cList = null;
		
		try {
			conn = factory.createConnection();
			cList = new CommunityDAO().printUserCommunity(conn, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return cList;
	}

}
