package vn.com.misa.hrm_contact.activity;

import vn.com.misa.hrm_contact.R;
import vn.com.misa.hrm_contact.R.id;
import vn.com.misa.hrm_contact.R.layout;
import vn.com.misa.hrm_contact.bean.Contact_;
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

public class NewContactActivity extends Activity {
	private ContactDataSource datasource;
	
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
	  		Contact_ resContact = null;
	  		Intent data = new Intent();
	  		String sName = etName.getText().toString();
	  		String sPhone = etPhone.getText().toString();
	  		String sEmail = etEmail.getText().toString();
	  		
	  		Contact_ ctNew = new Contact_(sName, sPhone, sEmail, false);
	  		//Lưu thông tin contact vào database
	  		datasource.open();
	  		//Kiểm tra thông tin contact hiện tại đã tồn tại các thông tin (name, phone, email) chưa?
	  		int iCheckDup = datasource.CheckDupContact(ctNew);
	  		if(iCheckDup == 0)
	  		{
		  		//Thêm vào Database
		  		resContact = datasource.createContact(sName, sPhone, sEmail);
		  		data.putExtra("new_id", resContact.getiID());
		  		setResult(RESULT_OK, data);
		  		finish();
	  		}
	  		else
	  		{
	  			if(iCheckDup == 1)
	  			{
	  				Toast.makeText(getApplicationContext(), "Tên " + ctNew.getsName() + " đã tồn tại!", Toast.LENGTH_LONG).show();
	  			}
	  			else
	  				if(iCheckDup == 2)
		  			{
		  				Toast.makeText(getApplicationContext(), "Số " + ctNew.getsPhone() + " đã tồn tại!", Toast.LENGTH_LONG).show();
		  			}
  				else
  					if(iCheckDup == 3)
  		  			{
  		  				Toast.makeText(getApplicationContext(), "Email " + ctNew.getsEmail() + " đã tồn tại!", Toast.LENGTH_LONG).show();
  		  			}
	  		}
	  		//Đóng database
	  		datasource.close();
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
