package com.sm.movietime;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.sm.movietime.NowShowing.ImageAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NextAttraction extends Activity {

	List<Integer> next;
	List<String> nextdetails;
	
	TextView details;
	Integer currentMovie;
	Button moreinfo;
	Intent btn_intnt;
	int x,y;
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_attraction);
        currentMovie=0;
        
        nextdetails = new ArrayList<String>();

        nextdetails.add("21 and Over");
        nextdetails.add("Man of Steel");
        nextdetails.add("Struck by Lightning");
        nextdetails.add("The Host");
        
        Field[] fields = R.drawable.class.getFields();
        next = new ArrayList<Integer>();
        for (Field field : fields) {
            // Take only those with name starting with "fr"
            if (field.getName().startsWith("next_")) {
                try {
					next.add(field.getInt(null));
					//nextdetails.add(String.valueOf(field.getInt(null)));
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
        details.setText(nextdetails.get(0));
        
        
        
//        CoverFlow coverFlow = new CoverFlow(this);
//       coverFlow.setBackgroundColor(Color.TRANSPARENT);
//        coverFlow.setAdapter(new ImageAdapter(this));
//        coverFlow.setGravity(Gravity.CENTER);
        
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
				details.setText(nextdetails.get(arg2));
				currentMovie = arg2;
				arg1.setAlpha(1);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
                        
        coverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Rect frame = new Rect();
				arg1.getGlobalVisibleRect(frame);
				
				//check if clicked poster is one at the center
				//if yes, go to moviedetails.class
				//if no, the unselected poster becomes center
				if (frame.contains(x, y) && arg2==currentMovie) {
					btn_intnt = new Intent(getBaseContext(), MovieDetails.class);
					btn_intnt.putExtra("MovieTitle", nextdetails.get(currentMovie));
					btn_intnt.putExtra("MoviePoster", next.get(currentMovie));
					btn_intnt.putExtra("status", "next");
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
        getMenuInflater().inflate(R.menu.activity_next_attraction, menu);
        return true;
    }
    
    public class ImageAdapter extends BaseAdapter {
		 int mGalleryItemBackground;
	     private Context mContext;
	
	     private ImageView[] mImages;
	     
	     public ImageAdapter(Context c) {
	      mContext = c;
	      mImages = new ImageView[next.size()];
	     }
	     
	     public boolean createReflectedImages() {
	          //The gap we want between the reflection and the original image
	    	 final int reflectionGap = 4;
	          
	          
	    	 int index = 0;
	         for (int imageId : next) {
	        	 Bitmap originalImage = BitmapFactory.decodeResource(getResources(), imageId);
	        	 int width = originalImage.getWidth();
	        	 int height = originalImage.getHeight();
	           
	     
		           //This will not scale but will flip on the Y axis
		           Matrix matrix = new Matrix();
		           matrix.preScale(1, -1);
		           
		           //Create a Bitmap with the flip matrix applied to it.
		           //We only want the bottom half of the image
		           Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0, height/2, width, height/2, matrix, false);
		           
		               
		           //Create a new bitmap with same width but taller to fit reflection
		           Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + height/2), Config.ARGB_8888);
		         
		          //Create a new Canvas with the bitmap that's big enough for
		          //the image plus gap plus reflection
		          Canvas canvas = new Canvas(bitmapWithReflection);
		          //Draw in the original image
		          canvas.drawBitmap(originalImage, 0, 0, null);
		          //Draw in the gap
		          Paint deafaultPaint = new Paint();
		          canvas.drawRect(0, height, width, height + reflectionGap, deafaultPaint);
		          //Draw in the reflection
		          canvas.drawBitmap(reflectionImage,0, height + reflectionGap, null);
		          
		          //Create a shader that is a linear gradient that covers the reflection
		          Paint paint = new Paint(); 
		          LinearGradient shader = new LinearGradient(0, originalImage.getHeight(), 0, 
		            bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, 
		            TileMode.CLAMP); 
		          //Set the paint to use this shader (linear gradient)
		          paint.setShader(shader); 
		          //Set the Transfer mode to be porter duff and destination in
		          paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN)); 
		          //Draw a rectangle using the paint with our linear gradient
		          canvas.drawRect(0, height, width, 
		            bitmapWithReflection.getHeight() + reflectionGap, paint); 
		          
		          ImageView imageView = new ImageView(mContext);
		          imageView.setImageBitmap(bitmapWithReflection);
		          imageView.setLayoutParams(new CoverFlow.LayoutParams(120, 180));
		          imageView.setScaleType(ScaleType.MATRIX);
		          mImages[index++] = imageView;
	          }//end of for
	       return true;
	     }
	
	     public int getCount() {
	         return next.size();
	     }
	
	     public Object getItem(int position) {
	         return position;
	     }
	
	     public long getItemId(int position) {
	         return position;
	     }
	
	     public View getView(int position, View convertView, ViewGroup parent) {
	
	      //Use this code if you want to load from resources
	    	 ImageView i = new ImageView(mContext);
	    	 i.setImageResource(next.get(position));
	         
	         i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	         i.setScaleType(ImageView.ScaleType.CENTER_INSIDE); 
	         
	         //Make sure we set anti-aliasing otherwise we get jaggies
	         BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
	         drawable.setAntiAlias(true);
	         	         
	         return i;
	      
	      //return mImages[position];
	     }
	   /** Returns the size (0.0f to 1.0f) of the views 
	      * depending on the 'offset' to the center. */ 
	      public float getScale(boolean focused, int offset) { 
	        /* Formula: 1 / (2 ^ offset) */ 
	          return Math.max(0, 1.0f / (float)Math.pow(2, Math.abs(offset))); 
	      } 
	
	 }

}
