package com.jalsalabs.ticklemyphonefull;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AntiTheftBootReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        TML_Library.Debug("Inside On Receive BootReceiver");
        Intent intentx = new Intent(context.getApplicationContext(), AntiTheftBootStartActivity.class);
        intentx.setFlags(268435456);
        context.startActivity(intentx);
    }
}
