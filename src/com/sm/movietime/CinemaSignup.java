package com.sm.movietime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sm.database.DBHelper_UserAccountTable;
import com.sm.database.UserAccount;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CinemaSignup extends Activity {

	Intent i;
	int flag = 0;
	String strNotif;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_signup);
                       
        Button submit = (Button)findViewById(R.id.signup_submit);
        submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DBHelper_UserAccountTable uh = new DBHelper_UserAccountTable(getBaseContext());
				UserAccount u = new UserAccount();
				EditText firstname = (EditText) findViewById(R.id.editText1);
		        String strFirstname = firstname.getText().toString();
		        EditText lastname = (EditText) findViewById(R.id.editText2);
		        String strLastname = lastname.getText().toString();
		        EditText middlename = (EditText) findViewById(R.id.editText3);
		        String strMiddlename = middlename.getText().toString();
		        EditText city = (EditText) findViewById(R.id.editText5);
		        String strCity = city.getText().toString();
		        /*birthdate*/
		        EditText bdate = (EditText)findViewById(R.id.editText6);
		        EditText email1 = (EditText) findViewById(R.id.editText10);
		        String strEmail1 = email1.getText().toString();
		        EditText email2 = (EditText) findViewById(R.id.editText11);
		        String strEmail2 = email2.getText().toString();
		        EditText password1 = (EditText) findViewById(R.id.editText12);
		        String strPassword1 = password1.getText().toString();
		        EditText password2 = (EditText) findViewById(R.id.editText13);
		        String strPassword2 = password2.getText().toString();
		                
		        if (strFirstname.isEmpty() && strLastname.isEmpty() && strMiddlename.isEmpty() && strCity.isEmpty()) {
		        	flag = 1;
		        	strNotif = "Some required fields are empty";
		        }
		        else if(emailValidator(strEmail1) != true) {
		        	flag = 1;
		        	strNotif = "Invalid Email Address";
		        }
		        else if(!(strEmail1.equals(strEmail2))) {
		        	flag = 1;
		        	strNotif = "Email Addresses do not match";
		        }
		        else if(!(strPassword1.equals(strPassword2))) {
			        flag = 1;
			        strNotif = "Passwords do not match";
		        }
		        	
		        if(flag == 0) {
		        	u.setEmail(strEmail1);
		        	u.setPassword(strPassword1);
		        	uh.addAccount(u);
		        	i = getIntent();
					i.setClass(getBaseContext(), CinemaLogin.class);
					Bundle e = i.getExtras();
					i.putExtra("MovieTitle", e.getString("MovieTitle"));
					i.putExtra("time", e.getString("time"));
					startActivity(i);
					finish();
		        }
		        else {
		        	Toast.makeText(getBaseContext(), strNotif, Toast.LENGTH_LONG).show();
		        	flag = 0;
		        	strNotif = "";
		        } 		        					
			}
		});
                
    }
    
    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cinema_signup, menu);
        return true;
    }
}
