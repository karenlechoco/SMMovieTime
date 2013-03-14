package com.sm.movietime;

import com.sm.database.DBHelper_UserAccountTable;
import com.sm.database.UserAccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CinemaLogin extends Activity {

	Intent i;
	EditText email, pass;
	DBHelper_UserAccountTable tbl;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_login);
        
        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.password);
        
        tbl = new DBHelper_UserAccountTable(getBaseContext());
                    
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
				UserAccount u = new UserAccount();
				u.setEmail(email.getText().toString());
		        u.setPassword(pass.getText().toString());
				if (tbl.verifyLogin(u)) {
					i = getIntent();
					i.setClass(getBaseContext(), PurchaseBreakdown.class);
					startActivity(i);
					finish();
				} else Toast.makeText(getBaseContext(), "Invalid Login", Toast.LENGTH_LONG).show();
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cinema_login, menu);
        return true;
    }

    
}
