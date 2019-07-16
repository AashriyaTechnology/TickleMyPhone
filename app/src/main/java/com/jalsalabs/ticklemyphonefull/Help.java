package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

public class Help extends Activity {
    Button Recommend;
    WebView webview;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.help);
        this.Recommend = (Button) findViewById(R.id.Recommend);
        this.webview = (WebView) findViewById(R.id.webview);
        this.webview.getSettings().setBuiltInZoomControls(true);
        this.webview.getSettings().setJavaScriptEnabled(true);
        this.webview.loadUrl("http://dl.dropbox.com/u/27314855/TouchMyLifeGraphicFiles/TML_to_upload2.htm");
        this.Recommend.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String LS_Body = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("Hey I have downloaded Tickle my Phone application on my Android phone. Its Awesome... " + "\n Its a must app for every Android")).append("\n\n You can download the same from http://ticklemyphone.weebly.com/ ").toString())).append("\n\n I am sure you will enjoy this app...").toString();
                Intent i = new Intent("android.intent.action.SEND");
                i.setType("text/plain");
                i.putExtra("android.intent.extra.SUBJECT", "Tickle my Phone application is Superb...");
                i.putExtra("android.intent.extra.TEXT", LS_Body);
                try {
                    Help.this.startActivity(Intent.createChooser(i, "SendMail.."));
                } catch (ActivityNotFoundException e) {
                    TML_Library.PutToast(Help.this, "Problem sending email... send separate mail to ticklemyphoneapp@gmail.com with the subject interested");
                }
            }
        });
    }
}
