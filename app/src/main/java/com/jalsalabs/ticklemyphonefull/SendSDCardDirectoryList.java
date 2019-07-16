package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import java.io.File;
import java.sql.Date;

public class SendSDCardDirectoryList extends Activity {
    static String Final_HTML;
    static String HTML_Begin;
    static String HTML_End;
    public String message;
    public String phoneNo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        File[] file = Environment.getExternalStorageDirectory().listFiles();
        TML_Library.Debug("I am inside Resume");
        String LS_BODY2NDTOKEN = TML_Library.GetParameter("BODY2NDTOKEN");
        TML_Library.Debug("BODY token is " + LS_BODY2NDTOKEN);
        HTML_Begin = "<table border=\"1\" bordercolor=\"FF3300\" style=\"background-color:FFFFFF\" width=\"800\" cellpadding=\"3\" cellspacing=\"3\">";
        HTML_End = "</table>\t   <p style=\"font-family:verdana,arial,sans-serif;font-size:10px;\"><a href=\"http://www.ticklemyphone.weebly.com\" target=\"_top\">Tickle my Phone</a></p>";
        String LS_EmailAddress = LS_BODY2NDTOKEN;
        String LS_SenderPhone = TML_Library.GetParameter("ORIGINAL_ADDRESS");
        if (TML_Library.isValidEmailAddress(LS_EmailAddress)) {
            String Is_EmailSent;
            TML_Library.Debug("I value is " + 0);
            TML_Library.Debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$I should not come here********************" + LS_EmailAddress);
            Final_HTML = HTML_Begin;
            Final_HTML += "Tickle my Phone SD Card Directory Listing\n";
            Final_HTML += "<tr>   <td>File name</td><td>File Size</td><td>Creation Date</td><td>File Type</td></tr>";
            recursiveFileFind(file);
            Final_HTML += HTML_End;
            String FileName = TML_Library.getTMLPath() + "ticklemyphonedirlist.html";
            TML_Library.CreateLogText(this, FileName, Final_HTML);
            if (TML_Library.GetInternetConnectionInfo(this).contains("no internet")) {
                Is_EmailSent = "\n" + getString(R.string.oth47_no_email_sent);
            } else {
                TML_Library.Debug("Email will  be sent to :" + LS_EmailAddress);
                TML_Library.SendEmail(this, TML_Library.GetU(this), TML_Library.GetP(this), LS_EmailAddress, "Tickle my Phone " + getString(R.string.oth60_SendDirectoryListing), "Directory Listing of SD Card is attached.  \n\n-Tickle my Phone", FileName);
                Is_EmailSent = "\n" + getString(R.string.oth48_email_sent_to) + LS_EmailAddress;
            }
            TML_Library.sendSMSBig(this, LS_SenderPhone, new StringBuilder(String.valueOf("SD Card Direcotry list " + Is_EmailSent)).append("\n").append(getString(R.string.app_name)).toString());
            finish();
            return;
        }
        TML_Library.sendSMSBig(this, LS_SenderPhone, new StringBuilder(String.valueOf("Invalid Email id :" + LS_EmailAddress)).append("\n").append(getString(R.string.app_name)).toString());
        TML_Library.Debug("Invalid email id hence exiting by finish()  srinath" + LS_EmailAddress);
        TML_Library.Debug("I value is " + 10);
        finish();
    }

    public void recursiveFileFind(File[] file1) {
        int i = 0;
        String filePath = "";
        if (file1 != null) {
            while (i != file1.length) {
                filePath = file1[i].getAbsolutePath();
                Long size = Long.valueOf(file1[i].length());
                String LastModifiedS = DateFormat.format("dd.MMM.yyyy h:mm a", new Date(Long.valueOf(file1[i].lastModified()).longValue())).toString();
                String FileType = "";
                if (file1[i].isDirectory()) {
                    FileType = "Directory";
                }
                if (file1[i].isDirectory()) {
                    recursiveFileFind(file1[i].listFiles());
                }
                i++;
                TML_Library.Debug(filePath);
                Final_HTML += "<tr>   <td>" + filePath + "</td><td>" + size + "</td><td>" + LastModifiedS + "</td><td>" + FileType + "</td></tr>";
            }
        }
    }
}
