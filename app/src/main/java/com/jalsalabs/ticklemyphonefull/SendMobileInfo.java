package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;

public class SendMobileInfo extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        String senderphoneNo = TML_Library.GetParameter("ORIGINAL_ADDRESS");
        TML_Library.Debug("***********************I AM INSIDE SendMobileInfo************************");
        String LS_RingMode = TML_Library.GetRingMode(this);
        String LS_GPSInfo = TML_Library.GetGPSinfo(this);
        String LS_InternetInfo = TML_Library.GetInternetConnectionInfo(this);
        TML_Library.SetParameter("BATTERY_LEVEL", "Unknown");
        TML_Library.Debug("Dummy set :" + TML_Library.GetParameter("BATTERY_LEVEL"));
        UpdateBatteryLevel(this);
        if (TML_Library.GetParameter("BATTERY_LEVEL").equals("Unknown")) {
            NewUpdateBatteryLevel(this);
        }
        String LS_BatteryLevel = TML_Library.GetParameter("BATTERY_LEVEL");
        String LS_SDCardSpace = TML_Library.GetSDCardSpace(this);
        TML_Library.getCallCount(this, 0, 0, 0);
        int TotalMissedCall = 0;
        String PhoneDevice = Build.DEVICE;
        String PhoneModel = Build.MODEL;
        String PhoneProduct = Build.PRODUCT;
        String LS_Message = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("Ring mode: " + LS_RingMode + "\n")).append("GPS: ").append(LS_GPSInfo).append("\n").toString())).append("Internet: ").append(LS_InternetInfo).append("\n").toString())).append("Battery: ").append(LS_BatteryLevel).append("\n").toString())).append("SD Space: ").append(LS_SDCardSpace).append("\n").toString())).append("Missed Calls: ").append(TotalMissedCall).append("\n").toString())).append("Brand: ").append(PhoneDevice).append(" ").append(PhoneModel).append(" ").append(PhoneProduct).append("\n").toString())).append("Android: ").append(VERSION.RELEASE).append("\n").toString();
        TML_Library.Debug("Message is " + LS_Message);
        if (TML_Library.GetPref(this, "KEY_IS_APP_ACTIVATE").equals("Y")) {
            TML_Library.sendSMSBig(this, senderphoneNo, LS_Message);
        } else {
            TML_Library.sendSMSBig(this, senderphoneNo, new StringBuilder(String.valueOf(LS_Message)).append(" ").append(getString(R.string.oth19_free_version)).toString());
        }
        finish();
    }

    private void NewUpdateBatteryLevel(Context context) {
        Intent batteryIntent = context.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int rawlevel = batteryIntent.getIntExtra("level", -1);
        double scale = (double) batteryIntent.getIntExtra("scale", -1);
        if (rawlevel >= 0 && scale > 0.0d) {
            TML_Library.SetParameter("BATTERY_LEVEL", new StringBuilder(String.valueOf(((double) (rawlevel * 100)) / scale)).append(" %").toString());
        }
    }

    private void UpdateBatteryLevel(Context context) {
        context.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                int rawlevel = intent.getIntExtra("level", -1);
                int scale = intent.getIntExtra("scale", -1);
                int level = -1;
                if (rawlevel >= 0 && scale > 0) {
                    level = (rawlevel * 100) / scale;
                }
                TML_Library.SetParameter("BATTERY_LEVEL", new StringBuilder(String.valueOf(level)).append(" %").toString());
            }
        }, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }
}
