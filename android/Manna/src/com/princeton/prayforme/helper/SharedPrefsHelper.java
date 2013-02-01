package com.princeton.prayforme.helper;

import android.content.Context;
import android.content.SharedPreferences;
import com.princeton.prayforme.R;

public class SharedPrefsHelper {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String KEY_FIRST = "first";

    private static final String KEY_NAME = "name";
    private static final String KEY_SIGNATURE = "signature";

    public SharedPrefsHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveName(String name) {
        editor.putString(KEY_NAME, name);
        editor.commit();
    }

    public String getName() {
        return sharedPreferences.getString(KEY_NAME, "");
    }

    public void saveSignature(String signature) {
        editor.putString(KEY_SIGNATURE, signature);
        editor.commit();
    }

    public String getSignature() {
        return sharedPreferences.getString(KEY_SIGNATURE, "");
    }

    public void setFirst() {
        editor.putBoolean(KEY_FIRST, true);
        editor.commit();
    }

    public boolean getFirst() {
        return sharedPreferences.getBoolean(KEY_FIRST, false);
    }
}
