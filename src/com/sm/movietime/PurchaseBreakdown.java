package com.sm.movietime;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
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
        getMenuInflater().inflate(R.menu.activity_purchase_breakdown, menu);
        return true;
    }
    
        
}
