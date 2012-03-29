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
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchContactActivity extends Activity {
public static final String PREFS_NAME = "ContactPreFile";
	
	private static final int iNewContact = Menu.FIRST + 1;
	private static final int iSearch = Menu.FIRST + 2;
	private static final int iDisplayOption = Menu.FIRST + 3;
	private static final int iAccount = Menu.FIRST + 4;
    private static final int iIOContact = Menu.FIRST + 5;
    
    ListView listView;
    EditText txtKey;
    TextView tvInfor;
    Toast msg;
    ArrayList<Contact> contacts;
    ArrayList<Contact> contactsRes;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_contact);
        
        /* gọi hiển thị danh bạ */
        initForm();
        initDefaultValue();
        /* Hiển thị theo key code truyền sang */
        Bundle extras = getIntent().getExtras();
        if(extras !=null) 
        {
        	String value = extras.getString("strKey");
        	String sKey = String.valueOf(value);
        	msg = Toast.makeText(this, "KeyCode: " + value, Toast.LENGTH_LONG);
            msg.show();
        	txtKey.setText(sKey);
        	displayContactByKey(sKey);
        }
        else
        {
        	displayContact();
        }
    }
    
    public void initForm()
    {
    	listView = (ListView) findViewById(R.id.ListContact);
    	tvInfor = (TextView) findViewById(R.id.search_infor);
    	txtKey = (EditText) findViewById(R.id.etKey);
    	txtKey.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				String sKey = txtKey.getText().toString();
				displayContactByKey(sKey);
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
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
    }
    
    public ArrayList<Contact> contactByKey(String key)
    {
    	ArrayList<Contact> res = new ArrayList<Contact>();
    	
    	for(int i=0;i<contacts.size();++i)
    	{
    		//|| contacts.get(i).getsEmail().contains(key) || contacts.get(i).getsPhone().contains(key)
    		if(contacts.get(i).getsName().toLowerCase().contains(key.toLowerCase()))
    		{
    			res.add(contacts.get(i));
    		}
    	}
    	
    	return res;
    }
    
    public void displayContact()
    {
        listView.setAdapter(new ContactAdapter(SearchContactActivity.this, android.R.layout.simple_list_item_1, contacts));
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
            	showContactDetails(position);
            }
          });
    }
    
    public void displayContactByKey(String key)
    {
    	contactsRes = null;
    	contactsRes = contactByKey(key);
        listView.setAdapter(new ContactAdapter(SearchContactActivity.this, android.R.layout.simple_list_item_1, contactsRes));
        tvInfor.setText("Result " + contactsRes.size() + " contact");
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
            	showContactDetails(position);
            }
          });
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
