package com.infoandroid.weatherpoc.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mukesh
 */
public class Shared_Preferance_utility {
    public static final String PrefName = "WeatherPOC";
    public static SharedPreferences sharedPreferences = null;
    public static final String PREF_BOOT_TIME = "PREF_BOOT_TIME";
    private SharedPreferences.Editor prefsEditor;

    public Shared_Preferance_utility(Context context) {

       // this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
        this.prefsEditor = sharedPreferences.edit();
    }



    /***********
     * set shared preferences
     *****************/
    public static void SetPreferences(Context ctx, String key, String value) {
        // save the data
        sharedPreferences = ctx.getSharedPreferences(PrefName, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    /***********
     * get shared preferences
     *****************/
    public static String getPreferences(Context ctx, String key) {

        sharedPreferences = ctx.getSharedPreferences(
                PrefName, 0);
        String value = sharedPreferences.getString(key, "");
        return value;

    }

    /***********
     * set shared preferences
     *****************/
    public static void SetBooleanPreferences(Context ctx, String key, boolean value) {
        // save the data
        sharedPreferences = ctx.getSharedPreferences(PrefName, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /***********
     * get shared preferences
     *****************/
    public static boolean getBooleanPreferences(Context ctx, String key) {

        sharedPreferences = ctx.getSharedPreferences(
                PrefName, 0);
        boolean value = sharedPreferences.getBoolean(key,false);
        return value;

    }

    /***********
     * clear all shared preferences
     *****************/
    public static void ClearPreferences(Context ctx) {
        sharedPreferences = ctx.getSharedPreferences(
                PrefName, 0);
        sharedPreferences.edit().clear().commit();
    }

    public static boolean isValidEmail(String emailId) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
    }
    public void removeKey(String Key) {
        prefsEditor.remove(Key).apply();
    }
}
