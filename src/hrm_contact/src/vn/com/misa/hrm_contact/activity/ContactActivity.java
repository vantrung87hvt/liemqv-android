package vn.com.misa.hrm_contact.activity;

import java.util.ArrayList;

import vn.com.misa.hrm_contact.NewContact;
import vn.com.misa.hrm_contact.R;
import vn.com.misa.hrm_contact.model.Contact;
import vn.com.misa.hrm_contact.model.ContactAdapter;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

public class ContactActivity extends Activity {
public static final String PREFS_NAME = "ContactPreFile";
	
	private static final int iNewContact = Menu.FIRST + 1;
	private static final int iSearch = Menu.FIRST + 2;
	private static final int iDisplayOption = Menu.FIRST + 3;
	private static final int iAccount = Menu.FIRST + 4;
    private static final int iIOContact = Menu.FIRST + 5;
    
    
    Toast msg;
    
    ArrayList<Contact> contacts;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_contact);
        
        /* gọi hiển thị danh bạ */
        initDefaultValue();
        displayContact();
    }
    
    public void initDefaultValue()
    {
    	contacts = new ArrayList<Contact>();
    	Contact c1 = new Contact("Quang Liem", "0989320758", "liemqv@gmail.com", false);
        Contact c2 = new Contact("Quang Trung", "098932077", "trungtq@gmail.com", false);
        contacts.add(c1);
        contacts.add(c2);
         
    }
    
    public void displayContact()
    {
    	ListView listView = (ListView) findViewById(R.id.ListContact);
        listView.setAdapter(new ContactAdapter(ContactActivity.this, android.R.layout.simple_list_item_1, contacts));
    }
    
    
    public void addContact()
    {
//    	Contact obj = new Contact("Bui Thanh Minh", "098932077", "minhbt@gmail.com", false);
//    	contacts.add(obj);
//    	displayContact();
    	
    	Intent newContact = new Intent(this, NewContact.class);
        startActivity(newContact);
        
        //Get data from NewContact Activity
        SharedPreferences dataContact = getSharedPreferences(PREFS_NAME, 0);
        
        String sStatus = dataContact.getString("sSatus", "open");
        while(sStatus.equals("open"))
        {
        	
        }
        String sName = dataContact.getString("sName", "No Name");
        String sPhone = dataContact.getString("sPhone", "No phone number");
        String sEmail = dataContact.getString("sEmail", "No Email");
        
    	Contact objContact = new Contact(sName, sPhone, sEmail, false);
    	contacts.add(objContact);
    	displayContact();
    }
    
    @Override
    public boolean onContextItemSelected (MenuItem item) {
        super.onContextItemSelected(item);
        String str = (String) item.getTitle();
		Toast.makeText(this, "select: " + str, Toast.LENGTH_LONG).show();
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
                msg = Toast.makeText(this, "Menu Item 2 Clicked", Toast.LENGTH_LONG);
                msg.show();
                break;
           case iSearch:
        	   msg = Toast.makeText(this, "Menu Item 3 Clicked", Toast.LENGTH_LONG);
               msg.show();
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
}
