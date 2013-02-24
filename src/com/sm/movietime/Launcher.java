package com.sm.movietime;

import com.sm.database.*;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

public class Launcher extends Activity {

	private ProgressBar bar;
	private ProgressDialog progress;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
                
        bar = (ProgressBar)findViewById(R.id.progressBar1);
        bar.setMax(10);
        
        new PopulateDatabase().execute(new String[]{});
                
    }
    
    	private class PopulateDatabase extends AsyncTask <String, Void, String> {			
    		
    		@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
			}

			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				
				DBHelper_MovieTable movieTable = new DBHelper_MovieTable(getBaseContext());
				
				Movie m = new Movie();
				
				//nowshowing
				
				m.setCode("GASQ0944");
				m.setGenre("Action");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/now_showing/now_gangstersquad_zpsbebf533c.jpg");
				m.setStarring("Emma Stone, Ryan Gosling");
				m.setStatus("now");
				m.setSummary("Summary");
				m.setTitle("Gangster Squad");
				movieTable.addMovie(m);
				
				m.setCode("HAGE6732");
				m.setGenre("Action, Suspense");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/now_showing/now_hanselandgretel_zps1a45f115.jpg");
				m.setStarring("Starring");
				m.setStatus("now");
				m.setSummary("Summary");
				m.setTitle("Hansel and Gretel");
				movieTable.addMovie(m);
				
				m.setCode("OZGP8976");
				m.setGenre("Fantasy");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/now_showing/now_oz_zps73d527ef.jpg");
				m.setStarring("James France, Mila Kunis");
				m.setStatus("now");
				m.setSummary("Summary");
				m.setTitle("Oz, The Great and Powerful");
				movieTable.addMovie(m);
				
				m.setCode("ZEDT4955");
				m.setGenre("Action");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/now_showing/now_zerodarkthirty_zpsbfcae747.jpg");
				m.setStarring("Jessica Chastain");
				m.setStatus("now");
				m.setSummary("Summary");
				m.setTitle("Zero Dark Thirty");
				movieTable.addMovie(m);
				
//				//nextattraction
//				
//				m.setCode("21OV3262");
//				m.setGenre("Comedy");
//				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/next_attraction/next_21andover_zps256048d3.jpg");
//				m.setStarring("Starring");
//				m.setStatus("next");
//				m.setSummary("Summary");
//				m.setCode("21 and Over");
//				movieTable.addMovie(m);
//				
//				m.setCode("MOST9051");
//				m.setGenre("Action, Fiction");
//				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/next_attraction/next_manofsteel_zpsf27cd3c2.jpg");
//				m.setStarring("Starring");
//				m.setStatus("next");
//				m.setSummary("Summary");
//				m.setTitle("Man of Steel");
//				movieTable.addMovie(m);
//				
//				m.setCode("SBLI1877");
//				m.setGenre("Comedy, Fiction");
//				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/next_attraction/next_struckbylightning_zpsfde477c2.jpg");
//				m.setStarring("Chris Colfer");
//				m.setStatus("next");
//				m.setSummary("Summary");
//				m.setTitle("Struck by Lightning");
//				movieTable.addMovie(m);
//				
//				m.setCode("HOST5508");
//				m.setGenre("Romance, Science Fiction");
//				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/next_attraction/next_thehost_zps7a47464c.jpg");
//				m.setStarring("Starring");
//				m.setStatus("next");
//				m.setSummary("Summary");
//				m.setTitle("The Host");
//				movieTable.addMovie(m);
//				
//				//coming soon
//				
//				m.setCode("FAF65622");
//				m.setGenre("Action");
//				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/coming_soon/soon_fastfurioussix_zps8489c131.jpg");
//				m.setStarring("Vin Diesel, Paul Walker");
//				m.setStatus("soon");
//				m.setSummary("Summary");
//				m.setTitle("Fast and Furious 6");
//				movieTable.addMovie(m);
//				
//				m.setCode("GIJ27711");
//				m.setGenre("Action, Science Fiction");
//				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/coming_soon/soon_gijoetwo_zpsb8b2e97f.jpg");
//				m.setStarring("Channing Tatum, Bruce Willis");
//				m.setStatus("soon");
//				m.setSummary("Summary");
//				m.setTitle("G.I. Joe 2");
//				movieTable.addMovie(m);
//				
//				m.setCode("IRM35709");
//				m.setGenre("Action, Science Fiction");
//				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/coming_soon/soon_ironmanthree_zpsce308eb0.jpg");
//				m.setStarring("Robert Downey Jr., Gwyneth Paltrow");
//				m.setStatus("soon");
//				m.setSummary("Summary");
//				m.setTitle("Iron Man 3");
//				movieTable.addMovie(m);
//				
//				m.setCode("UFO88620");
//				m.setGenre("Science Fiction, Suspense");
//				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/coming_soon/soon_ufo_zpsc1698203.jpg");
//				m.setStarring("Starring");
//				m.setStatus("soon");
//				m.setSummary("Summary");
//				m.setTitle("UFO");
//				movieTable.addMovie(m);
				
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				bar.incrementProgressBy(10);
				if (bar.getProgress() >= 10) {
					Intent i = new Intent(getBaseContext(), TabMenu.class);
					startActivity(i);
				}
			}
			    		
    	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_launcher, menu);
        return true;
    }
}
