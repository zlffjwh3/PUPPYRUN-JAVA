package community.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import community.model.vo.Community;

public class CommunityDAO {
	public CommunityDAO() {}
	
	// 전체 게시글 목록 리스트
	public ArrayList<Community> selectAllCommunity(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY COM_NO DESC) AS NUM, COM_NO, COM_ID, TAG_NO, COM_TITLE, COM_CONTENT, COM_VIEW, COM_DATE, COM_PHOTO, LIKE_COUNT, USER_NICK FROM COMMUNITY) WHERE NUM BETWEEN ? AND ?";
		ArrayList<Community> cList = null;
		
		// 한 페이지에 보여줄 게시물의 수
		int recordCountPage = 10;
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			if(rset != null) {
				cList = new ArrayList<Community>();
				
				while(rset.next()) {
					Community community = new Community();
					community.setComNo(rset.getInt("COM_NO"));
					community.setComId(rset.getString("COM_ID"));
					community.setTagNo(rset.getInt("TAG_NO"));
					community.setComTitle(rset.getString("COM_TITLE"));
					community.setComContent(rset.getString("COM_CONTENT"));
					community.setComview(rset.getInt("COM_VIEW"));
					community.setComDate(rset.getDate("COM_DATE"));
					community.setComPhoto(rset.getString("COM_PHOTO"));
					community.setLikeCount(rset.getInt("LIKE_COUNT"));
					community.setUserNick(rset.getString("USER_NICK"));
					
					cList.add(community);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return cList;
	}
	
	// 페이지 네비게이션
	public String getPageNavi(Connection conn, int currentPage) {
		int recordTotalCount = totalCount(conn);
		int recordCountPerPage = 10;
		int pageTotalCount = 0;
		
		if((recordTotalCount % recordCountPerPage) > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		// 오류방지 코드
		if(currentPage < 1) {
			currentPage = 1;
		} else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int naviCountPerPage = 10;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		
		// 오류방지 코드
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		if(needPrev) {
			sb.append("<a href='/community/list?currentPage=" + (startNavi - 1) + "' id='page-prev'> < </a>");
		}
		for(int i=startNavi; i<=endNavi; i++) {
			sb.append("<a href='/community/list?currentPage=" + i + "'>" + i + "</a>");
		}
		if(needNext) {
			sb.append("<a href='/community/list?currentPage=" + (endNavi + 1) + "' id='page-next'> > </a>");
		}
		
		return sb.toString();
	}

		// 총 게시물 수 계산
		public int totalCount(Connection conn) {
			Statement stmt = null;
			ResultSet rset = null;
			String query = "SELECT COUNT(*) AS TOTALCOUNT FROM COMMUNITY";
			
			int recordTotalCount = 0;
			
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);

				if(rset.next()) {
					recordTotalCount = rset.getInt("TOTALCOUNT");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(stmt);
			}
			
			return recordTotalCount;
		}
		
		// 태그 네비게이터
		public String getPageNavi(Connection conn, int currentPage, int tag) {
			int recordTotalCount = totalCount(conn, tag);
			int recordCountPerPage = 10;
			int pageTotalCount = 0;
			
			if((recordTotalCount % recordCountPerPage) > 0) {
				pageTotalCount = recordTotalCount / recordCountPerPage + 1;
			} else {
				pageTotalCount = recordTotalCount / recordCountPerPage;
			}
			
			// 오류방지 코드
			if(currentPage < 1) {
				currentPage = 1;
			} else if(currentPage > pageTotalCount) {
				currentPage = pageTotalCount;
			}
			
			int naviCountPerPage = 10;
			int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
			int endNavi = startNavi + naviCountPerPage - 1;
			
			// 오류방지 코드
			if(endNavi > pageTotalCount) {
				endNavi = pageTotalCount;
			}
			
			boolean needPrev = true;
			boolean needNext = true;
			if(startNavi == 1) {
				needPrev = false;
			}
			if(endNavi == pageTotalCount) {
				needNext = false;
			}
			
			StringBuilder sb = new StringBuilder();
			if(needPrev) {
				sb.append("<a href='/community/list?currentPage=" + (startNavi - 1) + "&tagNo=" + tag + "' id='page-prev'> < </a>");
			}
			for(int i=startNavi; i<=endNavi; i++) {
				sb.append("<a href='/community/list?currentPage=" + i + "&tagNo=" + tag + "'>" + i + "</a>");
			}
			if(needNext) {
				sb.append("<a href='/community/list?currentPage=" + (endNavi + 1) + "&tagNo =" + tag + "' id='page-next'> > </a>");
			}
			
			return sb.toString();
		}
		
		// 태그 전체 페이지 수
		public int totalCount(Connection conn, int tag) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String query = "SELECT COUNT(*) AS TOTALCOUNT FROM COMMUNITY WHERE TAG_NO = ?";
			
			int recordTotalCount = 0;
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, tag);
				rset = pstmt.executeQuery();

				if(rset.next()) {
					recordTotalCount = rset.getInt("TOTALCOUNT");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return recordTotalCount;
		}
		
	// 특정 태그 게시물 보기 (여기여기여기)
	public ArrayList<Community> selectTagList(Connection conn, int currentPage, int tag) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY COM_NO DESC) AS NUM, COM_NO, COM_ID, TAG_NO, COM_TITLE, COM_CONTENT, COM_VIEW, COM_DATE, COM_PHOTO, LIKE_COUNT, USER_NICK FROM COMMUNITY WHERE TAG_NO = ?) WHERE NUM BETWEEN ? AND ?";
		ArrayList<Community> cList = null;
		
		int recordCountPage = 10;
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tag);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			
			if(rset != null) {
				cList = new ArrayList<Community>();
				
				while(rset.next()) {
					Community community = new Community();
					community.setComNo(rset.getInt("COM_NO"));
					community.setComId(rset.getString("COM_ID"));
					community.setTagNo(rset.getInt("TAG_NO"));
					community.setComTitle(rset.getString("COM_TITLE"));
					community.setComContent(rset.getString("COM_CONTENT"));
					community.setComview(rset.getInt("COM_VIEW"));
					community.setComDate(rset.getDate("COM_DATE"));
					community.setComPhoto(rset.getString("COM_PHOTO"));
					community.setLikeCount(rset.getInt("LIKE_COUNT"));
					community.setUserNick(rset.getString("USER_NICK"));
					
					cList.add(community);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return cList;
	}
	
	// 게시물 상세보기 (디테일)
	public Community selectOneCommunity(Connection conn, int comNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM COMMUNITY WHERE COM_NO = ?";
		Community community = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				community = new Community();
				community.setComNo(rset.getInt("COM_NO"));
				community.setComId(rset.getString("COM_ID"));
				community.setTagNo(rset.getInt("TAG_NO"));
				community.setComTitle(rset.getString("COM_TITLE"));
				community.setComContent(rset.getString("COM_CONTENT"));
				community.setComview(rset.getInt("COM_VIEW"));
				community.setComDate(rset.getDate("COM_DATE"));
				community.setComPhoto(rset.getString("COM_PHOTO"));
				community.setLikeCount(rset.getInt("LIKE_COUNT"));
				community.setUserNick(rset.getString("USER_NICK"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return community;
	}
	
	// 글 등록
	public int insertCommunity(Connection conn, Community community) {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO COMMUNITY VALUES(SEQ_COMNO.NEXTVAL, ?, ?, ?, ?, 0, SYSDATE, ?, 0, ?)";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, community.getComId());
			pstmt.setInt(2, community.getTagNo());
			pstmt.setString(3, community.getComTitle());
			pstmt.setString(4, community.getComContent());
			pstmt.setString(5, community.getComPhoto());
			pstmt.setString(6, community.getUserNick());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	// 게시물 수정
	public int updateCommunity(Connection conn, Community community) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE COMMUNITY SET TAG_NO=?, COM_TITLE=?, COM_CONTENT=?, COM_PHOTO=? WHERE COM_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, community.getTagNo());
			pstmt.setString(2, community.getComTitle());
			pstmt.setString(3, community.getComContent());
			pstmt.setString(4, community.getComPhoto());
			pstmt.setInt(5, community.getComNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int deleteNotice(Connection conn, int comNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = " DELETE FROM COMMUNITY WHERE COM_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int addReadCount(Connection conn, int comNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE COMMUNITY SET COM_VIEW = COM_VIEW + 1 WHERE COM_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	// 검색기능
	public ArrayList<Community> selectSearchList(Connection conn, String search, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY COM_NO DESC) AS NUM, COM_NO, COM_ID, TAG_NO, COM_TITLE, COM_CONTENT,COM_VIEW, COM_DATE, COM_PHOTO, LIKE_COUNT, USER_NICK  FROM COMMUNITY WHERE COM_TITLE LIKE ?) WHERE NUM BETWEEN ? AND ?";
		ArrayList<Community> cList = null;
		
		int recordCountPerPage = 10;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			cList = new ArrayList<Community>();
			
			while(rset.next()) {
				Community community = new Community();
				community.setComNo(rset.getInt("COM_NO"));
				community.setComId(rset.getString("COM_ID"));
				community.setTagNo(rset.getInt("TAG_NO"));
				community.setComTitle(rset.getString("COM_TITLE"));
				community.setComContent(rset.getString("COM_CONTENT"));
				community.setComview(rset.getInt("COM_VIEW"));
				community.setComDate(rset.getDate("COM_DATE"));
				community.setComPhoto(rset.getString("COM_PHOTO"));
				community.setLikeCount(rset.getInt("LIKE_COUNT"));
				community.setUserNick(rset.getString("USER_NICK"));
				
				cList.add(community);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return cList;
	}
	// 검색된 게시물의 페이지 네비
	public String getSearchPageNavi(Connection conn, String search, int currentPage) {
		int recordCountPerPage = 10; // 한 페이지에 출력되는 게시물의 수
		int naviCountPerPage = 10; // 페이지 네비 수
		int recordTotalCount = searchTotalCount(conn, search); // 게시물의 전체 갯수
		
		int pageTotalCount = 0; // 페이지의 전체 갯수
		
		// 123개의 게시물을 10개씩 보여준다라고 했을 때 페이지 전체 갯수는 13개
	      // 계산 도중 나머지가 발생하면 페이지의 갯수를 하나 더 추가
		if(recordTotalCount % recordCountPerPage > 0) {
	         pageTotalCount = recordTotalCount / recordCountPerPage + 1;         
		}else {
	         pageTotalCount = recordTotalCount / recordCountPerPage;
	    }
		
		// 현재 페이지의 값이 -1 혹은 0이 들어올 때
		if(currentPage < 1) {
	         currentPage = 1;
	      // 현재 페이지의 값이 총 페이지 개수보다 높을 때
	    } else if(currentPage > pageTotalCount) {
	         currentPage = pageTotalCount;
	    }
		
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
	      int endNavi = startNavi + naviCountPerPage - 1;
	      if(endNavi > pageTotalCount) {
	         endNavi = pageTotalCount;
	    }
	      
	    //이전 페이지, 다음 페이지
	    boolean needPrev = true;
	    boolean needNext = true;
	    
	    if(startNavi == 1) {
	    	needPrev = false;
	    }
	    if(endNavi == pageTotalCount) {
	    	needNext = false;
	    }
	    
	    //<a href='/notice/search?searchKeyword=" + search + "currentPage=" + i + "'>" + i + " 
	    // a 태그를 만드는 코드
	    StringBuilder sb = new StringBuilder();
	    if(needPrev) {
	    	sb.append("<a href='/community/search?searchKeyword="+ search + "&currentPage=" + (startNavi - 1) + "'>  이전  </a>");
	    }
	    for(int i = startNavi; i <= endNavi; i++) {
	    	sb.append("<a href='/community/search?searchKeyword=" + search + "&currentPage=" + i + "'> " + i + " </a>");
	    }
	    if(needNext) {
	    	sb.append("<a href='/community/search?searchKeyword=" + search + "&currentPage=" + (endNavi + 1) + "'>  다음  </a>");
	    }
	    
		return sb.toString();
	}
	
	// 검색된 게시물의 총 개수
	public int searchTotalCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
	    ResultSet rset = null;
	    String query = "SELECT COUNT(*) AS TOTALCOUNT FROM COMMUNITY WHERE COM_TITLE LIKE ?";
	    int recordTotalCount = 0;
	      
	    try {
	       pstmt = conn.prepareStatement(query);
	       pstmt.setString(1, "%" + search + "%");
	       rset = pstmt.executeQuery();
	       if(rset.next()) {
	            recordTotalCount = rset.getInt("TOTALCOUNT");      
	       }
	    } catch (SQLException e) {
	       e.printStackTrace();
	    } finally {
	       JDBCTemplate.close(rset);
	       JDBCTemplate.close(pstmt);
	    }
	    return recordTotalCount;
	}
}
