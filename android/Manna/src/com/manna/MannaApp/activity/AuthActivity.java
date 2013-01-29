package com.manna.MannaApp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.manna.MannaApp.R;
import com.manna.MannaApp.helper.SharedPrefsHelper;

public class AuthActivity extends Activity {

    private EditText nameEntry;
    private EditText yearEntry;
    private EditText emailEntry;
    private Button goButton;
    private Button anonButton;

    SharedPrefsHelper prefsHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefsHelper = new SharedPrefsHelper(getApplicationContext());
//        if (prefsHelper.getFirst());
//            startHomeActivity();
//        else {
            setContentView(R.layout.activity_auth);

            nameEntry = (EditText) findViewById(R.id.auth_name_entry);
            yearEntry = (EditText) findViewById(R.id.auth_year_entry);
            emailEntry = (EditText) findViewById(R.id.auth_email_entry);
            goButton = (Button) findViewById(R.id.auth_btn_go);
            anonButton = (Button) findViewById(R.id.auth_btn_anon);

            goButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    prefsHelper.saveName(nameEntry.getText().toString());
                    prefsHelper.saveYear(yearEntry.getText().toString());
                    prefsHelper.saveEmail(emailEntry.getText().toString());
                    prefsHelper.setFirst();
                    startHomeActivity();
                }
            });

            anonButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    prefsHelper.setFirst();
                    startHomeActivity();
                }
            });
//        }
//
//        WebView webView = new WebView(getApplicationContext());
//        webView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
//
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setUserAgentString(webView.getSettings().getUserAgentString());
//        Log.d("PPFM", webView.getSettings().getUserAgentString());
//        addContentView(webView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
//        webView.loadUrl("https://login.persona.org");
    }

    private void startHomeActivity() {
        Log.d("MANNA", "start home");
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
    }
}
