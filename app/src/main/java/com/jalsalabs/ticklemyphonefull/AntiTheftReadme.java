package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AntiTheftReadme extends Activity {
    static String isDifferent = "N";
    static TextView readme;
    static TextView siminfo;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        TML_Library.SetFullScreen(this);
        setContentView(R.layout.antitheftreadme);
        TML_Library.Debug("0.0001");
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.antitheftreadme);
        if (TML_Library.GetPref(this, "KEY_IS_FREE").equals("Y")) {
            TML_Library.PutToast(this, "Please note SIM change settings done here will not be saved. Since you are using Free Version");
            TML_Library.UpdateSettingsBacktoFree(this);
        }
        TML_Library.Debug(" Ok 2");
        readme = (TextView) findViewById(R.id.readme);
        readme.setText(Html.fromHtml(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "<html>")).append("<head>").toString())).append("<title></title>").toString())).append("</head>").toString())).append("<body>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("<p>").toString())).append("<span style=\"font-size:16px;\"><strong>Please read following note, before you change the Antitheft alert setup</strong></span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#ff0000;\"><strong><u>Prerequisite</u></strong></span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;We assume this&nbsp; phone is yours.&nbsp; :) and it is neighter stolen nor somebody&#39;s phone</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;We assume the SIM what you have in this phone is  more or less your permanent phone number</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#ff0000;\"><strong><u>How it works</u></strong></span></p>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;You are now going to register your SIM&#39;s internal unique number with Tickle my Phone.</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;You also going to register your Alternative no (sencondary number) and Your email details with Tickle my Phone</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;When you lose your phone/stolen, the Culprit will switch off the phone, changes the SIM and switch it back on.</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;Well...When culprit switch it ON,&nbsp; Tickle my Phone will check.. Is the new SIM unique number is the same as Registered one</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;If it is different, then automatically Tickle my Phone&nbsp; will send the SMS to your secondory number with Culprit&#39;s mobile number</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;Then you can track your phone by sending Tickle my Phone commands.&nbsp;</span></p>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("<p>").toString())).append("<span style=\"color:#ff0000;\"><strong><u>Please Note:</u></strong></span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;This SIM Alert works only for GSM mobiles.  It also it depends service provider.</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;When the culprit switches ON and the phone connection doesn&rsquo;t have Internet or enough balance in his SIM ,&nbsp; the phone will not be able to send the Email or SMS</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;Makesure you give your alternative number of your spouse or friend contact no, Please don't give  this phone no.</span></p>").toString())).append("<p>").toString())).append("<span style=\"color:#0000ff;\">&nbsp;Regarding the pop message configuration&nbsp; please don&rsquo;t scare the culprit, be polite with him/her. Otherwise they may switch it off permanently and you may not get back your phone</span></p>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("</body>").toString())).append("</html>").toString()));
        readme.startAnimation(AnimationUtils.loadAnimation(this, R.animator.fadein));
    }
}
