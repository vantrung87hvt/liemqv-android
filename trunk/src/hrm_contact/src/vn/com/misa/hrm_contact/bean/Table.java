package vn.com.misa.hrm_contact.bean;

import java.util.Vector;
/*
 * Lớp này thể hiện cho bảng, có tên bảng, danh sách các cột
 * */
public class Table {
	private String sTableName; //Tên bảng
	private Vector<Column> lstColumn; //Danh sách các cột
	public String getsTableName() {
		return sTableName;
	}
	public void setsTableName(String sTableName) {
		this.sTableName = sTableName;
	}
	public Vector<Column> getLstColumn() {
		return lstColumn;
	}
	public void setLstColumn(Vector<Column> lstColumn) {
		this.lstColumn = lstColumn;
	}
	public Table(String sTableName, Vector<Column> lstColumn) {
		super();
		this.sTableName = sTableName;
		this.lstColumn = lstColumn;
	}
	public Table() {
		super();
	}
	
	public String toStringCreateTable()
	{
		String sRes = "";
		
		sRes += "create table + " + this.sTableName + "(";
		
		for(int i=0; i<lstColumn.size()-1; ++i)
		{
			Column columnTmp = lstColumn.get(i);
			sRes += columnTmp.getsCulumnname();
			sRes += " " + columnTmp.getsDataType();
			if(columnTmp.isPrimarykey() == true)
			{
				sRes += "primary key";
			}
			if(columnTmp.isIdentity() == true)
			{
				sRes += " autoincrement";
			}
			if(columnTmp.isNull() == false)
			{
				sRes += " not null";
			}
			if(!columnTmp.getForreign_key().equals(""))
			{
				sRes += columnTmp.getForreign_key();
			}
		}
		
		sRes += ");";
		
		return sRes;
		
	}
}
