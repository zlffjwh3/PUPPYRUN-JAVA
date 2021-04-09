package matching.model.service;


import common.JDBCTemplate;
import matching.model.vo.Matching;
import matching.model.vo.MatchingPage;

public class MatchingService {
	private JDBCTemplate factory;
	
	public MatchingService() {
		factory = JDBCTemplate.getConnection();
	}

	public MatchingPage printAllList(int currentPage) {
		return null;
	}

	public Matching printOne(int noticeNo) {
		return null;
	}

	public int registerMatching(Matching matching) {
		return 0;
	}

	public int deleteMatching(int matNo) {
		return 0;
	}

	public int modifyMatching(Matching matching) {
		return 0;
	}
}
