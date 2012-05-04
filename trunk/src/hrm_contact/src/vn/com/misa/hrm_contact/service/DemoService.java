package vn.com.misa.hrm_contact.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import vn.com.misa.hrm_contact.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DemoService extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);        
         
        initListContact();
    }

	private void initListContact() {
		ArrayList<String> persons = FromJSONtoArrayList();
        
        ListView listView1 = (ListView)findViewById(R.id.lstContact);
        listView1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, persons));
	}

	private ArrayList<String> FromJSONtoArrayList() {
		ArrayList<String> listItems = new ArrayList<String>();
        
        try {
             
            // Replace it with your own WCF service path
            URL json = new URL(R.string.service_address + "contact_getall");
            URLConnection jc = json.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
             
            String line = reader.readLine();
             
            JSONObject jsonResponse = new JSONObject(line);
            JSONArray jsonArray = jsonResponse.getJSONArray("");
         
            for (int i = 0; i < jsonArray.length(); i++) {
                 
                JSONObject jObject = (JSONObject)jsonArray.get(i);
 
                // "FullName" is the property of .NET object spGetPersonsResult, 
                // and also the name of column in SQL Server 2008
                listItems.add(jObject.getString("sLastname"));
            }
             
            reader.close();
             
        } catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
         
        return listItems;
	}
}
