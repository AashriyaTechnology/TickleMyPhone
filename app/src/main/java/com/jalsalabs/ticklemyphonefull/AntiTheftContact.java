package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AntiTheftContact extends Activity {
    static EditText alternativeemail;
    static EditText alternativeno1;
    static EditText alternativeno2;
    static Button updatecontacts;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        TML_Library.SetFullScreen(this);
        setContentView(R.layout.antitheftcontact);
        TML_Library.Debug("0.0001");
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.antitheftcontact);
        alternativeno1 = (EditText) findViewById(R.id.alternativeno1);
        alternativeno2 = (EditText) findViewById(R.id.alternativeno2);
        alternativeemail = (EditText) findViewById(R.id.alternativeemail);
        updatecontacts = (Button) findViewById(R.id.updatecontacts);
        String LS_second_phone = TML_Library.GetprefnoAT(this, "SIM_SECONDARY_PHONE");
        String LS_second_phone2 = TML_Library.GetprefnoAT(this, "SIM_SECONDARY_PHONE2");
        String LS_second_email = TML_Library.GetprefnoAT(this, "SIM_SECONDARY_EMAIL");
        if (LS_second_phone.equals("@")) {
            LS_second_phone = "";
        }
        if (LS_second_phone2.equals("@")) {
            LS_second_phone2 = "";
        }
        if (LS_second_email.equals("@")) {
            LS_second_email = "";
        }
        alternativeno1.setText(LS_second_phone);
        alternativeno2.setText(LS_second_phone2);
        alternativeemail.setText(LS_second_email);
        if (TML_Library.GetprefnoAT(this, "KEY_IS_FREE").equals("Y")) {
            TML_Library.PutToast(this, "Please note SIM change settings done here will not be saved. Since you are using Free Version");
            TML_Library.UpdateSettingsBacktoFree(this);
        }
        updatecontacts.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(AntiTheftContact.this);
                String LS_second_phone = AntiTheftContact.alternativeno1.getText().toString();
                String LS_second_phone2 = AntiTheftContact.alternativeno2.getText().toString();
                String LS_second_email = AntiTheftContact.alternativeemail.getText().toString();
                TML_Library.SetPref(AntiTheftContact.this, "SIM_SECONDARY_PHONE", LS_second_phone);
                TML_Library.SetPref(AntiTheftContact.this, "SIM_SECONDARY_PHONE2", LS_second_phone2);
                TML_Library.SetPref(AntiTheftContact.this, "SIM_SECONDARY_EMAIL", LS_second_email);
            }
        });
    }
}
