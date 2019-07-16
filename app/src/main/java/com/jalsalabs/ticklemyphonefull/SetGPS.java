package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings.System;

public class SetGPS extends Activity {
    public String message;
    public String phoneNo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String LS_BODY2NDTOKEN = TML_Library.GetParameter("BODY2NDTOKEN");
        TML_Library.Debug(" I am inside On Create of SetGPS");
        TML_Library.Debug(" I am inside On Create of SetGPS");
        TML_Library.TurnGPS(this, LS_BODY2NDTOKEN);
        finish();
    }

    public void setLockPatternEnabled(boolean enabled) {
        setBoolean("lock_pattern_autolock", enabled);
    }

    private void setBoolean(String systemSettingKey, boolean enabled) {
        System.putInt(getContentResolver(), systemSettingKey, enabled ? 1 : 0);
    }
}
