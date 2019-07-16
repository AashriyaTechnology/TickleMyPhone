package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;
import java.io.File;

public class GetFileFromSDCard extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        TML_Library.Debug("***********************I AM INSIDE GetFileFromSDCard file************************");
        String FileName = TML_Library.GetAfter2Token(TML_Library.GetParameter("MESSAGE_BODY")).trim();
        TML_Library.Debug("File name :" + FileName);
        String LS_EmailAddress = TML_Library.GetParameter("BODY2NDTOKEN");
        String LS_SenderPhone = TML_Library.GetParameter("ORIGINAL_ADDRESS");
        TML_Library.Debug("File name ::::[" + FileName + "]");
        TML_Library.Debug("LS_EmailAddress  :" + LS_EmailAddress);
        if (TML_Library.isValidEmailAddress(LS_EmailAddress)) {
            TML_Library.Debug("File Original File:" + FileName);
            if (FileName.startsWith("[")) {
                FileName = FileName.substring(1);
            }
            TML_Library.Debug("First cut File:" + FileName);
            if (FileName.endsWith("]")) {
                FileName = FileName.substring(1, FileName.length() - 1);
            }
            TML_Library.Debug("Second cut File:" + FileName);
            File file = new File(FileName);
            if (!file.exists()) {
                TML_Library.Debug("Failure : File doesn't exist [" + FileName + "]");
                TML_Library.sendSMSBig(this, LS_SenderPhone, new StringBuilder(String.valueOf("File  " + FileName + " Does not exists. Unable to send the email to  " + LS_EmailAddress + "\n")).append("\n").append(getString(R.string.app_name)).toString());
                finish();
            }
            if (file.isDirectory()) {
                TML_Library.Debug("Failure : is a Directory [" + FileName + "]");
                TML_Library.sendSMSBig(this, LS_SenderPhone, new StringBuilder(String.valueOf("File  " + FileName + " is a Directory. Unable to send the email to  " + LS_EmailAddress + "\n")).append("\n").append(getString(R.string.app_name)).toString());
                finish();
            }
            if (TML_Library.GetInternetConnectionInfo(this).contains("no internet")) {
                TML_Library.sendSMSBig(this, LS_SenderPhone, new StringBuilder(String.valueOf("No Internet connection! Unable to send the file " + FileName + " to " + LS_EmailAddress)).append("\n").append(getString(R.string.app_name)).toString());
                finish();
                return;
            }
            TML_Library.Debug("Email will  be sent to :" + LS_EmailAddress);
            TML_Library.SendEmail(this, TML_Library.GetU(this), TML_Library.GetP(this), LS_EmailAddress, "Tickle my Phone " + getString(R.string.oth61_Get_File_Name), "File : " + FileName + " is extracted from SD Card. Please find the enclosed.\n\n-Tickle my Phone", FileName);
            TML_Library.sendSMSBig(this, LS_SenderPhone, new StringBuilder(String.valueOf("File " + FileName + " sent to " + LS_EmailAddress + "\n")).append("\n" + getString(R.string.oth48_email_sent_to) + " " + LS_EmailAddress).append("\n").append(getString(R.string.app_name)).toString());
            TML_Library.Debug("Completing");
            finish();
            TML_Library.Debug("Completed after finish");
            return;
        }
        TML_Library.Debug("Invalid email id  [" + LS_EmailAddress + "]");
        TML_Library.sendSMSBig(this, LS_SenderPhone, new StringBuilder(String.valueOf("Invalid Email address " + LS_EmailAddress + "\n")).append("\n").append(getString(R.string.app_name)).toString());
        finish();
    }
}
