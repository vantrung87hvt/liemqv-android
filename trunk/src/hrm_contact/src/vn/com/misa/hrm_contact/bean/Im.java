/**
 * Create by: liemqv
 * Create date: 04/10/2012 
 * Lớp Contact để chứ đối tượng Im ánh xạ từ bảng tblIm trong Database
 * */
package vn.com.misa.hrm_contact.bean;

public class Im {
	private int iId;
	private String sIm;
	private int iFK_Im_categoryID;
	private int iFK_ContactID;
	public int getiId() {
		return iId;
	}
	public void setiId(int iId) {
		this.iId = iId;
	}
	public String getsIm() {
		return sIm;
	}
	public void setsIm(String sIm) {
		this.sIm = sIm;
	}
	public int getiFK_Im_categoryID() {
		return iFK_Im_categoryID;
	}
	public void setiFK_Im_categoryID(int iFK_Im_categoryID) {
		this.iFK_Im_categoryID = iFK_Im_categoryID;
	}
	public int getiFK_ContactID() {
		return iFK_ContactID;
	}
	public void setiFK_ContactID(int iFK_ContactID) {
		this.iFK_ContactID = iFK_ContactID;
	}
	public Im(int iId, String sIm, int iFK_Im_categoryID, int iFK_ContactID) {
		super();
		this.iId = iId;
		this.sIm = sIm;
		this.iFK_Im_categoryID = iFK_Im_categoryID;
		this.iFK_ContactID = iFK_ContactID;
	}
	public Im(String sIm, int iFK_Im_categoryID, int iFK_ContactID) {
		super();
		this.sIm = sIm;
		this.iFK_Im_categoryID = iFK_Im_categoryID;
		this.iFK_ContactID = iFK_ContactID;
	}
	public Im() {
		super();
	}
	
	
}
