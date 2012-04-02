package vn.com.misa.hrm_contact;

import java.util.ArrayList;
import java.util.Vector;

import vn.com.misa.hrm_contact.activity.ContactActivity;
import vn.com.misa.hrm_contact.activity.DemoActivity;
import vn.com.misa.hrm_contact.activity.FavoritestActivity;
import vn.com.misa.hrm_contact.model.Contact;
import vn.com.misa.hrm_contact.model.ContactAdapter;
import android.R.bool;
import android.app.Activity;
import android.app.ListActivity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class Hrm_contactActivity  extends TabActivity {
    /** Called when the activity is first created. */
	public static final String PREFS_NAME = "ContactPreFile";
	
	private static final int MENU_ITEM_1 = Menu.FIRST + 1;
    private static final int MENU_ITEM_2 = Menu.FIRST + 2;
    private static final int MENU_ITEM_3 = Menu.FIRST + 3;
    
    Toast msg;
    
    ArrayList<Contact> contacts;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab
        
     // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, DemoActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("phone").setIndicator("Phone",
                          res.getDrawable(R.drawable.tab_phone))
                      .setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, DemoActivity.class);
        spec = tabHost.newTabSpec("call_log").setIndicator("Call log",
                          res.getDrawable(R.drawable.tab_call_log))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, ContactActivity.class);
        spec = tabHost.newTabSpec("contact").setIndicator("Contacts",
                          res.getDrawable(R.drawable.tab_contact))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, FavoritestActivity.class);
        spec = tabHost.newTabSpec("favorites").setIndicator("Favorites",
                          res.getDrawable(R.drawable.tab_favorites))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(2); //Set list Contact display first
        
        
        /* gọi hiển thị danh bạ */
//        initDefaultValue();
//        displayContact();
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
        listView.setAdapter(new ContactAdapter(Hrm_contactActivity.this, android.R.layout.simple_list_item_1, contacts));
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
			case MENU_ITEM_1:
	            addContact();
	            break;
           case MENU_ITEM_2:
                msg = Toast.makeText(this, "Menu Item 2 Clicked", Toast.LENGTH_LONG);
                msg.show();
                break;
           case MENU_ITEM_3:
        	   msg = Toast.makeText(this, "Menu Item 3 Clicked", Toast.LENGTH_LONG);
               msg.show();
               break;         
        }
        return (super.onOptionsItemSelected(item));
    }
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mSearch = menu.add(0, 1, Menu.FIRST, "Search");
        mSearch.setIcon(R.drawable.ic_launcher);
        MenuItem mAdd = menu.add(0, 2, 0, "New Contact");
        mAdd.setIcon(R.drawable.ic_launcher);
        MenuItem mIO = menu.add(0, 2, 0, "Import/Export");
        mIO.setIcon(R.drawable.ic_launcher);
        return true;
    }
    
//    @Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		String item = (String) getListAdapter().getItem(position);
//		Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
//	}
//    

}