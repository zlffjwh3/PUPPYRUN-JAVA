package petdiary.model.vo;

import java.util.ArrayList;

public class GoalPage {
	private ArrayList<Goal> gList;
	private String pageNavi;
	
	public GoalPage() {}

	public ArrayList<Goal> getgList() {
		return gList;
	}

	public void setgList(ArrayList<Goal> gList) {
		this.gList = gList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

	@Override
	public String toString() {
		return "GoalPage [gList=" + gList + ", pageNavi=" + pageNavi + "]";
	}
}
