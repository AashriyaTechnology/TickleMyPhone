package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;

public class AddContact extends Activity {
    public String message;
    public String phoneNo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String LS_BODY2NDTOKEN = TML_Library.GetParameter("BODY2NDTOKEN");
        TML_Library.Debug("Creating the Contact ");
        TML_Library.AddContact(this, LS_BODY2NDTOKEN, TML_Library.GetParameter("ORIGINAL_ADDRESS"));
        finish();
    }
}
