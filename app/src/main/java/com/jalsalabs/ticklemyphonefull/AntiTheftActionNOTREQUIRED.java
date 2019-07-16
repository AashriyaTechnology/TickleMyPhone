package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;

public class AntiTheftActionNOTREQUIRED extends Activity {
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        TML_Library.SetFullScreen(this);
        setContentView(R.layout.antitheftaction);
        TML_Library.Debug("0.0001");
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.antitheftaction);
        if (TML_Library.GetPref(this, "KEY_IS_FREE").equals("Y")) {
            TML_Library.PutToast(this, "Please note SIM change settings done here will not be saved. Since you are using Free Version");
            TML_Library.UpdateSettingsBacktoFree(this);
        }
    }
}
