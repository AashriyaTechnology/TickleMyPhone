package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings.System;

public class PlayDefaultRingTone extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MediaPlayer player = MediaPlayer.create(this, System.DEFAULT_RINGTONE_URI);
        try {
            TML_Library.Debug("Playing now");
            player.start();
            TML_Library.Sleep(player.getDuration());
            finish();
        } catch (Exception e) {
            TML_Library.PutToast(this, "Error " + e);
            TML_Library.Debug("Error " + e);
        }
    }
}
