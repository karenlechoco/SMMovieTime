package com.sm.movietime;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.sm.database.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NowShowing extends Activity {

	List<Integer> now;	
	List<Movie> now_movies;

	TextView details;
	Integer currentMovie, x, y;
	Intent btn_intnt, tabs_intnt;
	
	ProgressBar bar;
		
	@Override
	public void onBackPressed() {
		finish();
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_showing);        
        currentMovie=0; //set default value to 0, in case first movie is preferred by user
        
        now_movies = new ArrayList<Movie>();
        
        DBHelper_MovieTable tbl = new DBHelper_MovieTable(getBaseContext());
		now_movies = tbl.getMovies("now");
        
        Field[] fields = R.drawable.class.getFields();
        now = new ArrayList<Integer>();
        for (Field field : fields) {
            // Take only those with name starting with "fr"
            if (field.getName().startsWith("now_")) {
                try {
					now.add(field.getInt(null));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
        
        details = (TextView)findViewById(R.id.details);
        details.setText(now_movies.get(0).getTitle());

        final Gallery coverFlow = (Gallery)findViewById(R.id.movie_catalogue);
        coverFlow.setAdapter(new ImageAdapter(this));
        coverFlow.setSpacing(10);
        coverFlow.setSelected(true);
        coverFlow.setSelection(0, true);
        coverFlow.setFocusable(false);
        
        coverFlow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				details.setText(now_movies.get(arg2).getTitle());
				currentMovie = arg2;
				arg1.setAlpha(1);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
                        
        coverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Rect frame = new Rect();
				arg1.getGlobalVisibleRect(frame);
				
				//check if clicked poster is one at the center
				//if yes, go to moviedetails.class
				//if no, the unselected poster becomes center
				if (frame.contains(x, y) && arg2==currentMovie) {
					btn_intnt = new Intent(getBaseContext(), MovieDetails.class);
					btn_intnt.putExtra("MovieTitle", now_movies.get(currentMovie).getTitle());
					btn_intnt.putExtra("MoviePoster", now.get(currentMovie));
					btn_intnt.putExtra("status", "now");
					startActivity(btn_intnt);
				}				
			}
		});        
    }

    @Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
    	x = (int)event.getRawX();
        y = (int)event.getRawY();
		return super.onTouchEvent(event);
	}
    
    public boolean onTouch(View v, MotionEvent event) {
        x = (int)event.getRawX();
        y = (int)event.getRawY();
        return false;
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_now_showing, menu);
        return true;
    }
    	
    public class ImageAdapter extends BaseAdapter {
		 int mGalleryItemBackground;
	     private Context mContext;
	     
	     public ImageAdapter(Context c) { mContext = c; }
	     	
	     public int getCount() { return now.size(); }
	
	     public Object getItem(int position) { return position; }
	
	     public long getItemId(int position) { return position; }
	
	     public View getView(int position, View convertView, ViewGroup parent) {
	    	 ImageView i = new ImageView(mContext);
	    	 i.setImageResource(now.get(position));
	         i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));	         	         
	         return i;
	     }
	
	 }

}
