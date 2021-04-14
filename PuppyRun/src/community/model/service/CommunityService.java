package community.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import common.JDBCTemplate;
import community.model.dao.CommunityDAO;
import community.model.vo.Community;
import community.model.vo.CommunityPage;
import notice.model.vo.Notice;

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
			System.out.println("서비스 완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return cp;
	}
	
	// 태그별로 다르게 보이게 해주는 메소드
	public CommunityPage selectTagList(int currentPage) {
		return null;
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
	
	
	public int updateCommunity(Notice community) {
		return 0;
	}
	
	public int deleteCommunity(int communityNo) {
		return 0;
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
	
	public int printSearchList(String search, int currentPage) {
		return 0;
	}


}
