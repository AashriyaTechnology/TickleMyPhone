package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;

public class PlayTouchMyLifeSong extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TML_Library.PlayWelcomeMusic(this);
        TML_Library.Sleep(15);
        finish();
    }
}
