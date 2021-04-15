package user.model.vo;

import java.util.ArrayList;

public class UserPage {
	private ArrayList<User> uList;
	private String pageNavi;
	
	public UserPage() {}

	public ArrayList<User> getuList() {
		return uList;
	}

	public void setuList(ArrayList<User> uList) {
		this.uList = uList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

	@Override
	public String toString() {
		return "UserPage [uList=" + uList + ", pageNavi=" + pageNavi + "]";
	}
}
