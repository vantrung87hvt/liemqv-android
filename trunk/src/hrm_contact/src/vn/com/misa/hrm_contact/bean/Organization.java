/**
 * Create by: liemqv
 * Create date: 04/10/2012 
 * Lớp Contact để chứ đối tượng Organization ánh xạ từ bảng tblOrganization trong Database
 * */
package vn.com.misa.hrm_contact.bean;

public class Organization {
	private int iId;
	private String sCompany;
	private String sTitle;
	private int iFK_ContactID;
	public int getiId() {
		return iId;
	}
	public void setiId(int iId) {
		this.iId = iId;
	}
	public String getsCompany() {
		return sCompany;
	}
	public void setsCompany(String sCompany) {
		this.sCompany = sCompany;
	}
	public String getsTitle() {
		return sTitle;
	}
	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}
	public int getiFK_ContactID() {
		return iFK_ContactID;
	}
	public void setiFK_ContactID(int iFK_ContactID) {
		this.iFK_ContactID = iFK_ContactID;
	}
	public Organization(int iId, String sCompany, String sTitle,
			int iFK_ContactID) {
		super();
		this.iId = iId;
		this.sCompany = sCompany;
		this.sTitle = sTitle;
		this.iFK_ContactID = iFK_ContactID;
	}
	public Organization(String sCompany, String sTitle, int iFK_ContactID) {
		super();
		this.sCompany = sCompany;
		this.sTitle = sTitle;
		this.iFK_ContactID = iFK_ContactID;
	}
	public Organization() {
		super();
	}
	
	
}
