package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AllowDisallow extends Activity {
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
        setContentView(R.layout.allowdisallow);
        this.ET_AllowDisallow = (EditText) findViewById(R.id.ET_AllowDisallow);
        this.BT_Save = (Button) findViewById(R.id.save);
        this.BT_Cancel = (Button) findViewById(R.id.cancel);
        this.rb0 = (RadioButton) findViewById(R.id.allow);
        this.rb1 = (RadioButton) findViewById(R.id.disallow);
        this.LS_IsAllowDisallow = TML_Library.GetPref(this, "KEY_ALLOW_DISALLOW");
        this.LS_Numbers = TML_Library.GetPref(this, "KEY_NUMBERS_STRING");
        this.ET_AllowDisallow.setText(this.LS_Numbers);
        if (this.LS_IsAllowDisallow.equals("ALLOW")) {
            this.rb0.setChecked(true);
        } else {
            this.rb1.setChecked(true);
        }
        this.BT_Save.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(AllowDisallow.this);
                TML_Library.SetPref(AllowDisallow.this, "KEY_NUMBERS_STRING", AllowDisallow.this.ET_AllowDisallow.getText().toString());
                if (AllowDisallow.this.rb0.isChecked()) {
                    TML_Library.SetPref(AllowDisallow.this, "KEY_ALLOW_DISALLOW", "ALLOW");
                }
                if (AllowDisallow.this.rb1.isChecked()) {
                    TML_Library.SetPref(AllowDisallow.this, "KEY_ALLOW_DISALLOW", "DISALLOW");
                }
            }
        });
        this.BT_Cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(AllowDisallow.this);
                AllowDisallow.this.finish();
            }
        });
    }
}
