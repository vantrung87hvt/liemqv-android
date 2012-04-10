package vn.com.misa.hrm_contact.xml;

import java.util.ArrayList;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import vn.com.misa.hrm_contact.bean.Column;
import vn.com.misa.hrm_contact.bean.Contact_;
import vn.com.misa.hrm_contact.bean.Table;

import android.content.Context;
import android.sax.Element;
import android.widget.Toast;

public class XmlToArrList {
	//create by
	//create date
	//modify by
	//modify date
	//parameter
	//lấy  
	public ArrayList<Contact_> getContactListFromXml(Context ctx, int resId)
	{
//		ArrayList<Contact> arrRes = null;
////		String strml = ReadXmlFromServer.getXML();
//		int intCount=0;
//		String xml = ReadXmlFromFile.readXmlFile(ctx, resId);
//	    Document doc = ParseXML.XMLfromString(xml);
//	    int numResults = doc.getChildNodes().getLength();
//	    if((numResults > 0)){
//	    	NodeList nodes = doc.getElementsByTagName("table");
//	    	arrRes = new ArrayList<Contact>();
//	    	//fill in the list items from the XML document
//			for (int i = 0; i < nodes.getLength(); i++) {
//				Element e = (Element)nodes.item(i);
//				
//				Contact ct = new Contact(Integer.parseInt(e.getChild("id").toString()), e.getChild("name").toString(), e.getChild("phone").toString(), e.getChild("email").toString(), false);
//				arrRes.add(ct);
//			}
//	    }
//	    return arrRes;
	    
	    
        Vector<Table> lstTable = new Vector<Table>();
        try {
	        	String xml = ReadXmlFromFile.readXmlFile(ctx, resId);
	    		Document documentDom = ParseXML.XMLfromString(xml);
	    		NodeList nodelistTable = documentDom.getElementsByTagName("table");
	    		Table tableTemp = null;
	    		String strTableName = "";
	    		NodeList nodelistColumn = null;
	    		Column columnTemp = null;
	    		if(nodelistTable.getLength() > 0)
	    		{
	    			for(int i=0; i<nodelistTable.getLength(); ++i)
	    			{
	    				strTableName = nodelistTable.item(i).getFirstChild().getNodeValue();
	    				tableTemp = new Table();
	    				tableTemp.setsTableName(strTableName);
	    				nodelistColumn = nodelistTable.item(i).getChildNodes();
	    				for(int j=1; j<nodelistColumn.getLength(); ++j)
	    				{
	    					columnTemp = new Column();
	    					
	    					columnTemp.setsCulumnname(nodelistColumn.item(i).getNodeValue());
	    				}
	    			}
	    		}
        }
	    catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
