package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class AudioRecordNow extends Activity {
    String LS_EmailAddress;
    String LS_SenderPhone;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TML_Library.Debug("***********************I AM INSIDE AudioRecord************************");
    }

    public void onResume() {
        int audiotime;
        String Signature;
        String LS_FileName;
        String SMSBody;
        super.onResume();
        TML_Library.Debug("I am inside resume");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        try {
            TML_Library.Debug("To be recorded for " + 30 + " Seconds.");
            audiotime = Integer.parseInt(prefs.getString("audiotime", "30"));
        } catch (Exception e) {
            audiotime = 30;
        }
        TML_Library.Debug("To be recorded for final" + audiotime + " Seconds.");
        TML_Library.Debug("***********************I AM INSIDE Resume AudioRecord************************");
        String LS_Option = TML_Library.GetParameter("AUDIO_SELECTION");
        TML_Library.Debug("Option = " + LS_Option);
        String LS_SenderPhone = TML_Library.GetParameter("ORIGINAL_ADDRESS");
        String LS_Date = TML_Library.GetDateFormat("yyyyMMdd");
        String LS_Time = TML_Library.GetDateFormat("hh_mm");
        String LS_IS_APP_Activate = TML_Library.GetPref(this, "KEY_IS_APP_ACTIVATE");
        if (LS_IS_APP_Activate.equals("Y")) {
            Signature = getString(R.string.app_name);
        } else {
            Signature = getString(R.string.app_name) + " " + getString(R.string.oth35_Free);
        }
        int Nseconds = audiotime;
        if (LS_Option.equals("1")) {
            TML_Library.Debug("I am inside option 1");
            LS_FileName = TML_Library.getTMLPath() + "tmlaudio_" + LS_Date + "_" + LS_Time + ".3gp";
            TML_Library.Debug("file name = " + LS_FileName);
            TML_Library.AudioRecord(this, LS_FileName, Nseconds);
            SMSBody = getString(R.string.oth2_audio_recorded) + " " + Nseconds + " seconds. File name :" + LS_FileName + "\n" + Signature;
            TML_Library.Debug("sms sending  body " + SMSBody + "\n Length =" + SMSBody.length());
            TML_Library.sendSMSBig(this, LS_SenderPhone, SMSBody);
            TML_Library.Debug("sms sent with body " + SMSBody);
            finish();
        }
        if (LS_Option.equals("2")) {
            TML_Library.Debug("I am inside option 2");
            Nseconds = Integer.parseInt(TML_Library.GetParameter("BODY2NDTOKEN"));
            LS_FileName = TML_Library.getTMLPath() + "tmlaudio_" + LS_Date + "_" + LS_Time + ".3gp";
            TML_Library.AudioRecord(this, LS_FileName, Nseconds);
            SMSBody = getString(R.string.oth2_audio_recorded) + " " + Nseconds + " seconds. File name :" + LS_FileName + "\n" + Signature;
            TML_Library.Debug("sms sending  body " + SMSBody + "\n Length =" + SMSBody.length());
            TML_Library.sendSMSBig(this, LS_SenderPhone, SMSBody);
            TML_Library.Debug("OPT 2 sms sent with body " + SMSBody);
            finish();
        }
        if (LS_Option.equals("3")) {
            String Is_EmailSent;
            TML_Library.Debug("I am inside option 3");
            String LS_Email = TML_Library.GetParameter("BODY2NDTOKEN");
            TML_Library.Debug("Email = " + LS_Email);
            if (LS_IS_APP_Activate.equals("Y")) {
                LS_FileName = TML_Library.getTMLPath() + "tmlaudio.3gp";
            } else {
                LS_FileName = TML_Library.getTMLPath() + "upgradetofull.jpg";
            }
            TML_Library.Debug("To be recorded for  FINAL *******    " + Nseconds + " Seconds.");
            if (TML_Library.GetInternetConnectionInfo(this).contains("no internet")) {
                Is_EmailSent = "\n" + getString(R.string.oth47_no_email_sent);
            } else {
                Is_EmailSent = "\n" + getString(R.string.oth48_email_sent_to) + LS_Email;
            }
            String EmailSubject = getString(R.string.oth3_sent_audio);
            String EmailBody = "Audio record Date : " + LS_Date + "\n" + "Audio record Time :" + LS_Time + "\n\n\n" + Signature;
            TML_Library.AudioRecord(this, LS_FileName, Nseconds);
            TML_Library.Debug("Completed audio Recording for " + Nseconds);
            SMSBody = getString(R.string.oth4_audio_recorded) + " " + Nseconds + " seconds. \n" + Signature + " " + Is_EmailSent;
            TML_Library.Debug("Now sending sms with sms body = " + SMSBody + " to " + LS_SenderPhone);
            TML_Library.SendEmail(this, TML_Library.GetU(this), TML_Library.GetP(this), LS_Email, EmailSubject, EmailBody, LS_FileName);
            TML_Library.sendSMSBig(this, LS_SenderPhone, SMSBody);
            finish();
        }
        finish();
    }
}
