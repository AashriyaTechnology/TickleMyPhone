package com.jalsalabs.ticklemyphonefull;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

public class Monitor2 extends ListActivity {
    String LS_WhereCondition = "";
    int Rec_id;
    Cursor cursor;
    TMLDDL eventsData;
    Button kd;
    private String[] mDialogue;
    private boolean[] mExpanded;
    private String[] mTitles;
    private int[] onlyid;

    private class SpeechListAdapter extends BaseAdapter {
        private Context mContext;

        public SpeechListAdapter(Context context) {
            this.mContext = context;
        }

        public int getCount() {
            return Monitor2.this.mTitles.length;
        }

        public Object getItem(int position) {
            return Integer.valueOf(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                return new SpeechView(this.mContext, Monitor2.this.mTitles[position], Monitor2.this.mDialogue[position], Monitor2.this.mExpanded[position]);
            }
            SpeechView sv = (SpeechView) convertView;
            sv.setTitle(Monitor2.this.mTitles[position]);
            sv.setDialogue(Monitor2.this.mDialogue[position]);
            sv.setExpanded(Monitor2.this.mExpanded[position]);
            return sv;
        }

        public void toggle(int position) {
            Monitor2.this.mExpanded[position] = !Monitor2.this.mExpanded[position];
            notifyDataSetChanged();
        }
    }

    private class SpeechView extends LinearLayout {
        private TextView mDialogue;
        private TextView mTitle;

        public SpeechView(Context context, String title, String dialogue, boolean expanded) {
            super(context);
            setOrientation(1);
            this.mTitle = new TextView(context);
            this.mTitle.setText(title);
            this.mTitle.setTextSize(20.0f);
            addView(this.mTitle, new LayoutParams(-1, -2));
            this.mDialogue = new TextView(context);
            this.mDialogue.setText(dialogue);
            addView(this.mDialogue, new LayoutParams(-1, -2));
            this.mDialogue.setVisibility(expanded ? 0 : 8);
        }

        public void setTitle(String title) {
            this.mTitle.setText(title);
        }

        public void setDialogue(String words) {
            this.mDialogue.setText(words);
        }

        public void setExpanded(boolean expanded) {
            this.mDialogue.setVisibility(expanded ? 0 : 8);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TML_Library.Debug("01");
        this.eventsData = new TMLDDL(this);
        TML_Library.Debug("02");
        SQLiteDatabase db = this.eventsData.getReadableDatabase();
        TML_Library.Debug("03");
        this.cursor = db.query(TMLDDL.TABLE1, null, this.LS_WhereCondition, null, null, null, null);
        TML_Library.Debug("04");
        startManagingCursor(this.cursor);
        TML_Library.Debug("05");
        int total_records = 0;
        while (this.cursor.moveToNext()) {
            total_records++;
        }
        this.cursor.close();
        TML_Library.Debug("05.5");
        this.mTitles = new String[total_records];
        this.mDialogue = new String[total_records];
        this.mExpanded = new boolean[total_records];
        this.onlyid = new int[total_records];
        this.cursor = db.query(TMLDDL.TABLE1, null, this.LS_WhereCondition, null, null, null, "_ID DESC");
        startManagingCursor(this.cursor);
        int i = 0;
        TML_Library.Debug("06");
        while (this.cursor.moveToNext()) {
            int Rec_id = this.cursor.getInt(0);
            String sender = this.cursor.getString(1);
            String sendername = this.cursor.getString(2);
            String servicecenter = this.cursor.getString(3);
            String smsreceivedstatus = this.cursor.getString(4);
            String statusonicc = this.cursor.getString(6);
            String messagebody = this.cursor.getString(7);
            String ddate = this.cursor.getString(8);
            String ttime = this.cursor.getString(9);
            String action = this.cursor.getString(10);
            String result = this.cursor.getString(11);
            String attr0 = this.cursor.getString(12);
            String attr1 = this.cursor.getString(13);
            String attr2 = this.cursor.getString(14);
            String attr3 = this.cursor.getString(15);
            String attr4 = this.cursor.getString(16);
            String attr5 = this.cursor.getString(17);
            String attr6 = this.cursor.getString(18);
            String attr7 = this.cursor.getString(19);
            String attr8 = this.cursor.getString(20);
            String attr9 = this.cursor.getString(21);
            this.mTitles[i] = TML_Library.GetContactName(this, sender) + " " + sender;
            this.mDialogue[i] = "Action:" + action;
            String[] strArr = this.mDialogue;
            strArr[i] = strArr[i] + "\nDate :" + ddate + " " + ttime;
            strArr = this.mDialogue;
            strArr[i] = strArr[i] + "\nBody :" + messagebody;
            strArr = this.mDialogue;
            strArr[i] = strArr[i] + "\nMsg id:" + Rec_id;
            this.onlyid[i] = Rec_id;
            this.mExpanded[i] = false;
            i++;
        }
        TML_Library.Debug("07");
        this.cursor.close();
        TML_Library.Debug("08");
        if (i == 0) {
            TML_Library.PutToast(this, getString(R.string.oth31_no_log_to_display));
            finish();
        }
        setListAdapter(new SpeechListAdapter(this));
        db.close();
    }

    /* Access modifiers changed, original: protected */
    public void onListItemClick(ListView l, View v, int position, long id) {
        ((SpeechListAdapter) getListAdapter()).toggle(position);
    }
}
