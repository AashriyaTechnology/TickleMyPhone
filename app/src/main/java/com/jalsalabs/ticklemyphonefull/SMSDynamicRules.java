package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SMSDynamicRules extends Activity {
    EditText ET_KeyWord;
    EditText ET_SMSReplyText;
    String LS_MenuOption;
    TextView StartActiontext;
    TextView Title;
    TextView acklabel;
    TextView actiontext;
    Button cancel;
    TextView exampletext;
    Button save;
    ToggleButton toggleaction;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TML_Library.SetFullScreen(this);
        setContentView(R.layout.dynamicsmsrulesv2);
        getWindow().setSoftInputMode(3);
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.dynamicsmsrulesv2);
        this.Title = (TextView) findViewById(R.id.Title);
        this.StartActiontext = (TextView) findViewById(R.id.StartActiontext);
        this.ET_KeyWord = (EditText) findViewById(R.id.ET_KeyWord);
        this.actiontext = (TextView) findViewById(R.id.actiontext);
        this.save = (Button) findViewById(R.id.save);
        this.cancel = (Button) findViewById(R.id.cancel);
        this.exampletext = (TextView) findViewById(R.id.exampletext);
        this.toggleaction = (ToggleButton) findViewById(R.id.toggleaction);
        this.LS_MenuOption = TML_Library.GetParameter("RULE_OPTION");
        this.ET_SMSReplyText = (EditText) findViewById(R.id.ET_SMSReplyText);
        this.acklabel = (TextView) findViewById(R.id.acklabel);
        IntialSettings(this.LS_MenuOption);
        this.save.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String LS_Toggle;
                TML_Library.HapticFeedback(SMSDynamicRules.this);
                String LS_Keyword = SMSDynamicRules.this.ET_KeyWord.getText().toString();
                if (SMSDynamicRules.this.toggleaction.isChecked()) {
                    LS_Toggle = "Y";
                } else {
                    LS_Toggle = "N";
                }
                if (SMSDynamicRules.this.LS_MenuOption.equals("0")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_CALLBACK", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_CALLBACK_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("1")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_CALLBACK_SPEAKER", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_CALLBACK_SPEAKER_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("2")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_VIBRATE", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_VIBRATE_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("3")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_VIBRATEN", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_VIBRATEN_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("4")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_POPOUT", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_POPOUT_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("5")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_RINGTONE", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_RINGTONE_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("6")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SMS_REPLY_TEXT", SMSDynamicRules.this.ET_SMSReplyText.getText().toString());
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SMS_REPLY", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SMS_REPLY_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("7")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_CALLFORWARD", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_CALLFORWARD_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("8")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_CALLFORWARD_SPEAKER", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_CALLFORWARD_SPEAKER_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("9")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_GEOSMS", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_GEOSMS_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("10")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SAVEPHOTOSDCARD", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SAVEPHOTOSDCARD_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("11")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SENDPHOTO", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SENDPHOTO_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("12")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SENDVIDEO", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SENDVIDEO_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("13")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_RECORDAUDIO", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_RECORDAUDIO_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("14")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_RECORDAUDIO_NSEC", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_RECORDAUDIO_NSEC_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("15")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SENDAUDIORECORD", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SENDAUDIORECORD_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("16")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SETGPS", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SETGPS_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("17")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SETWIFI", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SETWIFI_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("18")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SENDMOBILEINFO", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SENDMOBILEINFO_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("19")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SENDMISSCALLDETAIL", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SENDMISSCALLDETAIL_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("20")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_BUZZMOBILE", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_BUZZMOBILE_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("21")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_CALEVENT", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_CALEVENT_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("22")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_CONTACTLIST", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_CONTACTLIST_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("23")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SENDHELP", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SENDHELP_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("24")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_GETSMS", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_GETSMS_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("25")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_GETAPPLIST", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_GETAPPLIST_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("26")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_GETDIRLIST", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_GETDIRLIST_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("27")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_GETFILE", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_GETFILE_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("28")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_PLAY_FILE", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_PLAY_FILE_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("29")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_NETWORKDATA", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_NETWORKDATA_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("30")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SMS_AC_REPLY_TEXT", SMSDynamicRules.this.ET_SMSReplyText.getText().toString());
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_ADDCONTACT", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_ADDCONTACT_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("31")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_BLUETOOTH", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_BLUETOOTH_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("32")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SMSREPORT", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_SMSREPORT_ENABLE", LS_Toggle);
                } else if (SMSDynamicRules.this.LS_MenuOption.equals("33")) {
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_CALLREPORT", LS_Keyword);
                    TML_Library.SetPref(SMSDynamicRules.this, "KEY_CALLREPORT_ENABLE", LS_Toggle);
                }
                SMSDynamicRules.isDublicate(SMSDynamicRules.this);
                TML_Library.PutToast(SMSDynamicRules.this, "Saved");
            }
        });
        this.cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(SMSDynamicRules.this);
                SMSDynamicRules.this.finish();
            }
        });
        this.toggleaction.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (SMSDynamicRules.this.toggleaction.isChecked()) {
                    SMSDynamicRules.this.toggleaction.setText("This functionality is swiftched ON");
                } else {
                    SMSDynamicRules.this.toggleaction.setText("This functionality is swiftched OFF");
                }
                TML_Library.HapticFeedback(SMSDynamicRules.this);
            }
        });
    }

    private void IntialSettings(String MenuOption) {
        this.ET_SMSReplyText.setVisibility(8);
        this.acklabel.setVisibility(8);
        isDublicate(this);
        String LS_keyword;
        if (MenuOption.equals("0")) {
            this.Title.setText("  " + getString(R.string.rl0_title));
            this.StartActiontext.setText(getString(R.string.rl0_when));
            this.actiontext.setText(getString(R.string.rl0_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_CALLBACK");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl0_ex));
            if (TML_Library.GetPref(this, "KEY_CALLBACK_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("1")) {
            this.Title.setText(getString(R.string.rl1_title));
            this.StartActiontext.setText(getString(R.string.rl1_when));
            this.actiontext.setText(getString(R.string.rl1_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_CALLBACK_SPEAKER");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl1_ex));
            if (TML_Library.GetPref(this, "KEY_CALLBACK_SPEAKER_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("2")) {
            this.Title.setText(getString(R.string.rl2_title));
            this.StartActiontext.setText(getString(R.string.rl2_when));
            this.actiontext.setText(getString(R.string.rl2_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_VIBRATE");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl2_ex));
            if (TML_Library.GetPref(this, "KEY_VIBRATE_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("3")) {
            this.Title.setText(getString(R.string.rl3_title));
            this.StartActiontext.setText(getString(R.string.rl3_when));
            this.actiontext.setText(getString(R.string.rl3_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_VIBRATEN");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl3_ex));
            if (TML_Library.GetPref(this, "KEY_VIBRATEN_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("4")) {
            this.Title.setText(getString(R.string.rl4_title));
            this.StartActiontext.setText(getString(R.string.rl4_when));
            this.actiontext.setText(getString(R.string.rl4_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_POPOUT");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl4_ex));
            if (TML_Library.GetPref(this, "KEY_POPOUT_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("5")) {
            this.Title.setText(getString(R.string.rl5_title));
            this.StartActiontext.setText(getString(R.string.rl5_when));
            this.actiontext.setText(getString(R.string.rl5_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_RINGTONE");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl5_ex));
            if (TML_Library.GetPref(this, "KEY_RINGTONE_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("6")) {
            this.ET_SMSReplyText.setVisibility(0);
            this.acklabel.setVisibility(0);
            this.Title.setText(getString(R.string.rl6_title));
            this.acklabel.setText(getString(R.string.rl6_ack));
            this.ET_SMSReplyText.setText(TML_Library.GetPref(this, "KEY_SMS_REPLY_TEXT"));
            this.StartActiontext.setText(getString(R.string.rl6_when));
            this.actiontext.setText(getString(R.string.rl6_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_SMS_REPLY");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl6_ex));
            if (TML_Library.GetPref(this, "KEY_SMS_REPLY_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("7")) {
            this.Title.setText(getString(R.string.rl7_title));
            this.StartActiontext.setText(getString(R.string.rl7_when));
            this.actiontext.setText(getString(R.string.rl7_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_CALLFORWARD");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl7_ex));
            if (TML_Library.GetPref(this, "KEY_CALLFORWARD_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("8")) {
            this.Title.setText(getString(R.string.rl8_title));
            this.StartActiontext.setText(getString(R.string.rl8_when));
            this.actiontext.setText(getString(R.string.rl8_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_CALLFORWARD_SPEAKER");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl8_ex));
            if (TML_Library.GetPref(this, "KEY_CALLFORWARD_SPEAKER_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("9")) {
            this.Title.setText(getString(R.string.rl9_title));
            this.StartActiontext.setText(getString(R.string.rl9_when));
            this.actiontext.setText(getString(R.string.rl9_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_GEOSMS");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl9_ex));
            if (TML_Library.GetPref(this, "KEY_GEOSMS_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("10")) {
            this.Title.setText(getString(R.string.rl10_title));
            this.StartActiontext.setText(getString(R.string.rl10_when));
            this.actiontext.setText(getString(R.string.rl10_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_SAVEPHOTOSDCARD");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl10_ex));
            if (TML_Library.GetPref(this, "KEY_SAVEPHOTOSDCARD_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("11")) {
            this.Title.setText(getString(R.string.rl11_title));
            this.StartActiontext.setText(getString(R.string.rl11_when));
            this.actiontext.setText(getString(R.string.rl11_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_SENDPHOTO");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl11_ex));
            if (TML_Library.GetPref(this, "KEY_SENDPHOTO_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("12")) {
            this.Title.setText(getString(R.string.rl12_title));
            this.StartActiontext.setText(getString(R.string.rl12_when));
            this.actiontext.setText(getString(R.string.rl12_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_SENDVIDEO");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl12_ex));
            if (TML_Library.GetPref(this, "KEY_SENDVIDEO_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("13")) {
            this.Title.setText(getString(R.string.rl13_title));
            this.StartActiontext.setText(getString(R.string.rl13_when));
            this.actiontext.setText(getString(R.string.rl13_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_RECORDAUDIO");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl13_ex));
            if (TML_Library.GetPref(this, "KEY_RECORDAUDIO_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("14")) {
            this.Title.setText(getString(R.string.rl14_title));
            this.StartActiontext.setText(getString(R.string.rl14_when));
            this.actiontext.setText(getString(R.string.rl14_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_RECORDAUDIO_NSEC");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl14_ex));
            if (TML_Library.GetPref(this, "KEY_RECORDAUDIO_NSEC_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("15")) {
            this.Title.setText(getString(R.string.rl15_title));
            this.StartActiontext.setText(getString(R.string.rl15_when));
            this.actiontext.setText(getString(R.string.rl15_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_SENDAUDIORECORD");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl15_ex));
            if (TML_Library.GetPref(this, "KEY_SENDAUDIORECORD_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("16")) {
            this.Title.setText(getString(R.string.rl16_title));
            this.StartActiontext.setText(getString(R.string.rl16_when));
            this.actiontext.setText(getString(R.string.rl16_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_SETGPS");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl16_ex));
            if (TML_Library.GetPref(this, "KEY_SETGPS_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("17")) {
            this.Title.setText(getString(R.string.wifititle));
            this.StartActiontext.setText(getString(R.string.wifi_when));
            this.actiontext.setText(getString(R.string.wifi_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_SETWIFI");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.wifi_ex));
            if (TML_Library.GetPref(this, "KEY_SETWIFI_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("18")) {
            this.Title.setText(getString(R.string.rl17_title));
            this.StartActiontext.setText(getString(R.string.rl17_when));
            this.actiontext.setText(getString(R.string.rl17_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_SENDMOBILEINFO");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl17_ex));
            if (TML_Library.GetPref(this, "KEY_SENDMOBILEINFO_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("19")) {
            this.Title.setText(getString(R.string.rl18_title));
            this.StartActiontext.setText(getString(R.string.rl18_when));
            this.actiontext.setText(getString(R.string.rl18_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_SENDMISSCALLDETAIL");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl18_ex));
            if (TML_Library.GetPref(this, "KEY_SENDMISSCALLDETAIL_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("20")) {
            this.Title.setText(getString(R.string.rl19_title));
            this.StartActiontext.setText(getString(R.string.rl19_when));
            this.actiontext.setText(getString(R.string.rl19_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_BUZZMOBILE");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl19_ex));
            if (TML_Library.GetPref(this, "KEY_BUZZMOBILE_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("21")) {
            this.Title.setText(getString(R.string.rl20_title));
            this.StartActiontext.setText(getString(R.string.rl20_when));
            this.actiontext.setText(getString(R.string.rl20_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_CALEVENT");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl20_ex));
            if (TML_Library.GetPref(this, "KEY_CALEVENT_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("22")) {
            this.Title.setText(getString(R.string.rl21_title));
            this.StartActiontext.setText(getString(R.string.rl21_when));
            this.actiontext.setText(getString(R.string.rl21_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_CONTACTLIST");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl21_ex));
            if (TML_Library.GetPref(this, "KEY_CONTACTLIST_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("23")) {
            TML_Library.Debug("I am inside 23");
            this.Title.setText(getString(R.string.rl22_title));
            this.StartActiontext.setText(getString(R.string.rl22_when));
            this.actiontext.setText(getString(R.string.rl22_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_SENDHELP");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl22_ex));
            if (TML_Library.GetPref(this, "KEY_SENDHELP_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("24")) {
            this.Title.setText(getString(R.string.rl24_title));
            this.StartActiontext.setText(getString(R.string.rl24_when));
            this.actiontext.setText(getString(R.string.rl24_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_GETSMS");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl24_ex));
            if (TML_Library.GetPref(this, "KEY_GETSMS_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("25")) {
            this.Title.setText(getString(R.string.rl25_title));
            this.StartActiontext.setText(getString(R.string.rl25_when));
            this.actiontext.setText(getString(R.string.rl25_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_GETAPPLIST");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl25_ex));
            if (TML_Library.GetPref(this, "KEY_GETAPPLIST_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("26")) {
            this.Title.setText(getString(R.string.rl26_title));
            this.StartActiontext.setText(getString(R.string.rl26_when));
            this.actiontext.setText(getString(R.string.rl26_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_GETDIRLIST");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl26_ex));
            if (TML_Library.GetPref(this, "KEY_GETDIRLIST_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("27")) {
            this.Title.setText(getString(R.string.rl27_title));
            this.StartActiontext.setText(getString(R.string.rl27_when));
            this.actiontext.setText(getString(R.string.rl27_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_GETFILE");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl27_ex));
            if (TML_Library.GetPref(this, "KEY_GETFILE_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("28")) {
            this.Title.setText(getString(R.string.rl28_title));
            this.StartActiontext.setText(getString(R.string.rl28_when));
            this.actiontext.setText(getString(R.string.rl28_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_PLAY_FILE");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl28_ex));
            if (TML_Library.GetPref(this, "KEY_PLAY_FILE_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("29")) {
            this.Title.setText(getString(R.string.rl29_title));
            this.StartActiontext.setText(getString(R.string.rl29_when));
            this.actiontext.setText(getString(R.string.rl29_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_NETWORKDATA");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl29_ex));
            if (TML_Library.GetPref(this, "KEY_NETWORKDATA_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("30")) {
            this.ET_SMSReplyText.setVisibility(0);
            this.acklabel.setVisibility(0);
            this.Title.setText(getString(R.string.rl30_title));
            this.acklabel.setText(getString(R.string.rl6_ack));
            this.ET_SMSReplyText.setText(TML_Library.GetPref(this, "KEY_SMS_AC_REPLY_TEXT"));
            this.StartActiontext.setText(getString(R.string.rl30_when));
            this.actiontext.setText(getString(R.string.rl30_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_ADDCONTACT");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl30_ex));
            if (TML_Library.GetPref(this, "KEY_ADDCONTACT_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("31")) {
            this.Title.setText(getString(R.string.rl31_title));
            this.StartActiontext.setText(getString(R.string.rl31_when));
            this.actiontext.setText(getString(R.string.rl31_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_BLUETOOTH");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl31_ex));
            if (TML_Library.GetPref(this, "KEY_BLUETOOTH_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("32")) {
            this.Title.setText(getString(R.string.rl32_title));
            this.StartActiontext.setText(getString(R.string.rl32_when));
            this.actiontext.setText(getString(R.string.rl32_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_SMSREPORT");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl32_ex));
            if (TML_Library.GetPref(this, "KEY_SMSREPORT_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else if (MenuOption.equals("33")) {
            this.Title.setText(getString(R.string.rl33_title));
            this.StartActiontext.setText(getString(R.string.rl33_when));
            this.actiontext.setText(getString(R.string.rl33_then));
            LS_keyword = TML_Library.GetPref(this, "KEY_CALLREPORT");
            this.ET_KeyWord.setText(LS_keyword);
            this.exampletext.setText(getString(R.string.r_eg) + LS_keyword + getString(R.string.rl33_ex));
            if (TML_Library.GetPref(this, "KEY_CALLREPORT_ENABLE").equals("Y")) {
                this.toggleaction.setChecked(true);
            } else {
                this.toggleaction.setChecked(false);
            }
        } else {
            finish();
        }
        if (this.toggleaction.isChecked()) {
            this.toggleaction.setText("This functionality is swiftched ON");
        } else {
            this.toggleaction.setText("This functionality is swiftched OFF");
        }
    }

    public static boolean isDublicate(Context context) {
        String[] input = new String[]{TML_Library.GetPref(context, "KEY_CALLBACK"), TML_Library.GetPref(context, "KEY_CALLBACK_SPEAKER"), TML_Library.GetPref(context, "KEY_VIBRATE"), TML_Library.GetPref(context, "KEY_VIBRATEN"), TML_Library.GetPref(context, "KEY_POPOUT"), TML_Library.GetPref(context, "KEY_RINGTONE"), TML_Library.GetPref(context, "KEY_SMS_REPLY_TEXT"), TML_Library.GetPref(context, "KEY_SMS_REPLY"), TML_Library.GetPref(context, "KEY_CALLFORWARD"), TML_Library.GetPref(context, "KEY_CALLFORWARD_SPEAKER"), TML_Library.GetPref(context, "KEY_GEOSMS"), TML_Library.GetPref(context, "KEY_SENDPHOTO"), TML_Library.GetPref(context, "KEY_SENDVIDEO"), TML_Library.GetPref(context, "KEY_RECORDAUDIO"), TML_Library.GetPref(context, "KEY_RECORDAUDIO_NSEC"), TML_Library.GetPref(context, "KEY_SENDAUDIORECORD"), TML_Library.GetPref(context, "KEY_SETGPS"), TML_Library.GetPref(context, "KEY_SETWIFI"), TML_Library.GetPref(context, "KEY_SENDMOBILEINFO"), TML_Library.GetPref(context, "KEY_SENDMISSCALLDETAIL"), TML_Library.GetPref(context, "KEY_BUZZMOBILE"), TML_Library.GetPref(context, "KEY_CALEVENT"), TML_Library.GetPref(context, "KEY_CONTACTLIST"), TML_Library.GetPref(context, "KEY_GETSMS"), TML_Library.GetPref(context, "KEY_GETAPPLIST"), TML_Library.GetPref(context, "KEY_GETDIRLIST"), TML_Library.GetPref(context, "KEY_GETFILE"), TML_Library.GetPref(context, "KEY_PLAY_FILE"), TML_Library.GetPref(context, "KEY_SENDHELP"), TML_Library.GetPref(context, "KEY_NETWORKDATA"), TML_Library.GetPref(context, "KEY_ADDCONTACT"), TML_Library.GetPref(context, "KEY_BLUETOOTH"), TML_Library.GetPref(context, "KEY_SMSREPORT"), TML_Library.GetPref(context, "KEY_CALLREPORT")};
        int length = input.length;
        TML_Library.Debug("Input length = " + length);
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (input[i].equals(input[j])) {
                    TML_Library.Debug("Duplicates found ******" + input[i]);
                    TML_Library.PutToast(context, "Duplicate keys found :" + input[i]);
                    TML_Library.HapticFeedback(context);
                    TML_Library.HapticFeedback(context);
                    TML_Library.HapticFeedback(context);
                    return true;
                }
            }
        }
        return false;
    }
}
