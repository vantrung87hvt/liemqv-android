/**
 * Create by: liemqv
 * Create date: 04/10/2012 
 * Lớp Contact để chứ đối tượng Im_category ánh xạ từ bảng tblIm_category trong Database
 * */
package vn.com.misa.hrm_contact.bean;

public class Im_category {
	private int iId;
	private String sCategoryname;
	private String sSortname;
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
	public String getsSortname() {
		return sSortname;
	}
	public void setsSortname(String sSortname) {
		this.sSortname = sSortname;
	}
	public Im_category(int iId, String sCategoryname, String sSortname) {
		super();
		this.iId = iId;
		this.sCategoryname = sCategoryname;
		this.sSortname = sSortname;
	}
	public Im_category(String sCategoryname, String sSortname) {
		super();
		this.sCategoryname = sCategoryname;
		this.sSortname = sSortname;
	}
	public Im_category() {
		super();
	}
	
	
}
