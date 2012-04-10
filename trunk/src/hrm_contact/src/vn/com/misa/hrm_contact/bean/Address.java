/**
 * Create by: liemqv
 * Create date: 04/10/2012 
 * Lớp Contact để chứ đối tượng Address ánh xạ từ bảng tblAddress trong Database
 * */
package vn.com.misa.hrm_contact.bean;

public class Address {
	private int iId;
	private String sStreet;
	private String sCity;
	private String sState;
	private String sZipcode;
	private int iFK_Address_categoryID;
	private int iFK_iContactID;
	public int getiId() {
		return iId;
	}
	public void setiId(int iId) {
		this.iId = iId;
	}
	public String getsStreet() {
		return sStreet;
	}
	public void setsStreet(String sStreet) {
		this.sStreet = sStreet;
	}
	public String getsCity() {
		return sCity;
	}
	public void setsCity(String sCity) {
		this.sCity = sCity;
	}
	public String getsState() {
		return sState;
	}
	public void setsState(String sState) {
		this.sState = sState;
	}
	public String getsZipcode() {
		return sZipcode;
	}
	public void setsZipcode(String sZipcode) {
		this.sZipcode = sZipcode;
	}
	public int getiFK_Address_categoryID() {
		return iFK_Address_categoryID;
	}
	public void setiFK_Address_categoryID(int iFK_Address_categoryID) {
		this.iFK_Address_categoryID = iFK_Address_categoryID;
	}
	public int getiFK_iContactID() {
		return iFK_iContactID;
	}
	public void setiFK_iContactID(int iFK_iContactID) {
		this.iFK_iContactID = iFK_iContactID;
	}
	public Address(int _id, String sStreet, String sCity,
			String sState, String sZipcode, int iFK_Address_categoryID,
			int iFK_iContactID) {
		super();
		this.iId = _id;
		this.sStreet = sStreet;
		this.sCity = sCity;
		this.sState = sState;
		this.sZipcode = sZipcode;
		this.iFK_Address_categoryID = iFK_Address_categoryID;
		this.iFK_iContactID = iFK_iContactID;
	}
	public Address(String sStreet, String sCity, String sState,
			String sZipcode, int iFK_Address_categoryID, int iFK_iContactID) {
		super();
		this.sStreet = sStreet;
		this.sCity = sCity;
		this.sState = sState;
		this.sZipcode = sZipcode;
		this.iFK_Address_categoryID = iFK_Address_categoryID;
		this.iFK_iContactID = iFK_iContactID;
	}
	public Address() {
		super();
	}
	
	
}
