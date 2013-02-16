package com.sm.movietime;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Buyactivity extends Activity {

	Intent i;
	private String movietitle;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyactivity);
        
        final TextView title = (TextView)findViewById(R.id.buymovietitle);
    	Bundle ex = getIntent().getExtras();
    	if (ex != null) {
    		movietitle = ex.getString("MovieTitle");
    		title.setText(movietitle);
    	}
        
        final TextView manila = (TextView)findViewById(R.id.metro_manila);
        manila.setOnClickListener(new View.OnClickListener() {
			        	
			@Override
			public void onClick(View v) {
				i = new Intent(v.getContext(), SpecificLocation.class);
				i.putExtra("general_loc", "Metro Manila");
				i.putExtra("MovieTitle", movietitle);
				startActivity(i);
				
			}
		});
        
        final TextView luzon = (TextView)findViewById(R.id.luzon);
        luzon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				i = new Intent(v.getContext(), SpecificLocation.class);
				i.putExtra("general_loc", "Luzon");
				i.putExtra("MovieTitle", movietitle);
				startActivity(i);
				
			}
		});
        
        final TextView vismin = (TextView)findViewById(R.id.vismin);
        vismin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				i = new Intent(v.getContext(), SpecificLocation.class);
				i.putExtra("general_loc", "VisMin");
				i.putExtra("MovieTitle", movietitle);
				startActivity(i);
				
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_buyactivity, menu);
        return true;
    }
}
