package com.sm.movietime;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.widget.TabHost;

public class tabmenu extends TabActivity {

	private String string;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabmenu);
        
        Resources res = getResources();
        TabHost host = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
        
        intent = new Intent().setClass(this, NowShowing.class);
        spec = host.newTabSpec("now showing").setIndicator("Now Showing", res.getDrawable(R.drawable.ic_action_search)).setContent(intent);
        host.addTab(spec);
        
        intent = new Intent().setClass(this, NextAttraction.class);
        spec = host.newTabSpec("next attraction").setIndicator("Next Attraction", res.getDrawable(R.drawable.ic_action_search)).setContent(intent);
        host.addTab(spec);
        
        intent = new Intent().setClass(this, ComingSoon.class);
        spec = host.newTabSpec("coming soon").setIndicator("Coming Soon", res.getDrawable(R.drawable.ic_action_search)).setContent(intent);
        host.addTab(spec);
        
        host.setCurrentTab(2);
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_now_showing, menu);
        return true;
    }
}