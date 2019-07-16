package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;

public class CallUserNumberSpeakerOn extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TML_Library.Debug("******** I AM INSIDE   CallUserNumberSpeakerOn***********");
        String LS_UserCallNumber = TML_Library.GetParameter("BODY2NDTOKEN");
        TML_Library.Debug("Number to be called is " + LS_UserCallNumber);
        TML_Library.CallBackNumber(this, LS_UserCallNumber, Boolean.valueOf(true));
        LS_UserCallNumber = "";
        finish();
    }
}
