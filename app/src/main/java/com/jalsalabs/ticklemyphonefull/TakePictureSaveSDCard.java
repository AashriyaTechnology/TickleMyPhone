package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TakePictureSaveSDCard extends Activity {
    String LS_EmailAddress;
    String LS_SenderPhone;
    public Camera camera;
    PictureCallback jpegCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            FileNotFoundException e;
            IOException e2;
            TML_Library.Debug("20.0");
            TML_Library.Debug("20.1");
            String LS_Date = TML_Library.GetDateFormat("yyyyMMdd");
            String FileName = TML_Library.getTMLPath() + "tmlimage_" + LS_Date + "_" + TML_Library.GetDateFormat("hh_mm") + ".jpg";
            try {
                TML_Library.Debug("21");
                FileOutputStream outStream = new FileOutputStream(String.format(FileName, new Object[0]));
                FileOutputStream fileOutputStream;
                try {
                    TML_Library.Debug("22");
                    outStream.write(data);
                    TML_Library.Debug("23");
                    outStream.flush();
                    outStream.close();
                    TML_Library.Debug("24");
                    camera.unlock();
                    camera.release();
                    TML_Library.Debug("25");
                    String isFree = TML_Library.GetPref(TakePictureSaveSDCard.this, "KEY_IS_FREE");
                    TML_Library.Debug("26");
                    String LS_Date1 = TML_Library.GetDateFormat("dd MMMMM yyyy");
                    TML_Library.Debug("27");
                    String LS_Time1 = TML_Library.GetDateFormat("h:mm a");
                    TML_Library.Debug("28");
                    String SMSBody = new StringBuilder(String.valueOf(TakePictureSaveSDCard.this.getString(R.string.oth26_image_saved_sd))).append(" file name :").append(FileName).append("\n Date:").append(LS_Date1).append("\nTime:").append(LS_Time1).append("\n-Tickle my Phone").toString();
                    TML_Library.Debug("30");
                    TakePictureSaveSDCard.this.LS_SenderPhone = TML_Library.GetParameter("ORIGINAL_ADDRESS");
                    TML_Library.Debug("31  " + TakePictureSaveSDCard.this.LS_SenderPhone + "   " + SMSBody + " " + SMSBody.length());
                    TML_Library.sendSMSBig(TakePictureSaveSDCard.this, TakePictureSaveSDCard.this.LS_SenderPhone, SMSBody);
                    TML_Library.Debug("32");
                    TML_Library.Sleep(2);
                    TML_Library.WaterMarkProcessBottom(TakePictureSaveSDCard.this, FileName, FileName);
                    if (isFree.equals("Y")) {
                        TML_Library.WaterMarkProcessFor(TakePictureSaveSDCard.this, FileName, FileName);
                    }
                    TakePictureSaveSDCard.this.finish();
                    fileOutputStream = outStream;
                } catch (FileNotFoundException e3) {
                    e = e3;
                    fileOutputStream = outStream;
                    TML_Library.Debug("26");
                    e.printStackTrace();
                } catch (IOException e4) {
                    e2 = e4;
                    fileOutputStream = outStream;
                    e2.printStackTrace();
                    TML_Library.Debug("28");
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                TML_Library.Debug("26");
                e.printStackTrace();
            } catch (IOException e6) {
                e2 = e6;
                e2.printStackTrace();
                TML_Library.Debug("28");
            }
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        this.LS_SenderPhone = TML_Library.GetParameter("ORIGINAL_ADDRESS");
        TML_Library.Debug("******** I AM INSIDE   TakePictureSaveSDCard***********");
        this.camera = Camera.open();
        Parameters parameters = this.camera.getParameters();
        this.camera.startPreview();
        this.camera.takePicture(null, null, this.jpegCallback);
        TML_Library.Debug("111111111Released fired 111111111");
    }
}
