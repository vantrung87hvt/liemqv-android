package vn.com.misa.hrm_contact.model;

import java.util.ArrayList;

import vn.com.misa.hrm_contact.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ContactAdapter extends ArrayAdapter<Contact> {
	private ArrayList<Contact> arrContact;
	Activity context;

    public ContactAdapter(Activity context, int textViewResourceId, ArrayList<Contact> objContact) {
        super(context, textViewResourceId, objContact);
        this.arrContact = objContact;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
    	View v = convertView;
		if (v == null) {
            LayoutInflater vi = context.getLayoutInflater(); //  (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.contact_layout, null);
		}
      
		Contact objContact = arrContact.get(position);
		if (objContact != null) {
	            TextView txtName = (TextView) v.findViewById(R.id.name);
	            TextView txtEmail = (TextView) v.findViewById(R.id.email);
	
	      if (txtName != null) {
	                txtName.setText(objContact.getsName());
	      }
	
	      if(txtEmail != null) {
	    	  txtEmail.setText("Email: " + objContact.getsEmail() );
	      }
		}
		return v;
    }
}
