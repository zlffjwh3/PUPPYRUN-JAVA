package notice.model.service;

import common.JDBCTemplate;
import notice.model.vo.Notice;
import notice.model.vo.NoticePage;

public class NoticeService {
	private JDBCTemplate factory;
	
	public NoticeService() {
		factory = JDBCTemplate.getConnection();
	}
	
	public NoticePage selectAllNotice(int currentPage) {
		return null;
	}
	
	public Notice selectOneNotice(int noticeNo) {
		return null;
	}
	
	public int insertNotice(Notice notice) {
		return 0;
	}
	
	public int updateNotice(Notice notice) {
		return 0;
	}
	
	public int deleteNotice(int noticeNo) {
		return 0;
	}
	
	public int addReadCount(int noticeNo) {
		return 0;
	}
}
