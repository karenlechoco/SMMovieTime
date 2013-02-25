package com.sm.movietime;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ProgressBar;

import com.sm.database.DBHelper_MovieTable;
import com.sm.database.Movie;

public class Launcher extends Activity {

	private ProgressBar bar;
	
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
			protected String doInBackground(String... params) {
				DBHelper_MovieTable movieTable = new DBHelper_MovieTable(getBaseContext());
				Movie m = new Movie();
				//now showing
				m.setCode("GASQ0944");
				m.setGenre("Action");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/now_showing/now_gangstersquad_zpsbebf533c.jpg");
				m.setStarring("Emma Stone, Ryan Gosling");
				m.setStatus("now");
				m.setSummary("Los Angeles, 1949: A secret crew of police officers led by two determined sergeants work together in an effort to take down the ruthless mob king Mickey Cohen who runs the city.");
				m.setTitle("Gangster Squad");
				movieTable.addMovie(m);
				
				m.setCode("HAGE6732");
				m.setGenre("Action, Suspense");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/now_showing/now_hanselandgretel_zps1a45f115.jpg");
				m.setStarring("Starring");
				m.setStatus("now");
				m.setSummary("In this spin on the fairy tale, Hansel & Gretel are now bounty hunters who track and kill witches all over the world. As the fabled Blood Moon approaches, the siblings encounter a new form of evil that might hold a secret to their past.");
				m.setTitle("Hansel and Gretel: Witch Hunters");
				movieTable.addMovie(m);
				
				m.setCode("OZGP8976");
				m.setGenre("Fantasy");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/now_showing/now_oz_zps73d527ef.jpg");
				m.setStarring("James France, Mila Kunis");
				m.setStatus("now");
				m.setSummary("A small-time magician with dubious ethics arrives in a magical land and must decide if he will be a good man or a great one.");
				m.setTitle("Oz, The Great and Powerful");
				movieTable.addMovie(m);
				
				m.setCode("ZEDT4955");
				m.setGenre("Action");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/now_showing/now_zerodarkthirty_zpsbfcae747.jpg");
				m.setStarring("Jessica Chastain");
				m.setStatus("now");
				m.setSummary("A chronicle of the decade-long hunt for al-Qaeda terrorist leader Osama bin Laden after the September 2001 attacks, and his death at the hands of the Navy S.E.A.L. Team 6 in May 2011.");
				m.setTitle("Zero Dark Thirty");
				movieTable.addMovie(m);
				
				//next attraction
				
				m.setCode("21OV3262");
				m.setGenre("Comedy");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/next_attraction/next_21andover_zps256048d3.jpg");
				m.setStarring("Miles Teller, Justin Chon");
				m.setStatus("next");
				m.setSummary("The night before his big medical school exam, a promising student celebrates his 21st birthday with his two best friends.");
				m.setTitle("21 and Over");
				movieTable.addMovie(m);
				
				m.setCode("MOST9051");
				m.setGenre("Action, Fiction");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/next_attraction/next_manofsteel_zpsf27cd3c2.jpg");
				m.setStarring("Starring");
				m.setStatus("next");
				m.setSummary("A young journalist raised by his adoptive parents after he was transported to Earth in infancy from the dying planet of Krypton finds himself in the position to save humankind after Earth is attacked.");
				m.setTitle("Man of Steel");
				movieTable.addMovie(m);
				
				m.setCode("SBLI1877");
				m.setGenre("Comedy, Fiction");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/next_attraction/next_struckbylightning_zpsfde477c2.jpg");
				m.setStarring("Chris Colfer");
				m.setStatus("next");
				m.setSummary("After being struck and killed by lightning, a young man recounts the way he blackmailed his fellow classmates into contributing to his literary magazine.");
				m.setTitle("Struck by Lightning");
				movieTable.addMovie(m);
				
				m.setCode("HOST5508");
				m.setGenre("Romance, Science Fiction");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/next_attraction/next_thehost_zps7a47464c.jpg");
				m.setStarring("Saoirse Ronan");
				m.setStatus("next");
				m.setSummary("A parasitic alien soul is injected into the body of Melanie Stryder. Instead of carrying out her race's mission of taking over the Earth, (Wanda, as she comes to be called) forms a bond with her host and sets out to aid other free humans.");
				m.setTitle("The Host");
				movieTable.addMovie(m);
				
				//coming soon
				m.setCode("FAF65622");
				m.setGenre("Action");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/coming_soon/soon_fastfurioussix_zps8489c131.jpg");
				m.setStarring("Vin Diesel, Paul Walker");
				m.setStatus("soon");
				m.setSummary("Agent Hobbs enlists the aid of Dom and team to help bring a rival gang, led by Owen Shaw, to justice. In exchange for clear records, they must put an end to their schemes, no matter how personal the cost.");
				m.setTitle("Fast and Furious 6");
				movieTable.addMovie(m);
				
				m.setCode("GIJ27711");
				m.setGenre("Action, Science Fiction");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/coming_soon/soon_gijoetwo_zpsb8b2e97f.jpg");
				m.setStarring("Channing Tatum, Bruce Willis");
				m.setStatus("soon");
				m.setSummary("Summary");
				m.setTitle("G.I. Joe 2");
				movieTable.addMovie(m);
				
				m.setCode("IRM35709");
				m.setGenre("Action, Science Fiction");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/coming_soon/soon_ironmanthree_zpsce308eb0.jpg");
				m.setStarring("Robert Downey Jr., Gwyneth Paltrow");
				m.setStatus("soon");
				m.setSummary("Summary");
				m.setTitle("Iron Man 3");
				movieTable.addMovie(m);
				
				m.setCode("UFO88620");
				m.setGenre("Science Fiction, Suspense");
				m.setPosterUrl("http://i1326.photobucket.com/albums/u659/karenlechoco/coming_soon/soon_ufo_zpsc1698203.jpg");
				m.setStarring("Starring");
				m.setStatus("soon");
				m.setSummary("Summary");
				m.setTitle("UFO");
				movieTable.addMovie(m);
				
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
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
