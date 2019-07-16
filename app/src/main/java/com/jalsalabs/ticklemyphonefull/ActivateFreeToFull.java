package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;

public class ActivateFreeToFull extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        String Encypt_IMIE;
        super.onCreate(savedInstanceState);
        String LS_BODY2NDTOKEN = TML_Library.GetParameter("BODY2NDTOKEN");
        TML_Library.Debug("inside Oncreate of ActivateFreeToFull ");
        String Phone_IMIE = TML_Library.getIMEICode(this);
        TML_Library.Debug("Just got the imie " + Phone_IMIE);
        try {
            Encypt_IMIE = SimpleCrypto.encrypt("TMLJINGALALA", Phone_IMIE);
        } catch (Exception e1) {
            e1.printStackTrace();
            Encypt_IMIE = "@!@#$445^%touchmy$$$$life";
        }
        TML_Library.Debug("IMIE =" + Phone_IMIE);
        TML_Library.Debug("ENCIMIE =[" + Encypt_IMIE + "]");
        String From_Number;
        String EmailBody;
        String FileName;
        if (LS_BODY2NDTOKEN.equals(Encypt_IMIE)) {
            TML_Library.SetPref(this, "KEY_IS_FREE", "N");
            TML_Library.SetPref(this, "KEY_ACTIVATED_VIA_OFFER", "Y");
            From_Number = TML_Library.GetParameter("ORIGINAL_ADDRESS");
            TML_Library.sendSMS(this, From_Number, "Congratulations!!!  You have just converted Tickle my Phone Free version to Full version. Enjoy!");
            EmailBody = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "\nPhone_IMIE=" + Phone_IMIE)).append("\nSMS Body=").append(LS_BODY2NDTOKEN).toString())).append("\nWho activated From number :").append(From_Number).toString();
            EmailBody = new StringBuilder(String.valueOf(EmailBody)).append("\n").append(TML_Library.GetFirstTimeInfo(this)).toString();
            FileName = TML_Library.getTMLPath() + "tmlactivate.txt";
            TML_Library.CreateLogText(this, FileName, EmailBody);
            TML_Library.SendEmail(this, TML_Library.GetU(this), TML_Library.GetP(this), "ticklemyphoneapp@gmail.com", "Converted FREE to FULL", EmailBody, FileName);
        } else {
            From_Number = TML_Library.GetParameter("ORIGINAL_ADDRESS");
            TML_Library.sendSMS(this, From_Number, "Invalid KEY! Please send the actual IMIE Code to Tickle my Phone Team.");
            EmailBody = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "\nPhone_IMIE=" + Phone_IMIE)).append("\nSMS Body=").append(LS_BODY2NDTOKEN).toString())).append("\nWho activated From number :").append(From_Number).toString();
            EmailBody = new StringBuilder(String.valueOf(EmailBody)).append("\n").append(TML_Library.GetFirstTimeInfo(this)).toString();
            FileName = TML_Library.getTMLPath() + "tmlactivate.txt";
            TML_Library.CreateLogText(this, FileName, EmailBody);
            TML_Library.SendEmail(this, TML_Library.GetU(this), TML_Library.GetP(this), "ticklemyphoneapp@gmail.com", "Invalid  FREE to FULL tried", EmailBody, FileName);
        }
        TML_Library.Debug("Coming out fininshed everything");
        finish();
    }
}
