/**
 * Create by: liemqv
 * Create date: 04/10/2012 
 * Lớp Contact để chứ đối tượng Email_category ánh xạ từ bảng tblEmail_category trong Database
 * */
package vn.com.misa.hrm_contact.bean;

public class EmailCategory {
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
	public EmailCategory(String sCategoryname) {
		super();
		this.sCategoryname = sCategoryname;
	}
	public EmailCategory() {
		super();
	}
	
	
}
