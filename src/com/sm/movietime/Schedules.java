package com.sm.movietime;

import com.sm.database.DBHelper_PrefsTable;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Schedules extends Activity {

	Intent i;
	Bundle ex;
	private String movietitle;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);
                
        i = getIntent(); //intent from SpecificLocation
        ex = i.getExtras();
        if (ex != null) {
        	movietitle = ex.getString("MovieTitle");
        	final TextView crumbs = (TextView)findViewById(R.id.location_crumbs);
        	crumbs.setText(ex.getString("genloc") + " > " + ex.getString("smname"));
        }
        
        final TextView title = (TextView)findViewById(R.id.movietitle);
        title.setText(movietitle);
        
        final TextView time = (TextView)findViewById(R.id.time1);
        time.setOnClickListener(new View.OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				DBHelper_PrefsTable pt = new DBHelper_PrefsTable(getBaseContext());
				if (!pt.isLoggedIn()) {
					i.setClass(getBaseContext(), CinemaLogin.class);
					i.putExtra("MovieTitle", movietitle);
					i.putExtra("time", time.getText());
					startActivity(i);
				} else {
					i.setClass(getBaseContext(), PurchaseBreakdown.class);
					i.putExtra("MovieTitle", movietitle);
					i.putExtra("time", time.getText());
					startActivity(i);
				}
			}
		});
        
        final TextView time2 = (TextView)findViewById(R.id.time2);
        time2.setOnClickListener(new View.OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				DBHelper_PrefsTable pt = new DBHelper_PrefsTable(getBaseContext());
				if (!pt.isLoggedIn()) {
					i.setClass(getBaseContext(), CinemaLogin.class);
					i.putExtra("MovieTitle", movietitle);
					i.putExtra("time", time2.getText());
					startActivity(i);
				} else {
					i.setClass(getBaseContext(), PurchaseBreakdown.class);
					i.putExtra("MovieTitle", movietitle);
					i.putExtra("time", time2.getText());
					startActivity(i);
				}
			}
		});
        
        final TextView time3 = (TextView)findViewById(R.id.time3);
        time3.setOnClickListener(new View.OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				DBHelper_PrefsTable pt = new DBHelper_PrefsTable(getBaseContext());
				if (!pt.isLoggedIn()) {
					i.setClass(getBaseContext(), CinemaLogin.class);
					i.putExtra("MovieTitle", movietitle);
					i.putExtra("time", time3.getText());
					startActivity(i);
				} else {
					i.setClass(getBaseContext(), PurchaseBreakdown.class);
					i.putExtra("MovieTitle", movietitle);
					i.putExtra("time", time3.getText());
					startActivity(i);
				}
			}
		});
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
                i.putExtra("callingActivity", "Schedules");
                startActivity(i);
                //return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
