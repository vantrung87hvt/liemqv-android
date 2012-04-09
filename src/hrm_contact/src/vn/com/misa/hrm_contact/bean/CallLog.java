package vn.com.misa.hrm_contact.bean;

public class CallLog {
	private String sName;
	private int iCount;
	private String sType;
	private String sNumber;
	private String sTime;
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public int getiCount() {
		return iCount;
	}
	public void setiCount(int iCount) {
		this.iCount = iCount;
	}
	public String getsType() {
		return sType;
	}
	public void setsType(String sType) {
		this.sType = sType;
	}
	public String getsNumber() {
		return sNumber;
	}
	public void setsNumber(String sNumber) {
		this.sNumber = sNumber;
	}
	public String getsTime() {
		return sTime;
	}
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}
	public CallLog(String sName, int iCount, String sType, String sNumber,
			String sTime) {
		super();
		this.sName = sName;
		this.iCount = iCount;
		this.sType = sType;
		this.sNumber = sNumber;
		this.sTime = sTime;
	}
	public CallLog() {
		super();
	}
	
	
}
