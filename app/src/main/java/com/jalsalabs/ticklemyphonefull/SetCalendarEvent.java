package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import java.util.Calendar;

public class SetCalendarEvent extends Activity {
    public String message;
    public String phoneNo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String LS_BODY2NDTOKEN = TML_Library.GetParameter("BODY2NDTOKEN");
        String LS_SenderPhone = TML_Library.GetParameter("ORIGINAL_ADDRESS");
        Calendar cal = Calendar.getInstance();
        Uri EVENTS_URI = Uri.parse(getCalendarUriBase(this) + "events");
        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        values.put("calendar_id", Integer.valueOf(1));
        values.put("title", "Tickle my Phone Event");
        values.put("allDay", Integer.valueOf(0));
        values.put("dtstart", Long.valueOf(cal.getTimeInMillis() + 3600000));
        values.put("dtend", Long.valueOf(cal.getTimeInMillis() + 3900000));
        values.put("description", LS_BODY2NDTOKEN);
        values.put("visibility", Integer.valueOf(0));
        values.put("hasAlarm", Integer.valueOf(1));
        Uri event = cr.insert(EVENTS_URI, values);
        Uri REMINDERS_URI = Uri.parse(getCalendarUriBase(this) + "reminders");
        values = new ContentValues();
        values.put("event_id", Long.valueOf(Long.parseLong(event.getLastPathSegment())));
        values.put("method", Integer.valueOf(1));
        values.put("minutes", Integer.valueOf(10));
        cr.insert(REMINDERS_URI, values);
        TML_Library.sendSMSBig(this, LS_SenderPhone, "Calender event created event name :" + LS_BODY2NDTOKEN + "\nTickle my Phone");
        finish();
    }

    private String getCalendarUriBase(Activity act) {
        Cursor managedCursor = null;
        try {
            managedCursor = act.managedQuery(Uri.parse("content://calendar/calendars"), null, null, null, null);
        } catch (Exception e) {
        }
        if (managedCursor != null) {
            return "content://calendar/";
        }
        try {
            managedCursor = act.managedQuery(Uri.parse("content://com.android.calendar/calendars"), null, null, null, null);
        } catch (Exception e2) {
        }
        if (managedCursor != null) {
            return "content://com.android.calendar/";
        }
        return null;
    }
}
