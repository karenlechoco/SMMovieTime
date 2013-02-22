package com.sm.movietime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class CinemaLogin extends Activity {

	Intent i;
	Intent ii;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_login);
            
        final Button reg = (Button)findViewById(R.id.register_button);
        reg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				i = getIntent();
				i.setClass(getBaseContext(), CinemaSignup.class);
				startActivity(i);
				
			}
		});
        
        final Button login = (Button)findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//i = new Intent(v.getContext(), PurchaseBreakdown.class);
				i = getIntent();
				i.setClass(getBaseContext(), PurchaseBreakdown.class);
				startActivity(i);
				
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cinema_login, menu);
        return true;
    }

    
}
