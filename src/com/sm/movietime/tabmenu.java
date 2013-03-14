package com.sm.movietime;

import com.sm.database.DBHelper_PrefsTable;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class TabMenu extends TabActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabmenu);
        
        Resources res = getResources();
        TabHost host = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
        
        intent = new Intent().setClass(this, NowShowing.class);
        spec = host.newTabSpec("now showing").setIndicator("Now\nShowing").setContent(intent);
        host.addTab(spec);
        
        intent = new Intent().setClass(this, NextAttraction.class);
        spec = host.newTabSpec("next attraction").setIndicator("Next\nAttraction").setContent(intent);
        host.addTab(spec);
        
        intent = new Intent().setClass(this, ComingSoon.class);
        spec = host.newTabSpec("coming soon").setIndicator("Coming\nSoon").setContent(intent);
        host.addTab(spec);
        
        host.setCurrentTab(3);
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
		DBHelper_PrefsTable tbl = new DBHelper_PrefsTable(getBaseContext());
		if (tbl.isLoggedIn())
			getMenuInflater().inflate(R.menu.global_logout_menu, menu);
		else getMenuInflater().inflate(R.menu.global_login_menu, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
		switch (item.getItemId()) {
            case R.id.logout:
                DBHelper_PrefsTable tbl = new DBHelper_PrefsTable(getBaseContext());
                tbl.deletePref();
                i = new Intent(getBaseContext(),TabMenu.class);
                startActivity(i);
                return true;
            case R.id.login:
                i = new Intent(getBaseContext(),CinemaLogin.class);
                startActivity(i);
                //return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
