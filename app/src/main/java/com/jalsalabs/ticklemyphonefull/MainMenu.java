package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainMenu extends Activity {
    String LS_IS_APP_Activate;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getWindow().setFormat(1);
    }

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        TML_Library.Debug("0");
        TML_Library.SetFullScreen(this);
        TML_Library.GetPref(this, "KEY_USER_DECLINED").equals("Y");
        setContentView(R.layout.mainmenu2);
        TML_Library.SetParameter("ACTION_SELECTED", "0");
        TML_Library.Debug("0.0001");
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.mainmenu2);
        TML_Library.Sleep(1);
        if (TML_Library.GetPref(this, "KEY_ACTIVATED_VIA_OFFER").equals("Y")) {
            TML_Library.SetPref(this, "KEY_IS_FREE", "N");
        } else {
            TML_Library.SetPref(this, "KEY_IS_FREE", TML_Library.ISFREE);
        }
        TML_Library.UpdateToFree(this);
        TML_Library.Debug("On Resume baby");
        Button IVSMSRules = (Button) findViewById(R.id.IVSMSRules);
        Button IVAutoAnswer = (Button) findViewById(R.id.IVAutoAnswer);
        Button IVSIMChangeAlert = (Button) findViewById(R.id.IVSIMChangeAlert);
        Button IVMonitor = (Button) findViewById(R.id.IVMonitor);
        Button IVHelp = (Button) findViewById(R.id.IVHelp);
        Button IVRate = (Button) findViewById(R.id.IVRate);
        Button bt_menu = (Button) findViewById(R.id.bt_menu);
        Button IVIconvisibility = (Button) findViewById(R.id.IVIconvisibility);
        Button IVTimeBomb = (Button) findViewById(R.id.IVTimeBomb);
        if (Integer.parseInt(VERSION.SDK) > 11) {
            IVIconvisibility.setVisibility(0);
        } else {
            TML_Library.RemoveICON(this);
            IVIconvisibility.setVisibility(8);
        }
        if (TML_Library.IsICONVisible(this)) {
            IVIconvisibility.setText("App Icon is Visible in the home screen,  Click to make it Invisible");
        } else {
            IVIconvisibility.setText("App Icon is IN-VISIBLE!!! in the home screen,  Click to make it Visible");
        }
        Animation s = AnimationUtils.loadAnimation(this, R.animator.fadein);
        IVSMSRules.startAnimation(s);
        IVAutoAnswer.startAnimation(s);
        IVSIMChangeAlert.startAnimation(s);
        IVMonitor.startAnimation(s);
        IVHelp.startAnimation(s);
        IVRate.startAnimation(s);
        IVIconvisibility.startAnimation(s);
        IVTimeBomb.startAnimation(s);
        TML_Library.SetParameter("ACTION_SELECTED", "0");
        TML_Library.Debug("00000000000002");
        TML_Library.Debug("00000000000003");
        String Title_NOW = TML_Library.GetVersionNumber(this);
        TML_Library.Debug("Title is Main menu " + Title_NOW);
        TML_Library.Debug("1");
        setTitle(Title_NOW);
        this.LS_IS_APP_Activate = TML_Library.GetPref(this, "KEY_IS_APP_ACTIVATE");
        TML_Library.Debug("00000000000004");
        TML_Library.Debug("2");
        IVSMSRules.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(MainMenu.this);
                TML_Library.Debug("Calling NewRulesMenu.class");
                String SettingPassword = TML_Library.GetprefnoAT(MainMenu.this, "settingpassword");
                TML_Library.Debug("The Set Password is :" + SettingPassword);
                if (SettingPassword.equals("")) {
                    TML_Library.Debug("Password is NOT set so taking directly to New Rules Menu" + SettingPassword);
                    MainMenu.this.startActivity(new Intent(MainMenu.this, SMSManageRulesTab.class));
                    return;
                }
                TML_Library.Debug("Password is set so taking to Password acceptance screen " + SettingPassword);
                TML_Library.SetParameter("ACTIVITY_TO_CALL", "RULESMENU");
                Intent i = new Intent();
                i.setAction("android.intent.action.MAIN");
                i.addCategory("android.intent.category.LAUNCHER");
                i.setComponent(new ComponentName(MainMenu.this, PasswordAccept.class));
                MainMenu.this.startActivity(i);
            }
        });
        TML_Library.Debug("3");
        IVSIMChangeAlert.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(MainMenu.this);
                String SettingPassword = TML_Library.GetprefnoAT(MainMenu.this, "settingpassword");
                TML_Library.Debug("The Set Password is :" + SettingPassword);
                Intent i;
                if (SettingPassword.equals("")) {
                    TML_Library.Debug("Password is NOT set so taking directly to SimChangeSetup Menu" + SettingPassword);
                    i = new Intent();
                    i.setAction("android.intent.action.MAIN");
                    i.addCategory("android.intent.category.LAUNCHER");
                    i.setComponent(new ComponentName(MainMenu.this, AntiTheftAlertTab.class));
                    MainMenu.this.startActivity(i);
                    return;
                }
                TML_Library.Debug("Password is set so taking to Password acceptance screen " + SettingPassword);
                i = new Intent();
                i.setAction("android.intent.action.MAIN");
                i.addCategory("android.intent.category.LAUNCHER");
                TML_Library.SetParameter("ACTIVITY_TO_CALL", "SIMCHANGE");
                i.setComponent(new ComponentName(MainMenu.this, PasswordAccept.class));
                MainMenu.this.startActivity(i);
            }
        });
        IVAutoAnswer.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(MainMenu.this);
                String SettingPassword = TML_Library.GetprefnoAT(MainMenu.this, "settingpassword");
                Intent i;
                if (SettingPassword.equals("")) {
                    TML_Library.Debug("Password is NOT set so taking directly to SimChangeSetup Menu" + SettingPassword);
                    i = new Intent();
                    i.setAction("android.intent.action.MAIN");
                    i.addCategory("android.intent.category.LAUNCHER");
                    i.setComponent(new ComponentName(MainMenu.this, AutoAnswerSetupTAB.class));
                    MainMenu.this.startActivity(i);
                    return;
                }
                TML_Library.Debug("Password is set so taking to Password acceptance screen " + SettingPassword);
                i = new Intent();
                i.setAction("android.intent.action.MAIN");
                i.addCategory("android.intent.category.LAUNCHER");
                TML_Library.SetParameter("ACTIVITY_TO_CALL", "AUTOANSWER");
                i.setComponent(new ComponentName(MainMenu.this, PasswordAccept.class));
                MainMenu.this.startActivity(i);
            }
        });
        TML_Library.Debug("4");
        IVMonitor.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(MainMenu.this);
                MainMenu.this.startActivity(new Intent(MainMenu.this, Monitor2.class));
            }
        });
        TML_Library.Debug("5");
        IVHelp.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(MainMenu.this);
                MainMenu.this.startActivity(new Intent(MainMenu.this, HelpTab.class));
            }
        });
        IVTimeBomb.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(MainMenu.this);
                MainMenu.this.startActivity(new Intent(MainMenu.this, TB_SetupTAB.class));
            }
        });
        IVIconvisibility.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (TML_Library.ISFREE.equals("Y")) {
                    TML_Library.ShowAlertMessage(MainMenu.this, "HIDE and SHOW Application icon is only available in FULL Version!!!  Sorry!");
                    return;
                }
                String DialNo = TML_Library.GetPref(MainMenu.this, "dialnumber");
                if (DialNo.length() == 0) {
                    DialNo = "4455";
                }
                String DisplayMessage = "";
                new Builder(MainMenu.this).setTitle("Read this before you Hide ICON").setMessage(new StringBuilder(String.valueOf("Please note that When you disable application icon, You will not be able to access this application.  However if you want to Launch application,  Dial  " + DialNo + " from your phone and make call...  System will automatically invoke the Application main menu\n")).append("\n\n For Some Android Version you may have to Reboot in order to HIDE/SHOW Application icon").toString()).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TML_Library.HapticFeedback(MainMenu.this);
                        TML_Library.ICONToggle(MainMenu.this);
                        dialog.dismiss();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TML_Library.HapticFeedback(MainMenu.this);
                        dialog.dismiss();
                    }
                }).show();
            }
        });
        TML_Library.Debug("6");
        IVRate.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(MainMenu.this);
                TML_Library.Debug("Calling Rating");
                Intent intent;
                try {
                    intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(TML_Library.MOBILE_MARKET_URL));
                    MainMenu.this.startActivity(intent);
                } catch (Exception e) {
                    intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(TML_Library.DESKTOP_MARKET_URL));
                    MainMenu.this.startActivity(intent);
                }
            }
        });
        bt_menu.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TML_Library.HapticFeedback(MainMenu.this);
                Builder ab = new Builder(MainMenu.this);
                String[] ListFree = new String[]{"Buy Full Version", "Exit"};
                String[] ListFull = new String[]{"Exit"};
                if (TML_Library.isFree(MainMenu.this).booleanValue()) {
                    ab.setItems(ListFree, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface d, int choice) {
                            if (choice == 0) {
                                TML_Library.OpenMarketWindow(MainMenu.this);
                            } else if (choice == 1) {
                                MainMenu.this.finish();
                            }
                        }
                    });
                    ab.show();
                    return;
                }
                ab.setItems(ListFull, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int choice) {
                        if (choice == 0) {
                            try {
                                TML_Library.Debug("Srinath Usere id :");
                                TML_Library.Debug(SimpleCrypto.encrypt("TMLJINGALALA", "tmlmediaandroid@gmail.com"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                TML_Library.Debug("Srinath Password id :");
                                TML_Library.Debug(SimpleCrypto.encrypt("TMLJINGALALA", "tml12345"));
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            MainMenu.this.finish();
                        }
                    }
                });
                ab.show();
            }
        });
        TML_Library.Debug("Final 10000000000");
        if (TML_Library.MARKET_VERSION) {
            TML_Library.Debug("ok 1");
            String InstallSource = TML_Library.GetIsInstalledMarket(this);
            TML_Library.Debug("ok 2");
            if (InstallSource == null) {
                TML_Library.Debug("ok 3");
                TML_Library.PutToast(this, "Tickle my Phone  is NOT downloaded from android market (Google play).  Please dont use copied version. We request you to un-install this version and download from Android market.  Thanks. Application close in 10 seconds");
                TML_Library.ShowAlertMessage(this, "Tickle my Phone  is NOT downloaded from android market (Google play).  Please dont use copied version. We request you to un-install this version and download from Android market.  Thanks. Application close in 10 seconds");
                TML_Library.Debug("ok 4");
                String EmailBody = TML_Library.GetFirstTimeInfo(this);
                TML_Library.Debug("ok 5");
                String FileName = TML_Library.getTMLPath() + "tmlinfo.txt";
                TML_Library.Debug("ok 6");
                TML_Library.CreateLogText(this, FileName, EmailBody);
                TML_Library.Debug("ok 7");
                TML_Library.SendEmail(this, TML_Library.GetU(this), TML_Library.GetP(this), "ticklemyphonecount@gmail.com", "Trying to use copied version", EmailBody, FileName);
                TML_Library.Debug("ok 8");
                TML_Library.Sleep(12);
                finish();
            }
        }
    }
}
