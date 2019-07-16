package com.jalsalabs.ticklemyphonefull;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.ContactsContract.PhoneLookup;

public class AutomaticAnswerReceiverTML extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String phone_state = intent.getStringExtra("state");
        String number = intent.getStringExtra("incoming_number");
        TML_Library.Debug("Received broad cast receiver 1 Govinda");
        if (prefs.getBoolean("enableautoanswer", false)) {
            String number_string = TML_Library.GetprefnoAT(context, "KEY_NUMBERS_STRING_AUTOANSWER");
            TML_Library.Debug("AutomaticAnswerReceiverTML :number_string= " + number_string);
            if (!number_string.equals("")) {
                TML_Library.Debug("SmsReceiver: YES number_string is not null");
                if (!TML_Library.isSenderOkForTMLAutoAnswer(context, number_string, number)) {
                    TML_Library.Debug("AutomaticAnswerReceiverTML : isSenderOkForTML returned false so not performing TML");
                    return;
                }
            }
            TML_Library.Debug("Calling the service");
            context.startService(new Intent(context, AutomaticAnswerIntentServiceTML.class));
        }
    }

    private int isStarred(Context context, String number) {
        int starred = -1;
        Cursor c = context.getContentResolver().query(Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, number), new String[]{"starred"}, null, null, null);
        if (c != null) {
            if (c.moveToFirst()) {
                starred = c.getInt(0);
            }
            c.close();
        }
        return starred;
    }
}
