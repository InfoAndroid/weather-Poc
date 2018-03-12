package com.infoandroid.weatherpoc.utils;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by Mukesh on 3/3/2018.
 */

public class Validation {
    public static boolean isGpsEnabled(Context context) {
        boolean gps_enabled = false;
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return gps_enabled;
    }
}
