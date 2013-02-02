package com.princeton.prayforme;

import android.util.Log;

public class GlobalConstants {
    public static final String LOGTAG = "PPFM";
    public static final String API = "http://shielded-plateau-4848.herokuapp.com/";
    public static final String KEY_PRAYERS = "prayers";
    public static final String KEY_POSITION = "position";
    public static final String KEY_POST_SUBJECT = "post_subject";
    public static final String KEY_POST_TEXT = "post_text";
    public static final String KEY_POST_ID = "post_id";

    private static final boolean DEBUG = true;


    public static void log(String id, Object content) {
        if (DEBUG) Log.d(LOGTAG, id + ": " + content);
    }

    // implementation of JDK 1.6 String.isEmpty() method.
    // also return true if null
    public static boolean isNullOrEmpty(String s) {
        return  ((s == null) || (s.length() == 0));
    }
}
