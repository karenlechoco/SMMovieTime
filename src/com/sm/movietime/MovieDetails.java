package com.sm.movietime;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
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
        getMenuInflater().inflate(R.menu.activity_movie_details, menu);
        return true;
    }
}
