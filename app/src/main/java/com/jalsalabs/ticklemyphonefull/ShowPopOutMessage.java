package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class ShowPopOutMessage extends Activity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Builder adb = new Builder(this);
        adb.setTitle("Tickle my Phone");
        adb.setMessage(TML_Library.GetParameter("MESSAGE_BODY"));
        adb.setPositiveButton("Ok", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ShowPopOutMessage.this.finish();
            }
        });
        adb.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                ShowPopOutMessage.this.finish();
            }
        });
        adb.show();
    }
}
