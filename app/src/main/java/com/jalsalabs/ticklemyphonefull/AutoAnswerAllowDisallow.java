package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AutoAnswerAllowDisallow extends Activity {
    Button BT_Cancel;
    Button BT_Save;
    EditText ET_AllowDisallow;
    String LS_IsAllowDisallow;
    String LS_Numbers;
    RadioButton rb0;
    RadioButton rb1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allowdisallow);
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.autoanswerallowdisallow);
        this.ET_AllowDisallow = (EditText) findViewById(R.id.ET_AllowDisallow);
        this.BT_Save = (Button) findViewById(R.id.save);
        this.BT_Cancel = (Button) findViewById(R.id.cancel);
        this.rb0 = (RadioButton) findViewById(R.id.allow);
        this.rb1 = (RadioButton) findViewById(R.id.disallow);
        this.LS_IsAllowDisallow = TML_Library.GetPref(this, "KEY_ALLOW_DISALLOW_AUTOANSWER");
        this.LS_Numbers = TML_Library.GetprefnoAT(this, "KEY_NUMBERS_STRING_AUTOANSWER");
        this.ET_AllowDisallow.setText(this.LS_Numbers);
        if (this.LS_IsAllowDisallow.equals("ALLOW")) {
            this.rb0.setChecked(true);
        } else {
            this.rb1.setChecked(true);
        }
        this.BT_Save.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(AutoAnswerAllowDisallow.this);
                String LS_EnteredNumbers = AutoAnswerAllowDisallow.this.ET_AllowDisallow.getText().toString();
                TML_Library.Debug("Data Going insde is  : " + LS_EnteredNumbers);
                TML_Library.SetPref(AutoAnswerAllowDisallow.this, "KEY_NUMBERS_STRING_AUTOANSWER", LS_EnteredNumbers);
                TML_Library.Debug("Saved is : " + TML_Library.GetPref(AutoAnswerAllowDisallow.this, "KEY_NUMBERS_STRING_AUTOANSWER"));
                if (AutoAnswerAllowDisallow.this.rb0.isChecked()) {
                    TML_Library.SetPref(AutoAnswerAllowDisallow.this, "KEY_ALLOW_DISALLOW_AUTOANSWER", "ALLOW");
                }
                if (AutoAnswerAllowDisallow.this.rb1.isChecked()) {
                    TML_Library.SetPref(AutoAnswerAllowDisallow.this, "KEY_ALLOW_DISALLOW_AUTOANSWER", "DISALLOW");
                }
            }
        });
        this.BT_Cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(AutoAnswerAllowDisallow.this);
                AutoAnswerAllowDisallow.this.finish();
            }
        });
    }
}
