/**
 * Create by: liemqv
 * Create date: 04/10/2012 
 * Lớp Contact để chứ đối tượng Email ánh xạ từ bảng tblEmail trong Database
 * */
package vn.com.misa.hrm_contact.bean;

public class Email {
	private int iId;
	private String sEmail;
	private int iKF_Email_categoryID;
	private int iFK_ContactID;
	public int getiId() {
		return iId;
	}
	public void setiId(int iId) {
		this.iId = iId;
	}
	public String getsEmail() {
		return sEmail;
	}
	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}
	public int getiKF_Email_categoryID() {
		return iKF_Email_categoryID;
	}
	public void setiKF_Email_categoryID(int iKF_Email_categoryID) {
		this.iKF_Email_categoryID = iKF_Email_categoryID;
	}
	public int getiFK_ContactID() {
		return iFK_ContactID;
	}
	public void setiFK_ContactID(int iFK_ContactID) {
		this.iFK_ContactID = iFK_ContactID;
	}
	public Email(int _id, String sEmail, int iKF_Email_categoryID,
			int iFK_ContactID) {
		super();
		this.iId = _id;
		this.sEmail = sEmail;
		this.iKF_Email_categoryID = iKF_Email_categoryID;
		this.iFK_ContactID = iFK_ContactID;
	}
	public Email(String sEmail, int iKF_Email_categoryID, int iFK_ContactID) {
		super();
		this.sEmail = sEmail;
		this.iKF_Email_categoryID = iKF_Email_categoryID;
		this.iFK_ContactID = iFK_ContactID;
	}
	public Email() {
		super();
	}
	
	
}
