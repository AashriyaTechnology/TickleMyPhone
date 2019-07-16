package com.jalsalabs.ticklemyphonefull;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AutoAnswerSetupTAB extends TabActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost tabHost = getTabHost();
        Resources res = getResources();
        Intent intent = new Intent().setClass(this, AutoAnswerReadMe.class);
        TabSpec spec = tabHost.newTabSpec("Readme");
        spec.setIndicator("", res.getDrawable(R.drawable.readme));
        spec.setContent(intent);
        tabHost.addTab(spec);
        intent = new Intent().setClass(this, AutoAnswerSettings.class);
        spec = tabHost.newTabSpec("Settings");
        spec.setIndicator("", res.getDrawable(R.drawable.setup));
        spec.setContent(intent);
        tabHost.addTab(spec);
        if (TML_Library.GetPref(this, "KEY_IS_FREE").contains("Y")) {
            intent = new Intent().setClass(this, Free2Paid.class);
            spec = tabHost.newTabSpec("Free2Paid");
            spec.setIndicator("", res.getDrawable(R.drawable.thanks));
            spec.setContent(intent);
            tabHost.addTab(spec);
        }
        tabHost.setCurrentTab(0);
    }
}
