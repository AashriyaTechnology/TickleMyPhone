package com.jalsalabs.ticklemyphonefull;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;

public class OutgoingCallReceiver4455 extends BroadcastReceiver {
    private static final String INTENT_PHONE_NUMBER = "android.intent.extra.PHONE_NUMBER";
    private static final String OUTGOING_CALL_ACTION = "android.intent.action.NEW_OUTGOING_CALL";

    public void onReceive(Context context, Intent intent) {
        String ABORT_PHONE_NUMBER = PreferenceManager.getDefaultSharedPreferences(context).getString("dialnumber", "4455").toUpperCase();
        TML_Library.Debug("OutgoingCallReceiver onReceive");
        if (intent.getAction().equals(OUTGOING_CALL_ACTION)) {
            TML_Library.Debug("OutgoingCallReceiver NEW_OUTGOING_CALL received");
            String phoneNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
            if (phoneNumber == null) {
                return;
            }
            if (phoneNumber.equals(ABORT_PHONE_NUMBER) || phoneNumber.equals("4445")) {
                TML_Library.Sleep(1);
                setResultData(null);
                TML_Library.Sleep(2);
                TML_Library.Debug("Calling now sleeping is over");
                Intent intentx = new Intent(context, MainMenu.class);
                intentx.addFlags(268435456);
                intentx.addFlags(131072);
                intentx.addFlags(32);
                TML_Library.PutToast(context, "Opening Tickle my Phone Menu.");
                context.startActivity(intentx);
            }
        }
    }
}
