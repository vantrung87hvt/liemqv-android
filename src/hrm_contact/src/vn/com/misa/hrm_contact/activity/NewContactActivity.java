package vn.com.misa.hrm_contact.activity;

import vn.com.misa.hrm_contact.R;
import vn.com.misa.hrm_contact.R.id;
import vn.com.misa.hrm_contact.R.layout;
import vn.com.misa.hrm_contact.model.Contact;
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

public class NewContactActivity extends Activity {
	private ContactDataSource datasource;
	public static final String PREFS_NAME = "ContactPreFile";
	
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
        
     // Get the EditText and Button References
        etName = (EditText)findViewById(R.id.etName);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etEmail = (EditText)findViewById(R.id.etEmail);
        btnSave = (Button)findViewById(R.id.save_button);
        btnCancel = (Button)findViewById(R.id.cancel_button);
        
        // Set Click Listener
        btnSave.setOnClickListener(new OnClickListener() {
	  	@Override
	  	public void onClick(View v) {
	  		String sName = etName.getText().toString();
	  		String sPhone = etPhone.getText().toString();
	  		String sEmail = etEmail.getText().toString();
	  		
	  		//Lưu thông tin contact vào database
	  		datasource.open();
	  		//Thêm vào Database
	  		Contact resContact = datasource.createContact(sName, sPhone, sEmail);
	    	
	  		Intent resultIntent = new Intent();
	  	    resultIntent.putExtra("NEW_ID", resContact.getiID());
	  	    setResult(Activity.RESULT_OK, resultIntent);
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
