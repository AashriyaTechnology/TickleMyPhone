package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;

public class StartVibrate extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        TML_Library.Debug("***********************I AM INSIDE StartVibrate************************");
        ((Vibrator) getSystemService("vibrator")).vibrate(5000);
        TML_Library.Debug("Vibrated 5000 times");
        finish();
    }
}
