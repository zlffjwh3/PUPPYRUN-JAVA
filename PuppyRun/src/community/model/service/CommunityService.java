package community.model.service;

import common.JDBCTemplate;
import community.model.vo.Community;
import community.model.vo.CommunityPage;
import notice.model.vo.Notice;

public class CommunityService {
	private JDBCTemplate factory;
	
	public CommunityService() {
		factory = JDBCTemplate.getConnection();
	}
	
	public CommunityPage selectAllCommunity(int currentPage) {
		return null;
	}
	
	// 태그별로 다르게 보이게 해주는 메소드
	public CommunityPage selectTagList(int currentPage) {
		return null;
	}
	
	public Community selectOneCommunity(int communityNo) {
		return null;
	}
	
	public int insertCommunity(Notice community) {
		return 0;
	}
	
	public int updateCommunity(Notice community) {
		return 0;
	}
	
	public int deleteCommunity(int communityNo) {
		return 0;
	}
	
	public int addReadCount(int communityNo) {
		return 0;
	}
	
	public CommunityPage printSearchList(String search, int currentPage) {
		return null;
	}
}
