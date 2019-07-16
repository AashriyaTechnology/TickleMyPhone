package com.jalsalabs.ticklemyphonefull;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class SMSRulesSetup extends PreferenceActivity {
    Button BT_Cancel;
    Button BT_Save;
    EditText ET_AllowDisallow;
    String LS_IsAllowDisallow;
    String LS_Numbers;
    RadioButton rb0;
    RadioButton rb1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.stealth_privacy);
        TML_Library.Debug("Inside Create");
        TML_Library.Debug("Calling addPreferencesFromResource");
        TML_Library.Debug("Calling findPreference");
        Preference customPref = findPreference("customPref");
        Preference infoaboutSenderEmail = findPreference("infoaboutSenderEmail");
        customPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                SMSRulesSetup.this.startActivity(new Intent(SMSRulesSetup.this, AllowDisallow.class));
                return true;
            }
        });
        infoaboutSenderEmail.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                TML_Library.ShowAlertMessageHTML(SMSRulesSetup.this, new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "<html>")).append("<head>").toString())).append("<title></title>").toString())).append("</head>").toString())).append("<body>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("<p>").toString())).append("<span style=\"font-size:24px;\"><strong><u>What is sender email id</u></strong></span></p>").toString())).append("<p>").toString())).append("As you know Tickle my Phone commands like SENDPHOTO,SENDALLSMS,SENDCONTACTLIST and few other needs a email id to send details.&nbsp;</p>").toString())).append("<p>").toString())).append("By default &lsquo;<strong><em>Use user defined email id for sender</em></strong>&rsquo; is disabled, which means Tickle my Phone will use internal email id to send details.&nbsp;</p>").toString())).append("<p>").toString())).append("If you like your email id to send the details, you need to check the option and provide your email id and password details</p>").toString())).append("<p>").toString())).append("<strong><u>Please note:Email credentials will be encrypted and stored only in your mobile.&nbsp; These details will not be shared or used for any other purpose</u></strong></p>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("</body>").toString())).append("</html>").toString());
                return true;
            }
        });
    }

    public void onResume() {
        super.onResume();
        TML_Library.Debug("Inside Resume");
        if (TML_Library.GetPref(this, "KEY_IS_FREE").equals("Y")) {
            TML_Library.PutToast(this, "Please note settings done here will not be saved. Since you are using Free Version");
            TML_Library.UpdatePrefToFree(this);
        }
    }
}
