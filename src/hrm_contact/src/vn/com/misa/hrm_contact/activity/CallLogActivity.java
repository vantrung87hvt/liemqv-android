package vn.com.misa.hrm_contact.activity;

import java.util.ArrayList;

import vn.com.misa.hrm_contact.R;
import vn.com.misa.hrm_contact.model.CallLLogAdapter;
import vn.com.misa.hrm_contact.model.CallLog;
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

public class CallLogActivity extends Activity {
	ArrayList<CallLog> CallLog;
	private static final int iClearCallLog = Menu.FIRST + 1;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_log);
        
        initDefaultValue();
        displayCallLog();
    }
	
	
	public void initDefaultValue()
    {
		CallLog ctd1 = new CallLog("Quang Liem", 3, "Mobile", "123-456-789", "4 days ago");
		CallLog ctd2 = new CallLog("Quang Liem2", 3, "Mobile", "123-456-789", "4 days ago");
		CallLog ctd3 = new CallLog("Quang Liem3", 3, "Home", "123-456-789", "4 days ago");
		CallLog ctd4 = new CallLog("Quang Liem4", 3, "Mobile", "123-456-789", "4 days ago");
		CallLog ctd5 = new CallLog("Quang Liem5", 3, "Office", "123-456-789", "4 days ago");
		
		CallLog = new ArrayList<CallLog>();
		CallLog.add(ctd1);
		CallLog.add(ctd2);
		CallLog.add(ctd3);
		CallLog.add(ctd4);
		CallLog.add(ctd5);
    }
	
	public void displayCallLog()
    {
    	ListView listView = (ListView) findViewById(R.id.list_call_log);
        listView.setAdapter(new CallLLogAdapter(CallLogActivity.this, android.R.layout.simple_list_item_1, CallLog));
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
			case iClearCallLog:
	            
	            break;
        }
        return (super.onOptionsItemSelected(item));
    }
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mEdit = menu.add(0, iClearCallLog, iClearCallLog, "Edit");
        mEdit.setIcon(R.drawable.clear);
        return true;
    }
}
