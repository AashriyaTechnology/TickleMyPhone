package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.os.Bundle;
import java.io.File;
import java.io.IOException;

public class PlaySDCardFile extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        TML_Library.Debug("***********************I AM INSIDE Play file************************");
        String FileName = TML_Library.GetAfter1Token(TML_Library.GetParameter("MESSAGE_BODY")).trim();
        TML_Library.Debug("File Original File:" + FileName);
        String LS_SenderPhone = TML_Library.GetParameter("ORIGINAL_ADDRESS");
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
            TML_Library.sendSMSBig(this, LS_SenderPhone, new StringBuilder(String.valueOf("File  " + FileName + " Does not exists. Unable Play" + "\n")).append("\n").append(getString(R.string.app_name)).toString());
            finish();
        }
        if (file.isDirectory()) {
            TML_Library.Debug("Failure : is a Directory [" + FileName + "]");
            TML_Library.sendSMSBig(this, LS_SenderPhone, new StringBuilder(String.valueOf("File  " + FileName + " is a Directory. Unable play  " + "\n")).append("\n").append(getString(R.string.app_name)).toString());
            finish();
        }
        if (file.exists()) {
            try {
                TML_Library.PlayFileFromSD(this, FileName);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        } else {
            TML_Library.Debug("Failure : File doesn't exist" + FileName);
        }
        finish();
    }
}
