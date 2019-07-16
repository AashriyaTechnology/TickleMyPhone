package com.jalsalabs.ticklemyphonefull;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TMLDDL extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tmlapp.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE1 = "tmllog";
    public static final String action = "action";
    public static final String attr0 = "attr0";
    public static final String attr1 = "attr1";
    public static final String attr2 = "attr2";
    public static final String attr3 = "attr3";
    public static final String attr4 = "attr4";
    public static final String attr5 = "attr5";
    public static final String attr6 = "attr6";
    public static final String attr7 = "attr7";
    public static final String attr8 = "attr8";
    public static final String attr9 = "attr9";
    public static final String ddate = "ddate";
    public static final String messagebody = "messagebody";
    public static final String protocolid = "protocolid";
    public static final String result = "result";
    public static final String sender = "sender";
    public static final String sendername = "sendername";
    public static final String servicecenter = "servicecenter";
    public static final String smsreceivedstatus = "smsreceivedstatus";
    public static final String statusonicc = "statusonicc";
    public static final String ttime = "ttime";

    public TMLDDL(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql = "create table tmllog( _id integer primary key autoincrement,sender text,sendername text,servicecenter text,smsreceivedstatus text,protocolid text,statusonicc text,messagebody text,ddate text,ttime text,action text,result text,attr0 text,attr1 text,attr2 text,attr3 text,attr4 text,attr5 text,attr6 text,attr7 text,attr8 text,attr9 text);";
        TML_Library.Debug("onCreate: " + sql);
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            String sql = null;
            if (oldVersion == 1) {
                sql = "alter table tmllog add note text;";
            }
            if (oldVersion == 2) {
                sql = "";
            }
            TML_Library.Debug("onUpgrade\t: " + sql);
            if (sql != null) {
                db.execSQL(sql);
            }
        }
    }
}
