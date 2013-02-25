package com.sm.movietime;

//import com.sai.samples.views.R;

//import com.sai.samples.views.R;
//import com.sai.samples.views.GalleryView.ImageAdapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ReserveSeats extends Activity {
	
	Intent intnt;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seats);
        
        final Button cancel = (Button)findViewById(R.id.cancel_res_button);
        cancel.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick (View V) {
        		intnt = new Intent();
        		setResult(RESULT_OK,intnt);
        		finish();
        	}
        });
        
        final Button save = (Button)findViewById(R.id.save_res_button);
        save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				intnt = new Intent();
				setResult(RESULT_OK,intnt);
				finish();
			}
		});
        
        TableLayout tl = CreateSeatsLayout();
        
        ScrollView sc = (ScrollView)findViewById(R.id.reserve_scroll);
        sc.addView(tl);
                
    }

    
    	public TableLayout CreateSeatsLayout() {
			      		
    		String[] seatnameA = {
    				"A27","A26","A25","A24","A23","A22","A21",
    				"A20","A19","A18","A17","A16","A15","A14","A13","A12","A11",
    				"A10","A09","A08","A07","A06","A05","A04","A03","A02","A01"};
    		    		
    		String[] seatnameB = {
    				"B28","B27","B26","B25","B24","B23","B22","B21","B20",
    				"B19","B18","B17","B16","B15","B14","B13","B12","B11","B10",
    				"B09","B08","B07","B06","B05","B04","B03","B02","B01"};
    		    		
    		String[] seatnameC = {
    				"C25","C24","C23","C22","C21","C20","C19","C18","C17","C16",
    				"C15","C14","C13","C12","C11","C10","C09","C08","C07","C06",
    				"C05","C04","C03","C02","C01"};
    		
    		String num="";
    		
    		int[] seatcolA = {2,3,4,5,6,7,8,9,10,12,13,14,15,16,17,18,19,20,23,24,25,26,27,28,29,30,31};
    		int[] seatcolB = {2,3,4,5,6,7,8,9,10,12,13,14,15,16,17,18,19,20,21,23,24,25,26,27,28,29,30,31};
    		int[] seatcolC = {2,3,4,5,6,7,8,9,10,12,13,14,15,16,17,18,19,20,21,23,24,25,26,27,28,};
    		
			TextView seatnum = null;
			TableRow tr;
			TableLayout tl = new TableLayout(getBaseContext());
			
			int i, j;
			tr = new TableRow(getBaseContext());
			
			for (i=0, j=0; i<seatnameA.length; i++) {
				seatnum = new TextView(getBaseContext());
				seatnum.setText(seatnameA[i]);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);			
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(seatcolA[i]));
			}
			tl.addView(tr, 0);
			
			tr = new TableRow(getBaseContext());
			for (i=0; i<seatnameB.length; i++) {
				seatnum = new TextView(getBaseContext());
				seatnum.setText(seatnameB[i]);
				seatnum.setPadding(2, 2, 2, 2);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(seatcolB[i]));
			}
			tl.addView(tr, 1);
			
			tr = new TableRow(getBaseContext());
			for (i=0; i<seatnameC.length; i++) {
				seatnum = new TextView(getBaseContext());
				seatnum.setText(seatnameC[i]);
				seatnum.setPadding(2, 2, 2, 2);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(seatcolC[i]));
			}
			tl.addView(tr, 2);
			
			tr = new TableRow(getBaseContext());
			seatnum = new TextView(getBaseContext());
			seatnum.setTextColor(Color.TRANSPARENT);
			seatnum.setText("000");
			tr.addView(seatnum);
			tl.addView(tr, 3);
			
			tr = new TableRow(getBaseContext());
			for (i=25, j=2; i>0; i--, j++) {
				if (i==1) break;
				seatnum = new TextView(getBaseContext());
				num = Integer.toString(i);
				if (i < 10) num = "0" + num;
				seatnum.setText("D" + num);
				seatnum.setPadding(2, 2, 2, 2);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(j));
			}
			tl.addView(tr, 4);
			
			tr = new TableRow(getBaseContext());
			for (i=30, j=0; i>0; i--) {
				seatnum = new TextView(getBaseContext());
				num = Integer.toString(i);
				if (i < 10) num = "0" + num;
				seatnum.setText("E" + num);
				seatnum.setPadding(2, 2, 2, 2);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(j));
			}
			tl.addView(tr, 5);
			
			tr = new TableRow(getBaseContext());
			for (i=31, j=0; i>0; i--, j++) {
				seatnum = new TextView(getBaseContext());
				num = Integer.toString(i);
				if (i < 10) num = "0" + num;
				seatnum.setText("F" + num);
				seatnum.setPadding(2, 2, 2, 2);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(j));
			}
			tl.addView(tr, 6);
			
			tr = new TableRow(getBaseContext());
			for (i=32, j=0; i>0; i--, j++) {
				seatnum = new TextView(getBaseContext());
				num = Integer.toString(i);
				if (i < 10) num = "0" + num;
				seatnum.setText("G" + num);
				seatnum.setPadding(2, 2, 2, 2);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(j));
			}
			tl.addView(tr, 7);
			
			tr = new TableRow(getBaseContext());
			for (i=32, j=0; i>0; i--, j++) {
				seatnum = new TextView(getBaseContext());
				num = Integer.toString(i);
				if (i < 10) num = "0" + num;
				seatnum.setText("H" + num);
				seatnum.setPadding(2, 2, 2, 2);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(j));
			}
			tl.addView(tr, 8);
			
			tr = new TableRow(getBaseContext());
			for (i=32, j=0; i>0; i--, j++) {
				seatnum = new TextView(getBaseContext());
				num = Integer.toString(i);
				if (i < 10) num = "0" + num;
				seatnum.setText("I" + num);
				seatnum.setPadding(2, 2, 2, 2);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(j));
			}
			tl.addView(tr, 9);
			
			tr = new TableRow(getBaseContext());
			for (i=32, j=0; i>0; i--, j++) {
				seatnum = new TextView(getBaseContext());
				num = Integer.toString(i);
				if (i < 10) num = "0" + num;
				seatnum.setText("J" + num);
				seatnum.setPadding(2, 2, 2, 2);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(j));
			}
			tl.addView(tr, 10);
			
			tr = new TableRow(getBaseContext());
			for (i=32,j=0; i>0; i--,j++) {
				seatnum = new TextView(getBaseContext());
				num = Integer.toString(i);
				if (i < 10) num = "0" + num;
				seatnum.setText("K" + num);
				seatnum.setPadding(2, 2, 2, 2);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(j));
			}
			tl.addView(tr, 11);
			
			tr = new TableRow(getBaseContext());
			for (i=32,j=0; i>0; i--,j++) {
				seatnum = new TextView(getBaseContext());
				num = Integer.toString(i);
				if (i < 10) num = "0" + num;
				seatnum.setText("L" + num);
				seatnum.setPadding(2, 2, 2, 2);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(j));
			}
			tl.addView(tr, 12);
			
			tr = new TableRow(getBaseContext());
			for (i=32,j=0; i>0; i--,j++) {
				seatnum = new TextView(getBaseContext());
				num = Integer.toString(i);
				if (i < 10) num = "0" + num;
				seatnum.setText("M" + num);
				seatnum.setPadding(2, 2, 2, 2);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(j));
			}
			tl.addView(tr, 13);
			
			tr = new TableRow(getBaseContext());
			for (i=33,j=0; i>0; i--,j++) {
				seatnum = new TextView(getBaseContext());
				num = Integer.toString(i);
				if (i < 10) num = "0" + num;
				seatnum.setText("N" + num);
				seatnum.setPadding(2, 2, 2, 2);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(j));
			}
			tl.addView(tr, 14);
			
			tr = new TableRow(getBaseContext());
			for (i=33, j=0; i>0; i--,j++) {
				seatnum = new TextView(getBaseContext());
				num = Integer.toString(i);
				if (i < 10) num = "0" + num;
				seatnum.setText("P" + num);
				seatnum.setPadding(2, 2, 2, 2);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(j));
			}
			tl.addView(tr, 15);
			
			tr = new TableRow(getBaseContext());
			for (i=33,j=0; i>0; i--,j++) {
				seatnum = new TextView(getBaseContext());
				num = Integer.toString(i);
				if (i < 10) num = "0" + num;
				seatnum.setText("Q" + num);
				seatnum.setPadding(2, 2, 2, 2);
				seatnum.setTextColor(Color.BLACK);
				seatnum.setBackgroundResource(R.drawable.seat_background);
				seatnum.setGravity(Gravity.CENTER);
				seatnum.setOnClickListener(new View.OnClickListener() {
					
					boolean status=false;
					
					@Override
					public void onClick(View v) {
						if (status) {
							v.setBackgroundResource(R.drawable.seat_background);
							status = false;
						}
						else {
							v.setBackgroundColor(Color.YELLOW);
							status = true;
						}
					}					
				});
				tr.addView(seatnum, new TableRow.LayoutParams(j));
			}
			tl.addView(tr, 16);
			
			return tl;
		}
    
}
