package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelpSelfService extends Activity {
    static TextView DiagnosisText;
    static TextView StepsText;
    static Button contact_developer;
    static Button diagnosis;
    Context ctx = this;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        TML_Library.SetFullScreen(this);
        setContentView(R.layout.diagnosis);
        TML_Library.Debug("0.0001");
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.diagnosis);
        StepsText = (TextView) findViewById(R.id.steptext);
        StepsText.setText("Prerequisites\n-> Your phone has Read/Write to SD Card, Camera, SIM Card insert to Make call, send sms, receive sms capabilities. \n\n-> You have only One Tickle my Phone installtaion, Free or Paid.  Please note You can't have both.\n\n\n\nSteps to be followed:\n\nStep 1\nYou have downloaded Tickle my Phone from Google play (Android Market).  For our easy reference lets call this as phone A.\n\nStep 2\nTake another ordinary phone lets say Phone B and send SMS (text message) to phone A as callmeback\n\nStep 3\nClosely check Phone A (this phone), as soon as it receives the message Phone A will automatically call phone B\n\n\nif above steps does not work. Open Tickle my Phone app in phone A, goto main menu -> Log details check for the records. There should be record for callmeback. If you find record then Tickle my Phone is working fine. Please repeat step 1 to step 3 it will work.\n\n\n\nIf main menu -> Log details says [No log to display] then your phone has another SMS reading application which is not allowing Tickle my Phone to read the message to take action!!!\n\n\nOh!...Now What!!!!!?\n\n\nYou have to uninstall your other SMS reading application which is conflicting with Tickle my Phone !!!\n\nOk. How do I find what application is conflicting with Tickle my Phone?\n\nPress the below Diagnosis Button, which will list all your Installed Application which has 'READ_SMS' permission.  Once you have the list..  you have to 'Manually' uninstall those  apps one by one and check step 1 - 3 again.  I know its painful.  But you dont have choice!");
        diagnosis = (Button) findViewById(R.id.diagnosis);
        contact_developer = (Button) findViewById(R.id.contact_developer);
        DiagnosisText = (TextView) findViewById(R.id.diagtext);
        diagnosis.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(HelpSelfService.this);
                HelpSelfService.ShowWillTakeTime(HelpSelfService.this);
            }
        });
        contact_developer.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(HelpSelfService.this);
                LinearLayout layout = new LinearLayout(HelpSelfService.this);
                layout.setOrientation(1);
                layout.setGravity(1);
                final EditText username = new EditText(HelpSelfService.this);
                layout.setPadding(10, 0, 10, 0);
                username.setHint("Your Name");
                layout.addView(username);
                final EditText useremail = new EditText(HelpSelfService.this);
                layout.setPadding(10, 0, 10, 0);
                useremail.setHint("Your Email");
                layout.addView(useremail);
                Builder builder = new Builder(HelpSelfService.this);
                builder.setView(layout);
                builder.setTitle("Tickle my Phone");
                builder.setMessage("Please note that send button will take few mins (2-5 mins).   Please don't Cancel/Close.  Press Wait button if it promots to close.");
                builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String UserData = "User Name: " + username.getText().toString() + " | Email  : " + useremail.getText().toString();
                        TML_Library.Debug("====>Getting GetFirstTimeInfo");
                        String Personal_info = TML_Library.GetFirstTimeInfo(HelpSelfService.this);
                        TML_Library.Debug("====>Getting Application_Permissions");
                        String Installed_Appinfo = TML_Library.Application_Permissions(HelpSelfService.this);
                        String FileName = TML_Library.getTMLPath() + "tmldiagnosis.txt";
                        TML_Library.Debug("====>Creating the File");
                        TML_Library.CreateLogText(HelpSelfService.this, FileName, Installed_Appinfo);
                        TML_Library.Debug("====>Sending Email");
                        TML_Library.SendEmail(HelpSelfService.this, TML_Library.GetU(HelpSelfService.this), TML_Library.GetP(HelpSelfService.this), "ticklemyphoneapp@gmail.com", "Diagnosis information from " + UserData, Personal_info, FileName);
                        TML_Library.Debug("====>All Done");
                        TML_Library.PutToast(HelpSelfService.this, "Email Sent.  We will get back to you shortly");
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    public static void ShowWillTakeTime(Context context) {
        final Context ctxx = context;
        Builder adb = new Builder(context);
        adb.setTitle("Tickle my Phone");
        adb.setMessage("This Dianosis process will take few mins.  Press okay to continue.?");
        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                HelpSelfService.LocalApplication_Permissions(ctxx);
            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        adb.setTitle("Tickle my Phone Diagnosis");
        adb.show();
    }

    private static void LocalApplication_Permissions(Context context) {
        String LS_Final = "";
        String LS_Only_ReadSMS = "";
        PackageManager p = context.getPackageManager();
        int user_app_count = 0;
        for (PackageInfo pInfo : p.getInstalledPackages(4110)) {
            String[] reqPermission = pInfo.requestedPermissions;
            ServiceInfo[] services = pInfo.services;
            ProviderInfo[] providers = pInfo.providers;
            int versionCode = pInfo.versionCode;
            try {
                ApplicationInfo ai = p.getApplicationInfo(pInfo.applicationInfo.packageName, 0);
                LS_Final = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(LS_Final)).append("versionCode-package=").append(Integer.toString(versionCode)).append("\n").toString())).append("Installed Applications").append(pInfo.applicationInfo.loadLabel(p).toString()).append("\n").toString();
                if (reqPermission != null) {
                    for (int i = 0; i < reqPermission.length; i++) {
                        LS_Final = new StringBuilder(String.valueOf(LS_Final)).append("Permission list").append(reqPermission[i]).append("\n").toString();
                        if (reqPermission[i].contains("READ_SMS") && (ai.flags & 1) == 0) {
                            LS_Only_ReadSMS = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(LS_Only_ReadSMS)).append("Application Name   :").append(pInfo.applicationInfo.loadLabel(p).toString()).append("\n").toString())).append("Package Name       :").append(pInfo.applicationInfo.packageName.toString()).append("\n").toString())).append("Version Code       :").append(Integer.toString(versionCode)).append("\n").toString())).append("====================================\n").toString();
                            user_app_count++;
                        }
                    }
                }
            } catch (NameNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
        DiagnosisText.setText(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(LS_Only_ReadSMS)).append("Total    :").append(user_app_count).append("\n").toString())).append("====================================\n").toString());
    }
}
