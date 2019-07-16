package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TakePictureSendEmail extends Activity {
    String LS_EmailAddress;
    String LS_SenderPhone;
    public Camera camera;
    PictureCallback jpegCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            FileNotFoundException e;
            IOException e2;
            TML_Library.Debug("20.0");
            TML_Library.Debug("20.1");
            String AppEmailID = TML_Library.GetU(TakePictureSendEmail.this);
            String AppEmailPWD = TML_Library.GetP(TakePictureSendEmail.this);
            String FileName = TML_Library.getTMLPath() + "ticklemyphone.jpg";
            try {
                TML_Library.Debug("21");
                FileOutputStream fileOutputStream = new FileOutputStream(String.format(FileName, new Object[0]));
                FileOutputStream fileOutputStream2;
                try {
                    String Is_EmailSent;
                    String EmailSubject;
                    String EmailBody;
                    String SMSBody;
                    TML_Library.Debug("22");
                    fileOutputStream.write(data);
                    TML_Library.Debug("23");
                    fileOutputStream.close();
                    TML_Library.Debug("24");
                    camera.unlock();
                    camera.release();
                    TML_Library.Debug("25");
                    String LS_Date = TML_Library.GetDateFormat("dd MMMMM yyyy");
                    String LS_Time = TML_Library.GetDateFormat("h:mm a");
                    String isFree = TML_Library.GetPref(TakePictureSendEmail.this, "KEY_IS_FREE");
                    if (TML_Library.GetInternetConnectionInfo(TakePictureSendEmail.this).contains("no internet")) {
                        Is_EmailSent = "\n" + TakePictureSendEmail.this.getString(R.string.oth47_no_email_sent);
                    } else {
                        Is_EmailSent = "\n" + TakePictureSendEmail.this.getString(R.string.oth48_email_sent_to) + TakePictureSendEmail.this.LS_EmailAddress;
                    }
                    FileName = TML_Library.getTMLPath() + "ticklemyphone.jpg";
                    if (isFree.equals("N")) {
                        EmailSubject = new StringBuilder(String.valueOf(TakePictureSendEmail.this.getString(R.string.app_name))).append(" ").append(TakePictureSendEmail.this.getString(R.string.oth22_sent_live_image)).toString();
                        EmailBody = "Image Date : " + LS_Date + "\n" + "Image Time :" + LS_Time + "\n\n\n" + TML_Library.APP_FULL_VERSION + "!!!";
                        SMSBody = new StringBuilder(String.valueOf(TakePictureSendEmail.this.getString(R.string.app_name))).append("\n").append(TakePictureSendEmail.this.getString(R.string.oth25_image_sent_to)).append(Is_EmailSent).append(" at ").append(LS_Date).append(" ").append(LS_Time).toString();
                    } else {
                        EmailSubject = TML_Library.APP_FULL_VERSION + " " + TakePictureSendEmail.this.getString(R.string.oth22_sent_live_image);
                        EmailBody = "Image Date : " + LS_Date + "\n" + "Image Time :" + LS_Time + "\n\n\n " + TML_Library.APP_FULL_VERSION + TakePictureSendEmail.this.getString(R.string.oth23_you_r_using_trial);
                        TML_Library.WaterMarkProcessBottom(TakePictureSendEmail.this, FileName, FileName);
                        TML_Library.WaterMarkProcessFor(TakePictureSendEmail.this, FileName, FileName);
                        SMSBody = new StringBuilder(String.valueOf(TakePictureSendEmail.this.getString(R.string.app_name))).append("\n").append(TakePictureSendEmail.this.getString(R.string.oth24_trial_version_dummy)).append(" ").append(Is_EmailSent).append(" at ").append(LS_Date).append(" ").append(LS_Time).toString();
                    }
                    EmailBody = new StringBuilder(String.valueOf(EmailBody)).append(TML_Library.getEmail(TakePictureSendEmail.this)).toString();
                    try {
                        TML_Library.Debug(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "\nAppEmailID=" + AppEmailID)).append("\nAppEmailPWD=").append(AppEmailPWD).toString())).append("\nLS_EmailAddress=").append(TakePictureSendEmail.this.LS_EmailAddress).toString())).append("\nEmailSubject=").append(EmailSubject).toString())).append("\nEmailBody=").append(EmailBody).toString());
                        TML_Library.SendEmail(TakePictureSendEmail.this, AppEmailID, AppEmailPWD, TakePictureSendEmail.this.LS_EmailAddress, EmailSubject, EmailBody, FileName);
                        TML_Library.PutToast(TakePictureSendEmail.this, TakePictureSendEmail.this.getString(R.string.oth14_mail_sent));
                        TML_Library.sendSMSBig(TakePictureSendEmail.this, TakePictureSendEmail.this.LS_SenderPhone, SMSBody);
                        TML_Library.Debug("SMS sent");
                    } catch (Exception e3) {
                        TML_Library.sendSMSBig(TakePictureSendEmail.this, TakePictureSendEmail.this.LS_SenderPhone, "Unable to send image to :" + TakePictureSendEmail.this.LS_EmailAddress);
                        TML_Library.PutToast(TakePictureSendEmail.this, "Problem while mail sending");
                        TML_Library.Debug("Problem while mail sending");
                    }
                    TakePictureSendEmail.this.finish();
                    fileOutputStream2 = fileOutputStream;
                } catch (FileNotFoundException e4) {
                    e = e4;
                    fileOutputStream2 = fileOutputStream;
                    TML_Library.Debug("26");
                    e.printStackTrace();
                } catch (IOException e5) {
                    e2 = e5;
                    fileOutputStream2 = fileOutputStream;
                    e2.printStackTrace();
                    TML_Library.Debug("28");
                }
            } catch (FileNotFoundException e6) {
                e = e6;
                TML_Library.Debug("26");
                e.printStackTrace();
            } catch (IOException e7) {
                e2 = e7;
                e2.printStackTrace();
                TML_Library.Debug("28");
            }
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TML_Library.Debug("******** I AM INSIDE   create TakePictureSendEmail***********");
        this.LS_EmailAddress = TML_Library.GetParameter("BODY2NDTOKEN");
        this.LS_SenderPhone = TML_Library.GetParameter("ORIGINAL_ADDRESS");
        TML_Library.Debug("Email to be  " + this.LS_EmailAddress + " " + this.LS_SenderPhone);
        TML_Library.Debug("before acquire");
        getWindow().addFlags(128);
        ((WifiManager) getSystemService("wifi")).setWifiEnabled(true);
        TML_Library.Sleep(2);
        try {
            this.camera = Camera.open();
            this.camera.setParameters(this.camera.getParameters());
            this.camera.startPreview();
            this.camera.takePicture(null, null, this.jpegCallback);
        } catch (Exception e) {
            TML_Library.Debug("Problem boss " + e);
            TML_Library.PutToast(this, "Sorry  :(  Your camera doesnt support automatic clicking");
        }
        TML_Library.Debug("111111111Released fired 111111111");
        TML_Library.Debug("After Release");
        TML_Library.Debug("100");
    }
}
