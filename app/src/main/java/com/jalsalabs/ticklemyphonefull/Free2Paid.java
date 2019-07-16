package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import java.io.IOException;

public class Free2Paid extends Activity {
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
        String ReadMe;
        super.onResume();
        setContentView(R.layout.autoanswerreadme);
        readme = (TextView) findViewById(R.id.readme);
        header = (TextView) findViewById(R.id.header);
        header.setText("How to convert your Trial version to Paid without paying money");
        try {
            ReadMe = TML_Library.GetFree2PaidText();
        } catch (IOException e) {
            ReadMe = "Offer is not valid";
        }
        readme.setText(Html.fromHtml(ReadMe));
        readme.startAnimation(AnimationUtils.loadAnimation(this, R.animator.fadein));
    }
}
