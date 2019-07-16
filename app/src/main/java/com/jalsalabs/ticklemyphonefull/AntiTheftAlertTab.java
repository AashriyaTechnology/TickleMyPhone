package com.jalsalabs.ticklemyphonefull;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AntiTheftAlertTab extends TabActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost tabHost = getTabHost();
        Resources res = getResources();
        Intent intent = new Intent().setClass(this, AntiTheftReadme.class);
        TabSpec spec = tabHost.newTabSpec("Readme");
        spec.setIndicator("", res.getDrawable(R.drawable.readme));
        spec.setContent(intent);
        tabHost.addTab(spec);
        intent = new Intent().setClass(this, AntiTheftSIM.class);
        spec = tabHost.newTabSpec("Register");
        spec.setIndicator("", res.getDrawable(R.drawable.sim));
        spec.setContent(intent);
        tabHost.addTab(spec);
        intent = new Intent().setClass(this, AntiTheftContact.class);
        spec = tabHost.newTabSpec("Alternative");
        spec.setIndicator("", res.getDrawable(R.drawable.contact));
        spec.setContent(intent);
        tabHost.addTab(spec);
        intent = new Intent().setClass(this, AntiTheftAction.class);
        spec = tabHost.newTabSpec("Action");
        spec.setIndicator("", res.getDrawable(R.drawable.action));
        spec.setContent(intent);
        tabHost.addTab(spec);
        intent = new Intent().setClass(this, AntiTheftSaveEnable.class);
        spec = tabHost.newTabSpec("save");
        spec.setIndicator("", res.getDrawable(R.drawable.save));
        spec.setContent(intent);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);
    }
}
