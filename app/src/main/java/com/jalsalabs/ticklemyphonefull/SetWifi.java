package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;

public class SetWifi extends Activity {
    public String message;
    public String phoneNo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TML_Library.TurnWifi(this, TML_Library.GetParameter("BODY2NDTOKEN"));
        TML_Library.Debug("Yes baby i am setting wifi to 1");
        TML_Library.PutToast(this, "Yes baby i am setting wifi to 1");
        finish();
    }
}
