package com.sm.movietime;

import com.sm.database.DBHelper_PrefsTable;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetails extends Activity {

	Intent i;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        	Integer movieposter = extras.getInt("MoviePoster");
        	
        	if (extras.getString("status").equals("now")) {
        		setContentView(R.layout.activity_now_showing_movie_details);
                        
        		final TextView title = (TextView)findViewById(R.id.now_movietitle);
                final ImageView poster = (ImageView)findViewById(R.id.now_poster);
                final TextView summary = (TextView)findViewById(R.id.now_summary);
                final TextView genre = (TextView)findViewById(R.id.now_genre);
                final TextView starring = (TextView)findViewById(R.id.now_starring);
                
                title.setText(extras.getString("MovieTitle"));
            	poster.setImageResource(movieposter);
            	summary.setText(extras.getString("MovieSummary"));
            	genre.setText(extras.getString("MovieGenre"));
            	starring.setText(extras.getString("MovieStarring"));
            	
                final TextView manila = (TextView)findViewById(R.id.metro_manila);
                manila.setOnClickListener(new View.OnClickListener() {
        			
        			@Override
        			public void onClick(View v) {
        				i = new Intent(v.getContext(), SpecificLocationActivity.class);
        				i.putExtra("MovieTitle", title.getText());
        				i.putExtra("general_loc", "Metro Manila");
        				startActivity(i);
        				
        			}
        		});
                
                final TextView luzon = (TextView)findViewById(R.id.luzon);
                luzon.setOnClickListener(new View.OnClickListener() {
        			
        			@Override
        			public void onClick(View v) {
        				i = new Intent(v.getContext(), SpecificLocationActivity.class);
        				i.putExtra("MovieTitle", title.getText());
        				i.putExtra("general_loc", "Luzon");
        				startActivity(i);
        				
        			}
        		});
                
                final TextView vismin = (TextView)findViewById(R.id.vismin);
                vismin.setOnClickListener(new View.OnClickListener() {
        			
        			@Override
        			public void onClick(View v) {
        				i = new Intent(v.getContext(), SpecificLocationActivity.class);
        				i.putExtra("MovieTitle", title.getText());
        				i.putExtra("general_loc", "VisMin");
        				startActivity(i);
        				
        			}
        		});
        	}
        	else {
        		setContentView(R.layout.activity_movie_details);
        		
        		final TextView title = (TextView)findViewById(R.id.movietitle);
                final ImageView poster = (ImageView)findViewById(R.id.poster);
                final TextView summary = (TextView)findViewById(R.id.summary);
                final TextView genre = (TextView)findViewById(R.id.genre);
                final TextView starring = (TextView)findViewById(R.id.starring);
        		
                title.setText(extras.getString("MovieTitle"));
            	poster.setImageResource(movieposter);
            	summary.setText(extras.getString("MovieSummary"));
            	genre.setText(extras.getString("MovieGenre"));
            	starring.setText(extras.getString("MovieStarring"));
        	}
        }
        
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
