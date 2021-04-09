package petdiary.model.vo;

import java.util.ArrayList;


public class PetDiaryPage {
	private ArrayList<PetDiary> pList;
	private String pageNavi;
	
	public PetDiaryPage() {}

	public ArrayList<PetDiary> getpList() {
		return pList;
	}

	public void setpList(ArrayList<PetDiary> pList) {
		this.pList = pList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}
