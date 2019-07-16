package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;

public class CallBackNumber extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TML_Library.CallBackNumber(this, TML_Library.GetParameter("ORIGINAL_ADDRESS"), Boolean.valueOf(false));
        TML_Library.SetParameter("ORIGINAL_ADDRESS", "");
        finish();
    }
}
