package com.sm.movietime;


import com.sm.database.DBHelper_PrefsTable;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class PurchaseBreakdown extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_breakdown);
        
        Bundle b = getIntent().getExtras();
                
        TextView title = (TextView)findViewById(R.id.movietitle);
        title.setText(b.getString("MovieTitle"));
        
        TextView time = (TextView)findViewById(R.id.starttime);
        time.setText("Movie Name: " + b.getString("time"));
        
        TextView moviename = (TextView)findViewById(R.id.movieName);
        moviename.setText("Movie Name: " + b.getString("MovieTitle"));
        
        TextView name = (TextView)findViewById(R.id.location_crumbs);
        name.setText(b.getString("genloc") + " > " + b.getString("smname"));
        
        final TextView reserve = (TextView)findViewById(R.id.resseat);
        reserve.setOnClickListener(new OnClickListener () {
        	public void onClick (View V) {
        		Intent intnt = new Intent(V.getContext(), ReserveSeats.class);
        		startActivityForResult(intnt,0);
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
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
