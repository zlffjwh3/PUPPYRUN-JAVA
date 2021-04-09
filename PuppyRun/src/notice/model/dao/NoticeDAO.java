package notice.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import notice.model.vo.Notice;

public class NoticeDAO {
	public NoticeDAO() {}
	
	public ArrayList<Notice> selectAllNotice(Connection conn, int currentPage) {
		return null;
	}
	
	public String getPageNavi(Connection conn, int currentPage) {
		return null;
	}
	
	public int totalCount(Connection conn) {
		return 0;
	}
	
	public Notice selectOneNotice(Connection conn, int noticeNo) {
		return null;
	}
	
	public int insertNotice(Connection conn, Notice notice) {
		return 0;
	}
	
	public int updateNotice(Connection conn, Notice notice) {
		return 0;
	}
	
	public int deleteNotice(Connection conn, int noticeNo) {
		return 0;
	}
	
	public int addReadCount(Connection conn, int noticeNo) {
		return 0;
	}
}
