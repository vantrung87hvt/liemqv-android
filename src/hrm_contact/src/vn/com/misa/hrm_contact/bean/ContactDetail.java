package vn.com.misa.hrm_contact.bean;

import android.R.bool;

public class ContactDetail {
	private String sTitle;
	private String sValue;
	private Boolean isPhone;
	private Boolean isMail;
	public String getsTitle() {
		return sTitle;
	}
	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}
	public String getsValue() {
		return sValue;
	}
	public void setsValue(String sValue) {
		this.sValue = sValue;
	}
	public Boolean getIsPhone() {
		return isPhone;
	}
	public void setIsPhone(Boolean isPhone) {
		this.isPhone = isPhone;
	}
	public Boolean getIsMail() {
		return isMail;
	}
	public void setIsMail(Boolean isMail) {
		this.isMail = isMail;
	}
	public ContactDetail(String sTitle, String sValue, Boolean isPhone,
			Boolean isMail) {
		super();
		this.sTitle = sTitle;
		this.sValue = sValue;
		this.isPhone = isPhone;
		this.isMail = isMail;
	}
	public ContactDetail() {
		
	}
	
}
