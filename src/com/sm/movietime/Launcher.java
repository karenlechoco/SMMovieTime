package com.sm.movietime;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;

import com.sm.database.DBHelper_MovieTable;
import com.sm.database.DBHelper_ScheduleTable;
import com.sm.database.DBHelper_UserAccountTable;
import com.sm.database.DB_Initializer;
import com.sm.database.Movie;
import com.sm.database.Schedule;
import com.sm.database.UserAccount;

public class Launcher extends Activity {

	private ProgressBar bar;
	String[] movieCode = {
			"GASQ0944", "HAGE6732", "OZGP8976", "ZEDT4955",
			"21OV3262", "MOST9051", "SBLI1877", "HOST5508",
			"FAF65622", "GIJ27711", "IRM35709", "UFO88620"
	};
	
	String[] movieGenre = {
			"Action", "Action, Suspense", "Fantasy", "Action",
			"Comedy", "Action, Fiction", "Comedy, Fiction", "Romance, Science Fiction",
			"Action", "Action, Science Fiction", "Action, Science Fiction", "Science Fiction, Suspense"
	};
	
	String[] movieURL = {
			"http://i1326.photobucket.com/albums/u659/karenlechoco/now_showing/now_gangstersquad_zpsbebf533c.jpg",
			"http://i1326.photobucket.com/albums/u659/karenlechoco/now_showing/now_hanselandgretel_zps1a45f115.jpg",
			"http://i1326.photobucket.com/albums/u659/karenlechoco/now_showing/now_oz_zps73d527ef.jpg",
			"http://i1326.photobucket.com/albums/u659/karenlechoco/now_showing/now_zerodarkthirty_zpsbfcae747.jpg",
			
			"http://i1326.photobucket.com/albums/u659/karenlechoco/next_attraction/next_21andover_zps256048d3.jpg",
			"http://i1326.photobucket.com/albums/u659/karenlechoco/next_attraction/next_manofsteel_zpsf27cd3c2.jpg",
			"http://i1326.photobucket.com/albums/u659/karenlechoco/next_attraction/next_struckbylightning_zpsfde477c2.jpg",
			"http://i1326.photobucket.com/albums/u659/karenlechoco/next_attraction/next_thehost_zps7a47464c.jpg",
			
			"http://i1326.photobucket.com/albums/u659/karenlechoco/coming_soon/soon_fastfurioussix_zps8489c131.jpg",
			"http://i1326.photobucket.com/albums/u659/karenlechoco/coming_soon/soon_gijoetwo_zpsb8b2e97f.jpg",
			"http://i1326.photobucket.com/albums/u659/karenlechoco/coming_soon/soon_ironmanthree_zpsce308eb0.jpg",
			"http://i1326.photobucket.com/albums/u659/karenlechoco/coming_soon/soon_ufo_zpsc1698203.jpg"
	};
	
	String[] movieStarring = {
			"Emma Stone, Ryan Gosling", "Jeremy Rener", "James Franco, Mila Kunis", "Jessica Chastain",
			"Miles Teller, Justin Chon", "Henry Cavill", "Chris Colfer", "Saoirse Ronan",
			"Vin Diesel, Paul Walker", "Channing Tatum, Bruce Willis", "Robert Downey Jr., Gwyneth Paltrow", "Bianca Bree, Simon Phillips"
	};
	
	String[] movieSummary = {
		"Los Angeles, 1949: A secret crew of police officers led by two determined sergeants work together in an effort to take down the ruthless mob king Mickey Cohen who runs the city.",
		"In this spin on the fairy tale, Hansel & Gretel are now bounty hunters who track and kill witches all over the world. As the fabled Blood Moon approaches, the siblings encounter a new form of evil that might hold a secret to their past.",
		"A small-time magician with dubious ethics arrives in a magical land and must decide if he will be a good man or a great one.",
		"A chronicle of the decade-long hunt for al-Qaeda terrorist leader Osama bin Laden after the September 2001 attacks, and his death at the hands of the Navy S.E.A.L. Team 6 in May 2011.",
		
		"The night before his big medical school exam, a promising student celebrates his 21st birthday with his two best friends.",
		"A young journalist raised by his adoptive parents after he was transported to Earth in infancy from the dying planet of Krypton finds himself in the position to save humankind after Earth is attacked.",
		"After being struck and killed by lightning, a young man recounts the way he blackmailed his fellow classmates into contributing to his literary magazine.",
		"A parasitic alien soul is injected into the body of Melanie Stryder. Instead of carrying out her race's mission of taking over the Earth, (Wanda, as she comes to be called) forms a bond with her host and sets out to aid other free humans.",
		
		"Agent Hobbs enlists the aid of Dom and team to help bring a rival gang, led by Owen Shaw, to justice. In exchange for clear records, they must put an end to their schemes, no matter how personal the cost.",
		"The G.I. Joes are not only fighting their mortal enemy Cobra; they are forced to contend with threats from within the government that jeopardize their very existence.",
		"Marvel's Iron Man 3 pits brash-but-brilliant industrialist Tony Stark/Iron Man against an enemy whose reach knows no bounds (Mandarin). When Stark finds his personal world destroyed at his enemy's hands, he embarks on a harrowing quest to find those responsible. This journey, at every turn, will test his mettle. With his back against the wall, Stark is left to survive by his own devices, relying on his ingenuity and instincts to protect those closest to him. As he fights his way back, Stark discovers the answer to the question that has secretly haunted him: does the man make the suit or does the suit make the man?",
		"Friends get together for a night out in a bar. Life is wonderful as a couple share their proposal moment. When, suddenly strange things start to happen. Loss of power. Throughout the city. No phones, no lights, just darkness as the friends try to cope. The sky is darkened by the ships of the alien invaders. The takeover of planet Earth has begun as our the five friends struggle to survive amid the chaos and calamity. Will love survive the terror?"
	};
	
	String[] movieTitle = {
		"Gangster Squad", "Hansel and Gretel", "Oz: The Great and Powerful", "Zero Dark Thirty",
		"21 and Over", "Man of Steel", "Struck by Lightning", "The Host",
		"Fast & Furious 6", "G.I. Joe 2", "Iron Man 3", "UFO"
	};
	
	String[] status = {"now", "next", "soon"};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        bar = (ProgressBar)findViewById(R.id.progressBar1);
        bar.setMax(10);
        DB_Initializer db_init = new DB_Initializer(getBaseContext());
        new PopulateMovieTable().execute(new String[]{});
        new PopulateScheduleTable().execute(new String[]{});
        new PopulateUserTable().execute(new String[]{});
    }
    
    	private class PopulateMovieTable extends AsyncTask <String, Void, String> {			
			@Override
			protected String doInBackground(String... params) {
				DBHelper_MovieTable movieTable = new DBHelper_MovieTable(getBaseContext());
				Movie m = new Movie();
				//now showing
				
												
				for (int i = 0, j=0; i < movieTitle.length; i++) {
					
					m.setCode(movieCode[i]);
					m.setGenre(movieGenre[i]);
					m.setPosterUrl(movieURL[i]);
					m.setStarring(movieStarring[i]);
					m.setStatus(status[j]);
						if (j<3 && (i+1)%4 == 0) j++;
					m.setSummary(movieSummary[i]);
					m.setTitle(movieTitle[i]);
					movieTable.addMovie(m);
				}
				
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				bar.incrementProgressBy(3);
				if (bar.getProgress() >= 10) {
					Intent i = new Intent(getBaseContext(), TabMenu.class);
					startActivity(i);
				}
			}
			    		
    	}
    	
    	private class PopulateScheduleTable extends AsyncTask<String, Void, String> {

    		String [] metromanila={
					   "Bicutan",
			           "Fairview",
			           "Mall Of Asia",
			           "Manila",
			           "Marikina",
			           "Megamall",
			           "Muntinlupa",
			           "North Edsa",
			           "San Lazaro",
			           "Southmall",
			           "Sta. Mesa",
			           "Sta. Rosa",
			           "Sucat",
			           "Taytay",
			           "Valenzuela",
			           "The Podium",
			           "Novaliches"
			           }; //[16] largest
	
			String [] luzon={"Baguio",
				           "Batangas",
				           "Baliwag",
				           "Bacoor",
				           "Calamba",
				           "Clark",
				           "Dasmarinas",
				           "Lipa",
				           "Lucena",
				           "Marilao",
				           "Molino",
				           "Naga",
				           "Pampanga",
				           "Rosales",
				           "Rosario",
				           "San Pablo",
				           "Tarlac",
				           "Masinag",
				           "Olongapo",
				           "Calamba"
				           }; //[19] largest
			
			String [] vismin={"Bacolod",
			           "Cagayan de Oro",
			           "Cebu",
			           "Consolacion",
			           "Davao",
			           "Ilo-ilo"
			           }; // [5] largest
    		
			@Override
			protected String doInBackground(String... params) {
				DBHelper_ScheduleTable schedTable = new DBHelper_ScheduleTable(getBaseContext());
				
				Schedule s = new Schedule();
				
				Integer[] mm_locs = {0,3,4,6,9,11,15};
				Integer[] lu_locs = {1,3,6,9,10,15,17,18};
				Integer[] vm_locs = {0,1,2,3,4,5};
				
				s.setMovieName("Gangster Squad");
				s.setCinemaType("FreeSeating");
				s.setCinemaName("Cinema 2");
				s.setDate("March 13, 2013");
				s.setGeneralLocation("VisMin");
				s.setSpecificLocation("Cebu");
				s.setTicketPrice(200.00);
				String[] times = {"1:00PM", "3:00PM", "7:00PM"};
				Integer[] vacant = {98,72,131};
				for (int i=0; i<times.length; i++) {
					s.setTime(times[i]);
					s.setVacantSeats(vacant[i]);
					schedTable.addSchedule(s);
				}
				
				s.setMovieName("Gangster Squad");
				s.setCinemaType("FreeSeating");
				s.setCinemaName("Cinema 3");
				s.setDate("March 14, 2013");
				s.setGeneralLocation("VisMin");
				s.setSpecificLocation("Cebu");
				s.setTicketPrice(200.00);
				String[] times2 = {"2:00PM", "4:00PM", "8:00PM"};
				Integer[] vacant2 = {98,72,131};
				for (int i=0; i<times.length; i++) {
					s.setTime(times2[i]);
					s.setVacantSeats(vacant2[i]);
					schedTable.addSchedule(s);
				}
				
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				bar.incrementProgressBy(5);
				if (bar.getProgress() >= 10) {
					Intent i = new Intent(getBaseContext(), TabMenu.class);
					startActivity(i);
				}
			}
    	}
    	
    	private class PopulateUserTable extends AsyncTask <String, Void, String> {

			@Override
			protected String doInBackground(String... arg0) {
				DBHelper_UserAccountTable tbl = new DBHelper_UserAccountTable(getBaseContext());
				String[] emails = { "roy@gmail.com", "paula@gmail.com", "naphy@gmail.com", "karen@gmail.com" };
				
				UserAccount u = new UserAccount();
				for (int i=0; i<emails.length; i++) {
					u.setEmail(emails[i]);
					u.setPassword("password");
					tbl.addAccount(u);
					Log.d("APP", emails[i]);
				}
								
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				bar.incrementProgressBy(2);
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
