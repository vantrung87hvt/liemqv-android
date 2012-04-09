package vn.com.misa.hrm_contact.xml;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import vn.com.misa.hrm_contact.bean.Contact;

import android.sax.Element;
import android.widget.Toast;

public class XmlToArrList {
	public ArrayList<Contact> getContactListFromXml()
	{
		ArrayList<Contact> arrRes = null;
		String xml = ReadXmlFromServer.getXML();
	    Document doc = ParseXML.XMLfromString(xml);
	    int numResults = doc.getChildNodes().getLength();
	    if((numResults > 0)){
	    	NodeList nodes = doc.getElementsByTagName("contact");
	    	arrRes = new ArrayList<Contact>();
	    	//fill in the list items from the XML document
			for (int i = 0; i < nodes.getLength(); i++) {
				Element e = (Element)nodes.item(i);
				
				Contact ct = new Contact(Integer.parseInt(e.getChild("id").toString()), e.getChild("name").toString(), e.getChild("phone").toString(), e.getChild("email").toString(), false);
				arrRes.add(ct);
			}
	    }
	    return arrRes;
	}
}
