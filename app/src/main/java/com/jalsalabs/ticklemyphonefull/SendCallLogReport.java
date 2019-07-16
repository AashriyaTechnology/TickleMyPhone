package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;

public class SendCallLogReport extends Activity {
    String LS_EmailAddress;
    String LS_SenderPhone;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TML_Library.Debug("***********************I AM INSIDE SendMissedCallDetails************************");
    }

    public void onResume() {
        String Is_EmailSent;
        super.onResume();
        TML_Library.Debug("***********************I AM INSIDE SendMissedCallDetails************************");
        this.LS_EmailAddress = TML_Library.GetParameter("BODY2NDTOKEN");
        this.LS_SenderPhone = TML_Library.GetParameter("ORIGINAL_ADDRESS");
        TML_Library2.GetCallDetailsReport(this, this.LS_EmailAddress);
        String SMSBody = getString(R.string.app_name) + "Detailed Call LOG Report  ";
        TML_Library.Debug("Sendig SMS =" + SMSBody + " " + SMSBody.length());
        if (TML_Library.GetInternetConnectionInfo(this).contains("no internet")) {
            Is_EmailSent = "\n" + getString(R.string.oth47_no_email_sent);
        } else {
            Is_EmailSent = "\n" + getString(R.string.oth48_email_sent_to) + this.LS_EmailAddress;
        }
        TML_Library.sendSMSBig(this, this.LS_SenderPhone, new StringBuilder(String.valueOf(SMSBody)).append(Is_EmailSent).append("\n").append(getString(R.string.app_name)).toString());
        finish();
    }
}
