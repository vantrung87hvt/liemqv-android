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
	Intent actionIntent;
	String sPhone = "";
	String sEmail = "";
	
    public ContactDetailsAdapter(Activity context, int textViewResourceId, ArrayList<ContactDetail> _arrlContactDetails) {
        super(context, textViewResourceId, _arrlContactDetails);
        this.arrContactDetails = _arrlContactDetails;
        this.context = context;
        this.actionIntent = new Intent();
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
	            ImageButton imgSMS = (ImageButton) v.findViewById(R.id.sms_button);
	            
	            if(objContactDetails.getIsPhone() == true)
	            {
	            	sPhone = objContactDetails.getsValue();
	            	imgSMS.setImageResource(R.drawable.sms);
	            	
	            	imgSMS.setOnClickListener(new OnClickListener() {
		        		   public void onClick(View v) {
		        			   actionIntent.setType(Intent.ACTION_SENDTO);
		        			   actionIntent.setData(Uri.parse("sms:" + sPhone));
			        		   context.startActivity(actionIntent);
		        	   }
		        	});
	            	
	            	imgCall.setOnClickListener(new OnClickListener() {
		        		   public void onClick(View v) {
		        			   Intent callIntent = new Intent(Intent.ACTION_CALL);
		        			   callIntent.setData(Uri.parse("tel:" + sPhone));
		        			   context.startActivity(callIntent);
		        	   }
		        	});
	            	imgCall.setVisibility(View.VISIBLE);
	            }
	            
	            if(objContactDetails.getIsMail() == true)
	            {
	            	sEmail = objContactDetails.getsValue();
	            	imgSMS.setImageResource(R.drawable.email);
	            	imgSMS.setOnClickListener(new OnClickListener() {
		        		   public void onClick(View v) {
		        			   final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		        			   
		        			   emailIntent .setType("plain/text");
		        			    
		        			   emailIntent .putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"webmaster@website.com"});
		        			    
		        			   emailIntent .putExtra(android.content.Intent.EXTRA_SUBJECT, "");
		        			    
		        			   emailIntent .putExtra(android.content.Intent.EXTRA_TEXT, "");
		        			    
		        			   context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
		        		   }});
	            	imgCall.setVisibility(View.INVISIBLE);
	            }
	            
	        	
	        	
	
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
