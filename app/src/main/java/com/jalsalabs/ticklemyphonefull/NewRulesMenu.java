package com.jalsalabs.ticklemyphonefull;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;
import java.util.Vector;

public class NewRulesMenu extends ListActivity {
    public static int ItemPosition;
    public static String ItemSelected;
    public static CustomAdapter adapter;
    public static CustomAdapter adapter1;
    Animation ar3;
    private Vector<RowData> data;
    RelativeLayout layr1;
    String[] ls_tml_actions = new String[34];
    private LayoutInflater mInflater;
    int pos = 1;
    RowData rd;
    RowData rd1;
    int textlength = 0;

    private class CustomAdapter extends ArrayAdapter<RowData> {

        private class ViewHolder {
            private ImageView i11 = null;
            private View mRow;
            private TextView title = null;

            public ViewHolder(View row) {
                this.mRow = row;
            }

            public TextView gettitle() {
                if (this.title == null) {
                    this.title = (TextView) this.mRow.findViewById(R.id.title);
                }
                return this.title;
            }

            public ImageView getImage() {
                LayoutParams layoutParams = new LayoutParams(60, 60);
                this.i11 = (ImageView) this.mRow.findViewById(R.id.listimage);
                this.i11.setLayoutParams(layoutParams);
                return this.i11;
            }
        }

        public CustomAdapter(Context context, int resource, int textViewResourceId, List<RowData> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            RowData rowData = (RowData) getItem(position);
            if (convertView == null) {
                convertView = NewRulesMenu.this.mInflater.inflate(R.layout.newrulemenu, null);
                convertView.setTag(new ViewHolder(convertView));
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.gettitle().setText(rowData.mTitle);
            ImageView i11 = holder.getImage();
            NewRulesMenu.ItemSelected = rowData.mTitle;
            TML_Library.Debug("Position = " + position);
            String ls_OnOffImage = TML_Library.isRuleOn(NewRulesMenu.this, position);
            TML_Library.Debug("on off image = " + ls_OnOffImage + " for the position : " + position);
            i11.setImageResource(NewRulesMenu.this.getResources().getIdentifier(ls_OnOffImage, "drawable", TML_Library.PACKAGE_FULL_NAME));
            return convertView;
        }
    }

    public class RowData {
        protected int mId;
        protected String mTitle;

        RowData(int id, String title) {
            this.mId = id;
            this.mTitle = title;
        }

        public String toString() {
            return this.mId + " " + this.mTitle;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newrulesitems);
        TML_Library.Debug("Inside onCreate of NewRulesMenu");
        TML_Library.Debug("Setting minflater");
        this.mInflater = (LayoutInflater) getSystemService("layout_inflater");
        TML_Library.Debug("setting data");
        this.data = new Vector();
        TML_Library.Debug("now assiging ls_tml_actions");
        this.ls_tml_actions[0] = getString(R.string.rm_callback);
        this.ls_tml_actions[1] = getString(R.string.rm_callback_spk);
        this.ls_tml_actions[2] = getString(R.string.rm_vibrate);
        this.ls_tml_actions[3] = getString(R.string.rm_vibrate_nsec);
        this.ls_tml_actions[4] = getString(R.string.rm_popp);
        TML_Library.Debug("i am in 4");
        this.ls_tml_actions[5] = getString(R.string.rm_play_ringtone);
        TML_Library.Debug("i am in 5");
        this.ls_tml_actions[6] = getString(R.string.rm_sms_acknowledge);
        TML_Library.Debug("i am in 6");
        this.ls_tml_actions[7] = getString(R.string.rm_call_fwd);
        TML_Library.Debug("i am in 7");
        this.ls_tml_actions[8] = getString(R.string.rm_call_fwd_spk);
        TML_Library.Debug("i am in 8");
        this.ls_tml_actions[9] = getString(R.string.rm_whereareyou);
        TML_Library.Debug("i am in 9");
        this.ls_tml_actions[10] = getString(R.string.rm_takephoto);
        TML_Library.Debug("i am in 10");
        this.ls_tml_actions[11] = getString(R.string.rm_takepicksend);
        this.ls_tml_actions[12] = getString(R.string.rm_takevideosend);
        this.ls_tml_actions[13] = getString(R.string.rm_saveaudio);
        this.ls_tml_actions[14] = getString(R.string.rm_saveaudionsec);
        this.ls_tml_actions[15] = getString(R.string.rm_sendaudio);
        this.ls_tml_actions[16] = getString(R.string.rm_setgps);
        this.ls_tml_actions[17] = getString(R.string.rm_setwifi);
        this.ls_tml_actions[18] = getString(R.string.rm_mobileinfo);
        this.ls_tml_actions[19] = getString(R.string.rm_send_miss_call);
        this.ls_tml_actions[20] = getString(R.string.rm_buzzmobile);
        this.ls_tml_actions[21] = getString(R.string.rm_calevent);
        this.ls_tml_actions[22] = getString(R.string.rm_sendcontactlists);
        this.ls_tml_actions[23] = getString(R.string.rm_help);
        this.ls_tml_actions[24] = getString(R.string.rm_getsms);
        this.ls_tml_actions[25] = getString(R.string.rm_installlist);
        this.ls_tml_actions[26] = getString(R.string.rm_getdir);
        this.ls_tml_actions[27] = getString(R.string.rm_getfile);
        this.ls_tml_actions[28] = getString(R.string.rm_playfile);
        this.ls_tml_actions[29] = getString(R.string.rm_networkdata);
        this.ls_tml_actions[30] = getString(R.string.rm_add_contacts);
        this.ls_tml_actions[31] = getString(R.string.rm_bluetooth);
        this.ls_tml_actions[32] = getString(R.string.rm_smsreport);
        this.ls_tml_actions[33] = getString(R.string.rm_callreport);
        TML_Library.Debug("building the RD");
        for (int i = 0; i < this.ls_tml_actions.length; i++) {
            try {
                this.rd = new RowData(i, this.ls_tml_actions[i]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.data.add(this.rd);
        }
        TML_Library.Debug("Calling adapter");
        adapter = new CustomAdapter(this, R.layout.newrulemenu, R.id.title, this.data);
        setListAdapter(adapter);
        getListView().setTextFilterEnabled(true);
        getListView().setDividerHeight(4);
    }

    public void onResume() {
        int last_position;
        super.onResume();
        setContentView(R.layout.newrulesitems);
        TML_Library.UpdateToFree(this);
        TML_Library.Debug("Now in Resume of NewRulesMenu");
        ((TextView) findViewById(R.id.header)).setText("Tickle my Phone " + getString(R.string.MM_Manage_Rules));
        if (TML_Library.GetParameter("ACTION_SELECTED").equals("UNDEFINED")) {
            TML_Library.SetPref(this, "ACTION_SELECTED", "0");
        }
        try {
            last_position = Integer.parseInt(TML_Library.GetParameter("ACTION_SELECTED"));
        } catch (NumberFormatException e) {
            last_position = 0;
        }
        TML_Library.Debug("************Now pointing " + last_position);
        getListView().setSelection(last_position);
    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        ItemSelected = ((RowData) this.data.get(position)).mTitle;
        TML_Library.HapticFeedback(this);
        TML_Library.SetParameter("RULE_OPTION", position);
        TML_Library.SetParameter("ACTION_SELECTED", new StringBuilder(String.valueOf(position)).toString());
        TML_Library.Debug("****************Action selected and stored = " + position);
        startActivity(new Intent(this, SMSDynamicRules.class));
    }
}
