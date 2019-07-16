package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;

public class SendCallLogDetails extends Activity {
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
        String TotalMissedCall = "0";
        String TotalIncomingCall = "0";
        String TotalOutgoingCall = "0";
        TML_Library.getCallDetails(this, this.LS_EmailAddress);
        TotalIncomingCall = TML_Library.GetPref(this, "INCOMING_COUNT");
        TotalOutgoingCall = TML_Library.GetPref(this, "OUTGOING_COUNT");
        String SMSBody = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("Incoming:" + TotalIncomingCall + "\n")).append("Outgoing:").append(TotalOutgoingCall).append("\n").toString())).append("Missed:").append(TML_Library.GetPref(this, "MISSCALL_COUNT")).append("\n").toString();
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
