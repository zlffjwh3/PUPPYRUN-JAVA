package notice.model.vo;

import java.util.ArrayList;

public class NoticePage {
	private ArrayList<Notice> nList;
	private String pageNavi;
	
	public NoticePage() {}

	public ArrayList<Notice> getnList() {
		return nList;
	}

	public void setnList(ArrayList<Notice> nList) {
		this.nList = nList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}
