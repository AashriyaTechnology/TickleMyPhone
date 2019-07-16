package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends Activity {
    String LS_IS_APP_Activate;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TML_Library.Debug("1");
        setContentView(R.layout.splash);
        TML_Library.CreateFolderinSDCard(this, "ticklemyphone");
        TML_Library.SetPref(this, "KEY_IS_APP_ACTIVATE", "Y");
    }

    public void onResume() {
        super.onResume();
        TML_Library.Debug("2");
        setContentView(R.layout.splash);
        ((ImageView) findViewById(R.id.frame_1)).startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropfromtop));
        try {
            TML_Library.PlayWelcomeMusic(this);
        } catch (Exception e) {
        }
        DeleteOldLogs(this, 100);
        this.LS_IS_APP_Activate = TML_Library.GetPref(this, "KEY_IS_APP_ACTIVATE");
        ((TextView) findViewById(R.id.freeversion)).setText(TML_Library.GetVersionNumber(this));
        TML_Library.Debug("3");
        new Thread() {
            public void run() {
                Intent intentx;
                try {
                    super.run();
                    TML_Library.Debug("4");
                    TML_Library.Sleep(5);
                    try {
                        intentx = new Intent(SplashScreen.this, FirstTimeGetInstalledApps.class);
                        intentx.addFlags(268435456);
                        SplashScreen.this.startActivity(intentx);
                    } catch (Exception e) {
                        TML_Library.PutToast(SplashScreen.this, "Unable to open Tickle my Phone menu. Try another time. If the problem persist.  Try uninstall and reinstall the app from market.");
                    }
                    SplashScreen.this.finish();
                } catch (Exception e2) {
                    System.out.println("EXc=" + e2);
                    try {
                        intentx = new Intent(SplashScreen.this, FirstTimeGetInstalledApps.class);
                        intentx.addFlags(268435456);
                        SplashScreen.this.startActivity(intentx);
                    } catch (Exception e3) {
                        TML_Library.PutToast(SplashScreen.this, "Unable to open Tickle my Phone menu. Try another time. If the problem persist.  Try uninstall and reinstall the app from market.");
                    }
                    SplashScreen.this.finish();
                } finally {
                    TML_Library.Debug("5");
                    try {
                        intentx = new Intent(SplashScreen.this, FirstTimeGetInstalledApps.class);
                        intentx.addFlags(268435456);
                        SplashScreen.this.startActivity(intentx);
                    } catch (Exception e4) {
                        TML_Library.PutToast(SplashScreen.this, "Unable to open Tickle my Phone menu. Try another time. If the problem persist.  Try uninstall and reinstall the app from market.");
                    }
                    SplashScreen.this.finish();
                }
            }
        }.start();
    }

    private void DeleteOldLogs(Context context, int keeponly) {
        int MaxRecid = 0;
        SQLiteDatabase db = new TMLDDL(context).getReadableDatabase();
        Cursor cursor = db.query(TMLDDL.TABLE1, new String[]{"max(_ID)"}, null, null, null, null, null);
        startManagingCursor(cursor);
        while (cursor.moveToNext()) {
            MaxRecid = cursor.getInt(0);
        }
        String LS_WhereCondition = "_ID<" + (MaxRecid - keeponly);
        TML_Library.Debug("Delete Where Clause is :" + LS_WhereCondition);
        db.delete(TMLDDL.TABLE1, LS_WhereCondition, null);
        db.close();
    }
}
