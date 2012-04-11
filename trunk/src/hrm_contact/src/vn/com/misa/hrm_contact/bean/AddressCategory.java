package vn.com.misa.hrm_contact.bean;

public class AddressCategory {
	private int iId;
	private String sCategoryname;
	public int getiId() {
		return iId;
	}
	public void setiId(int iId) {
		this.iId = iId;
	}
	public String getsCategoryname() {
		return sCategoryname;
	}
	public void setsCategoryname(String sCategoryname) {
		this.sCategoryname = sCategoryname;
	}
	public AddressCategory(int iId, String sCategoryname) {
		super();
		this.iId = iId;
		this.sCategoryname = sCategoryname;
	}
	public AddressCategory(String sCategoryname) {
		super();
		this.sCategoryname = sCategoryname;
	}
	public AddressCategory() {
		super();
	}
	
	
}
