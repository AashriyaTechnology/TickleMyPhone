package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

public class SendSMSReport extends Activity {
    String LS_EmailAddress;
    String LS_SenderPhone;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TML_Library.Debug("***********************I AM INSIDE SendSMSReport************************");
        TML_Library.SwitchOfftheScreen(this);
        setContentView(R.layout.working);
    }

    public void onResume() {
        String Is_EmailSent;
        super.onResume();
        TML_Library.Debug("***********************I AM INSIDE SendSMSReport************************");
        TML_Library.SwitchOfftheScreen(this);
        setContentView(R.layout.working);
        this.LS_EmailAddress = TML_Library.GetParameter("BODY2NDTOKEN");
        this.LS_SenderPhone = TML_Library.GetParameter("ORIGINAL_ADDRESS");
        String SMStextMessages = TML_Library2.GetSMSContentReport(this);
        TML_Library.Debug("Environment variable =" + Environment.getExternalStorageDirectory().getPath());
        String FileName = TML_Library.getTMLPath() + "ticklymyphonesmsreport.tmp";
        TML_Library.CreateLogText(this, FileName, SMStextMessages);
        String SMSBody = getString(R.string.app_name) + " SMS/Text message Detailed report  ";
        String EmailBody = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("Detail SMS / Text message report is attached here.  File is in TMP(Tickle my Phone) format." + "\n\nFollowing steps to be followed to open the report :")).append("\n-> Download the attached file to local folder").toString())).append("\n-> Open the file in any browser which supports  Flash").toString())).append("\n\n\n Supported browsers:").toString())).append("\nGoogle Chrome").toString())).append("\nInternet explorer").toString())).append("\nFirefox").toString())).append("\nOpera").toString())).append("\nSafari").toString())).append("\n\n\n-").append(getString(R.string.app_name)).toString();
        if (TML_Library.GetInternetConnectionInfo(this).contains("no internet")) {
            Is_EmailSent = "\n" + getString(R.string.oth47_no_email_sent);
        } else {
            TML_Library.Debug("Email will  be sent to :" + this.LS_EmailAddress);
            TML_Library.SendEmail(this, TML_Library.GetU(this), TML_Library.GetP(this), this.LS_EmailAddress, "Tickle my Phone  Detailed SMS Report", EmailBody, FileName);
            Is_EmailSent = "\n" + getString(R.string.oth48_email_sent_to) + this.LS_EmailAddress;
        }
        TML_Library.Debug("Sendig SMS =" + SMSBody + " " + SMSBody.length());
        TML_Library.sendSMSBig(this, this.LS_SenderPhone, new StringBuilder(String.valueOf(SMSBody)).append(Is_EmailSent).append("\n").append(getString(R.string.app_name)).toString());
        finish();
    }
}
