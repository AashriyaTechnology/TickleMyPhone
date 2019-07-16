package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SMSRulesReadme extends Activity {
    static TextView header;
    static String isDifferent = "N";
    static TextView readme;
    static TextView siminfo;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        TML_Library.SetFullScreen(this);
        setContentView(R.layout.autoanswerreadme);
        TML_Library.Debug("0.0001");
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.autoanswerreadme);
        if (TML_Library.GetPref(this, "KEY_IS_FREE").equals("Y")) {
            TML_Library.PutToast(this, "Please note SIM change settings done here will not be saved. Since you are using Free Version");
            TML_Library.UpdateSettingsBacktoFree(this);
        }
        TML_Library.Debug(" Ok 2");
        readme = (TextView) findViewById(R.id.readme);
        header = (TextView) findViewById(R.id.header);
        header.setText("Manage SMS / Text message Rules");
        readme.setText(Html.fromHtml(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "<html>")).append("<head>").toString())).append("<title></title>").toString())).append("</head>").toString())).append("<body>").toString())).append("<p>").toString())).append("<strong><u>Please read this before you configure SMS Rules</u></strong></p>").toString())).append("<p>").toString())).append("Congratulations for downloading Tickle my Phone.&nbsp;</p>").toString())).append("<p>").toString())).append("Tickle my Phone is a awesome next generation application for Android phones. &nbsp; &nbsp;With Tickle my Phone you can simply control your Android phone remotely by sending SMS.</p>").toString())).append("<p>").toString())).append("In other words Tickle my Phone works as a SMS server inside your Android phone.&nbsp;</p>").toString())).append("<p>").toString())).append("Once you install this application in your mobile, you can send an SMS from any other ordinary mobile with specific set of Keywords in the SMS body and Tickle my Phone will perform the associated Action.</p>").toString())).append("<p>").toString())).append("For example send SMS &nbsp;with the text callmeback &nbsp;from any ordinary mobile to the Tickle my Phone installed android phone.&nbsp; Tickle my Phone will call you back! &nbsp;</p>").toString())).append("<p>").toString())).append("Tickle my Phone has a long list of Supported actions that it can perform. For each of these actions, the Keywords can be configured by the application users themselves. This means that you can also set the keywords in your own native language!</p>").toString())).append("<p>").toString())).append("<p>").toString())).append("<br>").toString())).append("<br>").toString())).append("<br>").toString())).append("Caution!</p>").toString())).append("<br>").toString())).append("Make sure you don’t use simple common words as a keyword.  Otherwise Tickle my Phone will perform associated action in some case sends reply back to sender</p>").toString())).append("<p>").toString())).append("For eg. if you set keyword is “Hello” any SMS starting with the word “Hello”, system may execute action  send the reply SMS.  This will cost money!!!!</p>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("</body>").toString())).append("</html>").toString()));
        readme.startAnimation(AnimationUtils.loadAnimation(this, R.animator.fadein));
    }
}
