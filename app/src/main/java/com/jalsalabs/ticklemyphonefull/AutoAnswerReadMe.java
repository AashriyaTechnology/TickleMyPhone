package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class AutoAnswerReadMe extends Activity {
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
        readme.setText(Html.fromHtml(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "<html>")).append("<head>").toString())).append("<title></title>").toString())).append("</head>").toString())).append("<body>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("<p>").toString())).append("<strong><u>Auto Answering</u></strong></p>").toString())).append("<p>").toString())).append("Auto Answering, feature enable answering the phone automatically.&nbsp; &nbsp;You can setup pre-defined numbers.&nbsp; When you received call from these number phone automatically answers.&nbsp; If required you can also puts the speaker on. &nbsp;&nbsp;Please note this feature is only available for Paid Version.</p>").toString())).append("<p>").toString())).append("This features helps for handicapped people or &nbsp;&nbsp;if your hand dirty and you don&rsquo;t want to messup the phone or You &nbsp;are driving car and don&rsquo;t want to look down to answer the call or simply you want to spy on someone. </p>").toString())).append("<p>").toString())).append("<p>").toString())).append("Please use settings tab to enable, Control who&rsquo;s call can only accept by using allow / disallow.  There is also a feature to add the sender number automatically to allow/disallow by an SMS command [ADDAUTOANSWER].   </p>").toString())).append("<p>").toString())).append("<span style=\"color:#ff8c00;\"><strong>Go-ahead enjoy Tickle my Phone Auto Answer</strong></span></p>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("<p>").toString())).append("&nbsp;</p>").toString())).append("</body>").toString())).append("</html>").toString()));
    }
}
