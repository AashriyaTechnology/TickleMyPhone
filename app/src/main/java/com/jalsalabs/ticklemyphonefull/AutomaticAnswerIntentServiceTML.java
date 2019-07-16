package com.jalsalabs.ticklemyphonefull;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import com.jalsalabs.ticklemyphonefull.TMLiFACE.ITelephony;
import java.lang.reflect.Method;

public class AutomaticAnswerIntentServiceTML extends IntentService {
    public AutomaticAnswerIntentServiceTML() {
        super("AutomaticAnswerIntentServiceTML");
    }

    /* Access modifiers changed, original: protected */
    public void onHandleIntent(Intent intent) {
        Context context = getBaseContext();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (((TelephonyManager) context.getSystemService("phone")).getCallState() == 1) {
            try {
                TML_Library.Debug("I am just about to call answerPhoneAIDL Boss");
                answerPhoneAidl(context);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("AutoAnswer", "Error trying to answer using telephony service.  Falling back to headset.");
                answerPhoneHeadsethook(context);
            }
            if (prefs.getBoolean("autoanswespeaker", false)) {
                enableSpeakerPhone(context);
            }
        }
    }

    private void enableSpeakerPhone(Context context) {
        ((AudioManager) context.getSystemService("audio")).setSpeakerphoneOn(true);
    }

    private void answerPhoneHeadsethook(Context context) {
        Intent buttonDown = new Intent("android.intent.action.MEDIA_BUTTON");
        buttonDown.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, 79));
        context.sendOrderedBroadcast(buttonDown, "android.permission.CALL_PRIVILEGED");
        Intent buttonUp = new Intent("android.intent.action.MEDIA_BUTTON");
        buttonUp.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(1, 79));
        context.sendOrderedBroadcast(buttonUp, "android.permission.CALL_PRIVILEGED");
    }

    private void answerPhoneAidl(Context context) throws Exception {
        TelephonyManager tm = (TelephonyManager) getSystemService("phone");
        Method m = Class.forName(tm.getClass().getName()).getDeclaredMethod("getITelephony", new Class[0]);
        m.setAccessible(true);
        ITelephony telephonyService = (ITelephony) m.invoke(tm, new Object[0]);
        telephonyService.silenceRinger();
        telephonyService.answerRingingCall();
    }
}
