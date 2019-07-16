package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class AntiTheftSaveEnable extends Activity {
    Button AntiTheftSave;
    Button BT_close;
    CheckBox CK_simchangealert;
    Boolean sendemailwhenstolen;
    Boolean sendsmswhenstolen;
    Boolean simchangealert;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        TML_Library.SetFullScreen(this);
        setContentView(R.layout.antitheftsaveenable);
        TML_Library.Debug("0.0001");
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.antitheftsaveenable);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        TML_Library.Debug("in Resume");
        this.AntiTheftSave = (Button) findViewById(R.id.AntiTheftSave);
        this.BT_close = (Button) findViewById(R.id.BT_close);
        TML_Library.Debug("in Resume 0");
        this.CK_simchangealert = (CheckBox) findViewById(R.id.CK_simchangealert);
        TML_Library.Debug("in Resume 1");
        this.simchangealert = Boolean.valueOf(prefs.getBoolean("simchangealert", false));
        TML_Library.Debug("in Resume 1.1");
        this.sendsmswhenstolen = Boolean.valueOf(prefs.getBoolean("sendsmswhenstolen", false));
        TML_Library.Debug("in Resume 1.2");
        this.sendemailwhenstolen = Boolean.valueOf(prefs.getBoolean("sendemailwhenstolen", false));
        TML_Library.Debug("in Resume 2");
        if (this.simchangealert.booleanValue()) {
            this.CK_simchangealert.setChecked(true);
        } else {
            this.CK_simchangealert.setChecked(false);
        }
        TML_Library.Debug("in Resume 3");
        if (TML_Library.GetPref(this, "KEY_IS_FREE").equals("Y")) {
            TML_Library.PutToast(this, "Please note SIM change settings done here will not be saved. Since you are using Free Version");
            TML_Library.UpdateSettingsBacktoFree(this);
        }
        TML_Library.Debug("in Resume 4");
        this.AntiTheftSave.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(AntiTheftSaveEnable.this);
                String LS_second_phone = TML_Library.GetPref(AntiTheftSaveEnable.this, "SIM_SECONDARY_PHONE");
                String LS_second_phone2 = TML_Library.GetPref(AntiTheftSaveEnable.this, "SIM_SECONDARY_PHONE2");
                String LS_second_email = TML_Library.GetPref(AntiTheftSaveEnable.this, "SIM_SECONDARY_EMAIL");
                String RegisteredSimSrlno = TML_Library.GetPref(AntiTheftSaveEnable.this, "REGISTERED_SIM_SRLNO");
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AntiTheftSaveEnable.this);
                AntiTheftSaveEnable.this.simchangealert = Boolean.valueOf(prefs.getBoolean("simchangealert", false));
                AntiTheftSaveEnable.this.sendsmswhenstolen = Boolean.valueOf(prefs.getBoolean("sendsmswhenstolen", false));
                AntiTheftSaveEnable.this.sendemailwhenstolen = Boolean.valueOf(prefs.getBoolean("sendemailwhenstolen", false));
                TML_Library.Debug(" save 1");
                if (!AntiTheftSaveEnable.this.CK_simchangealert.isChecked()) {
                    TML_Library.Debug(" save 6");
                    TML_Library.SetPrefPrivacyStealth(AntiTheftSaveEnable.this, "simchangealert", "false");
                    TML_Library.ShowAlertMessage(AntiTheftSaveEnable.this, "Antitheft/SIM Card change alert is Disabled");
                } else if (AntiTheftSaveEnable.this.sendsmswhenstolen.booleanValue() && (LS_second_phone.equals("") || LS_second_phone.equals("@"))) {
                    TML_Library.Debug(" save 2");
                    TML_Library.ShowAlertMessage(AntiTheftSaveEnable.this, "If Send SMS is enabled,  Please enter alternative  number 1 must be entered");
                } else if (AntiTheftSaveEnable.this.sendemailwhenstolen.booleanValue() && (LS_second_email.equals("") || LS_second_email.equals("@"))) {
                    TML_Library.Debug(" save 3");
                    TML_Library.ShowAlertMessage(AntiTheftSaveEnable.this, "If Send EMAIL  is enabled,  Please enter valid email id.");
                } else if (RegisteredSimSrlno.equals("") || RegisteredSimSrlno.equals("@")) {
                    TML_Library.Debug(" save 4");
                    TML_Library.ShowAlertMessage(AntiTheftSaveEnable.this, "You'r Registered SIM is null.  Please register your SIM with Tickle my Phone");
                } else {
                    TML_Library.Debug(" save 5");
                    TML_Library.SetPrefPrivacyStealth(AntiTheftSaveEnable.this, "simchangealert", "true");
                    TML_Library.HapticFeedback(AntiTheftSaveEnable.this);
                    String Total_Message = "" + "Congratulations!! \nYou have set the AntiTheft / SIM  Change alert\nYour Registered SIM Serial no is : " + RegisteredSimSrlno + "\n\nWhen your phone is lost/stolen and SIM is changed your Phone will\n\n";
                    Boolean bigalaramstolen = Boolean.valueOf(prefs.getBoolean("bigalaramstolen", false));
                    Boolean showmessagestolen = Boolean.valueOf(prefs.getBoolean("showmessagestolen", false));
                    if (bigalaramstolen.booleanValue()) {
                        Total_Message = new StringBuilder(String.valueOf(Total_Message)).append(" #  Play BIG Alaram on your stolen phone \n").toString();
                    }
                    if (showmessagestolen.booleanValue()) {
                        Total_Message = new StringBuilder(String.valueOf(Total_Message)).append(" # Show you'r configured Message \n").toString();
                    }
                    if (AntiTheftSaveEnable.this.sendsmswhenstolen.booleanValue()) {
                        Total_Message = new StringBuilder(String.valueOf(Total_Message)).append(" # Send SMS with contact details to  ").append(LS_second_phone).append("  ").append(LS_second_phone2).append("\n").toString();
                    }
                    if (AntiTheftSaveEnable.this.sendemailwhenstolen.booleanValue()) {
                        Total_Message = new StringBuilder(String.valueOf(Total_Message)).append(" # Send Email  to  ").append(LS_second_email).append("\n").toString();
                    }
                    TML_Library.ShowAlertMessage(AntiTheftSaveEnable.this, Total_Message);
                }
            }
        });
        this.BT_close.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(AntiTheftSaveEnable.this);
                AntiTheftSaveEnable.this.finish();
            }
        });
    }
}
