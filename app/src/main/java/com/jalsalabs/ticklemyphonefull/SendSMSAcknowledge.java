package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;

public class SendSMSAcknowledge extends Activity {
    public String message;
    public String phoneNo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.phoneNo = TML_Library.GetParameter("ORIGINAL_ADDRESS");
        this.message = TML_Library.GetPref(this, "KEY_SMS_REPLY_TEXT");
        if (TML_Library.GetPref(this, "KEY_IS_APP_ACTIVATE").equals("N")) {
            this.message += getString(R.string.oth19_free_version);
        }
        if (this.phoneNo.length() <= 0 || this.message.length() <= 0) {
            TML_Library.Error("ERROR001:Unable to send SMS Phone number or message not present while sending!");
        } else {
            TML_Library.sendSMSBig(this, this.phoneNo, this.message);
        }
        finish();
    }
}
