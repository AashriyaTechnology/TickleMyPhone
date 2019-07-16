package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Activation extends Activity {
    EditText ET_ActivationCode;
    EditText ET_IMEICode;
    String LS_IS_APP_Activate;
    String LS_ImeiCode;
    Button activate;
    Button cancel;
    Button interested;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activation);
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.activation);
        ET_IMEICode = (EditText) findViewById(R.id.ET_IMEICode);
        this.ET_ActivationCode = (EditText) findViewById(R.id.ET_ActivationCode);
        this.activate = (Button) findViewById(R.id.activate);
        this.interested = (Button) findViewById(R.id.interested);
        this.cancel = (Button) findViewById(R.id.cancel);
        this.LS_IS_APP_Activate = TML_Library.GetPref(this, "KEY_IS_APP_ACTIVATE");
        this.LS_ImeiCode = TML_Library.getIMEICode(this);
        this.ET_IMEICode.setText(this.LS_ImeiCode);
        if (this.LS_IS_APP_Activate.equals("Y")) {
            TML_Library.PutToast(this, "Application is already active");
            finish();
        }
        this.interested.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String LS_Body = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("Its a nice application.  I am interested to have full version." + "\n  Please send me more details.")).append("\n\n My IMEI code  is :  ").append(Activation.this.LS_ImeiCode).toString())).append("\n\n Thanks").toString();
                Intent i = new Intent("android.intent.action.SEND");
                i.setType("text/plain");
                i.putExtra("android.intent.extra.EMAIL", new String[]{"ticklemyphoneapp@gmail.com", ""});
                i.putExtra("android.intent.extra.SUBJECT", "I am interested go for Full version of Tickle my Phone");
                i.putExtra("android.intent.extra.TEXT", LS_Body);
                try {
                    Activation.this.startActivity(Intent.createChooser(i, "SendMail.."));
                } catch (ActivityNotFoundException e) {
                    TML_Library.PutToast(Activation.this, "Problem sending email... send separate mail to ticklemyphoneapp@gmail.com with the subject interested");
                }
            }
        });
        this.cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Activation.this.finish();
            }
        });
        this.activate.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (Activation.this.ET_ActivationCode.getText().toString().trim().equals(TML_Library.TmlEncrypt(TML_Library.getIMEICode(Activation.this)).trim())) {
                    TML_Library.PutToast(Activation.this, "Congratulations!!  Application is Active now !! Welcome to Tickle my Phone");
                    TML_Library.SetPref(Activation.this, "KEY_IS_APP_ACTIVATE", "Y");
                    Activation.this.finish();
                    return;
                }
                TML_Library.PutToast(Activation.this, "Sorry incorrect activation code.");
                TML_Library.SetPref(Activation.this, "KEY_IS_APP_ACTIVATE", "N");
            }
        });
    }
}
