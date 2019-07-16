package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.CallLog.Calls;
import android.text.format.DateFormat;
import java.sql.Date;
import java.util.Enumeration;
import java.util.Vector;

public class TML_Library2 {
    public static void GetCallDetailsReport(Context context, String EmailAddress) {
        String[] strFields = new String[]{"number", "type", "date", "numbertype", "name", "duration"};
        TML_Library.Debug("I am here 1");
        Cursor c = context.getContentResolver().query(Calls.CONTENT_URI, strFields, null, null, "date DESC");
        TML_Library.Debug("I am here 2");
        ((Activity) context).startManagingCursor(c);
        int numberColumn = c.getColumnIndex("number");
        int dateColumn = c.getColumnIndex("date");
        int typeColumn = c.getColumnIndex("type");
        int CachedName = c.getColumnIndex("name");
        int CachedNumberType = c.getColumnIndex("numbertype");
        int iDuration = c.getColumnIndex("duration");
        TML_Library.Debug("I am here 3");
        TML_Library.Debug("I am here 4");
        SQLiteDatabase db = new SQLiteOpenHelper(context, "tmltemp.db", null, 1) {
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            }

            public void onCreate(SQLiteDatabase db) {
            }
        }.getWritableDatabase();
        db.execSQL("CREATE TEMP TABLE IF NOT EXISTS  tmlcalllog (c_phone text,c_type TEXT, c_date text,c_cache_num_type text , c_cache_name text,c_duration text)");
        db.execSQL("delete from tmlcalllog");
        while (c.moveToNext()) {
            String C_PhoneNumber = c.getString(numberColumn);
            String C_Dateddmmyyy = DateFormat.format("dd.MMM.yyyy h:mm a", new Date(Long.parseLong(c.getString(dateColumn)))).toString();
            int callType = c.getInt(typeColumn);
            String C_ChachedName = c.getString(CachedName);
            String C_CachedNumberType = c.getString(CachedNumberType);
            String C_Duration = c.getString(iDuration);
            String C_CallType = "";
            switch (callType) {
                case 1:
                    C_CallType = "INCOMING";
                    break;
                case 2:
                    C_CallType = "OUTGOING";
                    break;
                case 3:
                    C_CallType = "MISSED";
                    break;
                default:
                    break;
            }
            String q = "'";
            String m = ",";
            String Values = "";
            String FinalSQL = "INSERT INTO tmlcalllog values (" + new StringBuilder(String.valueOf(q)).append(C_PhoneNumber).append(q).append(m).append(q).append(C_CallType).append(q).append(m).append(q).append(C_Dateddmmyyy).append(q).append(m).append(q).append(C_CachedNumberType).append(q).append(m).append(q).append(C_ChachedName).append(q).append(m).append(q).append(C_Duration).append(q).toString() + ")";
            TML_Library.Debug("Final SQL " + FinalSQL);
            db.execSQL(FinalSQL);
        }
        String LS_Date = TML_Library.GetDateFormat("dd MMMMM yyyy");
        String HTML_Begin = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("\n<html xmlns=\"http://www.w3.org/1999/xhtml\">\n<a name=\"top\"></a>\n" + "\n<img src=\"http://dl.dropbox.com/u/27314855/image001.gif\"></img>")).append("\n<img src=\"https://chart.googleapis.com/chart?chst=d_text_outline&chld=FF6600|30|h|FF6600|_|Tickle my Phone |Call Log Detail Report as on :").append(LS_Date).append(" ").append(TML_Library.GetDateFormat("h:mm:ss a")).append("\"></img>").toString())).append("<br><br><br><br><br><br><br><br>").toString())).append("&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; <img src=\"https://chart.googleapis.com/chart?chst=d_text_outline&chld=3923D6|20|h|3923D6|_|Options\"></img>").toString())).append("\n&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<LI ><A  HREF='#id1x'>........................................................................ No of calls Coming to your phone</A>").toString())).append("\n&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<LI ><A  HREF='#id2x'>........................................................................ No of calls going out from your phone</A>").toString())).append("\n&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<LI ><A  HREF='#id3x'>........................................................................ No of missed calls received</A>").toString())).append("\n&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<LI ><A  HREF='#id4x'>........................................................................ Duration of call (minutes)  coming to your phone</A>").toString())).append("\n&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<LI ><A  HREF='#id5x'>........................................................................ Duration of call (minutes)  going out from your phone</A>").toString())).append("\n&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<LI ><A  HREF='#id6x'>........................................................................ Detail Call log Report</A>").toString())).append("\n<br><br><br><br><br><br><br><br><br>").toString();
        String HTML_End = TML_Library.getHTMLFooter(context);
        String HTML_IncomingCalls_PieOut = GoogleChartPie(db, "No of calls Coming to your phone", "select c_phone||'-'||c_cache_name ,count(*) count from  tmlcalllog where  c_type = 'INCOMING' group by c_phone||'-'||c_cache_name order by count desc", "id1", context);
        String HTML_OutGoingCalls_PieOut = GoogleChartPie(db, "No of calls going out from your phone", "select c_phone||'-'||c_cache_name ,count(*) count from  tmlcalllog where  c_type = 'OUTGOING' group by c_phone||'-'||c_cache_name order by count desc", "id2", context);
        String HTML_MissedCalls_PieOut = GoogleChartPie(db, "No of missed calls received", "select c_phone||'-'||c_cache_name ,count(*) count from  tmlcalllog where  c_type = 'MISSED' group by c_phone||'-'||c_cache_name order by count desc", "id3", context);
        String HTML_IncomingCallDuration_PieOut = GoogleChartPie(db, "Duration of call (minutes)  coming to your phone", "select c_phone||'-'||c_cache_name ,round(sum(c_duration/60)) count from  tmlcalllog where  c_type = 'INCOMING' group by c_phone||'-'||c_cache_name order by count desc", "id4", context);
        String HTML_OutGoingCallDuration_PieOut = GoogleChartPie(db, "Duration of call (minutes)  going out from your phone", "select c_phone||'-'||c_cache_name ,round(sum(c_duration/60)) count from  tmlcalllog where  c_type = 'OUTGOING' group by c_phone||'-'||c_cache_name order by count desc", "id5", context);
        String HTML_Tableout = GoogleChartTableFormat(db, "Detailed call log (Click column header to sort)", "select c_phone,c_cache_name,c_type,c_date,c_duration/60 count from tmlcalllog ", "id6", context);
        String FileName = TML_Library.getTMLPath() + "tmlcalllogreport.tmp";
        TML_Library.CreateLogText(context, FileName, "\n" + HTML_Begin + "\n" + "" + "\n" + HTML_IncomingCalls_PieOut + "\n" + HTML_OutGoingCalls_PieOut + "\n" + HTML_MissedCalls_PieOut + "\n" + HTML_IncomingCallDuration_PieOut + "\n" + HTML_OutGoingCallDuration_PieOut + "\n" + HTML_Tableout + "\n" + HTML_End);
        String EmailSubject = "";
        String EmailBody = "";
        EmailBody = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("Detail Call Log report is attached here.  File is in TMP(Tickle my Phone) format." + "\n\nFollowing steps to be followed to open the report :")).append("\n-> Download the attached file to local folder").toString())).append("\n-> Open the file in any browser which supports  Flash").toString())).append("\n\n\n Supported browsers:").toString())).append("\nGoogle Chrome").toString())).append("\nInternet explorer").toString())).append("\nFirefox").toString())).append("\nOpera").toString())).append("\nSafari").toString())).append("\n\n\n-").append(context.getString(R.string.app_name)).toString();
        Context context2 = context;
        TML_Library.SendEmail(context2, TML_Library.GetU(context), TML_Library.GetP(context), EmailAddress, "Tickle my Phone sent Call LOG detailed analytical report.", EmailBody, FileName);
        TML_Library.Debug("I am here 11");
        db.close();
        TML_Library.ShowAlertMessage(context, "Work completed srinath");
    }

    public static String GoogleChartTableFormat(SQLiteDatabase db, String Title, String P_SqlQuery, String chartid, Context context) {
        boolean free;
        String HTML_out = "";
        String q = "'";
        String m = ",";
        String Table_Format_H = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "<a name=\"" + chartid + "x\" id=\"" + chartid + "x\"></a>\n")).append("<h2>").append(Title).append("</h2>\n").toString())).append("\n<html>").toString())).append("\n  <head>").toString())).append("\n    <script type='text/javascript' src='https://www.google.com/jsapi'></script>").toString())).append("\n    <script type='text/javascript'>").toString())).append("\n      google.load('visualization', '1', {packages:['table']});").toString())).append("\n      google.setOnLoadCallback(drawTable);").toString())).append("\n      function drawTable() {").toString())).append("\n        var data = new google.visualization.DataTable();").toString())).append("\n        data.addColumn('string', 'Number');").toString())).append("\n        data.addColumn('string', 'Name');").toString())).append("\n        data.addColumn('string', 'Call Type');").toString())).append("\n        data.addColumn('string', 'Date');").toString())).append("\n        data.addColumn('number', 'Duration(Mins)');").toString())).append("\n        data.addColumn('number', 'Call no (Sorting)');").toString())).append("\n        data.addRows([").toString();
        String Table_Format_T = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "\n        ]);")).append("\n").toString())).append("\n        var table = new google.visualization.Table(document.getElementById('table_div'));").toString())).append("\n        table.draw(data, {showRowNumber: true});").toString())).append("\n      }").toString())).append("\n    </script>").toString())).append("\n  </head>").toString())).append("\n").toString())).append("\n  <body>").toString())).append("\n    <div id='table_div'></div>").toString())).append("\n  </body>").toString())).append("\n</html>").toString())).append("\n<a href=\"#top\">Back to Menu</a> ").toString();
        Cursor cursor = db.rawQuery(P_SqlQuery, null);
        HTML_out = Table_Format_H;
        boolean firstrow = true;
        int rownum = 1;
        if (TML_Library.GetPref(context, "KEY_IS_FREE").equals("Y")) {
            free = true;
        } else {
            free = false;
        }
        while (cursor.moveToNext()) {
            String s_sender = cursor.getString(0);
            String s_cache_name = cursor.getString(1);
            String s_type = cursor.getString(2);
            String s_date = cursor.getString(3);
            String s_duratio = cursor.getString(4);
            String first_comma = "";
            if (firstrow) {
                first_comma = "";
            } else {
                first_comma = ",";
            }
            TML_Library.Debug(s_sender);
            TML_Library.Debug(s_type);
            TML_Library.Debug(s_date);
            TML_Library.Debug(s_cache_name);
            TML_Library.Debug(s_duratio);
            if (free && rownum > 5) {
                s_sender = "XXXXXXXXXXX";
                s_cache_name = "You are using free version,Full version will have name and no.";
            }
            HTML_out = new StringBuilder(String.valueOf(HTML_out)).append(first_comma).append("\n[").append(q).append(s_sender).append(q).append(m).append(q).append(s_cache_name).append(q).append(m).append(q).append(s_type).append(q).append(m).append(q).append(s_date).append(q).append(m).append(s_duratio).append(m).append(rownum).append("]").toString();
            rownum++;
            firstrow = false;
        }
        TML_Library.Debug("I am here 20");
        return new StringBuilder(String.valueOf(HTML_out)).append(Table_Format_T).toString();
    }

    public static String GoogleChartPie(SQLiteDatabase db, String p_title, String P_SqlQuery, String chartid, Context context) {
        boolean free;
        String HTML_out = "";
        String q = "'";
        String m = ",";
        String Pie_Html_H = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "<a name=\"" + chartid + "x\" id=\"" + chartid + "x\"></a>\n")).append("\n<html>").toString())).append("\n  <head>").toString())).append("\n    <script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>").toString())).append("\n    <script type=\"text/javascript\">").toString())).append("\n      google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});").toString())).append("\n      google.setOnLoadCallback(drawChart);").toString())).append("\n      function drawChart() {").toString())).append("\n        var data = google.visualization.arrayToDataTable([").toString())).append("          ['Name', 'Calls Made'],").toString();
        String Pie_Html_T = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "\n       ]);")).append("\n        var options = {").toString())).append("\n          title: '").append(p_title).append("', is3D:true").toString())).append("\n        };").toString())).append("\n        var chart = new google.visualization.PieChart(document.getElementById('").append(chartid).append("'));").toString())).append("\n        chart.draw(data, options);").toString())).append("\n      }").toString())).append("\n    </script>").toString())).append("\n  </head>").toString())).append("\n  <body>").toString())).append("\n    <div id=\"").append(chartid).append("\" style=\"width: 900px; height: 500px;\"></div>").toString())).append("\n  </body>").toString())).append("\n</html>").toString())).append("\n<a href=\"#top\">Back to Menu</a> ").toString();
        Cursor cursor = db.rawQuery(P_SqlQuery, null);
        HTML_out = Pie_Html_H;
        boolean firstrow = true;
        if (TML_Library.GetPref(context, "KEY_IS_FREE").equals("Y")) {
            free = true;
        } else {
            free = false;
        }
        int rownum = 0;
        while (cursor.moveToNext()) {
            String s_sender = cursor.getString(0);
            String s_count = cursor.getString(1);
            String first_comma = "";
            if (firstrow) {
                first_comma = "";
            } else {
                first_comma = ",";
            }
            if (free && rownum <= 3 && rownum >= 5) {
                s_sender = "XXX You are using free version. Upgrade to Full.";
            }
            HTML_out = new StringBuilder(String.valueOf(HTML_out)).append(first_comma).append("\n[").append(q).append(s_sender).append(q).append(m).append(s_count).append("]").toString();
            rownum++;
            firstrow = false;
            TML_Library.Debug(s_sender);
            TML_Library.Debug(s_count);
        }
        TML_Library.Debug("I am here 20");
        return new StringBuilder(String.valueOf(HTML_out)).append(Pie_Html_T).toString();
    }

    public static String GoogleDashBoardPieChart(SQLiteDatabase db, String p_title, String P_SqlQuery, String chartid) {
        String HTML_out = "";
        String q = "'";
        String m = ",";
        String DashbrdPie_Html_H = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "\n <html xmlns=\"http://www.w3.org/1999/xhtml\">")).append("\n   <head>").toString())).append("\n     <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"/>").toString())).append("\n     <title>").toString())).append("\n       Tickle my Phone Call Log Analytics").toString())).append("\n     </title>").toString())).append("\n     <script type=\"text/javascript\" src=\"http://www.google.com/jsapi\"></script>").toString())).append("\n     <script type=\"text/javascript\">").toString())).append("\n       google.load('visualization', '1.1', {packages: ['controls']});").toString())).append("\n     </script>").toString())).append("\n     <script type=\"text/javascript\">").toString())).append("\n       function drawVisualization() {").toString())).append("\n         // Prepare the data").toString())).append("\n         var data = google.visualization.arrayToDataTable([").toString())).append("\n \t\t\t['Name', 'Call Type', 'Call Count'],").toString();
        String DashbrdPie_Html_T = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("" + "\n         ]);")).append("\n       ").toString())).append("\n         // Define a slider control for the Age column.").toString())).append("\n         var slider = new google.visualization.ControlWrapper({").toString())).append("\n           'controlType': 'NumberRangeFilter',").toString())).append("\n           'containerId': 'control1',").toString())).append("\n           'options': {").toString())).append("\n             'filterColumnLabel': 'Call Count',").toString())).append("\n           'ui': {'labelStacking': 'vertical'}").toString())).append("\n           }").toString())).append("\n         });").toString())).append("\n       ").toString())).append("\n         // Define a category picker control for the Gender column").toString())).append("\n         var categoryPicker = new google.visualization.ControlWrapper({").toString())).append("\n           'controlType': 'CategoryFilter',").toString())).append("\n           'containerId': 'control2',").toString())).append("\n           'options': {").toString())).append("\n             'filterColumnLabel': 'Call Type',").toString())).append("\n             'ui': {").toString())).append("\n             'labelStacking': 'vertical',").toString())).append("\n               'allowTyping': false,").toString())).append("\n \t      'allowMultiple': false").toString())).append("\n             }").toString())).append("\n           }").toString())).append("\n         });").toString())).append("\n       ").toString())).append("\n         // Define a Pie chart").toString())).append("\n         var pie = new google.visualization.ChartWrapper({").toString())).append("\n           'chartType': 'PieChart',").toString())).append("\n           'containerId': 'chart1',").toString())).append("\n           'options': {").toString())).append("\n             'width': 750,").toString())).append("\n             'height': 900,").toString())).append("\n             'title': 'Call details title',").toString())).append("\n             'chartArea': {'left': 15, 'top': 15, 'right': 0, 'bottom': 0},").toString())).append("\n \t      is3D:true").toString())).append("\n           },").toString())).append("\n           // Instruct the piechart to use colums 0 (Name) and 3 (Donuts Eaten)").toString())).append("\n           // from the 'data' DataTable.").toString())).append("\n           'view': {'columns': [0, 2]}").toString())).append("\n         });").toString())).append("\n       ").toString())).append("\n         // Define a table").toString())).append("\n         var table = new google.visualization.ChartWrapper({").toString())).append("\n           'chartType': 'Table',").toString())).append("\n           'containerId': 'chart2',").toString())).append("\n           'options': {").toString())).append("\n             'width': '600px'").toString())).append("\n           }").toString())).append("\n         });").toString())).append("\n       ").toString())).append("\n         // Create a dashboard").toString())).append("\n         new google.visualization.Dashboard(document.getElementById('dashboard')).").toString())).append("\n             // Establish bindings, declaring the both the slider and the category").toString())).append("\n             // picker will drive both charts.").toString())).append("\n             bind([slider, categoryPicker], [pie]).").toString())).append("\n             // Draw the entire dashboard.").toString())).append("\n             draw(data);").toString())).append("\n       }").toString())).append("\n       ").toString())).append("\n ").toString())).append("\n       google.setOnLoadCallback(drawVisualization);").toString())).append("\n     </script>").toString())).append("\n   </head>").toString())).append("\n   <body style=\"font-family: Arial;border: 0 none;\">").toString())).append("\n     <div id=\"dashboard\">").toString())).append("\n       <table>").toString())).append("\n         <tr style='vertical-align: top'>").toString())).append("\n           <td style='width: 300px; font-size: 0.9em;'>").toString())).append("\n             <div id=\"control1\"></div>").toString())).append("\n             <div id=\"control2\"></div>").toString())).append("\n             <div id=\"control3\"></div>").toString())).append("\n           </td>").toString())).append("\n           <td style='width: 600px'>").toString())).append("\n             <div style=\"float: left;\" id=\"chart1\"></div>").toString())).append("\n             <div style=\"float: left;\" id=\"chart2\"></div>").toString())).append("\n             <div style=\"float: left;\" id=\"chart3\"></div>").toString())).append("\n           </td>").toString())).append("\n         </tr>").toString())).append("\n       </table>").toString())).append("\n     </div>").toString())).append("\n   </body>").toString())).append("\n </html>").toString();
        Cursor cursor = db.rawQuery(P_SqlQuery, null);
        HTML_out = DashbrdPie_Html_H;
        boolean firstrow = true;
        while (cursor.moveToNext()) {
            String Column1 = cursor.getString(0);
            String Column2 = cursor.getString(1);
            String Column3 = cursor.getString(2);
            String first_comma = "";
            if (firstrow) {
                first_comma = "";
            } else {
                first_comma = ",";
            }
            HTML_out = new StringBuilder(String.valueOf(HTML_out)).append(first_comma).append("\n[").append(q).append(Column1).append(q).append(m).append(q).append(Column2).append(q).append(m).append(Column3).append("]").toString();
            firstrow = false;
        }
        TML_Library.Debug("I am here 20 " + P_SqlQuery);
        HTML_out = new StringBuilder(String.valueOf(HTML_out)).append(DashbrdPie_Html_T).toString();
        TML_Library.Debug("HTML out size" + HTML_out.length());
        return HTML_out;
    }

    public static String GetSMSContentReport(Context context) {
        String HTML_Begin = "";
        String HTML_End = "";
        String Final_HTML = "";
        String MainIndexHTML = "\n";
        String LS_Date = TML_Library.GetDateFormat("dd MMMMM yyyy");
        int temp = 0;
        HTML_Begin = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("\n<html xmlns=\"http://www.w3.org/1999/xhtml\">\n<a name=\"top\"></a>\n" + "\n<img src=\"http://dl.dropbox.com/u/27314855/image001.gif\"></img>")).append("\n<img src=\"https://chart.googleapis.com/chart?chst=d_text_outline&chld=FF6600|30|h|FF6600|_|Tickle my Phone |SMS Text message detailed Report as on :").append(LS_Date).append(" ").append(TML_Library.GetDateFormat("h:mm:ss a")).append("\"></img>").toString())).append("<br><br><br><br><br><br><br><br>").toString())).append("<br><br><img src=\"https://chart.googleapis.com/chart?chst=d_text_outline&chld=3923D6|16|h|FFFFFF|_|Legend\"></img>").toString())).append("<br><img src=\"https://chart.googleapis.com/chart?chst=d_bubble_texts_big&chld=bbbr|4CC417|000000||You sent\"></img>").toString())).append("<br><img src=\"https://chart.googleapis.com/chart?chst=d_bubble_texts_big&chld=bb|FFB573|000000||Received\"></img>").toString())).append("&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; <img src=\"https://chart.googleapis.com/chart?chst=d_text_outline&chld=3923D6|20|h|3923D6|_|Index\"></img>").toString();
        HTML_End = TML_Library.getHTMLFooter(context);
        boolean firstrow = true;
        Cursor c = context.getContentResolver().query(Uri.parse("content://sms"), null, "type in (1,2)", null, "address asc, date desc ");
        ((Activity) context).startManagingCursor(c);
        int smsEntriesCount = c.getCount();
        String[] body = new String[smsEntriesCount];
        String[] number = new String[smsEntriesCount];
        String[] read = new String[smsEntriesCount];
        String[] status = new String[smsEntriesCount];
        String[] date = new String[smsEntriesCount];
        String[] type = new String[smsEntriesCount];
        String[] subject = new String[smsEntriesCount];
        String old_number = "X";
        int Free_count = 0;
        String isFree = TML_Library.GetPref(context, "KEY_IS_FREE");
        if (c.moveToFirst()) {
            for (int i = 0; i < smsEntriesCount; i++) {
                TML_Library.Debug("at Begin Lengt of Final_HTML" + Final_HTML.length() + " Length of MainIndexHTML" + MainIndexHTML.length());
                type[i] = c.getString(c.getColumnIndexOrThrow("type")).toString();
                body[i] = c.getString(c.getColumnIndexOrThrow("body")).toString();
                number[i] = c.getString(c.getColumnIndexOrThrow("address")).toString();
                date[i] = DateFormat.format("dd.MMM.yyyy h:mm a", new Date(Long.parseLong(c.getString(c.getColumnIndexOrThrow("date")).toString()))).toString();
                read[i] = c.getString(c.getColumnIndexOrThrow("read")).toString();
                status[i] = c.getString(c.getColumnIndexOrThrow("status")).toString();
                type[i] = c.getString(c.getColumnIndexOrThrow("type")).toString();
                subject[i] = "not working ";
                TML_Library.Debug(" Srinath 1");
                String LogoBody = "";
                TML_Library.Debug(" Doing for " + number[i]);
                temp++;
                String BodyText = body[i];
                String Br_center = "\n<br>" + "\n&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                String Br_left = "\n<br>" + "\n&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                String Br_right = "\n<br>" + "\n&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                TML_Library.Debug(" Srinath 2");
                if (isFree.equals("Y") && Free_count > 4) {
                    BodyText = "You are using Tickle my Phone Free version with Limited features. To see Actual messages, please consider buying.";
                }
                if (!old_number.equals(number[i])) {
                    Free_count = 0;
                    Final_HTML = new StringBuilder(String.valueOf(Final_HTML)).append(Br_center).toString();
                    if (firstrow) {
                        firstrow = false;
                    } else {
                        Final_HTML = new StringBuilder(String.valueOf(Final_HTML)).append("<html> <LI ><a href=\"#top\">Back to  Menu</a> </L1> </html>").toString();
                    }
                    Final_HTML = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(Final_HTML)).append("<a name=\"").append(number[i]).append("\" id=\"").append(number[i]).append("\"></a>").toString())).append("\n<hr /> <img src=\"https://chart.googleapis.com/chart?chst=d_text_outline&chld=FF65BB|20|h|00FF00|_|SMS text message conversaion : ").append(TML_Library.GetContactName(context, number[i])).append(" ").append(number[i]).append("\"></img>").toString();
                    MainIndexHTML = new StringBuilder(String.valueOf(MainIndexHTML)).append("\n&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<LI ><A  HREF='#").append(number[i]).append("'>........................................................................ SMS Conversation details with : ").append(TML_Library.GetContactName(context, number[i])).append(" ").append(number[i]).append("</A>").toString();
                }
                BodyText = "|" + wrapText(BodyText, 50);
                if (type[i].equals("1")) {
                    Final_HTML = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(Final_HTML)).append(Br_left).toString())).append("\n<img src=\"https://chart.googleapis.com/chart?chst=d_bubble_texts_big&chld=bb|FFB573|000000|").append(BodyText).append("|").append(date[i]).append("\"></img>").toString();
                }
                if (type[i].equals("2")) {
                    Final_HTML = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(Final_HTML)).append(Br_right).toString())).append("\n<img src=\"https://chart.googleapis.com/chart?chst=d_bubble_texts_big&chld=bbbr|4CC417|000000|").append(BodyText).append("|").append(date[i]).append("\"></img>").toString();
                }
                c.moveToNext();
                old_number = number[i];
                Free_count++;
                TML_Library.Debug("at End Lengt of Final_HTML" + Final_HTML.length() + " Length of MainIndexHTML" + MainIndexHTML.length());
            }
        }
        c.close();
        MainIndexHTML = new StringBuilder(String.valueOf(MainIndexHTML)).append("</html>").toString();
        Final_HTML = new StringBuilder(String.valueOf(Final_HTML)).append("<html>   <LI ><a href=\"#top\">Back to  Menu</a> </L1> </html>").toString();
        TML_Library.Debug("Lengt of Final_HTML" + Final_HTML.length() + " Length of MainIndexHTML" + MainIndexHTML.length());
        TML_Library.Debug("Final Exit");
        return new StringBuilder(String.valueOf(HTML_Begin)).append("\n").append(MainIndexHTML).append("\n").append(Final_HTML).append("\n").append(HTML_End).toString();
    }

    public static String wrap(String in, int len) {
        in = in.trim();
        if (in.length() < len) {
            return in;
        }
        if (in.substring(0, len).contains("\n")) {
            return new StringBuilder(String.valueOf(in.substring(0, in.indexOf("\n")).trim())).append("\n\n").append(wrap(in.substring(in.indexOf("\n") + 1), len)).toString();
        }
        int place = Math.max(Math.max(in.lastIndexOf(" ", len), in.lastIndexOf("\t", len)), in.lastIndexOf("-", len));
        return new StringBuilder(String.valueOf(in.substring(0, place).trim())).append("\n").append(wrap(in.substring(place), len)).toString();
    }

    public static String wrapwithpipe(String in, int len) {
        return "|" + wrap(in, len).replaceAll("\n", "|");
    }

    public static String wrapText(String text, int len) {
        if (text == null) {
            return "";
        }
        if (len <= 0 || text.length() <= len) {
            return text;
        }
        int i;
        char[] chars = text.toCharArray();
        Vector lines = new Vector();
        StringBuffer line = new StringBuffer();
        StringBuffer word = new StringBuffer();
        for (i = 0; i < chars.length; i++) {
            word.append(chars[i]);
            if (chars[i] == ' ') {
                if (line.length() + word.length() > len) {
                    lines.add(line.toString());
                    line.delete(0, line.length());
                }
                line.append(word);
                word.delete(0, word.length());
            }
        }
        if (word.length() > 0) {
            if (line.length() + word.length() > len) {
                lines.add(line.toString());
                line.delete(0, line.length());
            }
            line.append(word);
        }
        if (line.length() > 0) {
            lines.add(line.toString());
        }
        String[] ret = new String[lines.size()];
        int c = 0;
        Enumeration e = lines.elements();
        while (e.hasMoreElements()) {
            ret[c] = (String) e.nextElement();
            c++;
        }
        String[] lines2 = ret;
        String final_string = "";
        for (String append : lines2) {
            final_string = new StringBuilder(String.valueOf(final_string)).append(append).append("|").toString();
        }
        return final_string;
    }
}
