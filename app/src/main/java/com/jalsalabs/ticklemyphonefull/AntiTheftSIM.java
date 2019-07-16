package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AntiTheftSIM extends Activity {
    static Button BT_Register;
    static String CurrentOperatorName;
    static String CurrentSimSrlno;
    static String RegisteredOperatorName;
    static String RegisteredSimSrlno;
    static Button clear;
    static EditText currentsimno;
    static EditText currentsimprovider;
    static String isDifferent = "N";
    static TextView is_same_diffrent;
    static EditText regsimno;
    static EditText regsimprovider;
    Context context;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        TML_Library.SetFullScreen(this);
        TML_Library.Debug("inside on create ");
        setContentView(R.layout.antitheftsim);
        this.context = this;
        TML_Library.Debug("exit on create");
    }

    public void onResume() {
        TML_Library.Debug("on resume");
        super.onResume();
        this.context = this;
        setContentView(R.layout.antitheftsim);
        TML_Library.Debug("resume 1");
        if (TML_Library.GetPref(this, "KEY_IS_FREE").equals("Y")) {
            TML_Library.PutToast(this, "Please note SIM change settings done here will not be saved. Since you are using Free Version");
            TML_Library.UpdateSettingsBacktoFree(this);
        }
        TML_Library.Debug("resume 2");
        regsimno = (EditText) findViewById(R.id.regsimno);
        TML_Library.Debug("resume 2.1");
        regsimprovider = (EditText) findViewById(R.id.regsimprovider);
        TML_Library.Debug("resume 2.2");
        currentsimno = (EditText) findViewById(R.id.currentsimno);
        TML_Library.Debug("resume 2.");
        currentsimprovider = (EditText) findViewById(R.id.currentsimprovider);
        TML_Library.Debug("resume 2.3");
        BT_Register = (Button) findViewById(R.id.BT_Register);
        TML_Library.Debug("resume 2.4");
        clear = (Button) findViewById(R.id.clear);
        TML_Library.Debug("resume 2.5");
        is_same_diffrent = (TextView) findViewById(R.id.is_same_diffrent);
        TML_Library.Debug("resume 3");
        TML_Library.Debug("Before update");
        UpdateScreen(this.context);
        TML_Library.Debug("After update");
        BT_Register.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(AntiTheftSIM.this);
                String CurrentSimSrlno = TML_Library.GetSimSerialNumber(AntiTheftSIM.this);
                String CurrentOperatorName = TML_Library.GetSimOperatorName(AntiTheftSIM.this);
                TML_Library.SetPref(AntiTheftSIM.this, "REGISTERED_SIM_SRLNO", CurrentSimSrlno);
                TML_Library.SetPref(AntiTheftSIM.this, "REGISTERED_OPERATOR_NAME", CurrentOperatorName);
                AntiTheftSIM.regsimno.setText(CurrentSimSrlno);
                AntiTheftSIM.regsimprovider.setText(CurrentOperatorName);
                TML_Library.PutToast(AntiTheftSIM.this, "SIM Srl no : " + CurrentSimSrlno + " is registered with Tickle my Phone now.");
                AntiTheftSIM.UpdateScreen(AntiTheftSIM.this.context);
            }
        });
        clear.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(AntiTheftSIM.this);
                String CurrentSimSrlno = TML_Library.GetSimSerialNumber(AntiTheftSIM.this);
                String CurrentOperatorName = TML_Library.GetSimOperatorName(AntiTheftSIM.this);
                TML_Library.SetPref(AntiTheftSIM.this, "REGISTERED_SIM_SRLNO", "");
                TML_Library.SetPref(AntiTheftSIM.this, "REGISTERED_OPERATOR_NAME", "");
                AntiTheftSIM.regsimno.setText(CurrentSimSrlno);
                AntiTheftSIM.regsimprovider.setText(CurrentOperatorName);
                AntiTheftSIM.UpdateScreen(AntiTheftSIM.this.context);
            }
        });
    }

    public static void UpdateScreen(Context context) {
        RegisteredSimSrlno = TML_Library.GetprefnoAT(context, "REGISTERED_SIM_SRLNO");
        String RegisteredOperatorName = TML_Library.GetprefnoAT(context, "REGISTERED_OPERATOR_NAME");
        String CurrentSimSrlno = TML_Library.GetSimSerialNumber(context);
        String CurrentOperatorName = TML_Library.GetSimOperatorName(context);
        regsimno.setText(RegisteredSimSrlno);
        regsimprovider.setText(RegisteredOperatorName);
        currentsimno.setText(CurrentSimSrlno);
        currentsimprovider.setText(CurrentOperatorName);
        if (RegisteredSimSrlno.equals(CurrentSimSrlno)) {
            isDifferent = "Y";
            BT_Register.setEnabled(false);
            is_same_diffrent.setText("The Registered SIM and Current SIM Same. No NEED to register");
            is_same_diffrent.setBackgroundColor(R.color.lightgreen);
            return;
        }
        isDifferent = "N";
        BT_Register.setEnabled(true);
        is_same_diffrent.setText("New SIM! The Registered SIM and Currently inserted SIM are Different.  Please press below button to register now.");
        is_same_diffrent.setBackgroundColor(R.color.red);
    }
}
