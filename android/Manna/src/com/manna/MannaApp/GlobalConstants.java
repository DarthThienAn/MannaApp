package com.manna.MannaApp;

import android.util.Log;

public class GlobalConstants {
    static public final String LOGTAG = "PPFM";


    public static void log(String id, Object content) {
        Log.d(LOGTAG, id + ": " + content.toString());
    }
}
