package com.jalsalabs.ticklemyphonefull;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;

public class TB_BootReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intentxx) {
        if (TML_Library.GetPrefNew(context, "IS_TIME_BOMB_ON", "N").equals("Y")) {
            String ONE_TIME = TB_AlarmManagerBroadcastReceiver.ONE_TIME;
            TML_Library.Debug("janagana");
            AlarmManager am = (AlarmManager) context.getSystemService("alarm");
            TML_Library.Debug("mana");
            Intent intentx = new Intent(context, TB_AlarmManagerBroadcastReceiver.class);
            TML_Library.Debug("hadinayaka");
            intentx.putExtra(ONE_TIME, Boolean.FALSE);
            TML_Library.Debug("jaya hai");
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, intentx, 0);
            TML_Library.Debug("Bharatha");
            Calendar calSet = (Calendar) Calendar.getInstance().clone();
            calSet.set(11, 8);
            calSet.set(12, 0);
            calSet.set(13, 0);
            calSet.set(14, 0);
            TML_Library.Debug("Bhagya");
            am.setRepeating(0, calSet.getTimeInMillis(), TML_Library.REPEAT_FREQUENCY, pi);
            TML_Library.Debug("Vidhatha");
        }
    }
}
