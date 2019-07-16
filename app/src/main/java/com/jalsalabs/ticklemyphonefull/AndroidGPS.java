package com.jalsalabs.ticklemyphonefull;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AndroidGPS extends Activity {
    String YouGotLocation = "N";
    private boolean gps_enabled = false;
    private LocationListener locListener = new MyLocationListener();
    private LocationManager locManager;
    private boolean network_enabled = false;
    String phoneNo;

    public class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
            TML_Library.Debug("Location changed Srinath");
            AndroidGPS.this.phoneNo = TML_Library.GetParameter("ORIGINAL_ADDRESS");
            if (location != null) {
                AndroidGPS.this.locManager.removeUpdates(AndroidGPS.this.locListener);
                String lati = location.getLatitude();
                String lond = location.getLongitude();
                String Alti = location.getAltitude();
                String londitude = "Londitude: " + location.getLongitude();
                String latitude = "Latitude: " + location.getLatitude();
                String altitiude = "Altitiude: " + location.getAltitude();
                String accuracy = "Accuracy: " + location.getAccuracy();
                String time = "Time: " + location.getTime();
                String TotalString = new StringBuilder(String.valueOf(londitude)).append("\n").append(latitude).append("\n").append(altitiude).append("\n").append(accuracy).append("\n").append(time).toString();
                TML_Library.PutToast(AndroidGPS.this, new StringBuilder(String.valueOf(londitude)).append("\n").append(latitude).append("\n").append(altitiude).append("\n").append(accuracy).append("\n").append(time).toString());
                String addressString = "";
                if (TML_Library.isNetworkAvailable(AndroidGPS.this)) {
                    addressString = "No address found";
                    TML_Library.Debug("okay 1");
                    Context c = AndroidGPS.this;
                    TML_Library.Debug("okay 2");
                    Geocoder gc = new Geocoder(c, Locale.getDefault());
                    TML_Library.Debug("okay 3");
                    try {
                        TML_Library.Debug("okay 4");
                        List<Address> addresses = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        TML_Library.Debug("okay 5");
                        StringBuilder sb = new StringBuilder();
                        TML_Library.Debug("okay 6");
                        if (addresses.size() > 0) {
                            TML_Library.Debug("okay 7");
                            Address address = (Address) addresses.get(0);
                            for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                                TML_Library.Debug("address.getAddressLine Value of " + i + " is " + address.getAddressLine(i));
                                sb.append(address.getAddressLine(i)).append("\n");
                            }
                            sb.append(address.getCountryName());
                            addressString = sb.toString();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (addressString.equals("")) {
                    TML_Library.sendSMSBig(AndroidGPS.this, AndroidGPS.this.phoneNo, "\n\nhttp://maps.google.com/maps?q=" + lati + "," + lond + "+(TickleMyPhone)&iwloc=A&hl=en" + "\n\nAddr:\n" + "NO address found");
                } else {
                    String LS_MessageString = "http://maps.google.com/maps?q=" + lati + "," + lond + "+(TickleMyPhone)&iwloc=A&hl=en" + "\n\nAddr:\n" + addressString;
                    TML_Library.sendSMSBig(AndroidGPS.this, AndroidGPS.this.phoneNo, LS_MessageString);
                    TML_Library.sendSMSBig(AndroidGPS.this, AndroidGPS.this.phoneNo, LS_MessageString);
                }
                TML_Library.SetPref(AndroidGPS.this, "TEMPMESSAGE", TotalString);
                AndroidGPS.this.YouGotLocation = "Y";
                AndroidGPS.this.finish();
            }
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.splash);
        this.phoneNo = TML_Library.GetParameter("ORIGINAL_ADDRESS");
        TML_Library.Debug("I am at line 1 ");
        TML_Library.SetPref(this, "TEMPMESSAGE", "@@");
        super.onResume();
        TML_Library.Debug("I am at line 2 ");
        this.locManager = (LocationManager) getSystemService("location");
        TML_Library.Debug("I am at line 3 ");
        TML_Library.Debug("I am at line 4 ");
        try {
            TML_Library.Debug("I am at line 5 ");
            this.gps_enabled = this.locManager.isProviderEnabled("gps");
            TML_Library.Debug("I am at line 6 ");
        } catch (Exception e) {
            TML_Library.Debug("I am at line 7 ");
        }
        TML_Library.Debug("I am at line 888888888 ");
        try {
            TML_Library.Debug("I am at line 9 ");
            this.network_enabled = this.locManager.isProviderEnabled("network");
            TML_Library.Debug("I am at line 10 ");
        } catch (Exception e2) {
            TML_Library.Debug("I am at line 11 ");
        }
        TML_Library.Debug("I am at line 122222222222222 ");
        if (!(this.gps_enabled || this.network_enabled)) {
            TML_Library.PutToast(this, "Sorry, location is not determined. Please enable location providers");
            TML_Library.sendSMSBig(this, this.phoneNo, "Tickle my Phone\nSorry, location is not determined. Please enable location provider in the server");
            finish();
        }
        TML_Library.Debug("I am at line 13 ");
        if (this.gps_enabled) {
            TML_Library.Debug("I am at line 14 ");
            this.locManager.requestLocationUpdates("gps", 0, 0.0f, this.locListener);
            TML_Library.Debug("I am at line 15 ");
        }
        TML_Library.Debug("I am at line 166666666666 ");
        if (this.network_enabled) {
            TML_Library.Debug("I am at line 17 ");
            this.locManager.requestLocationUpdates("network", 0, 0.0f, this.locListener);
            TML_Library.Debug("I am at line 18 ");
        }
        TML_Library.Debug("I am coming out ");
        finish();
        TML_Library.Debug("finish done");
    }
}
