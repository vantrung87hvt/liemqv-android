package vn.com.misa.hrm_contact.bean;

import java.util.ArrayList;

import vn.com.misa.hrm_contact.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContactDetailsAdapter extends ArrayAdapter<ContactDetail> {
	private ArrayList<ContactDetail> arrContactDetails;
	Activity context;
	ContactDetail objContactDetails;
	
	
    public ContactDetailsAdapter(Activity context, int textViewResourceId, ArrayList<ContactDetail> _arrlContactDetails) {
        super(context, textViewResourceId, _arrlContactDetails);
        this.arrContactDetails = _arrlContactDetails;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
    	View v = convertView;
		if (v == null) {
            LayoutInflater vi = context.getLayoutInflater();
            v = vi.inflate(R.layout.contact_details_item, null);
		}
      
		objContactDetails = arrContactDetails.get(position);
		if (objContactDetails != null) {
	            TextView txtTitle = (TextView) v.findViewById(R.id.details_title);
	            TextView txtValue = (TextView) v.findViewById(R.id.details_value);
	            ImageButton imgCall = (ImageButton) v.findViewById(R.id.call_button);
	        	imgCall.setOnClickListener(new OnClickListener() {
	        		   public void onClick(View v) { 
	        			   Intent callIntent = new Intent(Intent.ACTION_CALL);
	        			   callIntent.setData(Uri.parse("tel:" + objContactDetails.getsValue()));
	        			   context.startActivity(callIntent);
	        	   }
	        	});
	
	      if (txtTitle != null) {
	    	  txtTitle.setText(objContactDetails.getsTitle());
	      }
	
	      if(txtValue != null) {
	    	  txtValue.setText(objContactDetails.getsValue() );
	      }
		}
		return v;
    }
    
    
}
