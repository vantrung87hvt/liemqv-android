package vn.com.misa.hrm_contact.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DemoActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is the Demo tab");
        setContentView(textview);
    }
}
