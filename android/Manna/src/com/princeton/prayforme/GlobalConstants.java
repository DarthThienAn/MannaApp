package com.princeton.prayforme;

import android.util.Log;

public class GlobalConstants {
    static public final String LOGTAG = "PPFM";
    static private final boolean DEBUG = true;


    public static void log(String id, Object content) {
        if (DEBUG) Log.d(LOGTAG, id + ": " + content.toString());
    }

    // implementation of JDK 1.6 String.isEmpty() method.
    // also return true if null
    public static boolean isNullOrEmpty(String s) {
        return  ((s == null) || (s.length() == 0));
    }
}
