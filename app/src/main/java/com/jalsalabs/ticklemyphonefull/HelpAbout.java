package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HelpAbout extends Activity {
    Button Recommend;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TML_Library.Debug("1");
        TML_Library.SetFullScreen(this);
        setContentView(R.layout.helpabout);
    }

    public void onResume() {
        super.onResume();
        TML_Library.Debug("2");
        setContentView(R.layout.helpabout);
        TML_Library.PlayWelcomeMusic(this);
        this.Recommend = (Button) findViewById(R.id.Recommend);
        this.Recommend.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String LS_Body = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(HelpAbout.this.getString(R.string.rec_email1))).append("\n").append(HelpAbout.this.getString(R.string.rec_email2)).toString())).append("\n\n ").append(HelpAbout.this.getString(R.string.rec_email3)).append("http://ticklemyphone.weebly.com/").toString();
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", LS_Body);
                HelpAbout.this.startActivity(Intent.createChooser(intent, "Share with"));
            }
        });
    }
}
