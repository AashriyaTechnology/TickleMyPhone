package com.jalsalabs.ticklemyphonefull;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TB_SetupTAB extends TabActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost tabHost = getTabHost();
        Resources res = getResources();
        String isFree = TML_Library.GetPref(this, "KEY_IS_FREE");
        Intent intent = new Intent().setClass(this, AutoAnswerReadMe.class);
        TabSpec spec = tabHost.newTabSpec("Readme");
        spec.setIndicator("", res.getDrawable(R.drawable.readme));
        spec.setContent(intent);
        tabHost.addTab(spec);
        intent = new Intent().setClass(this, TB_AlarmManagerActivity.class);
        spec = tabHost.newTabSpec("Settings");
        spec.setIndicator("", res.getDrawable(R.drawable.timebomb));
        spec.setContent(intent);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);
    }
}
