package community.model.vo;

import java.util.ArrayList;


public class CommunityPage {
	private ArrayList<Community> cList;
	private String pageNavi;
	
	public CommunityPage() {}

	public ArrayList<Community> getcList() {
		return cList;
	}

	public void setcList(ArrayList<Community> cList) {
		this.cList = cList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}
