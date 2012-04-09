package vn.com.misa.hrm_contact.activity;

import vn.com.misa.hrm_contact.R;
import vn.com.misa.hrm_contact.R.id;
import vn.com.misa.hrm_contact.R.layout;
import vn.com.misa.hrm_contact.bean.Contact;
import vn.com.misa.hrm_contact.sql.ContactDataSource;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditContactActivity extends Activity {
	private ContactDataSource datasource;
	
	private int _id, _index;
	
	private EditText etName;
	private EditText etPhone;
	private EditText etEmail;
	private Button btnSave;
	private Button btnCancel;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contact);
        
        datasource = new ContactDataSource(this);
        
        etName = (EditText)findViewById(R.id.etName);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etEmail = (EditText)findViewById(R.id.etEmail);
        btnSave = (Button)findViewById(R.id.save_button);
        btnCancel = (Button)findViewById(R.id.cancel_button);
        
        //Lấy ID và vị trí được gửi từ Activity Detail
        Bundle extras = getIntent().getExtras();
		if(extras !=null) 
		{
			_id = extras.getInt("contact_id", 0);
			_index = extras.getInt("contact_index", 0);
			
			datasource.open();
			Contact ct = datasource.getOneContacts(_id);
			etName.setText(ct.getsName());
			etPhone.setText(ct.getsPhone());
			etEmail.setText(ct.getsEmail());
		}
        // Set Click Listener
        btnSave.setOnClickListener(new OnClickListener() {
	  	@Override
	  	public void onClick(View v) {
	  		String sName = etName.getText().toString();
	  		String sPhone = etPhone.getText().toString();
	  		String sEmail = etEmail.getText().toString();
	  		
	  		Contact ctTemp = new Contact(_id, sName, sPhone, sEmail, false);
	  		
	  		//Lưu thông tin contact vào database
	  		datasource.open();
	  		//Update contact
	  		Intent data = new Intent();
	  		if(datasource.editContact(ctTemp) == true)
	  		{
	  			Toast.makeText(getApplicationContext(), "Edit successfull!", Toast.LENGTH_LONG).show();
	  			data.putExtra("edit_id", _id);
	  			data.putExtra("edit_index", _index);
	  			setResult(RESULT_OK, data);
	  		}
	  		else
	  		{
	  			Toast.makeText(getApplicationContext(), "Edit failed!", Toast.LENGTH_LONG).show();
	  			setResult(RESULT_CANCELED, data);
	  		}
	  		//Đóng database
	  		datasource.close();

	  		finish();
	  	}
	  });
        btnCancel.setOnClickListener(new OnClickListener() {
	  	@Override
	  	public void onClick(View v) {
	  		Intent resultIntent = new Intent();
	  	    setResult(Activity.RESULT_CANCELED, resultIntent);
	  	    finish();
	  	}
	  });
        
    }
}
