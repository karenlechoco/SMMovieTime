package com.sm.movietime;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class CinemaSignup extends Activity {

	Intent i;
	
	Intent iii;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_signup);
        
        Button submit = (Button)findViewById(R.id.signup_submit);
        submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				i = getIntent();
				i.setClass(getBaseContext(), PurchaseBreakdown.class);
				startActivity(i);
				
			}
		});
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cinema_signup, menu);
        return true;
    }
}
