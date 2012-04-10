/**
 * Create by: liemqv
 * Create date: 04/10/2012 
 * Lớp Contact để chứ đối tượng contact ánh xạ từ bảng tblContact trong Database
 * */
package vn.com.misa.hrm_contact.bean;

public class Contact {
	private int iId;
	private String sFirstname;
	private String sLastname;
	private int iGender;
	private String sNotes;
	private String sNickname;
	private byte[] bAvatar;
	
	public String getsFirstname() {
		return sFirstname;
	}
	public void setsFirstname(String sFirstname) {
		this.sFirstname = sFirstname;
	}
	public String getsLastname() {
		return sLastname;
	}
	public void setsLastname(String sLastname) {
		this.sLastname = sLastname;
	}
	public int getiGender() {
		return iGender;
	}
	public void setiGender(int iGender) {
		this.iGender = iGender;
	}
	public String getsNotes() {
		return sNotes;
	}
	public void setsNotes(String sNotes) {
		this.sNotes = sNotes;
	}
	public String getsNickname() {
		return sNickname;
	}
	public void setsNickname(String sNickname) {
		this.sNickname = sNickname;
	}
	public byte[] getbAvatar() {
		return bAvatar;
	}
	public void setbAvatar(byte[] bAvatar) {
		this.bAvatar = bAvatar;
	}
	
	public int getiId() {
		return iId;
	}
	public void setiId(int iId) {
		this.iId = iId;
	}
	public Contact(int _id, String sFirstname, String sLastname, int iGender,
			String sNotes, String sNickname, byte[] bAvatar) {
		super();
		this.iId = _id;
		this.sFirstname = sFirstname;
		this.sLastname = sLastname;
		this.iGender = iGender;
		this.sNotes = sNotes;
		this.sNickname = sNickname;
		this.bAvatar = bAvatar;
	}
	
	public Contact(String sFirstname, String sLastname, int iGender,
			String sNotes, String sNickname, byte[] bAvatar) {
		super();
		this.sFirstname = sFirstname;
		this.sLastname = sLastname;
		this.iGender = iGender;
		this.sNotes = sNotes;
		this.sNickname = sNickname;
		this.bAvatar = bAvatar;
	}
	
	public Contact() {
	}
}
