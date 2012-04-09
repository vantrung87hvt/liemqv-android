package vn.com.misa.hrm_contact.bean;


public class Contact {
	private int iID;
	private String sName;
	private String sPhone;
	private String sEmail;
	private boolean bIsselected;
	
	public Contact(){}
	
	public Contact(String _sName, String _sPhone, String _sEmail, boolean _bIsSelected)
	{
		this.sName = _sName;
		this.sPhone = _sPhone;
		this.sEmail = _sEmail;
		this.bIsselected = _bIsSelected;
	}
	
	public Contact(int _id, String _sName, String _sPhone, String _sEmail, boolean _bIsSelected)
	{
		this.iID = _id;
		this.sName = _sName;
		this.sPhone = _sPhone;
		this.sEmail = _sEmail;
		this.bIsselected = _bIsSelected;
	}
	
	
	public int getiID() {
		return iID;
	}

	public void setiID(int iID) {
		this.iID = iID;
	}

	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsPhone() {
		return sPhone;
	}
	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}
	public String getsEmail() {
		return sEmail;
	}
	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}
	public boolean isbIsselected() {
		return bIsselected;
	}
	public void setbIsselected(boolean bIsselected) {
		this.bIsselected = bIsselected;
	}
	
	public String toText()
	{
		String sRes = "";
		sRes += "+)Name: " + this.sName;
		sRes += "\n+)Phone: " + this.sPhone;
		sRes += "\n+)Email: " + this.sEmail;
		return sRes;
	}

}
