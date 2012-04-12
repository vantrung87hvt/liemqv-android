/**
 * Create by: liemqv
 * Create date: 29/03/2012 
 * Acitvity này dùng để hiển thị danh sách contact và thực hiện các tác vụ có trong menu
 * Modify by: liemqv
 * Modify date: 12/04/2012 
 * Modify: thử nghiệm database mới, thay Activity NewContactActivity thành AddContactActivity
 * */
package vn.com.misa.hrm_contact.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vn.com.misa.hrm_contact.R;
import vn.com.misa.hrm_contact.bean.Contact_;
import vn.com.misa.hrm_contact.bean.ContactAdapter;
import vn.com.misa.hrm_contact.sql.ContactDataSource;
import vn.com.misa.hrm_contact.xml.XmlToArrList;
import android.R.bool;
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

public class ContactActivity extends Activity {
	private static boolean isFirst = true;
	private static boolean isSetlist = false;
	private ContactDataSource datasource;
	public static final String PREFS_NAME = "ContactPreFile";
	
	private static final int iNewContact = Menu.FIRST + 1;
	private static final int iSearch = Menu.FIRST + 2;
	private static final int iDisplayOption = Menu.FIRST + 3;
	private static final int iAccount = Menu.FIRST + 4;
    private static final int iIOContact = Menu.FIRST + 5;
    
    public static final int REQUEST_CODE_ADD = 1;
    public static final int REQUEST_CODE_DETAILS = 2;
    public static final int REQUEST_CODE_EDIT = 3;
    
    ListView listView;
    Toast msg;
    ContactAdapter _contacAdapter;
    
    ArrayList<Contact_> contacts;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_contact);
        
        /* gọi hiển thị danh bạ */
        initForm();
        displayContact();
    }
    
    public void initForm()
    {
    	listView = (ListView) findViewById(R.id.ListContact);
    	listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
            	int _id = contacts.get(position).getiID();
            	showContactDetails(position ,_id);
            }
          });
    }
    
    public void getContactData()
    {
        //Lấy dữ liệu từ Database 'hrm_contact'
        datasource = new ContactDataSource(this);
		datasource.open();

		contacts = (ArrayList<Contact_>) datasource.getAllContacts();
        _contacAdapter = new ContactAdapter(ContactActivity.this, android.R.layout.simple_list_item_1, contacts);
    }
    
    public void displayContact()
    {
    	//Chỉ lấy danh sách từ Database một lần duy nhất
		if(isSetlist == false || _contacAdapter == null)
		{
	    	//Gọi hàm lấy dữ liệu từ Database
	    	getContactData();
	    	//Kiểm tra số lượng danh bạ
	    	if(contacts.size() <= 0)
	    	{
	    		TextView tvMsg = (TextView) findViewById(R.id.msg);
	    		tvMsg.append("Không có danh bạ để hiển thị");
	    		tvMsg.append("\n+ Menu -> Thêm để thêm danh bạ mới");
	    	}
	    	_contacAdapter.setArrContact(contacts);
	        listView.setAdapter(_contacAdapter);
	        //Đánh dấu đã gán danh sách cho Adapter
	    	isSetlist = true;
		}
		else
		{
//			Collections.sort(contacts);
			_contacAdapter.notifyDataSetChanged();
		}
    }
    
    public void showContactDetails(int _index, int _id)
    {
        Intent iContact = new Intent(ContactActivity.this, ContactDetailsActivity.class);
        Bundle b = new Bundle();
        b.putInt("contact_index", _index);
        b.putInt("contact_id", _id);
        iContact.putExtras(b);
        startActivityForResult(iContact, REQUEST_CODE_DETAILS);
    }
    
    public void addContact()
    {
    	Intent newContact = new Intent(this, AddContactActivity.class);
        startActivityForResult(newContact, REQUEST_CODE_ADD);
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
	        Bundle b = new Bundle();
	        String strKey = "" + keyCode;
	        b.putString("strKey", strKey);
	        searchContact.putExtras(b);
	        startActivity(searchContact);
		}
		return super.onKeyDown(keyCode, event);
	}
	
	/*
	 * hàm nhận sự kiện khi có activity khác phát tín hiệu
	 * */
	@Override
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
	     super.onActivityResult(requestCode, resultCode, data);
	     if(resultCode == RESULT_OK)
	     {
	    	 if(requestCode == REQUEST_CODE_ADD)
		     {
		    	 int new_id = data.getExtras().getInt("new_id");
		    	 Contact_ newContact = datasource.getOneContacts(new_id);
		    	 contacts.add(newContact);
		    	 //Hiển thị
		    	 displayContact();
		     }
	    	 else
	    		 if(requestCode == REQUEST_CODE_DETAILS)
			     {
	    			 String action_type = data.getExtras().getString("action_type");
	    			 if(action_type.equals("delete"))
	    			 {
				    	 int contact_index = data.getExtras().getInt("contact_index");
				    	 contacts.remove(contact_index);
	    			 }
	    			 else
	    				 if(action_type.equals("edit"))
	    				 {
	    					int edit_index = data.getExtras().getInt("edit_index");
	    				    int edit_id = data.getExtras().getInt("edit_id");
	    				    
	    				    Contact_ ctEdit = datasource.getOneContacts(edit_id);
	    				    contacts.set(edit_index, ctEdit);
	    				 }
			    	 //Hiển thị
			    	 displayContact();
			     }
	     }
    }	
}
