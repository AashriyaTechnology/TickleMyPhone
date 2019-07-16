package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import com.sun.mail.imap.IMAPStore;

public class StartVibrateNSeconds extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        int i;
        super.onCreate(savedInstanceState);
        TML_Library.Debug("***********************I AM INSIDE StartVibrateNSecond************************");
        Vibrator myVib = (Vibrator) getSystemService("vibrator");
        String noofseconds = TML_Library.GetParameter("BODY2NDTOKEN");
        try {
            i = Integer.parseInt(noofseconds);
        } catch (Exception e) {
            i = 10;
        }
        TML_Library.Debug("noofseconds=" + noofseconds + " and parse int i = " + i + " Now i will vibreate for " + (i * IMAPStore.RESPONSE));
        myVib.vibrate((long) (i * IMAPStore.RESPONSE));
        finish();
    }
}
