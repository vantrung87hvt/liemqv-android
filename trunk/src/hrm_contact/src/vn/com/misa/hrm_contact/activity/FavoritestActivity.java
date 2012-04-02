package vn.com.misa.hrm_contact.activity;

import java.util.ArrayList;
import java.util.Collections;

import vn.com.misa.hrm_contact.NewContact;
import vn.com.misa.hrm_contact.R;
import vn.com.misa.hrm_contact.model.Contact;
import vn.com.misa.hrm_contact.model.ContactAdapter;
import vn.com.misa.hrm_contact.xml.XmlToArrList;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FavoritestActivity extends Activity {
public static final String PREFS_NAME = "ContactPreFile";
	
	private static final int iNewContact = Menu.FIRST + 1;
	private static final int iSearch = Menu.FIRST + 2;
	private static final int iDisplayOption = Menu.FIRST + 3;
	private static final int iAccount = Menu.FIRST + 4;
    private static final int iIOContact = Menu.FIRST + 5;
    
    ListView listView;
    Toast msg;
    ContactAdapter _contacAdapter;
    
    ArrayList<Contact> contacts;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_contact);
        
        /* gọi hiển thị danh bạ */
        initForm();
        initDefaultValue();
        displayContact();
    }
    
    public void initForm()
    {
    	listView = (ListView) findViewById(R.id.ListContact);
    	listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
            	showContactDetails(position);
            }
          });
    }
    
    public void initDefaultValue()
    {
    	contacts = new ArrayList<Contact>();
    	Contact c1 = new Contact("Quang Liem", "0989320758", "liemqv@gmail.com", false);
        Contact c2 = new Contact("Tran Quang Trung", "098932077", "trungtq@gmail.com", false);
        Contact c3 = new Contact("Bui Viet Anh", "0989320758", "anhbv@gmail.com", false);
        Contact c4 = new Contact("Phan Van Anh", "098932077", "anhpv@gmail.com", false);
        Contact c5 = new Contact("Tran Trung Dung", "0989320758", "dungtt@gmail.com", false);
        Contact c6 = new Contact("Be Va Khanh", "098932077", "khanhbv@gmail.com", false);
        contacts.add(c1);
        contacts.add(c2);
        contacts.add(c3);
        contacts.add(c4);
        contacts.add(c5);
        
//        contacts = new XmlToArrList().getContactListFromXml();
		Toast.makeText(this, "Count: " + contacts.size(), Toast.LENGTH_LONG).show();
        //------
        _contacAdapter = new ContactAdapter(FavoritestActivity.this, android.R.layout.simple_list_item_1, contacts);
    }
    
    public void displayContact()
    {
    	_contacAdapter.setArrContact(contacts);
        listView.setAdapter(_contacAdapter);
    }
    
    public void showContactDetails(int _id)
    {
    	Intent contactDetails = new Intent(this, ContactDetailsActivity.class);
        startActivity(contactDetails);
    }
    
    public void addContact()
    {
    	Contact obj = new Contact("Bui Thanh Minh", "098932077", "minhbt@gmail.com", false);
    	contacts.add(obj);
    	displayContact();
    	
//    	Intent newContact = new Intent(this, NewContact.class);
//        startActivity(newContact);
//        
//        //Get data from NewContact Activity
//        SharedPreferences dataContact = getSharedPreferences(PREFS_NAME, 0);
//        
//        String sStatus = dataContact.getString("sSatus", "open");
//        while(sStatus.equals("open"))
//        {
//        	
//        }
//        String sName = dataContact.getString("sName", "No Name");
//        String sPhone = dataContact.getString("sPhone", "No phone number");
//        String sEmail = dataContact.getString("sEmail", "No Email");
//        
//    	Contact objContact = new Contact(sName, sPhone, sEmail, false);
//    	contacts.add(objContact);
//    	displayContact();
    }
    
    @Override
    public boolean onContextItemSelected (MenuItem item) {
        super.onContextItemSelected(item);
//        String str = (String) item.getTitle();
//		Toast.makeText(this, "select: " + str, Toast.LENGTH_LONG).show();
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
	{
    	switch (item.getItemId())
    	{
			case iNewContact:
	            addContact();
	            break;
			case iIOContact:
                
                break;
			case iSearch:
        	   searchContact();
               break;         
        }
        return (super.onOptionsItemSelected(item));
    }
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mSearch = menu.add(0, iSearch, iSearch, "Search");
        mSearch.setIcon(R.drawable.search);
        MenuItem mAdd = menu.add(0, iNewContact, iNewContact, "New Contact");
        mAdd.setIcon(R.drawable.new_contact);
        
        MenuItem mDisplay = menu.add(1, iDisplayOption, iDisplayOption, "Display Options");
        mDisplay.setIcon(R.drawable.display_option);
        MenuItem mAccount = menu.add(1, iAccount, iAccount, "Accounts");
        mAccount.setIcon(R.drawable.accounts);
        MenuItem mIO = menu.add(1, iIOContact, iIOContact, "Import/Export");
        mIO.setIcon(R.drawable.import_export);
        return true;
    }
    
    public void searchContact()
    {
    	Intent searchContact = new Intent(this, SearchContactActivity.class);
        startActivity(searchContact);
    }
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if((keyCode >= 16 && keyCode <= 25) || (keyCode >= 30 && keyCode <= 38) || (keyCode >= 44 && keyCode <= 50))
		{
			Intent searchContact = new Intent(this, SearchContactActivity.class);
	        searchContact.putExtra("strKey", keyCode);
	        startActivity(searchContact);
		}
		return super.onKeyDown(keyCode, event);
	}

}
