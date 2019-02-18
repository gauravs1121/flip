package com.example.flipassignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtils {

    private static String START_DATE = "start";
    private static String END_DATE = "end";


    private static SharedPreferences getSharedPreferenceInstanced(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }



    public static void saveStartDate(Context context, String start) {
        SharedPreferences.Editor editor = getSharedPreferenceInstanced(context).edit();
        editor.putString(START_DATE, start);
        editor.apply();
    }

    public static String getStartDate(Context context) {
        return getSharedPreferenceInstanced(context).getString(START_DATE, null);
    }


    public static String getEndDate(Context context) {
        return getSharedPreferenceInstanced(context).getString(END_DATE, null);
    }


    public static void saveEndDate(Context context, String endDate) {
        SharedPreferences.Editor editor = getSharedPreferenceInstanced(context).edit();
        editor.putString(END_DATE, endDate);
        editor.apply();
    }


}
