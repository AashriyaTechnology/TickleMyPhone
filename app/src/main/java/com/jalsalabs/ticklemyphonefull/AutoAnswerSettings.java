package com.jalsalabs.ticklemyphonefull;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AutoAnswerSettings extends PreferenceActivity {
    Button BT_Cancel;
    Button BT_Save;
    EditText ET_AllowDisallow;
    String LS_IsAllowDisallow;
    String LS_Numbers;
    RadioButton rb0;
    RadioButton rb1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TML_Library.GetPref(this, "KEY_IS_FREE").equals("Y")) {
            TML_Library.PutToast(this, "Please note that Auto Answer is not enabled for Free Version.  Settings done here will not be saved.");
            TML_Library.UpdateSettingsBacktoFree(this);
        }
        TML_Library.Debug("Inside Create");
        TML_Library.Debug("Calling addPreferencesFromResource");
        addPreferencesFromResource(R.xml.autocall_settings);
        TML_Library.Debug("Calling findPreference");
        Preference autoansweallow = findPreference("autoansweallow");
        TML_Library.Debug("Calling setOnPreferenceClickListener");
        autoansweallow.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                TML_Library.HapticFeedback(AutoAnswerSettings.this);
                Intent i = new Intent();
                i.setAction("android.intent.action.MAIN");
                i.addCategory("android.intent.category.LAUNCHER");
                i.setComponent(new ComponentName(AutoAnswerSettings.this, AutoAnswerAllowDisallow.class));
                AutoAnswerSettings.this.startActivity(i);
                return true;
            }
        });
    }

    public void onResume() {
        super.onResume();
        TML_Library.Debug("Inside Resume");
        if (TML_Library.GetPref(this, "KEY_IS_FREE").equals("Y")) {
            TML_Library.PutToast(this, "Please note settings done here will not be saved. Since you are using Free Version");
            TML_Library.UpdateSettingsBacktoFree(this);
        }
    }
}
