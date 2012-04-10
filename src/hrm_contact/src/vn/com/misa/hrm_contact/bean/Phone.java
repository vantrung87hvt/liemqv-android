/**
 * Create by: liemqv
 * Create date: 04/10/2012 
 * Lớp Contact để chứ đối tượng Phone ánh xạ từ bảng tblPhone trong Database
 * */
package vn.com.misa.hrm_contact.bean;

public class Phone {
	private int iId;
	private String sNumber;
	private int iFK_Phone_categoryID;
	private int iFK_ContactID;
	public int getiId() {
		return iId;
	}
	public void setiId(int iId) {
		this.iId = iId;
	}
	public String getsNumber() {
		return sNumber;
	}
	public void setsNumber(String sNumber) {
		this.sNumber = sNumber;
	}
	public int getiFK_Phone_categoryID() {
		return iFK_Phone_categoryID;
	}
	public void setiFK_Phone_categoryID(int iFK_Phone_categoryID) {
		this.iFK_Phone_categoryID = iFK_Phone_categoryID;
	}
	public int getiFK_ContactID() {
		return iFK_ContactID;
	}
	public void setiFK_ContactID(int iFK_ContactID) {
		this.iFK_ContactID = iFK_ContactID;
	}
	public Phone(int _id, String sNumber, int iFK_Phone_categoryID,
			int iFK_ContactID) {
		super();
		this.iId = _id;
		this.sNumber = sNumber;
		this.iFK_Phone_categoryID = iFK_Phone_categoryID;
		this.iFK_ContactID = iFK_ContactID;
	}
	public Phone(String sNumber, int iFK_Phone_categoryID, int iFK_ContactID) {
		super();
		this.sNumber = sNumber;
		this.iFK_Phone_categoryID = iFK_Phone_categoryID;
		this.iFK_ContactID = iFK_ContactID;
	}
	public Phone() {
		super();
	}
	
	
}
