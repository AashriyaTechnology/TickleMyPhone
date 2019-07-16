package com.jalsalabs.ticklemyphonefull;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TB_AlarmManagerBroadcastReceiver extends BroadcastReceiver {
    public static final String ONE_TIME = "onetime";

    public void onReceive(Context context, Intent intent) {
        WakeLock wl = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "YOUR TAG");
        wl.acquire();
        Bundle extras = intent.getExtras();
        StringBuilder msgStr = new StringBuilder();
        TML_Library.Debug("101");
        Format formatter = new SimpleDateFormat("hh:mm:ss a");
        TML_Library.Debug("102");
        msgStr.append(formatter.format(new Date()));
        TML_Library.Debug("103");
        TML_Library.Debug("104 :" + msgStr);
        if (TML_Library.GetPrefNew(context, "IS_TIME_BOMB_ON", "N").equals("Y")) {
            String FileName = TML_Library.getTMLPath() + "tml.txt";
            TML_Library.PutToast(context, "Message will be sent");
            TML_Library.TimeBombRun(context);
        }
        TML_Library.Debug("105");
        wl.release();
    }

    public void SetAlarm(Context context, int Hr, int Min) {
        AlarmManager am = (AlarmManager) context.getSystemService("alarm");
        TML_Library.Debug("1");
        Intent intent = new Intent(context, TB_AlarmManagerBroadcastReceiver.class);
        TML_Library.Debug("2");
        intent.putExtra(ONE_TIME, Boolean.FALSE);
        TML_Library.Debug("3");
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        TML_Library.Debug("4");
        TML_Library.Debug("5");
        Calendar calSet = (Calendar) Calendar.getInstance().clone();
        calSet.set(11, Hr);
        calSet.set(12, Min);
        TML_Library.Debug("6");
        am.setRepeating(0, calSet.getTimeInMillis(), TML_Library.REPEAT_FREQUENCY, pi);
        TML_Library.Debug("7");
    }

    public void CancelAlarm(Context context) {
        ((AlarmManager) context.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(context, 0, new Intent(context, TB_AlarmManagerBroadcastReceiver.class), 0));
        TML_Library.SetPrefNew(context, "IS_TIME_BOMB_ON", "N");
    }
}
