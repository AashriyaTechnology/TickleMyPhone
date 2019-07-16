package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class SimChangeSetupBackup extends Activity {
    static Button BT_Cancel;
    static Button BT_Save;
    static CheckBox CK_bigalaramstolen;
    static CheckBox CK_sendemailwhenstolen;
    static CheckBox CK_sendsmswhenstolen;
    static CheckBox CK_showmessagestolen;
    static EditText ET_second_email;
    static EditText ET_second_phone;
    static String isDifferent = "N";
    static TextView readme;
    static Button registerthissim;
    static Button show_config_message;
    static TextView siminfo;
    String LS_second_email;
    String LS_second_phone;
    Boolean bigalaramstolen;
    Context ctx = this;
    Boolean sendemailwhenstolen;
    Boolean sendsmswhenstolen;
    Boolean showmessagestolen;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        TML_Library.SetFullScreen(this);
        setContentView(R.layout.simsetup);
        TML_Library.Debug("0.0001");
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.simsetup);
        if (TML_Library.GetPref(this, "KEY_IS_FREE").equals("Y")) {
            TML_Library.PutToast(this, "Please note SIM change settings done here will not be saved. Since you are using Free Version");
            TML_Library.UpdateSettingsBacktoFree(this);
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        TML_Library.Debug(" Ok 1");
        TML_Library.Debug(" Ok 2");
        readme = (TextView) findViewById(R.id.readme);
        siminfo = (TextView) findViewById(R.id.siminfo);
        registerthissim = (Button) findViewById(R.id.registerthissim);
        show_config_message = (Button) findViewById(R.id.show_config_message);
        ET_second_phone = (EditText) findViewById(R.id.ET_second_phone);
        ET_second_email = (EditText) findViewById(R.id.ET_second_email);
        CK_sendsmswhenstolen = (CheckBox) findViewById(R.id.sendsmswhenstolen);
        CK_sendemailwhenstolen = (CheckBox) findViewById(R.id.sendemailwhenstolen);
        CK_showmessagestolen = (CheckBox) findViewById(R.id.showmessagestolen);
        CK_bigalaramstolen = (CheckBox) findViewById(R.id.bigalaramstolen);
        BT_Save = (Button) findViewById(R.id.BT_Save);
        BT_Cancel = (Button) findViewById(R.id.BT_Cancel);
        this.sendsmswhenstolen = Boolean.valueOf(prefs.getBoolean("sendsmswhenstolen", false));
        this.sendemailwhenstolen = Boolean.valueOf(prefs.getBoolean("sendemailwhenstolen", false));
        this.showmessagestolen = Boolean.valueOf(prefs.getBoolean("showmessagestolen", true));
        this.bigalaramstolen = Boolean.valueOf(prefs.getBoolean("bigalaramstolen", true));
        this.LS_second_phone = TML_Library.GetPref(this, "SIM_SECONDARY_PHONE");
        this.LS_second_email = TML_Library.GetPref(this, "SIM_SECONDARY_EMAIL");
        if (this.sendsmswhenstolen.booleanValue()) {
            CK_sendsmswhenstolen.setChecked(true);
        } else {
            CK_sendsmswhenstolen.setChecked(false);
        }
        if (this.sendemailwhenstolen.booleanValue()) {
            CK_sendemailwhenstolen.setChecked(true);
        } else {
            CK_sendemailwhenstolen.setChecked(false);
        }
        if (this.showmessagestolen.booleanValue()) {
            CK_showmessagestolen.setChecked(true);
        } else {
            CK_showmessagestolen.setChecked(false);
        }
        if (this.bigalaramstolen.booleanValue()) {
            CK_bigalaramstolen.setChecked(true);
        } else {
            CK_bigalaramstolen.setChecked(false);
        }
        ET_second_phone.setText(this.LS_second_phone);
        ET_second_email.setText(this.LS_second_email);
        TML_Library.Debug(" Ok 3");
        UpdateSIMInfo(this);
        TML_Library.Debug(" Ok 4");
        readme.setText(Html.fromHtml(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "<html>")).append("<head>").toString())).append("<title></title>").toString())).append("</head>").toString())).append("<body>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("<p>").toString())).append("<span style=\"font-size:16px;\"><strong>Please read following note, before you SIM Change alert setup</strong></span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#ff0000;\"><strong><u>Prerequisite</u></strong></span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;We assume this&nbsp; phone is yours.&nbsp; :) and it is neighter stolen nor somebody&#39;s phone</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;We assume the SIM what you have in this phone is your permanent phone number</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#ff0000;\"><strong><u>How it works</u></strong></span></p>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;You are now going to register your SIM&#39;s internal unique number. with Tickle my Phone.</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;You also going to register your sencondary number and your email details with Tickle my Phone</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;When you loose your phone/stolen, the Culprit will switch off the phone, changes the SIM and switch it back on.</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;Well...When culprit switch it ON,&nbsp; Tickle my Phone will check.. Is the new SIM unique number is the same as Registered one</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;If it is different, then automatically Tickle my Phone&nbsp; will send the SMS to your secondory number with Culprit&#39;s mobile number</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;Then you can track your phone by sending Tickle my Phone commands.&nbsp;</span></p>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("<p>").toString())).append("<span style=\"color:#ff0000;\"><strong><u>Please Note:</u></strong></span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;This SIM Alert works only for GSM mobiles and most of the developed countries.</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;When the culprit switches ON and the phone connection doesn&rsquo;t have Internet or enough balance in his SIM ,&nbsp; the phone will not be able to send the Email or SMS</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;Makesure you give your secondory number as your spouse or friend, not this phone no.</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;Regarding the pop message configuration&nbsp; please don&rsquo;t scare the culprit, be polite with him. Otherwise he may switch it off permanently and you may not get back your phone</span></p>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("</body>").toString())).append("</html>").toString()));
        TML_Library.Debug(" Ok 4");
        TML_Library.Debug(" Ok 5");
        registerthissim.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(SimChangeSetupBackup.this);
                String CurrentSimSrlno = TML_Library.GetSimSerialNumber(SimChangeSetupBackup.this);
                String CurrentOperatorName = TML_Library.GetSimOperatorName(SimChangeSetupBackup.this);
                TML_Library.SetPref(SimChangeSetupBackup.this, "REGISTERED_SIM_SRLNO", CurrentSimSrlno);
                TML_Library.SetPref(SimChangeSetupBackup.this, "REGISTERED_OPERATOR_NAME", CurrentOperatorName);
                TML_Library.PutToast(SimChangeSetupBackup.this, "SIM Srl no : " + CurrentSimSrlno + " is registered with Tickle my Phone now.");
                SimChangeSetupBackup.UpdateSIMInfo(SimChangeSetupBackup.this);
            }
        });
        show_config_message.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(SimChangeSetupBackup.this);
                TML_Library.ShowMobileLostMessage(SimChangeSetupBackup.this);
            }
        });
        BT_Save.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(SimChangeSetupBackup.this);
                SimChangeSetupBackup.this.LS_second_phone = SimChangeSetupBackup.ET_second_phone.getText().toString();
                SimChangeSetupBackup.this.LS_second_email = SimChangeSetupBackup.ET_second_email.getText().toString();
                if (SimChangeSetupBackup.CK_sendsmswhenstolen.isChecked()) {
                    TML_Library.SetPrefPrivacyStealth(SimChangeSetupBackup.this, "sendsmswhenstolen", "true");
                } else {
                    TML_Library.SetPrefPrivacyStealth(SimChangeSetupBackup.this, "sendsmswhenstolen", "false");
                }
                if (SimChangeSetupBackup.CK_sendemailwhenstolen.isChecked()) {
                    TML_Library.SetPrefPrivacyStealth(SimChangeSetupBackup.this, "sendemailwhenstolen", "true");
                } else {
                    TML_Library.SetPrefPrivacyStealth(SimChangeSetupBackup.this, "sendemailwhenstolen", "false");
                }
                if (SimChangeSetupBackup.CK_showmessagestolen.isChecked()) {
                    TML_Library.SetPrefPrivacyStealth(SimChangeSetupBackup.this, "showmessagestolen", "true");
                } else {
                    TML_Library.SetPrefPrivacyStealth(SimChangeSetupBackup.this, "showmessagestolen", "false");
                }
                if (SimChangeSetupBackup.CK_bigalaramstolen.isChecked()) {
                    TML_Library.SetPrefPrivacyStealth(SimChangeSetupBackup.this, "bigalaramstolen", "true");
                } else {
                    TML_Library.SetPrefPrivacyStealth(SimChangeSetupBackup.this, "bigalaramstolen", "false");
                }
                TML_Library.SetPrefPrivacyStealth(SimChangeSetupBackup.this, "simchangealert", "true");
                TML_Library.SetPref(SimChangeSetupBackup.this, "SIM_SECONDARY_PHONE", SimChangeSetupBackup.this.LS_second_phone);
                TML_Library.SetPref(SimChangeSetupBackup.this, "SIM_SECONDARY_EMAIL", SimChangeSetupBackup.this.LS_second_email);
                if (TML_Library.GetPref(SimChangeSetupBackup.this, "KEY_IS_FREE").equals("Y")) {
                    TML_Library.UpdateSettingsBacktoFree(SimChangeSetupBackup.this);
                }
            }
        });
        BT_Cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(SimChangeSetupBackup.this);
                SimChangeSetupBackup.this.finish();
            }
        });
        TML_Library.Debug(" Ok 6");
    }

    private static void UpdateSIMInfo(Context context) {
        String RegisteredSimSrlno = TML_Library.GetPref(context, "REGISTERED_SIM_SRLNO");
        String RegisteredOperatorName = TML_Library.GetPref(context, "REGISTERED_OPERATOR_NAME");
        String CurrentSimSrlno = TML_Library.GetSimSerialNumber(context);
        String CurrentOperatorName = TML_Library.GetSimOperatorName(context);
        TML_Library.Debug(" inside Ok 1");
        String SimInfo = "Your Tickle my Phone Registered SIM Info\n\nSIM Srl no   :" + RegisteredSimSrlno + "\n" + "SIM Operator :" + RegisteredOperatorName + "\n\n" + "Your Current SIM Info  \n\n" + "SIM Srl no   :" + CurrentSimSrlno + "\n" + "SIM Operator :" + CurrentOperatorName + "\n\n";
        if (RegisteredSimSrlno.equals(CurrentSimSrlno)) {
            isDifferent = "N";
            TML_Library.Debug(" inside Ok 2");
            registerthissim.setEnabled(false);
            TML_Library.Debug(" inside Ok 3");
        } else {
            isDifferent = "Y";
            TML_Library.Debug(" inside Ok 2");
            registerthissim.setEnabled(true);
            TML_Library.Debug(" inside Ok 3");
        }
        TML_Library.Debug(" inside Ok 4");
        if (isDifferent.equals("N")) {
            SimInfo = new StringBuilder(String.valueOf(SimInfo)).append("\nThis current SIM is registered with Tickle my Phone\n").toString();
        } else {
            SimInfo = new StringBuilder(String.valueOf(SimInfo)).append("\nNew Sim Found or You have NOT registered with Tickle my Phone\n").toString();
        }
        siminfo.setText(SimInfo);
        TML_Library.Debug(" inside Ok 5");
    }
}
