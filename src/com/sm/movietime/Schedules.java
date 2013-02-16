package com.sm.movietime;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
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
				// i contains: genloc, smname
				// i activity: Schedules.class
				i.setClass(getBaseContext(), CinemaLogin.class);
				i.putExtra("MovieTitle", movietitle);
				startActivity(i);				
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_schedules, menu);
        return true;
    }
}
