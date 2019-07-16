package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PasswordAccept extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceptpassword);
        TML_Library.Debug("Inside Create");
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.acceptpassword);
        TML_Library.Debug("Inside Resume");
        final EditText ET_Password = (EditText) findViewById(R.id.ET_Password);
        Button BT_accept = (Button) findViewById(R.id.BT_accept);
        Button BT_recover = (Button) findViewById(R.id.BT_recover);
        Button BT_cancel = (Button) findViewById(R.id.BT_cancel);
        TML_Library.Debug("After Declaration");
        final String SettingPassword = TML_Library.GetPref(this, "settingpassword");
        BT_accept.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.Debug("Accept pressed. Calling new rules menu");
                String PasswordEntered = ET_Password.getText().toString();
                TML_Library.Debug("Entered password :" + PasswordEntered);
                TML_Library.Debug("Setting Password :" + SettingPassword);
                if (PasswordEntered.equals(SettingPassword)) {
                    String iCameFrom = TML_Library.GetParameter("ACTIVITY_TO_CALL");
                    TML_Library.Debug("************ **********i Came from :" + iCameFrom);
                    Intent i;
                    if (iCameFrom.equals("RULESMENU")) {
                        i = new Intent();
                        i.setAction("android.intent.action.MAIN");
                        i.setFlags(16777216);
                        i.setComponent(new ComponentName(PasswordAccept.this, SMSManageRulesTab.class));
                        PasswordAccept.this.startActivity(i);
                        return;
                    } else if (iCameFrom.equals("STEALTH_PRIVACY")) {
                        i = new Intent();
                        i.setAction("android.intent.action.MAIN");
                        i.setFlags(16777216);
                        i.setComponent(new ComponentName(PasswordAccept.this, SMSRulesSetup.class));
                        PasswordAccept.this.startActivity(i);
                        return;
                    } else if (iCameFrom.equals("SIMCHANGE")) {
                        i = new Intent();
                        i.setAction("android.intent.action.MAIN");
                        i.setFlags(16777216);
                        i.setComponent(new ComponentName(PasswordAccept.this, AntiTheftAlertTab.class));
                        PasswordAccept.this.startActivity(i);
                        return;
                    } else if (iCameFrom.equals("AUTOANSWER")) {
                        i = new Intent();
                        i.setAction("android.intent.action.MAIN");
                        i.setFlags(16777216);
                        i.setComponent(new ComponentName(PasswordAccept.this, AutoAnswerSetupTAB.class));
                        PasswordAccept.this.startActivity(i);
                        return;
                    } else {
                        i = new Intent();
                        i.setAction("android.intent.action.MAIN");
                        i.setFlags(16777216);
                        i.setComponent(new ComponentName(PasswordAccept.this, NewRulesMenu.class));
                        PasswordAccept.this.startActivity(i);
                        return;
                    }
                }
                TML_Library.ShowAlertMessage(PasswordAccept.this, "Invalid PIN/Password.  Please re-enter");
            }
        });
        BT_recover.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String RecoveryEmail = TML_Library.GetPref(PasswordAccept.this, "recoveryemail");
                TML_Library.Debug("Recoverey Email " + RecoveryEmail);
                if (RecoveryEmail.equals("")) {
                    TML_Library.ShowAlertMessage(PasswordAccept.this, "You have not set the recovery email!  Please uninstall and reinstall the app from Google play " + RecoveryEmail);
                    return;
                }
                String MessageBody = "Your password is :" + SettingPassword;
                String FileName = TML_Library.getTMLPath() + "tmlversion.txt";
                TML_Library.CreateLogText(PasswordAccept.this, FileName, TML_Library.GetVersionNumber(PasswordAccept.this));
                TML_Library.SendEmail(PasswordAccept.this, TML_Library.GetU(PasswordAccept.this), TML_Library.GetP(PasswordAccept.this), RecoveryEmail, "Tickle my Phone Password recovered", MessageBody, FileName);
                TML_Library.ShowAlertMessage(PasswordAccept.this, "Password recovered.  Email sent to :" + RecoveryEmail);
            }
        });
        BT_cancel.setLongClickable(true);
        BT_cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.Debug("Closing the application");
                PasswordAccept.this.finish();
            }
        });
    }
}
