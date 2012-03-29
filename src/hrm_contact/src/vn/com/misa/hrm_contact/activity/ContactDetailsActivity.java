package vn.com.misa.hrm_contact.activity;

import java.util.ArrayList;

import vn.com.misa.hrm_contact.R;
import vn.com.misa.hrm_contact.model.ContactDetails;
import vn.com.misa.hrm_contact.model.ContactDetailsAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDetailsActivity extends Activity {
	ArrayList<ContactDetails> contactDetails;
	private static final int iEdit = Menu.FIRST + 1;
	private static final int iShare = Menu.FIRST + 2;
	private static final int iOption = Menu.FIRST + 3;
	private static final int iDelete = Menu.FIRST + 4;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details);
        
        initDefaultValue();
        displayContactDetails();
    }
	
	
	public void initDefaultValue()
    {
		ContactDetails ctd1 = new ContactDetails("Call home","123456789", true, false);
		ContactDetails ctd2 = new ContactDetails("Call mobile","0989320758", true, false);
		ContactDetails ctd3 = new ContactDetails("Call office","112", true, false);
		ContactDetails ctd4 = new ContactDetails("Email","liemqv@gmail.com", false, true);
		ContactDetails ctd5 = new ContactDetails("Misa JSC","Duy Tan, Ha Noi", false, false);
		
		contactDetails = new ArrayList<ContactDetails>();
		contactDetails.add(ctd1);
		contactDetails.add(ctd2);
		contactDetails.add(ctd3);
		contactDetails.add(ctd4);
		contactDetails.add(ctd5);
    }
	
	public void displayContactDetails()
    {
    	ListView listView = (ListView) findViewById(R.id.ListContactDetails);
    	TextView tvNameDetails = (TextView) findViewById(R.id.details_name);
    	tvNameDetails.setText("Quang Liem");
        listView.setAdapter(new ContactDetailsAdapter(ContactDetailsActivity.this, android.R.layout.simple_list_item_1, contactDetails));
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) 
            {
            	
            }
          });
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
			case iEdit:
	            
	            break;
			case iShare:
                
                break;
			case iOption:
        	   
               break;
			case iDelete:
	        	   
	               break;
        }
        return (super.onOptionsItemSelected(item));
    }
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mEdit = menu.add(0, iEdit, iEdit, "Edit");
        mEdit.setIcon(R.drawable.edit);
        MenuItem mShare = menu.add(0, iShare, iShare, "Share");
        mShare.setIcon(R.drawable.share);
        
        MenuItem mOptions = menu.add(1, iOption, iOption, "Options");
        mOptions.setIcon(R.drawable.display_option);
        MenuItem mDelete = menu.add(1, iDelete, iDelete, "Delete contact");
        mDelete.setIcon(R.drawable.delete);
        return true;
    }
}
