package vn.com.misa.hrm_contact.activity;

import java.util.ArrayList;
import java.util.Vector;

import vn.com.misa.hrm_contact.R;
import vn.com.misa.hrm_contact.bean.Contact;
import vn.com.misa.hrm_contact.bean.ContactDetail;
import vn.com.misa.hrm_contact.bean.ContactDetailsAdapter;
import vn.com.misa.hrm_contact.sql.ContactDataSource;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
	ArrayList<ContactDetail> contactDetails;
	private static final int iEdit = Menu.FIRST + 1;
	private static final int iShare = Menu.FIRST + 2;
	private static final int iOption = Menu.FIRST + 3;
	private static final int iDelete = Menu.FIRST + 4;
	
	public ContactDataSource dtSource;
	
	private int _id;
	private int _index;
	
	public static int REQUEST_CODE_EDIT = 4;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details);
        dtSource = new ContactDataSource(this);
        getContactFromDatabase();
    }
	
	
	public void getContactFromDatabase()
    {
		try
		{
			//Get ID from Contact Activity
			Bundle extras = getIntent().getExtras();
			if(extras !=null) 
			{
				_id = extras.getInt("contact_id", 0);
				_index = extras.getInt("contact_index", 0);
				dtSource.open();
				Contact ct = dtSource.getOneContacts(_id);
				if(_id != 0 && ct != null)
				{
					contactDetails = new ArrayList<ContactDetail>();
					Vector<ContactDetail> vtDetails = new Vector<ContactDetail>();
					ContactDetail ctDetails;
					if(!ct.getsName().equals(""))
					{
						TextView tvNameDetails = (TextView) findViewById(R.id.details_name);
				    	tvNameDetails.setText(ct.getsName());
					}
					if(!ct.getsPhone().equals(""))
					{
						ctDetails = new ContactDetail("Mobile: ", ct.getsPhone(), true, false);
						contactDetails.add(ctDetails);
					}
					if(!ct.getsEmail().equals(""))
					{
						ctDetails = new ContactDetail("Email: ", ct.getsEmail(), false, true);
						contactDetails.add(ctDetails);
					}
					displayContactDetails();
				}
				else
				{
					finish();
				}
			}
		}
		catch(Exception e)
		{
			Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_LONG).show();
		}
    }
	
	public void displayContactDetails()
    {
    	ListView listView = (ListView) findViewById(R.id.ListContactDetails);
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
	            editContact();
	            break;
			case iShare:
                shareContact();
                break;
			case iOption:
        	   
               break;
			case iDelete:
	        	   deleteContact();
	               break;
        }
        return (super.onOptionsItemSelected(item));
    }
    
    public void shareContact()
    {
    	Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
    	sharingIntent.setType("text/plain");
    	Contact ctShare = dtSource.getOneContacts(_id);
    	sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Gửi danh bạ");
    	sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, ctShare.toText());
    	startActivity(Intent.createChooser(sharingIntent, "Chọn phương thức chia sẻ"));
    }
    
    public void editContact()
    {
    	Intent iEdit = new Intent(ContactDetailsActivity.this, EditContactActivity.class);
        Bundle b = new Bundle();
        b.putInt("contact_index", _index);
        b.putInt("contact_id", _id);
        iEdit.putExtras(b);
        startActivityForResult(iEdit, REQUEST_CODE_EDIT);
    }
    
    public void deleteContact()
    {
    	try
    	{
    		AlertDialog.Builder alertDialog = new AlertDialog.Builder(ContactDetailsActivity.this);
            alertDialog.setTitle("Xóa danh bạ");
            alertDialog.setMessage("Bạn chắc chắn muốn xóa?");
            alertDialog.setIcon(R.drawable.delete);
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {
                	dtSource.deleteContact(_id);
            		Toast.makeText(getApplicationContext(), "Xóa thành công!", Toast.LENGTH_LONG).show();
            		//Gửi vị trí xóa sang Activity Contact để load lại danh sách
            		Intent data = new Intent();
            		data.putExtra("action_type", "delete");
        	  		data.putExtra("contact_index", _index);
        	  		setResult(RESULT_OK, data);
        	  		finish();
                }
            });
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,    int which) {
                Toast.makeText(getApplicationContext(), "Đã hủy thành công!", Toast.LENGTH_SHORT).show();
                dialog.cancel();
                }
            });
            alertDialog.show();
    	}
    	catch (Exception e)
    	{
    		Toast.makeText(this, "Xóa thất bại!\nLỗi: " + e.getMessage(), Toast.LENGTH_LONG).show();
    		finish();
    	}
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


	@Override
	protected void onDestroy() {
		dtSource.close();
		super.onDestroy();
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK)
	     {
    		 if(requestCode == REQUEST_CODE_EDIT)
		     {
		    	int edit_index = data.getExtras().getInt("edit_index");
		    	int edit_id = data.getExtras().getInt("edit_id");
		    	//Gửi ID và index sang Activity Contact để load lại danh sách
         		Intent iEdit = new Intent();
         		data.putExtra("action_type", "edit");
     	  		data.putExtra("edit_index", _index);
     	  		data.putExtra("edit_id", _id);
     	  		setResult(RESULT_OK, data);
     	  		finish();
		     }
	     }
	}
	
	
}
