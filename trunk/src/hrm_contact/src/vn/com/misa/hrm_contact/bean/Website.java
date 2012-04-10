/**
 * Create by: liemqv
 * Create date: 04/10/2012 
 * Lớp Contact để chứ đối tượng Website ánh xạ từ bảng tblWebsite trong Database
 * */
package vn.com.misa.hrm_contact.bean;

public class Website {
	private int iId;
	private String sWebsite;
	private int iFK_ContactID;
	public int getiId() {
		return iId;
	}
	public void setiId(int iId) {
		this.iId = iId;
	}
	public String getsWebsite() {
		return sWebsite;
	}
	public void setsWebsite(String sWebsite) {
		this.sWebsite = sWebsite;
	}
	public int getiFK_ContactID() {
		return iFK_ContactID;
	}
	public void setiFK_ContactID(int iFK_ContactID) {
		this.iFK_ContactID = iFK_ContactID;
	}
	public Website(int iId, String sWebsite, int iFK_ContactID) {
		super();
		this.iId = iId;
		this.sWebsite = sWebsite;
		this.iFK_ContactID = iFK_ContactID;
	}
	public Website(String sWebsite, int iFK_ContactID) {
		super();
		this.sWebsite = sWebsite;
		this.iFK_ContactID = iFK_ContactID;
	}
	public Website() {
		super();
	}
	
}
