package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class AntiTheftBootStartActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        TML_Library.Debug("Inside BootStartActivity Resume");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        TML_Library.Debug("BootStartActivity Debug  1 ");
        TML_Library.Debug("BootStartActivity Debug  2 ");
        Boolean simchangealert = Boolean.valueOf(prefs.getBoolean("simchangealert", false));
        TML_Library.Debug("BootStartActivity Debug  3 ");
        String CurrentSimSrlno = TML_Library.GetSimSerialNumber(this);
        TML_Library.Debug("BootStartActivity Debug  4 ");
        String CurrentOperatorName = TML_Library.GetSimOperatorName(this);
        TML_Library.Debug("BootStartActivity Debug  5 ");
        String RegisteredSimSrlno = TML_Library.GetPref(this, "REGISTERED_SIM_SRLNO");
        TML_Library.Debug("BootStartActivity Debug  6 ");
        String RegisteredOperatorName = TML_Library.GetPref(this, "REGISTERED_OPERATOR_NAME");
        TML_Library.Debug("BootStartActivity Debug  7 ");
        TML_Library.Debug("Showing the alert message");
        TML_Library.Debug("Showing the alert completed");
        if (simchangealert.booleanValue() && !RegisteredSimSrlno.equals(CurrentSimSrlno)) {
            TML_Library.Debug(RegisteredSimSrlno);
            TML_Library.Debug(RegisteredOperatorName);
            TML_Library.Debug(CurrentSimSrlno);
            TML_Library.Debug(CurrentOperatorName);
            TML_Library.Debug("Creatig the file");
            Boolean sendsmswhenstolen = Boolean.valueOf(prefs.getBoolean("sendsmswhenstolen", false));
            Boolean sendemailwhenstolen = Boolean.valueOf(prefs.getBoolean("sendemailwhenstolen", false));
            Boolean showmessagestolen = Boolean.valueOf(prefs.getBoolean("showmessagestolen", true));
            Boolean bigalaramstolen = Boolean.valueOf(prefs.getBoolean("bigalaramstolen", true));
            String SMS_StolenMessage = TML_Library.GetPref(this, "DEFAULT_PHONE_LOST_TEXT");
            String SecondoryPhoneNo = TML_Library.GetPref(this, "SIM_SECONDARY_PHONE");
            String SecondoryPhoneNo2 = TML_Library.GetPref(this, "SIM_SECONDARY_PHONE2");
            String SecondaryEmail = TML_Library.GetPref(this, "SIM_SECONDARY_EMAIL");
            String CulprintContact = TML_Library.getUserContactNumber(this);
            String CulpritServiceProvider = TML_Library.GetSimOperatorName(this);
            String CulpritCountry = TML_Library.GetSimCountry(this);
            if (sendsmswhenstolen.booleanValue()) {
                TML_Library.Debug("sendsmswhenstolen is true");
                String SMS_Message = "You phone (Sim #" + RegisteredSimSrlno + ") is currently with unknown person. \n" + "Contact no  is :" + CulprintContact + "\n" + "Culprit SIM Srl no : " + CurrentSimSrlno + "\n" + "His/Her Serv. Provider: " + CulpritServiceProvider + "\n" + "Country : " + CulpritCountry + "\n" + "Now you can contact culprit or send Tickle my Phone commands. \nTickle my Phone";
                int i = 0;
                if (!SecondoryPhoneNo.equals("")) {
                    while (i < 5) {
                        try {
                            TML_Library.sendSMSBig(this, SecondoryPhoneNo, SMS_Message);
                            TML_Library.Debug("sent in if");
                            i = 10;
                        } catch (Exception e) {
                            TML_Library.Debug("Got Error sleeping for 10 seconds" + e);
                            TML_Library.Sleep(10);
                            i++;
                        }
                    }
                }
                i = 0;
                if (!SecondoryPhoneNo2.equals("")) {
                    while (i < 5) {
                        try {
                            TML_Library.sendSMSBig(this, SecondoryPhoneNo, SMS_Message);
                            TML_Library.Debug("sent in if");
                            i = 10;
                        } catch (Exception e2) {
                            TML_Library.Debug("Got Error sleeping for 10 seconds" + e2);
                            TML_Library.Sleep(10);
                            i++;
                        }
                    }
                }
            }
            if (showmessagestolen.booleanValue()) {
                TML_Library.Debug("showmessagestolen is true");
                TML_Library.SetParameter("MESSAGE_BODY", SMS_StolenMessage);
                Intent intent = new Intent(getApplicationContext(), ShowPopOutMessage.class);
                intent.setFlags(268435456);
                startActivity(intent);
            }
            if (bigalaramstolen.booleanValue()) {
                TML_Library.Debug("showmessagestolen is true");
                TML_Library.PlayBIGAlaram(this, 1);
            }
            if (sendemailwhenstolen.booleanValue()) {
                TML_Library.Debug("showmessagestolen is true");
                String Mail_Message_Body = "You phone (Sim #" + RegisteredSimSrlno + ") is currently with unknown person and his/her\n" + "Contact no  is :" + CulprintContact + "\n" + "Culprit SIM Srl no : " + CurrentSimSrlno + "\n" + "His/Her Serv. Provider: " + CulpritServiceProvider + "\n" + "Country : " + CulpritCountry + "\n" + "Now you can contact culprit or send Tickle my Phone commands. \nTickle my Phone";
                String FileName = TML_Library.getTMLPath() + "phonelost.txt";
                TML_Library.CreateLogText(this, FileName, Mail_Message_Body);
                TML_Library.SendEmail(this, TML_Library.GetU(this), TML_Library.GetP(this), SecondaryEmail, "Tickle my Phone Mail from your lost Mobile ", Mail_Message_Body, FileName);
                TML_Library.getCallDetails(this, SecondaryEmail);
            }
            TML_Library.Debug("Boot Receiver completed");
        }
        finish();
    }
}
