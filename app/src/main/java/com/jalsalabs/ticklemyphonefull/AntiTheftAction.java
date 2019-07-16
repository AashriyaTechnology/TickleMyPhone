package com.jalsalabs.ticklemyphonefull;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AntiTheftAction extends PreferenceActivity {
    Button BT_Cancel;
    Button BT_Save;
    EditText ET_AllowDisallow;
    String LS_IsAllowDisallow;
    String LS_Numbers;
    RadioButton rb0;
    RadioButton rb1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TML_Library.Debug("Inside Create");
        TML_Library.Debug("Calling addPreferencesFromResource Srinath");
        addPreferencesFromResource(R.xml.antitheft_action);
        TML_Library.Debug("Calling findPreference");
        Preference configpop = findPreference("configpop");
        TML_Library.Debug("Calling setOnPreferenceClickListener");
        configpop.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                TML_Library.HapticFeedback(AntiTheftAction.this);
                TML_Library.ShowMobileLostMessage(AntiTheftAction.this);
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
