package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

public class TB_AlarmManagerActivity extends Activity {
    static final int TIME_DIALOG_ID_FROM = 0;
    private TB_AlarmManagerBroadcastReceiver alarm;
    EditText et_timebombtime;
    boolean isfree;
    private OnTimeSetListener mTimeSetListener = new OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            TB_AlarmManagerActivity.this.pHourFrom = hourOfDay;
            TB_AlarmManagerActivity.this.pMinuteFrom = minute;
            TB_AlarmManagerActivity.this.updateDisplay();
        }
    };
    private int pHourFrom;
    private int pMinuteFrom;

    private class TimeBombTask extends AsyncTask<String, String, String> {
        private TimeBombTask() {
        }

        /* synthetic */ TimeBombTask(TB_AlarmManagerActivity tB_AlarmManagerActivity, TimeBombTask timeBombTask) {
            this();
        }

        /* Access modifiers changed, original: protected|varargs */
        public String doInBackground(String... params) {
            TML_Library.TimeBombRun(TB_AlarmManagerActivity.this);
            return "Executed";
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(String result) {
            TML_Library.LoadStop(TB_AlarmManagerActivity.this);
            TML_Library.ShowAlertMessage(TB_AlarmManagerActivity.this, "Sample email processed.  Please check your Email inbox");
        }

        /* Access modifiers changed, original: protected */
        public void onPreExecute() {
            TML_Library.LoadStart(TB_AlarmManagerActivity.this, "Processing. Please wait...");
        }

        /* Access modifiers changed, original: protected|varargs */
        public void onProgressUpdate(String... values) {
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tb_activity_alarm_manager);
        if (TML_Library.GetPref(this, "KEY_IS_FREE").equals("Y")) {
            this.isfree = true;
        } else {
            this.isfree = false;
        }
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.tb_activity_alarm_manager);
        final ImageView iv_timebombactive = (ImageView) findViewById(R.id.iv_timebombactive);
        this.et_timebombtime = (EditText) findViewById(R.id.et_timebombtime);
        final EditText et_timebombemail = (EditText) findViewById(R.id.et_timebombemail);
        final CheckBox cb_allsmsinbox = (CheckBox) findViewById(R.id.cb_allsmsinbox);
        final CheckBox cb_allsmssent = (CheckBox) findViewById(R.id.cb_allsmssent);
        final CheckBox cb_allincomingcall = (CheckBox) findViewById(R.id.cb_allincomingcall);
        final CheckBox cb_alloutgoingcall = (CheckBox) findViewById(R.id.cb_alloutgoingcall);
        final CheckBox cb_allmissedcall = (CheckBox) findViewById(R.id.cb_allmissedcall);
        Button bt_timepick = (Button) findViewById(R.id.bt_timepick);
        Button bt_cancel = (Button) findViewById(R.id.bt_cancel);
        Button bt_save = (Button) findViewById(R.id.bt_save);
        Button BT_sendsampleemail = (Button) findViewById(R.id.BT_sendsampleemail);
        this.alarm = new TB_AlarmManagerBroadcastReceiver();
        Button btStart = (Button) findViewById(R.id.btStart);
        Button btCancel = (Button) findViewById(R.id.btCancel);
        String isTimeBombON = TML_Library.GetPrefNew(this, "IS_TIME_BOMB_ON", "N");
        this.et_timebombtime.setText(TML_Library.GetPrefNew(this, "TIME_BOMB_TIME_HR", "9") + ":" + TML_Library.GetPrefNew(this, "TIME_BOMB_TIME_MIN", "00"));
        et_timebombemail.setText(TML_Library.GetPrefNew(this, "TIME_BOMB_EMAIL", ""));
        if (isTimeBombON.equals("Y")) {
            iv_timebombactive.setImageResource(R.drawable.on_btn);
            iv_timebombactive.setTag("on");
        } else {
            iv_timebombactive.setImageResource(R.drawable.off_btn);
            iv_timebombactive.setTag("off");
        }
        if (TML_Library.GetPrefNew(this, "TB_ALL_SMS_INBOX", "N").equals("Y")) {
            cb_allsmsinbox.setChecked(true);
        } else {
            cb_allsmsinbox.setChecked(false);
        }
        TML_Library.Debug("TB_ALL_SMS_SENT=" + TML_Library.GetPrefNew(this, "TB_ALL_SMS_SENT", "N"));
        if (TML_Library.GetPrefNew(this, "TB_ALL_SMS_SENT", "N").equals("Y")) {
            cb_allsmssent.setChecked(true);
        } else {
            cb_allsmssent.setChecked(false);
        }
        if (TML_Library.GetPrefNew(this, "TB_ALL_INCOMING_CALL_LOG", "N").equals("Y")) {
            cb_allincomingcall.setChecked(true);
        } else {
            cb_allincomingcall.setChecked(false);
        }
        if (TML_Library.GetPrefNew(this, "TB_ALL_OUTGOING_CALL_LOG", "N").equals("Y")) {
            cb_alloutgoingcall.setChecked(true);
        } else {
            cb_alloutgoingcall.setChecked(false);
        }
        if (TML_Library.GetPrefNew(this, "TB_ALL_MISSED_CALL_LOG", "N").equals("Y")) {
            cb_allmissedcall.setChecked(true);
        } else {
            cb_allmissedcall.setChecked(false);
        }
        btStart.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TB_AlarmManagerActivity.this.startRepeatingTimer(TB_AlarmManagerActivity.this, 9, 30);
            }
        });
        btStart.setText("Start :" + isTimeBombON);
        iv_timebombactive.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String ImageTag = (String) iv_timebombactive.getTag();
                TML_Library.Debug("Image name = " + ImageTag);
                if (ImageTag.equals("off")) {
                    TML_Library.Debug("Its off.   setting to on");
                    iv_timebombactive.setImageResource(R.drawable.on_btn);
                    iv_timebombactive.setTag("on");
                    return;
                }
                TML_Library.Debug("Its ON.   setting to OFF");
                iv_timebombactive.setImageResource(R.drawable.off_btn);
                iv_timebombactive.setTag("off");
            }
        });
        bt_save.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (TB_AlarmManagerActivity.this.isfree) {
                    TML_Library.PutToast(TB_AlarmManagerActivity.this, "Time Bomb is only available for Paid Version!");
                    TML_Library.ShowWantFullVersion(TB_AlarmManagerActivity.this);
                    return;
                }
                String TimeBombTime = TB_AlarmManagerActivity.this.et_timebombtime.getText().toString();
                String TimeBombEmail = et_timebombemail.getText().toString();
                if (TimeBombTime.length() == 0 || TimeBombEmail.length() == 0) {
                    TML_Library.ShowAlertMessage(TB_AlarmManagerActivity.this, "Time and Email is manditory");
                    return;
                }
                String StrTimeHR = TML_Library.Time2Hr(TimeBombTime);
                String StrTimeMIN = TML_Library.Time2Min(TimeBombTime);
                TML_Library.SetPrefNew(TB_AlarmManagerActivity.this, "TIME_BOMB_TIME_HR", StrTimeHR);
                TML_Library.SetPrefNew(TB_AlarmManagerActivity.this, "TIME_BOMB_TIME_MIN", StrTimeMIN);
                TML_Library.SetPrefNew(TB_AlarmManagerActivity.this, "TIME_BOMB_EMAIL", TimeBombEmail);
                if (cb_allsmsinbox.isChecked()) {
                    TML_Library.SetPrefNew(TB_AlarmManagerActivity.this, "TB_ALL_SMS_INBOX", "Y");
                } else {
                    TML_Library.SetPrefNew(TB_AlarmManagerActivity.this, "TB_ALL_SMS_INBOX", "N");
                }
                if (cb_allsmssent.isChecked()) {
                    TML_Library.Debug("all sms sent checked so seetting up to Y");
                    TML_Library.SetPrefNew(TB_AlarmManagerActivity.this, "TB_ALL_SMS_SENT", "Y");
                } else {
                    TML_Library.SetPrefNew(TB_AlarmManagerActivity.this, "TB_ALL_SMS_SENT", "N");
                }
                if (cb_allincomingcall.isChecked()) {
                    TML_Library.SetPrefNew(TB_AlarmManagerActivity.this, "TB_ALL_INCOMING_CALL_LOG", "Y");
                } else {
                    TML_Library.SetPrefNew(TB_AlarmManagerActivity.this, "TB_ALL_INCOMING_CALL_LOG", "N");
                }
                if (cb_alloutgoingcall.isChecked()) {
                    TML_Library.SetPrefNew(TB_AlarmManagerActivity.this, "TB_ALL_OUTGOING_CALL_LOG", "Y");
                } else {
                    TML_Library.SetPrefNew(TB_AlarmManagerActivity.this, "TB_ALL_OUTGOING_CALL_LOG", "N");
                }
                if (cb_allmissedcall.isChecked()) {
                    TML_Library.SetPrefNew(TB_AlarmManagerActivity.this, "TB_ALL_MISSED_CALL_LOG", "Y");
                } else {
                    TML_Library.SetPrefNew(TB_AlarmManagerActivity.this, "TB_ALL_MISSED_CALL_LOG", "N");
                }
                if (((String) iv_timebombactive.getTag()).equals("on")) {
                    TB_AlarmManagerActivity.this.cancelRepeatingTimer(TB_AlarmManagerActivity.this);
                    TML_Library.SetPrefNew(TB_AlarmManagerActivity.this, "IS_TIME_BOMB_ON", "Y");
                    int iHr = Integer.parseInt(StrTimeHR);
                    int iMin = Integer.parseInt(StrTimeMIN);
                    TML_Library.Debug("Hr = " + iHr + " Min=" + iMin);
                    TB_AlarmManagerActivity.this.startRepeatingTimer(TB_AlarmManagerActivity.this, iHr, iMin);
                } else {
                    TML_Library.SetPrefNew(TB_AlarmManagerActivity.this, "IS_TIME_BOMB_ON", "N");
                    TB_AlarmManagerActivity.this.cancelRepeatingTimer(TB_AlarmManagerActivity.this);
                }
                TML_Library.PutToast(TB_AlarmManagerActivity.this, "Saved.");
            }
        });
        bt_cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TB_AlarmManagerActivity.this.finish();
            }
        });
        bt_timepick.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(TB_AlarmManagerActivity.this);
                TB_AlarmManagerActivity.this.showDialog(0);
            }
        });
        BT_sendsampleemail.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(TB_AlarmManagerActivity.this);
                String TimeBombTime = TB_AlarmManagerActivity.this.et_timebombtime.getText().toString();
                String TimeBombEmail = et_timebombemail.getText().toString();
                if (TimeBombTime.length() == 0 || TimeBombEmail.length() == 0) {
                    TML_Library.ShowAlertMessage(TB_AlarmManagerActivity.this, "Time and Email is manditory");
                    return;
                }
                new TimeBombTask(TB_AlarmManagerActivity.this, null).execute(new String[]{""});
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                return new TimePickerDialog(this, this.mTimeSetListener, this.pHourFrom, this.pMinuteFrom, false);
            default:
                return null;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
    }

    public void startRepeatingTimer(Context ctx, int Hr, int Min) {
        Context context = getApplicationContext();
        if (this.alarm != null) {
            this.alarm.SetAlarm(context, Hr, Min);
        } else {
            Toast.makeText(context, "Alarm is null", 0).show();
        }
    }

    public void cancelRepeatingTimer(Context ctx) {
        Context context = getApplicationContext();
        if (this.alarm != null) {
            this.alarm.CancelAlarm(context);
        } else {
            Toast.makeText(context, "Alarm is null", 0).show();
        }
    }

    private void updateDisplay() {
        this.et_timebombtime.setText(new StringBuilder().append(pad(this.pHourFrom)).append(":").append(pad(this.pMinuteFrom)));
    }

    private static String pad(int c) {
        if (c >= 10) {
            return String.valueOf(c);
        }
        return "0" + String.valueOf(c);
    }
}
