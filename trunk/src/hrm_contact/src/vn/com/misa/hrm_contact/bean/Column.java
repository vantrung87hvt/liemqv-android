package vn.com.misa.hrm_contact.bean;
/*
 * Lớp coulumn để tạo các cột cho lớp table
 * */
public class Column {
	private String sCulumnname; //Tên cột
	private String sDataType; //Kiểu dữ liệu
	private boolean isIdentity; //Cho phép tăng ?
	private boolean isPrimarykey; //Khóa chính ?
	private boolean isNull; //Cho phép null ?
	private String foreign_key; //Khóa ngoại
	public String getsCulumnname() {
		return sCulumnname;
	}
	public void setsCulumnname(String sCulumnname) {
		this.sCulumnname = sCulumnname;
	}
	public String getsDataType() {
		return sDataType;
	}
	public void setsDataType(String sDataType) {
		this.sDataType = sDataType;
	}
	public boolean isPrimarykey() {
		return isPrimarykey;
	}
	public void setPrimarykey(boolean isPrimarykey) {
		this.isPrimarykey = isPrimarykey;
	}
	public boolean isNull() {
		return isNull;
	}
	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}
	public String getForreign_key() {
		return foreign_key;
	}
	public void setForreign_key(String forreign_key) {
		this.foreign_key = forreign_key;
	}
	
	
	public boolean isIdentity() {
		return isIdentity;
	}
	public void setIdentity(boolean isIdentity) {
		this.isIdentity = isIdentity;
	}
	public Column(String sCulumnname, String sDataType, boolean _isIdentity, boolean isPrimarykey,
			boolean isNull, String forreign_key) {
		super();
		this.sCulumnname = sCulumnname;
		this.sDataType = sDataType;
		this.isIdentity = _isIdentity;
		this.isPrimarykey = isPrimarykey;
		this.isNull = isNull;
		this.foreign_key = forreign_key;
	}
	public Column() {
		super();
	}
	
}
