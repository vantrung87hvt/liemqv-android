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

public class CallLLogAdapter extends ArrayAdapter<CallLog> {
	private ArrayList<CallLog> arrCallLog;
	Activity context;
	CallLog objCallLog;
	
	
    public CallLLogAdapter(Activity context, int textViewResourceId, ArrayList<CallLog> _arrlCallLog) {
        super(context, textViewResourceId, _arrlCallLog);
        this.arrCallLog = _arrlCallLog;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
    	View v = convertView;
		if (v == null) {
            LayoutInflater vi = context.getLayoutInflater();
            v = vi.inflate(R.layout.call_log_item, null);
		}
      
		objCallLog = arrCallLog.get(position);
		if (objCallLog != null) {
	            TextView txtContactName = (TextView) v.findViewById(R.id.contact_name);
	            TextView txtLogCount = (TextView) v.findViewById(R.id.log_count);
	            TextView txtContactNumber = (TextView) v.findViewById(R.id.contact_number);
	            TextView txtLogTime = (TextView) v.findViewById(R.id.log_time);
	            ImageButton imgCall = (ImageButton) v.findViewById(R.id.call_button);
	        	imgCall.setOnClickListener(new OnClickListener() {
	        		   public void onClick(View v) { 
	        			   Intent callIntent = new Intent(Intent.ACTION_CALL);
	        			   callIntent.setData(Uri.parse("tel:" + objCallLog.getsNumber()));
	        			   context.startActivity(callIntent);
	        	   }
	        	});
	
	      if (txtContactName != null) {
	    	  txtContactName.setText(objCallLog.getsName());
	      }
//	      if(txtLogCount != null) {
//	    	  txtLogCount.setText(objCallLog.getiCount());
//	      }
	      if(txtContactNumber != null) {
	    	  txtContactNumber.setText(objCallLog.getsNumber());
	      }
	      if(txtLogTime != null) {
	    	  txtLogTime.setText(objCallLog.getsTime());
	      }
		}
		return v;
    }
    
    
}
