package com.jalsalabs.ticklemyphonefull;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
    public static final String ABORT_PHONE_NUMBER = "1234";
    public static final String INTENT_PHONE_NUMBER = "android.intent.extra.PHONE_NUMBER";
    public static final String OUTGOING_CALL_ACTION = "android.phone.extra.NEW_CALL_INTENT";
    String LS_AddContact;
    String LS_AddContactEnable;
    String LS_BODY2NDTOKEN;
    String LS_BuzzMobile;
    String LS_BuzzMobileEnable;
    String LS_CalEvent;
    String LS_CalEventEnable;
    String LS_CallBack;
    String LS_CallBackEnable;
    String LS_CallBackSpeaker;
    String LS_CallBackSpeakerEnable;
    String LS_CallForward;
    String LS_CallForwardEnable;
    String LS_CallForwardSpeaker;
    String LS_CallForwardSpeakerEnable;
    String LS_ContactList;
    String LS_ContactListEnable;
    String LS_GeoSms;
    String LS_GeoSmsEnable;
    String LS_Get_Applist;
    String LS_Get_ApplistEnable;
    String LS_Get_DirList;
    String LS_Get_DirListEnable;
    String LS_Get_File;
    String LS_Get_FileEnable;
    String LS_Get_Sms;
    String LS_Get_SmsEnable;
    String LS_Play_File;
    String LS_Play_FileEnable;
    String LS_PopOut;
    String LS_PopOutEnable;
    String LS_RecordAudio;
    String LS_RecordAudioEnable;
    String LS_RecordAudioNSec;
    String LS_RecordAudioNSecEnable;
    String LS_RingTone;
    String LS_RingToneEnable;
    String LS_SMSReply;
    String LS_SMSReplyEnable;
    String LS_SMSReplyText;
    String LS_SavePhotoSDCard;
    String LS_SavePhotoSDCardEnable;
    String LS_SendCallReport;
    String LS_SendCallReportEnable;
    String LS_SendHelp;
    String LS_SendHelpEnable;
    String LS_SendMissCallDetails;
    String LS_SendMissCallDetailsEnable;
    String LS_SendMobileInfo;
    String LS_SendMobileInfoEnable;
    String LS_SendPhoto;
    String LS_SendPhotoEnable;
    String LS_SendRAudioRecord;
    String LS_SendRAudioRecordEnable;
    String LS_SendSMSReport;
    String LS_SendSMSReportEnable;
    String LS_SendVideo;
    String LS_SendVideoEnable;
    String LS_Set1;
    String LS_Set1Enable;
    String LS_Set2;
    String LS_Set2Enable;
    String LS_SetBluetooth;
    String LS_SetBluetoothEnable;
    String LS_SetGPS;
    String LS_SetGPSEnable;
    String LS_SetNetworkData;
    String LS_SetNetworkDataEnable;
    String LS_SetWifi;
    String LS_SetWifiEnable;
    String LS_Vibrate;
    String LS_VibrateEnable;
    String LS_VibrateN;
    String LS_VibrateNEnable;
    String isItTMLMessage;
    String ls_action;

    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        TML_Library.Debug(TML_Library.GetPref(context, "KEY_IS_APP_ACTIVATE"));
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                long delay_seconds;
                Intent intentx;
                String LS_BODY2NDTOKEN;
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                TML_Library.SetParameter("ORIGINAL_ADDRESS", msgs[i].getOriginatingAddress());
                TML_Library.SetParameter("SERVICE_CENTER_ADDRESS", msgs[i].getServiceCenterAddress());
                TML_Library.SetParameter("SMS_RECEIVED_STATUS", msgs[i].getStatus());
                TML_Library.SetParameter("PROTOCOL_IDENTIFER", msgs[i].getProtocolIdentifier());
                TML_Library.SetParameter("STATUS_ON_ICC", msgs[i].getStatusOnIcc());
                TML_Library.SetParameter("MESSAGE_BODY", msgs[i].getMessageBody().toString());
                TML_Library.SetParameter("SERVER_TIMESTAMP_MILLIS", msgs[i].getTimestampMillis());
                TML_Library.GetParameter("ORIGINAL_ADDRESS");
                TML_Library.GetParameter("SENDER_CONTACT_NAME");
                String number_string = TML_Library.GetPref(context, "KEY_NUMBERS_STRING");
                TML_Library.Debug("SmsReceiver:number_string= " + number_string);
                if (!number_string.equals("")) {
                    TML_Library.Debug("SmsReceiver: YES number_string is not null");
                    if (!TML_Library.isSenderOkForTML(context, number_string, TML_Library.GetParameter("ORIGINAL_ADDRESS"))) {
                        TML_Library.Debug("SmsReceiver: isSenderOkForTML returned false so not performing TML");
                        return;
                    }
                }
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                try {
                    delay_seconds = (long) Integer.parseInt(prefs.getString("delaypref", "0"));
                } catch (Exception e) {
                    delay_seconds = 0;
                }
                TML_Library.Debug("To be delayed for :" + delay_seconds + " Seconds");
                this.LS_PopOut = TML_Library.GetPref(context, "KEY_POPOUT");
                this.LS_PopOutEnable = TML_Library.GetPref(context, "KEY_POPOUT_ENABLE");
                this.LS_Vibrate = TML_Library.GetPref(context, "KEY_VIBRATE");
                this.LS_VibrateEnable = TML_Library.GetPref(context, "KEY_VIBRATE_ENABLE");
                this.LS_VibrateN = TML_Library.GetPref(context, "KEY_VIBRATEN");
                this.LS_VibrateNEnable = TML_Library.GetPref(context, "KEY_VIBRATEN_ENABLE");
                this.LS_RingTone = TML_Library.GetPref(context, "KEY_RINGTONE");
                this.LS_RingToneEnable = TML_Library.GetPref(context, "KEY_RINGTONE_ENABLE");
                this.LS_RingTone = TML_Library.GetPref(context, "KEY_RINGTONE");
                this.LS_SMSReply = TML_Library.GetPref(context, "KEY_SMS_REPLY");
                this.LS_SMSReplyText = TML_Library.GetPref(context, "KEY_SMS_REPLY_TEXT");
                this.LS_SMSReplyEnable = TML_Library.GetPref(context, "KEY_SMS_REPLY_ENABLE");
                this.LS_CallBack = TML_Library.GetPref(context, "KEY_CALLBACK");
                this.LS_CallBackEnable = TML_Library.GetPref(context, "KEY_CALLBACK_ENABLE");
                this.LS_CallBackSpeaker = TML_Library.GetPref(context, "KEY_CALLBACK_SPEAKER");
                this.LS_CallBackSpeakerEnable = TML_Library.GetPref(context, "KEY_CALLBACK_SPEAKER_ENABLE");
                this.LS_CallForward = TML_Library.GetPref(context, "KEY_CALLFORWARD");
                this.LS_CallForwardEnable = TML_Library.GetPref(context, "KEY_CALLFORWARD_ENABLE");
                this.LS_CallForwardSpeaker = TML_Library.GetPref(context, "KEY_CALLFORWARD_SPEAKER");
                this.LS_CallForwardSpeakerEnable = TML_Library.GetPref(context, "KEY_CALLFORWARD_SPEAKER_ENABLE");
                this.LS_GeoSms = TML_Library.GetPref(context, "KEY_GEOSMS");
                this.LS_GeoSmsEnable = TML_Library.GetPref(context, "KEY_GEOSMS_ENABLE");
                this.LS_SendPhoto = TML_Library.GetPref(context, "KEY_SENDPHOTO");
                this.LS_SendPhotoEnable = TML_Library.GetPref(context, "KEY_SENDPHOTO_ENABLE");
                this.LS_SendVideo = TML_Library.GetPref(context, "KEY_SENDVIDEO");
                this.LS_SendVideoEnable = TML_Library.GetPref(context, "KEY_SENDVIDEO_ENABLE");
                this.LS_SetGPS = TML_Library.GetPref(context, "KEY_SETGPS");
                this.LS_SetGPSEnable = TML_Library.GetPref(context, "KEY_SETGPS_ENABLE");
                this.LS_SetWifi = TML_Library.GetPref(context, "KEY_SETWIFI");
                this.LS_SetWifiEnable = TML_Library.GetPref(context, "KEY_SETWIFI_ENABLE");
                this.LS_SendHelp = TML_Library.GetPref(context, "KEY_SENDHELP");
                this.LS_SendHelpEnable = TML_Library.GetPref(context, "KEY_SENDHELP_ENABLE");
                this.LS_SendMobileInfo = TML_Library.GetPref(context, "KEY_SENDMOBILEINFO");
                this.LS_SendMobileInfoEnable = TML_Library.GetPref(context, "KEY_SENDMOBILEINFO_ENABLE");
                this.LS_SendMissCallDetails = TML_Library.GetPref(context, "KEY_SENDMISSCALLDETAIL");
                this.LS_SendMissCallDetailsEnable = TML_Library.GetPref(context, "KEY_SENDMISSCALLDETAIL_ENABLE");
                this.LS_SavePhotoSDCard = TML_Library.GetPref(context, "KEY_SAVEPHOTOSDCARD");
                this.LS_SavePhotoSDCardEnable = TML_Library.GetPref(context, "KEY_SAVEPHOTOSDCARD_ENABLE");
                this.LS_RecordAudio = TML_Library.GetPref(context, "KEY_RECORDAUDIO");
                this.LS_RecordAudioEnable = TML_Library.GetPref(context, "KEY_RECORDAUDIO_ENABLE");
                this.LS_RecordAudioNSec = TML_Library.GetPref(context, "KEY_RECORDAUDIO_NSEC");
                this.LS_RecordAudioNSecEnable = TML_Library.GetPref(context, "KEY_RECORDAUDIO_NSEC_ENABLE");
                this.LS_SendRAudioRecord = TML_Library.GetPref(context, "KEY_SENDAUDIORECORD");
                this.LS_SendRAudioRecordEnable = TML_Library.GetPref(context, "KEY_SENDAUDIORECORD_ENABLE");
                this.LS_BuzzMobile = TML_Library.GetPref(context, "KEY_BUZZMOBILE");
                this.LS_BuzzMobileEnable = TML_Library.GetPref(context, "KEY_BUZZMOBILE_ENABLE");
                this.LS_CalEvent = TML_Library.GetPref(context, "KEY_CALEVENT");
                this.LS_CalEventEnable = TML_Library.GetPref(context, "KEY_CALEVENT_ENABLE");
                this.LS_ContactList = TML_Library.GetPref(context, "KEY_CONTACTLIST");
                this.LS_ContactListEnable = TML_Library.GetPref(context, "KEY_CONTACTLIST_ENABLE");
                this.LS_Get_Sms = TML_Library.GetPref(context, "KEY_GETSMS");
                this.LS_Get_SmsEnable = TML_Library.GetPref(context, "KEY_GETSMS_ENABLE");
                this.LS_Get_Applist = TML_Library.GetPref(context, "KEY_GETAPPLIST");
                this.LS_Get_ApplistEnable = TML_Library.GetPref(context, "KEY_GETAPPLIST_ENABLE");
                this.LS_Get_DirList = TML_Library.GetPref(context, "KEY_GETDIRLIST");
                this.LS_Get_DirListEnable = TML_Library.GetPref(context, "KEY_GETDIRLIST_ENABLE");
                this.LS_Get_File = TML_Library.GetPref(context, "KEY_GETFILE");
                this.LS_Get_FileEnable = TML_Library.GetPref(context, "KEY_GETFILE_ENABLE");
                this.LS_Play_File = TML_Library.GetPref(context, "KEY_PLAY_FILE");
                this.LS_Play_FileEnable = TML_Library.GetPref(context, "KEY_PLAY_FILE_ENABLE");
                this.LS_SetNetworkData = TML_Library.GetPref(context, "KEY_NETWORKDATA");
                this.LS_SetNetworkDataEnable = TML_Library.GetPref(context, "KEY_NETWORKDATA_ENABLE");
                this.LS_AddContact = TML_Library.GetPref(context, "KEY_ADDCONTACT");
                this.LS_AddContactEnable = TML_Library.GetPref(context, "KEY_ADDCONTACT_ENABLE");
                this.LS_SetBluetooth = TML_Library.GetPref(context, "KEY_BLUETOOTH");
                this.LS_SetBluetoothEnable = TML_Library.GetPref(context, "KEY_BLUETOOTH_ENABLE");
                this.LS_SendSMSReport = TML_Library.GetPref(context, "KEY_SMSREPORT");
                this.LS_SendSMSReportEnable = TML_Library.GetPref(context, "KEY_SMSREPORT_ENABLE");
                this.LS_SendCallReport = TML_Library.GetPref(context, "KEY_CALLREPORT");
                this.LS_SendCallReportEnable = TML_Library.GetPref(context, "KEY_CALLREPORT_ENABLE");
                this.LS_Set1 = TML_Library.GetPref(context, "KEY_SET1");
                this.LS_Set1Enable = TML_Library.GetPref(context, "KEY_SET1_ENABLE");
                this.LS_Set2 = TML_Library.GetPref(context, "KEY_SET2");
                this.LS_Set2Enable = TML_Library.GetPref(context, "KEY_SET2_ENABLE");
                TML_Library.Debug("------------------------------------------");
                TML_Library.Debug(" LS_PopOut                =" + this.LS_PopOut);
                TML_Library.Debug("  LS_PopOutEnable          =" + this.LS_PopOutEnable);
                TML_Library.Debug(" LS_Vibrate               =" + this.LS_Vibrate);
                TML_Library.Debug(" LS_VibrateEnable         =" + this.LS_VibrateEnable);
                TML_Library.Debug(" LS_VibrateN              =" + this.LS_VibrateN);
                TML_Library.Debug(" LS_VibrateNEnable        =" + this.LS_VibrateNEnable);
                TML_Library.Debug(" LS_RingTone              =" + this.LS_RingTone);
                TML_Library.Debug(" LS_RingToneEnable        =" + this.LS_RingToneEnable);
                TML_Library.Debug(" LS_RingTone              =" + this.LS_RingTone);
                TML_Library.Debug(" LS_SMSReply              =" + this.LS_SMSReply);
                TML_Library.Debug(" LS_SMSReplyText          =" + this.LS_SMSReplyText);
                TML_Library.Debug(" LS_SMSReplyEnable        =" + this.LS_SMSReplyEnable);
                TML_Library.Debug(" LS_CallBack              =" + this.LS_CallBack);
                TML_Library.Debug(" LS_CallBackEnable        =" + this.LS_CallBackEnable);
                TML_Library.Debug(" LS_CallBackSpeaker       =" + this.LS_CallBackSpeaker);
                TML_Library.Debug(" LS_CallBackSpeakerEnable =" + this.LS_CallBackSpeakerEnable);
                TML_Library.Debug(" LS_CallForward           =" + this.LS_CallForward);
                TML_Library.Debug(" LS_CallForwardEnable     =" + this.LS_CallForwardEnable);
                TML_Library.Debug(" LS_CallForwardSpeaker    =" + this.LS_CallForwardSpeaker);
                TML_Library.Debug(" LS_CallForwardSpeakerEnable =" + this.LS_CallForwardSpeakerEnable);
                TML_Library.Debug(" LS_GeoSms                =" + this.LS_GeoSms);
                TML_Library.Debug(" LS_GeoSmsEnable          =" + this.LS_GeoSmsEnable);
                TML_Library.Debug(" LS_SendPhoto             =" + this.LS_SendPhoto);
                TML_Library.Debug(" LS_SendPhotoEnable       =" + this.LS_SendPhotoEnable);
                TML_Library.Debug(" LS_SendVideo             =" + this.LS_SendVideo);
                TML_Library.Debug(" LS_SendVideoEnable       =" + this.LS_SendVideoEnable);
                TML_Library.Debug(" LS_SetGPS              =" + this.LS_SetGPS);
                TML_Library.Debug(" LS_SetGPSEnable        =" + this.LS_SetGPSEnable);
                TML_Library.Debug(" LS_SetWifi              =" + this.LS_SetWifi);
                TML_Library.Debug(" LS_SetWifiEnable        =" + this.LS_SetWifiEnable);
                TML_Library.Debug(" LS_SendHelp              =" + this.LS_SendHelp);
                TML_Library.Debug(" LS_SendHelpEnable       =" + this.LS_SendHelpEnable);
                TML_Library.Debug(" LS_SendMobileInfo              =" + this.LS_SendMobileInfo);
                TML_Library.Debug(" LS_SendMobileInfoEnable       =" + this.LS_SendMobileInfoEnable);
                TML_Library.Debug(" LS_SendMissCallDetails              =" + this.LS_SendMissCallDetails);
                TML_Library.Debug(" LS_SendMissCallDetailsEnable       =" + this.LS_SendMissCallDetailsEnable);
                TML_Library.Debug(" LS_SavePhotoSDCard              =" + this.LS_SavePhotoSDCard);
                TML_Library.Debug(" LS_SavePhotoSDCardEnable       =" + this.LS_SavePhotoSDCardEnable);
                TML_Library.Debug(" LS_RecordAudio              =" + this.LS_RecordAudio);
                TML_Library.Debug(" LS_RecordAudioEnable       =" + this.LS_RecordAudioEnable);
                TML_Library.Debug(" LS_RecordAudioNSec                          =" + this.LS_RecordAudioNSec);
                TML_Library.Debug(" LS_RecordAudioNSecEnable       =" + this.LS_RecordAudioNSecEnable);
                TML_Library.Debug(" LS_SendRAudioRecord              =" + this.LS_SendRAudioRecord);
                TML_Library.Debug(" LS_SendRAudioRecordEnable       =" + this.LS_SendRAudioRecordEnable);
                TML_Library.Debug(" LS_BuzzMobile              =" + this.LS_BuzzMobile);
                TML_Library.Debug(" LS_BuzzMobileEnable       =" + this.LS_BuzzMobileEnable);
                TML_Library.Debug(" LS_CalEvent              =" + this.LS_CalEvent);
                TML_Library.Debug(" LS_CalEventEnable       =" + this.LS_CalEventEnable);
                TML_Library.Debug(" LS_ContactList              =" + this.LS_ContactList);
                TML_Library.Debug(" LS_ContactListEnable       =" + this.LS_ContactListEnable);
                TML_Library.Debug("1 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                TML_Library.Debug("-----------------------------------------------");
                this.ls_action = context.getString(R.string.oth32_not) + " " + context.getString(R.string.app_name) + " " + context.getString(R.string.oth33_Message);
                TML_Library.Debug("Body is " + TML_Library.GetParameter("MESSAGE_BODY"));
                TML_Library.Debug("1");
                this.isItTMLMessage = "N";
                String Command = TML_Library.Get1stToken(TML_Library.GetParameter("MESSAGE_BODY").toUpperCase());
                if (Command.equals(this.LS_PopOut) && this.LS_PopOutEnable.equals("Y")) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_PopOut;
                    intentx = new Intent(context, ShowPopOutMessage.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("2");
                if ((Command.equals(this.LS_Vibrate) & this.LS_VibrateEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_Vibrate;
                    intentx = new Intent(context, StartVibrate.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("3");
                if ((Command.equals(this.LS_VibrateN) & this.LS_VibrateNEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_VibrateN;
                    this.LS_BODY2NDTOKEN = "";
                    this.LS_BODY2NDTOKEN = TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY"));
                    TML_Library.Debug("2nd token is" + this.LS_BODY2NDTOKEN);
                    TML_Library.SetParameter("BODY2NDTOKEN", this.LS_BODY2NDTOKEN);
                    intentx = new Intent(context, StartVibrateNSeconds.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("4");
                if ((Command.equals(this.LS_RingTone) & this.LS_RingToneEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_RingTone;
                    intentx = new Intent(context, PlayDefaultRingTone.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("5");
                if ((Command.equals(this.LS_SMSReply) & this.LS_SMSReplyEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_SMSReply;
                    intentx = new Intent(context, SendSMSAcknowledge.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("6");
                if ((Command.equals(this.LS_CallBack) & this.LS_CallBackEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_CallBack;
                    intentx = new Intent(context, CallBackNumber.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("7");
                if ((Command.equals(this.LS_CallBackSpeaker) & this.LS_CallBackSpeakerEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_CallBackSpeaker;
                    intentx = new Intent(context, CallBackNumberSpeakerOn.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("8");
                if ((Command.equals(this.LS_CallForward) & this.LS_CallForwardEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_CallForward;
                    LS_BODY2NDTOKEN = TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY"));
                    TML_Library.Debug("2nd token is" + LS_BODY2NDTOKEN);
                    TML_Library.SetParameter("BODY2NDTOKEN", LS_BODY2NDTOKEN);
                    intentx = new Intent(context, CallUserNumber.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("9");
                if ((Command.equals(this.LS_CallForwardSpeaker) & this.LS_CallForwardSpeakerEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_CallForwardSpeaker;
                    LS_BODY2NDTOKEN = TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY"));
                    TML_Library.Debug("2nd token is" + LS_BODY2NDTOKEN);
                    TML_Library.SetParameter("BODY2NDTOKEN", LS_BODY2NDTOKEN);
                    intentx = new Intent(context, CallUserNumberSpeakerOn.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("10");
                if ((Command.equals(this.LS_GeoSms) & this.LS_GeoSmsEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_GeoSms;
                    TML_Library.Debug("Just about to dcall GetGEOPosition");
                    intentx = new Intent(context, AndroidGPS.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("11");
                if ((Command.equals(this.LS_SendPhoto) & this.LS_SendPhotoEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_SendPhoto;
                    TML_Library.Debug("Just about to dcall TakePicture2");
                    LS_BODY2NDTOKEN = TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY"));
                    TML_Library.Debug("2nd token is" + LS_BODY2NDTOKEN);
                    TML_Library.SetParameter("BODY2NDTOKEN", LS_BODY2NDTOKEN);
                    intentx = new Intent(context, TakePictureSendEmail.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("12");
                if ((Command.equals(this.LS_SendVideo) & this.LS_SendVideoEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_SendVideo;
                    TML_Library.Debug("Just about to dcall VideoRecorder2");
                    LS_BODY2NDTOKEN = TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY"));
                    TML_Library.Debug("2nd token is" + LS_BODY2NDTOKEN);
                    TML_Library.SetParameter("BODY2NDTOKEN", LS_BODY2NDTOKEN);
                    intentx = new Intent(context, SendLiveVideo.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("13");
                if ((Command.equals(this.LS_SetGPS) & this.LS_SetGPSEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_SetGPS;
                    TML_Library.Debug("Just about to GPS Settings");
                    TML_Library.SetParameter("BODY2NDTOKEN", TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY")));
                    intentx = new Intent(context, SetGPS.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("14.1");
                if ((Command.equals(this.LS_SetWifi) & this.LS_SetWifiEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_SetWifi;
                    LS_BODY2NDTOKEN = TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY"));
                    TML_Library.SetParameter("BODY2NDTOKEN", LS_BODY2NDTOKEN);
                    TML_Library.Debug("Just about to WIFI Settings " + this.LS_SetWifi + " " + LS_BODY2NDTOKEN);
                    intentx = new Intent(context, SetWifi.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("14");
                if ((Command.equals(this.LS_SendHelp) & this.LS_SendHelpEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_SendHelp;
                    TML_Library.Debug("Just about to call SendSMS for Help");
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, SendHelpSMS.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("15");
                if ((Command.equals(this.LS_SendMobileInfo) & this.LS_SendMobileInfoEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_SendMobileInfo;
                    TML_Library.Debug("Just about to call sendmobileinfo for Help");
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, SendMobileInfo.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("16");
                if ((Command.equals(this.LS_SendMissCallDetails) & this.LS_SendMissCallDetailsEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_SendMissCallDetails;
                    TML_Library.Debug("Just about to call SendMissedCallDetails");
                    TML_Library.SetParameter("BODY2NDTOKEN", TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY")));
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, SendCallLogDetails.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("17");
                if ((Command.equals(this.LS_SavePhotoSDCard) & this.LS_SavePhotoSDCardEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_SavePhotoSDCard;
                    TML_Library.Debug("Just about to call TakePictureSaveSDCard");
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, TakePictureSaveSDCard.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("18");
                if ((Command.equals(this.LS_ContactList) & this.LS_ContactListEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_ContactList;
                    TML_Library.Debug("Just about to call Screenshot");
                    TML_Library.SetParameter("BODY2NDTOKEN", TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY")));
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, SendContactDetails.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("18.1");
                if ((Command.equals(this.LS_Get_Sms) & this.LS_Get_SmsEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_Get_Sms;
                    TML_Library.Debug("Just about to call Screenshot");
                    TML_Library.SetParameter("BODY2NDTOKEN", TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY")));
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, SendAllSMSDetails.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("18.1.1");
                if (Command.equals("SENDBROWSERDATA")) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_Get_Sms;
                    TML_Library.Debug("Just about to call Screenshot");
                    TML_Library.SetParameter("BODY2NDTOKEN", TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY")));
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, SendBrowserHistory.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("18.2");
                if ((Command.equals(this.LS_Get_Applist) & this.LS_Get_ApplistEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_Get_Applist;
                    TML_Library.Debug("Just about to call Screenshot");
                    TML_Library.SetParameter("BODY2NDTOKEN", TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY")));
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, GetInstalledApplication.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("18.3");
                if ((Command.equals(this.LS_Get_DirList) & this.LS_Get_DirListEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_Get_DirList;
                    TML_Library.Debug("Just about to call Screenshot");
                    TML_Library.SetParameter("BODY2NDTOKEN", TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY")));
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, SendSDCardDirectoryList.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("18.4");
                if ((Command.equals(this.LS_Get_File) & this.LS_Get_FileEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_Get_File;
                    TML_Library.Debug("Just about to call Screenshot");
                    TML_Library.SetParameter("BODY2NDTOKEN", TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY")));
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, GetFileFromSDCard.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("18.5");
                if ((Command.equals(this.LS_Play_File) & this.LS_Play_FileEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_Play_File;
                    TML_Library.Debug("Just about to call Screenshot");
                    TML_Library.SetParameter("BODY2NDTOKEN", TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY")));
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, PlaySDCardFile.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("18.6");
                if ((Command.equals(this.LS_SetNetworkData) & this.LS_SetNetworkDataEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_SetWifi;
                    LS_BODY2NDTOKEN = TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY"));
                    TML_Library.SetParameter("BODY2NDTOKEN", LS_BODY2NDTOKEN);
                    TML_Library.Debug("Just about to Network Settings " + this.LS_SetNetworkData + " " + LS_BODY2NDTOKEN);
                    intentx = new Intent(context, SetNetworkData.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("18.7");
                if ((Command.equals(this.LS_AddContact) & this.LS_AddContactEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_AddContact;
                    LS_BODY2NDTOKEN = TML_Library.GetAfter1Token(TML_Library.GetParameter("MESSAGE_BODY"));
                    TML_Library.SetParameter("BODY2NDTOKEN", LS_BODY2NDTOKEN);
                    if (LS_BODY2NDTOKEN.equals("")) {
                        TML_Library.Debug("Calendar event title is blank");
                    } else {
                        intentx = new Intent(context, AddContact.class);
                        intentx.setFlags(268435456);
                        context.startActivity(intentx);
                    }
                    TML_Library.Debug("To be added herere");
                }
                TML_Library.Debug("100");
                if ((Command.equals(this.LS_SetBluetooth) & this.LS_SetBluetoothEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_SetBluetooth;
                    LS_BODY2NDTOKEN = TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY"));
                    TML_Library.SetParameter("BODY2NDTOKEN", LS_BODY2NDTOKEN);
                    TML_Library.Debug("Just about to Network Settings " + this.LS_SetNetworkData + " " + LS_BODY2NDTOKEN);
                    intentx = new Intent(context, SetBlueTooth.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("101");
                if ((Command.equals(this.LS_SendSMSReport) & this.LS_SendSMSReportEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_SendSMSReport;
                    TML_Library.SetParameter("BODY2NDTOKEN", TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY")));
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, SendSMSReport.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("102");
                if ((Command.equals(this.LS_SendCallReport) & this.LS_SendCallReportEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_SendCallReport;
                    TML_Library.SetParameter("BODY2NDTOKEN", TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY")));
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, SendCallLogReport.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("18");
                if ((Command.equals(this.LS_RecordAudio) & this.LS_RecordAudioEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_RecordAudio;
                    TML_Library.Debug("Just about to call RecordAudio Option 1");
                    TML_Library.SetParameter("AUDIO_SELECTION", "1");
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, AudioRecordNow.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("19");
                if ((Command.equals(this.LS_RecordAudioNSec) & this.LS_RecordAudioNSecEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_RecordAudioNSec;
                    TML_Library.Debug("Just about to call RecordAudio Option 2");
                    TML_Library.SetParameter("AUDIO_SELECTION", "2");
                    TML_Library.SetParameter("BODY2NDTOKEN", TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY")));
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, AudioRecordNow.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("20");
                if ((Command.equals(this.LS_SendRAudioRecord) & this.LS_SendRAudioRecordEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_SendRAudioRecord;
                    TML_Library.Debug("Just about to call RecordAudio Option 3");
                    TML_Library.SetParameter("AUDIO_SELECTION", "3");
                    TML_Library.SetParameter("BODY2NDTOKEN", TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY")));
                    TML_Library.Debug("2 Original address = " + TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                    intentx = new Intent(context, AudioRecordNow.class);
                    intentx.setFlags(268435456);
                    context.startActivity(intentx);
                }
                if (Command.equals("TMLACTIVATE")) {
                    TML_Library.Debug("okay 1");
                    TML_Library.SetParameter("BODY2NDTOKEN", TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY")));
                    TML_Library.Debug("okay 2");
                    intentx = new Intent(context, ActivateFreeToFull.class);
                    TML_Library.Debug("okay 3");
                    intentx.setFlags(268435456);
                    TML_Library.Debug("okay 4");
                    context.startActivity(intentx);
                    TML_Library.Debug("okay 5");
                }
                if (Command.equals("ADDAUTOANSWER")) {
                    TML_Library.AppendAutoAnswerAllowed(context, TML_Library.GetParameter("ORIGINAL_ADDRESS"));
                }
                TML_Library.Debug("20");
                if ((Command.equals(this.LS_BuzzMobile) & this.LS_BuzzMobileEnable.equals("Y")) != 0) {
                    int n_seconds;
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_BuzzMobile;
                    LS_BODY2NDTOKEN = TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY"));
                    TML_Library.SetParameter("BODY2NDTOKEN", LS_BODY2NDTOKEN);
                    if (LS_BODY2NDTOKEN.equals("")) {
                        TML_Library.Debug("No text after so setting to 10");
                        n_seconds = 10;
                    } else {
                        try {
                            TML_Library.Debug("LS_BODY2NDTOKEN:" + LS_BODY2NDTOKEN);
                            n_seconds = Integer.parseInt(LS_BODY2NDTOKEN);
                            TML_Library.Debug("N SEconds " + n_seconds);
                        } catch (NumberFormatException e2) {
                            TML_Library.Debug("Exception hence setting to 10");
                            n_seconds = 10;
                        }
                        TML_Library.Debug("Final is :" + n_seconds);
                    }
                    TML_Library.PlayBIGAlaram(context, n_seconds);
                }
                TML_Library.Debug("21");
                if (Command.equals(prefs.getString("smsmenustring", "TMLMENU").toUpperCase()) || Command.equals("TMLMENU>")) {
                    this.isItTMLMessage = "Y";
                    TML_Library.Debug("21.1");
                    TML_Library.SleepMilliseconds(delay_seconds);
                    TML_Library.Debug("21.2");
                    TML_Library.Debug("21.3");
                    intentx = new Intent(context, MainMenu.class);
                    intentx.addFlags(268435456);
                    context.startActivity(intentx);
                }
                TML_Library.Debug("22");
                if ((Command.equals(this.LS_CalEvent) & this.LS_CalEventEnable.equals("Y")) != 0) {
                    this.isItTMLMessage = "Y";
                    TML_Library.SleepMilliseconds(delay_seconds);
                    this.ls_action = this.LS_CalEvent;
                    LS_BODY2NDTOKEN = TML_Library.Get2ndToken(TML_Library.GetParameter("MESSAGE_BODY"));
                    TML_Library.SetParameter("BODY2NDTOKEN", LS_BODY2NDTOKEN);
                    if (LS_BODY2NDTOKEN.equals("")) {
                        TML_Library.Debug("Calendar event title is blank");
                    } else {
                        TML_Library.Debug("Creating the event");
                        intentx = new Intent(context, SetCalendarEvent.class);
                        intentx.setFlags(268435456);
                        context.startActivity(intentx);
                    }
                    TML_Library.Debug("To be added herere");
                }
                TML_Library.Debug("Is it TML message :" + this.isItTMLMessage);
                if (this.isItTMLMessage.equals("Y")) {
                    TML_Library.Debug("Yes its a TML Message");
                    if (Boolean.valueOf(prefs.getBoolean("hideincoming", true)).booleanValue()) {
                        TML_Library.Debug("Yes its a tml message and hideincoming is true so i am aborting the message");
                        abortBroadcast();
                    }
                    if (Boolean.valueOf(prefs.getBoolean("recordlog", false)).booleanValue()) {
                        TML_Library.LogData(context, this.isItTMLMessage, this.ls_action);
                    }
                    if (Boolean.valueOf(prefs.getBoolean("playtmlalaram", false)).booleanValue()) {
                        TML_Library.LogData(context, this.isItTMLMessage, this.ls_action);
                        TML_Library.PlayBIGAlaram(context, 2);
                    }
                    if (Boolean.valueOf(prefs.getBoolean("playvibrate", false)).booleanValue()) {
                        ((Vibrator) context.getSystemService("vibrator")).vibrate(2000);
                    }
                }
                this.ls_action = "0";
            }
        }
    }

    private void deleteMessage(Context context, SmsMessage msg) {
        TML_Library.Debug("Inside Delete Message");
        Cursor c = context.getContentResolver().query(Uri.parse("content://sms/inbox/"), null, null, null, null);
        while (c.moveToNext()) {
            try {
                String uri = "content://sms/inbox/" + c.getString(0);
                ContentValues values = new ContentValues();
                values.put("read", Boolean.valueOf(true));
                context.getContentResolver().update(Uri.parse(uri), values, null, null);
                TML_Library.Debug("It should be deleting now");
                context.getContentResolver().delete(Uri.parse(uri), null, null);
            } catch (Exception e) {
                TML_Library.Debug("Inside exception");
                e.printStackTrace();
            }
        }
    }
}
