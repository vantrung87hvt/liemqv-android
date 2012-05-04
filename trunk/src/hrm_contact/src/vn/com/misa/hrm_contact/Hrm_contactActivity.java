package vn.com.misa.hrm_contact;

import vn.com.misa.hrm_contact.activity.CallLogActivity;
import vn.com.misa.hrm_contact.activity.ContactActivity;
import vn.com.misa.hrm_contact.activity.DemoActivity;
import vn.com.misa.hrm_contact.activity.FavoritestActivity;
import vn.com.misa.hrm_contact.service.DemoService;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class Hrm_contactActivity  extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent = null; 
        
     // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, DemoActivity.class);
        
        /*
         * Tab gọi điện thoại
         * */
//        Uri uri = Uri.parse("tel:113");
//        intent = new Intent (Intent.ACTION_VIEW, uri);

        spec = tabHost.newTabSpec("phone").setIndicator("Phone",
                          res.getDrawable(R.drawable.tab_phone))
                      .setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, CallLogActivity.class);
        spec = tabHost.newTabSpec("call_log").setIndicator("Call log",
                          res.getDrawable(R.drawable.tab_call_log))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, ContactActivity.class);
        spec = tabHost.newTabSpec("contact").setIndicator("Contacts",
                          res.getDrawable(R.drawable.tab_contact))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, FavoritestActivity.class);
        spec = tabHost.newTabSpec("favorites").setIndicator("Favorites",
                          res.getDrawable(R.drawable.tab_favorites))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        //Tab demo
        
        intent = new Intent().setClass(this, DemoService.class);
        spec = tabHost.newTabSpec("Service").setIndicator("Service",
                          res.getDrawable(R.drawable.tab_favorites))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(4);
        
    }
}