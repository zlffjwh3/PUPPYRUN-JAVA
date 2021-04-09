package community.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import community.model.vo.Community;

public class CommunityDAO {
	public CommunityDAO() {}
	
	public ArrayList<Community> selectAllCommunity(Connection conn, int currentPage) {
		return null;
	}
	
	public ArrayList<Community> selectTagList(Connection conn, int currentPage) {
		return null;
	}
	
	public String getPageNavi(Connection conn, int currentPage) {
		return null;
	}
	
	public Community selectOneCommunity(Connection conn, int comNo) {
		return null;
	}
	
	public int insertCommunity(Connection conn, Community community) {
		return 0;
	}
	
	public int updateCommunity(Connection conn, Community community) {
		return 0;
	}
	
	public int deleteNotice(Connection conn, int comNo) {
		return 0;
	}
	
	public int addReadCount(Connection conn, int comNo) {
		return 0;
	}
	
	public ArrayList<Community> selectSearchList(Connection conn, String search, int currentPage) {
		return null;
	}

	public String selectPageNavi(Connection conn, String search, int currentPage) {
		searchTotalCount(conn, search);
		return null;
	}

	public int searchTotalCount(Connection conn, String search) {
		return 0;
	}
}
