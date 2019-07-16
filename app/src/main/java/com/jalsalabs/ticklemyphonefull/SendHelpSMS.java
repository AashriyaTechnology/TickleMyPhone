package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;

public class SendHelpSMS extends Activity {
    public String message;
    public String phoneNo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.phoneNo = TML_Library.GetParameter("ORIGINAL_ADDRESS");
        TML_Library.Debug("3 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
        TML_Library.Debug("Inside SendHelpSMS Original Phone number is " + this.phoneNo);
        this.message = TML_Library.GetHelp(this);
        TML_Library.Debug("And the Message is  " + this.message + "\n\nMessage length = " + this.message.length());
        if (this.phoneNo.length() <= 0 || this.message.length() <= 0) {
            TML_Library.Error("ERROR001:Unable to send SMS Phone number or message not present while sending!" + this.phoneNo + "\n" + this.message);
        } else {
            TML_Library.Debug("Message length :" + this.message.length());
            TML_Library.sendSMSBig(this, this.phoneNo, this.message);
        }
        finish();
    }
}
