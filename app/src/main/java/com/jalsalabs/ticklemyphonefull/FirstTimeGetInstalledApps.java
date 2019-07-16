package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

public class FirstTimeGetInstalledApps extends Activity {
    public String message;
    public String phoneNo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firsttimelegalnotice);
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.firsttimelegalnotice);
        TML_Library.Debug("I am inside Resume");
        String isLegalNoticeDeclined = TML_Library.GetPref(this, "KEY_USER_DECLINED");
        if (isLegalNoticeDeclined.equals("")) {
            isLegalNoticeDeclined = "Y";
        }
        if (isLegalNoticeDeclined.equals("N")) {
            Intent intentx = new Intent(this, MainMenu.class);
            intentx.addFlags(268435456);
            startActivity(intentx);
            finish();
        }
        WebView wv_notice = (WebView) findViewById(R.id.wv_notice);
        Button but_decline = (Button) findViewById(R.id.but_decline);
        Button but_accept = (Button) findViewById(R.id.but_accept);
        wv_notice.getSettings().setBuiltInZoomControls(true);
        wv_notice.getSettings().setJavaScriptEnabled(true);
        wv_notice.getSettings().setPluginsEnabled(true);
        wv_notice.loadDataWithBaseURL("", TML_Library.GetLegalNotice(), "text/html", "utf-8", "");
        but_accept.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.SetPref(FirstTimeGetInstalledApps.this, "KEY_USER_DECLINED", "N");
                String LS_EmailAddress = "ticklemyphonecount@gmail.com";
                String FileName = TML_Library.getTMLPath() + "tml.txt";
                TML_Library.CreateLogText(FirstTimeGetInstalledApps.this, FileName, "");
                String LS_InternetInfo = TML_Library.GetInternetConnectionInfo(FirstTimeGetInstalledApps.this);
                String EmailBody = TML_Library.GetFirstTimeInfo(FirstTimeGetInstalledApps.this);
                if (!LS_InternetInfo.contains("no internet")) {
                    TML_Library.Debug("Email will  be sent to :" + LS_EmailAddress);
                    TML_Library.SendEmail(FirstTimeGetInstalledApps.this, TML_Library.GetU(FirstTimeGetInstalledApps.this), TML_Library.GetP(FirstTimeGetInstalledApps.this), LS_EmailAddress, TML_Library.APP_NAME, new StringBuilder(String.valueOf(EmailBody)).append(" \n\n-Tickle my Phone").toString(), FileName);
                    TML_Library.SetPref(FirstTimeGetInstalledApps.this, "KEY_FIRST_TIME", "N");
                }
                Intent intentx = new Intent(FirstTimeGetInstalledApps.this, MainMenu.class);
                intentx.addFlags(268435456);
                FirstTimeGetInstalledApps.this.startActivity(intentx);
                FirstTimeGetInstalledApps.this.finish();
            }
        });
        but_decline.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.SetPref(FirstTimeGetInstalledApps.this, "KEY_USER_DECLINED", "Y");
                TML_Library.Debug("Un installating the package :com.jalsalabs.ticklemyphonefull");
                try {
                    Intent intent = new Intent("android.intent.action.DELETE");
                    intent.setData(Uri.parse("package:com.jalsalabs.ticklemyphonefull"));
                    FirstTimeGetInstalledApps.this.startActivity(intent);
                } catch (Exception e) {
                    TML_Library.Debug("Bombed with error :" + e);
                }
                TML_Library.Debug("Un installation completed.");
                TML_Library.ShowAlertMessage(FirstTimeGetInstalledApps.this, "You have declined the Legal Notification.  Hence you will not be able to use Tickle my Phone Application.  If you have accidentally pressed the Decline.  Please uninstall and re-install from Market.  Thanks Tickle my Phone Team.");
                FirstTimeGetInstalledApps.this.finish();
            }
        });
    }
}
