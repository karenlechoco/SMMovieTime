package com.sm.movietime;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class TabMenu extends TabActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabmenu);
        
        Resources res = getResources();
        TabHost host = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
        
        intent = new Intent().setClass(this, NowShowing.class);
        spec = host.newTabSpec("now showing").setIndicator("Now Showing").setContent(intent);
        host.addTab(spec);
        
        intent = new Intent().setClass(this, NextAttraction.class);
        spec = host.newTabSpec("next attraction").setIndicator("Next Attraction").setContent(intent);
        host.addTab(spec);
        
        intent = new Intent().setClass(this, ComingSoon.class);
        spec = host.newTabSpec("coming soon").setIndicator("Coming Soon").setContent(intent);
        host.addTab(spec);
        
        host.setCurrentTab(3);
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tabmenu, menu);
        return true;
    }
}
