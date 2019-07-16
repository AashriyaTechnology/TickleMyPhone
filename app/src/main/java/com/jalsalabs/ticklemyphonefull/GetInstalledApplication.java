package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetInstalledApplication extends Activity {
    static String Final_HTML;
    static String HTML_Begin;
    static String HTML_End;
    private String appname = "";
    private Drawable icon;
    public String message;
    public String phoneNo;
    private String pname = "";
    private int versionCode = 0;
    private String versionName = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void prettyPrint() {
        TML_Library.Debug(this.appname + "\t" + this.pname + "\t" + this.versionName + "\t" + this.versionCode);
        Final_HTML += "<tr>   <td>" + this.appname + "</td> <td>" + this.pname + "</td><td>" + this.versionName + "</td><td>" + this.versionCode + "</td></tr>";
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
            Final_HTML = HTML_Begin;
            Final_HTML += "Installed Applications\n";
            Final_HTML += "<tr>   <td>Appliction Name</td><td>Package Name</td><td>Version Name</td><td>Version Code</td></tr>";
            getPackages(this);
            ListActiveApplications(this);
            Final_HTML += HTML_End;
            String FileName = TML_Library.getTMLPath() + "ticklemyphoneappslist.html";
            TML_Library.CreateLogText(this, FileName, Final_HTML);
            if (TML_Library.GetInternetConnectionInfo(this).contains("no internet")) {
                Is_EmailSent = "\n" + getString(R.string.oth47_no_email_sent);
            } else {
                TML_Library.Debug("Email will  be sent to :" + LS_EmailAddress);
                TML_Library.SendEmail(this, TML_Library.GetU(this), TML_Library.GetP(this), LS_EmailAddress, "Tickle my Phone " + getString(R.string.oth62_RetrieveMessage), "Tickle my Phone has sent the Application Installed,  Application Running and Services Running.  \nPlease find the Enclosed.  \n\n-Tickle my Phone", FileName);
                Is_EmailSent = "\n" + getString(R.string.oth48_email_sent_to) + LS_EmailAddress;
            }
            TML_Library.sendSMSBig(this, LS_SenderPhone, "Installed Application and Running application list" + Is_EmailSent + "\n" + getString(R.string.app_name));
            TML_Library.Debug("Now calling SD Card listing of files.........................");
            TML_Library.Debug("Now calling SD Card listing of files completed");
            finish();
            return;
        }
        TML_Library.sendSMSBig(this, LS_SenderPhone, new StringBuilder(String.valueOf("Invalid or No Email id  found :" + LS_EmailAddress)).append("\n").append(getString(R.string.app_name)).toString());
        finish();
    }

    private ArrayList<GetInstalledApplication> getPackages(Context context) {
        ArrayList<GetInstalledApplication> apps = getInstalledApps(context, false);
        int max = apps.size();
        for (int i = 0; i < max; i++) {
            ((GetInstalledApplication) apps.get(i)).prettyPrint();
        }
        return apps;
    }

    private ArrayList<GetInstalledApplication> getInstalledApps(Context context, boolean getSysPackages) {
        ArrayList<GetInstalledApplication> res = new ArrayList();
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = (PackageInfo) packs.get(i);
            if (getSysPackages || p.versionName != null) {
                GetInstalledApplication newInfo = new GetInstalledApplication();
                newInfo.appname = p.applicationInfo.loadLabel(context.getPackageManager()).toString();
                newInfo.pname = p.packageName;
                newInfo.versionName = p.versionName;
                newInfo.versionCode = p.versionCode;
                newInfo.icon = p.applicationInfo.loadIcon(context.getPackageManager());
                res.add(newInfo);
            }
        }
        return res;
    }

    private static void ListActiveApplications(Context context) {
        List<RunningServiceInfo> rs = ((ActivityManager) context.getSystemService("activity")).getRunningServices(50);
        int total_process = 0;
        Final_HTML += "Running Services/Application\n";
        int nt = 0;
        Final_HTML += "<tr>   <td>Srl no</td> <td>Process</td><td>Component</td><td>Process ID</td></tr>";
        int i = 0;
        while (i < rs.size()) {
            RunningServiceInfo rsi = (RunningServiceInfo) rs.get(i);
            TML_Library.Debug("Process " + rsi.process + " with component " + rsi.service.getClassName());
            Final_HTML += "<tr>   <td>" + i + "</td> <td>" + rsi.process + "</td><td>" + rsi.service.getClassName() + "</td><td>" + rsi.pid + "</td></tr>";
            total_process++;
            nt = i;
            i++;
        }
        for (RunningTaskInfo runningTaskInfo : ((ActivityManager) context.getSystemService("activity")).getRunningTasks(10)) {
            int id = runningTaskInfo.id;
            CharSequence desc = runningTaskInfo.description;
            String topActivity = runningTaskInfo.topActivity.getShortClassName();
            nt++;
            Final_HTML += "<tr>   <td>" + nt + "</td> <td>" + runningTaskInfo.topActivity.getPackageName() + "</td><td>" + topActivity + "</td><td>" + runningTaskInfo.numActivities + "</td></tr>";
        }
    }

    public void recursiveFileFind(File[] file1) {
        int i = 0;
        String filePath = "";
        if (file1 != null) {
            while (i != file1.length) {
                filePath = file1[i].getAbsolutePath();
                if (file1[i].isDirectory()) {
                    recursiveFileFind(file1[i].listFiles());
                }
                i++;
                TML_Library.Debug(filePath);
                Final_HTML += "<tr>   <td>" + filePath + "</td><td></td><td></td><td></td></tr>";
            }
        }
    }
}
