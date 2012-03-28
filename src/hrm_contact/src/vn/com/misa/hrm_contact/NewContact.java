package vn.com.misa.hrm_contact;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewContact extends Activity {
	// Declare our Views, so we can access them later
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
	
	  		//Send data to Display Contact Activity
//	  		Intent i = new Intent(getApplicationContext(), Hrm_contactActivity.class);
//	  		i.putExtra("sName", sName);
//	  		i.putExtra("sPhone", sPhone);
//	  		i.putExtra("sEmail", sEmail);
//	  		i.putExtra("new_contact_close", true);
//	  		startActivity(i);
	  		
	  		SharedPreferences dataContact = getSharedPreferences(PREFS_NAME, 0);
	        SharedPreferences.Editor editor = dataContact.edit();
	        editor.putString("sName", sName);
	        editor.putString("sPhone", sPhone);
	        editor.putString("sEmail", sEmail);
	        editor.putString("sSatus", "close");

	        // Commit the edits!
	        editor.commit();
	  		
	  		finish();
	  	}
	  });
        btnCancel.setOnClickListener(new OnClickListener() {
	  	@Override
	  	public void onClick(View v) {
	  		// Close the application
	  		finish();
	  	}
	  });
        
    }
}
