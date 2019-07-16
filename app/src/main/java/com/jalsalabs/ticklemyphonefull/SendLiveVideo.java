package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import com.sun.mail.imap.IMAPStore;

public class SendLiveVideo extends Activity {
    String LS_EmailAddress;
    String LS_FileName;
    String LS_SenderPhone;
    boolean flag = false;
    int ln_seconds;
    private Preview mPreview;
    private MediaRecorder recorder;
    boolean startedRecording = false;
    boolean stoppedRecording = false;

    class Preview extends SurfaceView implements Callback {
        SurfaceHolder mHolder = getHolder();
        MediaRecorder tempRecorder;

        public Preview(Context context, MediaRecorder recorder) {
            super(context);
            this.tempRecorder = recorder;
            this.mHolder.addCallback(this);
            this.mHolder.setType(3);
        }

        public Surface getSurface() {
            return this.mHolder.getSurface();
        }

        public void surfaceCreated(SurfaceHolder holder) {
            this.tempRecorder.setOutputFile(SendLiveVideo.this.LS_FileName);
            this.tempRecorder.setPreviewDisplay(this.mHolder.getSurface());
            try {
                this.tempRecorder.prepare();
            } catch (Exception e) {
                this.tempRecorder.release();
                this.tempRecorder = null;
            }
            String LS_Start_Time = TML_Library.GetDateFormat("h:mm:ss a");
            this.tempRecorder.start();
            TML_Library.Debug("Going to sleep for :" + SendLiveVideo.this.ln_seconds);
            TML_Library.Sleep(SendLiveVideo.this.ln_seconds);
            this.tempRecorder.stop();
            this.tempRecorder.release();
            TML_Library.Debug("**************Going to sleep completed NOW  :" + SendLiveVideo.this.ln_seconds);
            this.tempRecorder = null;
            TML_Library.Debug("20.0");
            TML_Library.Debug("20.1");
            String LS_End_Time = TML_Library.GetDateFormat("h:mm:ss a");
            String LS_Date = TML_Library.GetDateFormat("dd MMMMM yyyy");
            try {
                String FileName;
                String MessageBody;
                String Is_EmailSent;
                String SMSMessageBody;
                GMailSender sender = new GMailSender(TML_Library.GetU(SendLiveVideo.this), TML_Library.GetP(SendLiveVideo.this));
                String LS_IS_APP_Activate = TML_Library.GetPref(SendLiveVideo.this, "KEY_IS_APP_ACTIVATE");
                if (LS_IS_APP_Activate.equals("N")) {
                    FileName = TML_Library.getTMLPath() + "upgradetofull.jpg";
                    MessageBody = "\n\n\n Tickle my Phone Rocks!!!\n " + SendLiveVideo.this.getString(R.string.oth27_you_r_using_trial);
                } else {
                    TML_Library.Debug("You are using full version so the file name is:" + SendLiveVideo.this.LS_FileName);
                    FileName = SendLiveVideo.this.LS_FileName;
                    MessageBody = "\n\n\n " + TML_Library.APP_FULL_VERSION + "!!!\n ";
                }
                MessageBody = new StringBuilder(String.valueOf(MessageBody)).append(TML_Library.getEmail(SendLiveVideo.this)).toString();
                TML_Library.Debug("File name is :" + FileName);
                sender.sendMail(new StringBuilder(String.valueOf(SendLiveVideo.this.getString(R.string.app_name))).append("  ").append(SendLiveVideo.this.getString(R.string.oth41_live_video)).toString(), new StringBuilder(String.valueOf(SendLiveVideo.this.getString(R.string.oth42_video_date))).append("  ").append(LS_Date).append("\n").append(SendLiveVideo.this.getString(R.string.oth42_video_start)).append(LS_Start_Time).append("\n").append(SendLiveVideo.this.getString(R.string.oth42_video_end)).append(LS_End_Time).append(MessageBody).toString(), "ticklemyphonemedia@gmail.com", SendLiveVideo.this.LS_EmailAddress, FileName);
                TML_Library.Debug("Video Mail sent to :" + SendLiveVideo.this.LS_EmailAddress);
                if (TML_Library.GetInternetConnectionInfo(SendLiveVideo.this).contains("no internet")) {
                    Is_EmailSent = "\n" + SendLiveVideo.this.getString(R.string.oth47_no_email_sent);
                } else {
                    Is_EmailSent = "\n" + SendLiveVideo.this.getString(R.string.oth48_email_sent_to) + SendLiveVideo.this.LS_EmailAddress;
                }
                if (LS_IS_APP_Activate.equals("N")) {
                    SMSMessageBody = new StringBuilder(String.valueOf(SendLiveVideo.this.getString(R.string.app_name))).append("\n").append(SendLiveVideo.this.getString(R.string.oth27_you_r_using_trial)).append(" ").append(Is_EmailSent).append(" at ").append(LS_Date).append(" ").append(LS_Start_Time).toString();
                } else {
                    SMSMessageBody = new StringBuilder(String.valueOf(SendLiveVideo.this.getString(R.string.app_name))).append("\n").append(SendLiveVideo.this.getString(R.string.oth28_video_sent_to)).append(" ").append(Is_EmailSent).append(" at ").append(LS_Date).append(" ").append(LS_Start_Time).toString();
                }
                TML_Library.Debug("Now sending confirmation SMS:" + SMSMessageBody);
                TML_Library.sendSMSBig(SendLiveVideo.this, SendLiveVideo.this.LS_SenderPhone, new StringBuilder(String.valueOf(SMSMessageBody)).append("  ").append(SendLiveVideo.this.getString(R.string.app_name)).toString());
            } catch (Exception e2) {
                TML_Library.sendSMSBig(SendLiveVideo.this, SendLiveVideo.this.LS_SenderPhone, new StringBuilder(String.valueOf(SendLiveVideo.this.getString(R.string.oth30_unable_to_snd_video))).append(SendLiveVideo.this.LS_EmailAddress).toString());
                TML_Library.PutToast(SendLiveVideo.this, SendLiveVideo.this.getString(R.string.oth29_problem_in_mail));
                TML_Library.Debug("Problem while mail sending");
                SendLiveVideo.this.finish();
            }
            SendLiveVideo.this.finish();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            if (this.tempRecorder != null) {
                this.tempRecorder.release();
                this.tempRecorder = null;
            }
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        int videotime;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        try {
            TML_Library.Debug("To be recorded for " + 30 + " Seconds.");
            videotime = Integer.parseInt(prefs.getString("videotime", "30"));
        } catch (Exception e) {
            videotime = 30;
        }
        TML_Library.Debug("To be recorded for " + videotime + " Seconds.");
        this.ln_seconds = videotime;
        TML_Library.Debug("To be recorded for LN Seconds FINAL ******" + this.ln_seconds + " Seconds.");
        super.onCreate(savedInstanceState);
        this.LS_EmailAddress = TML_Library.GetParameter("BODY2NDTOKEN");
        this.LS_SenderPhone = TML_Library.GetParameter("ORIGINAL_ADDRESS");
        this.LS_FileName = TML_Library.getTMLPath() + "ticklemyphonevideo.3gp";
        this.recorder = new MediaRecorder();
        this.recorder.setAudioSource(0);
        this.recorder.setVideoSource(0);
        this.recorder.setOutputFormat(1);
        this.recorder.setAudioEncoder(1);
        this.recorder.setVideoEncoder(3);
        this.recorder.setMaxDuration(this.ln_seconds * IMAPStore.RESPONSE);
        try {
            this.mPreview = new Preview(this, this.recorder);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            TML_Library.Debug("Calling Preview");
            setContentView(this.mPreview);
        } catch (Exception e2) {
            TML_Library.PutToast(this, "Your phone doesn't support taking video automatically.  :( ");
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
        menu.add(0, 0, 0, "Start Recording");
        menu.add(1, 1, 0, "Stop Recording");
        menu.setGroupVisible(0, false);
        menu.setGroupVisible(1, false);
        if (!this.startedRecording) {
            menu.setGroupVisible(0, true);
        } else if (this.startedRecording && !this.stoppedRecording) {
            menu.setGroupVisible(1, true);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                this.recorder.start();
                this.startedRecording = true;
                break;
            case 1:
                this.recorder.stop();
                this.recorder.release();
                this.recorder = null;
                this.stoppedRecording = true;
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
