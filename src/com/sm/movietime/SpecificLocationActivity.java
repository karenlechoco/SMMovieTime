package com.sm.movietime;

import com.sm.database.DBHelper_PrefsTable;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SpecificLocationActivity extends ListActivity {

	Intent i;
	String gen_loc;
	String movietitle;
	String[] smname;
	String[] metromanila = { "Bicutan", "Fairview", "Mall Of Asia", "Manila",
			"Marikina", "Megamall", "Muntinlupa", "North Edsa", "San Lazaro",
			"Southmall", "Sta. Mesa", "Sta. Rosa", "Sucat", "Taytay",
			"Valenzuela", "The Podium", "Novaliches" };

	String[] luzon = { "Baguio", "Batangas", "Baliwag", "Bacoor", "Calamba",
			"Clark", "Dasmarinas", "Lipa", "Lucena", "Marilao", "Molino",
			"Naga", "Pampanga", "Rosales", "Rosario", "San Pablo", "Tarlac",
			"Masinag", "Olongapo", "Calamba" };

	String[] vismin = { "Bacolod", "Cagayan de Oro", "Cebu", "Consolacion",
			"Davao", "Ilo-ilo" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle ex = getIntent().getExtras();
		if (ex != null) {

			gen_loc = ex.getString("general_loc");
			movietitle = ex.getString("MovieTitle");

			if (gen_loc.equals("Metro Manila"))
				smname = metromanila;
			else if (gen_loc.equals("Luzon"))
				smname = luzon;
			else if (gen_loc.equals("VisMin"))
				smname = vismin;
		}
		
		TextView header = new TextView(getBaseContext());
		header.setText(gen_loc);
		header.setTextSize(20);
		header.setBackgroundColor(Color.BLACK);
		header.setTextColor(Color.WHITE);
		header.setPadding(5, 5, 5, 5);
		header.setTypeface(null, Typeface.BOLD);

		ImageView bborder = new ImageView(getBaseContext());
		bborder.setImageResource(R.drawable.transparentborder);

		LinearLayout ll = new LinearLayout(getBaseContext());
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.addView(header);
		ll.addView(bborder);

		ListView lv = getListView();
		lv.addHeaderView(ll, null, false);

		ListAdapter adapter = new ListAdapter(this, smname);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub

		i = new Intent(getBaseContext(), Schedules.class);
		i.putExtra("smname", smname[position - 1]); // -1
		i.putExtra("genloc", gen_loc);
		i.putExtra("MovieTitle", movietitle);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		DBHelper_PrefsTable tbl = new DBHelper_PrefsTable(getBaseContext());
		if (tbl.isLoggedIn())
			getMenuInflater().inflate(R.menu.global_logout_menu, menu);
		else
			getMenuInflater().inflate(R.menu.global_login_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i;
		switch (item.getItemId()) {
		case R.id.logout:
			DBHelper_PrefsTable tbl = new DBHelper_PrefsTable(getBaseContext());
			tbl.deletePref();
			i = new Intent(getBaseContext(), TabMenu.class);
			startActivity(i);
			return true;
		case R.id.login:
			i = new Intent(getBaseContext(), CinemaLogin.class);
			startActivity(i);
			// return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public class ListAdapter extends ArrayAdapter<String> {
		private final Context context;
		private final String[] smname;

		public ListAdapter(Context context, String[] smname) {
			super(context, R.layout.activity_specific_location, smname);
			// TODO Auto-generated constructor stub
			this.smname = smname;
			this.context = context;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowview = (View) inflater.inflate(
					R.layout.activity_specific_location, parent, false);
			TextView txt1 = (TextView) rowview.findViewById(R.id.smname);
			txt1.setText(this.smname[position]);
			return rowview;
		}

	}

}
