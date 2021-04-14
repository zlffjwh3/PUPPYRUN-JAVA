package matching.model.vo;

import java.util.ArrayList;

public class MatchingPage {
	private ArrayList<Matching> mList;
	private String pageNavi;
	
	public MatchingPage() {}

	public ArrayList<Matching> getmList() {
		return mList;
	}

	public void setmList(ArrayList<Matching> mList) {
		this.mList = mList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}
