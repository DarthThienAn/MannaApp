package com.princeton.prayforme.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.*;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.R;
import com.princeton.prayforme.Security;
import com.princeton.prayforme.helper.SharedPrefsHelper;

public class AuthActivity extends Activity {

    private EditText nameEdit;
    private EditText signatureEdit;
    private TextView goButton;
    private TextView anonButton;

    SharedPrefsHelper prefsHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefsHelper = new SharedPrefsHelper(getApplicationContext());
//        if (prefsHelper.getFirst());
//            startPrayersActivity();
//        else {
            setContentView(R.layout.activity_auth);

            nameEdit = (EditText) findViewById(R.id.auth_name_edit);
            signatureEdit = (EditText) findViewById(R.id.auth_signature_edit);
            goButton = (TextView) findViewById(R.id.auth_btn_go);
            anonButton = (TextView) findViewById(R.id.auth_btn_anon);

            goButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (GlobalConstants.isNullOrEmpty(nameEdit.getText().toString())) {
                        Toast.makeText(AuthActivity.this, R.string.auth_name_toast, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if (GlobalConstants.isNullOrEmpty(signatureEdit.getText().toString())) {
                        Toast.makeText(AuthActivity.this, R.string.auth_signature_toast, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String name = nameEdit.getText().toString();
                    String signature = signatureEdit.getText().toString();
                    prefsHelper.saveName(name);
                    prefsHelper.saveSignature(Security.getMD5(name, signature));
                    prefsHelper.setFirst();
                    startPrayersActivity();
                }
            });

            anonButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    prefsHelper.clear();
                    prefsHelper.saveName(Security.ANONYMOUS_NAME);
                    prefsHelper.saveSignature(Security.ANONYMOUS_SIGNATURE);
                    prefsHelper.setFirst();
                    startPrayersActivity();
                }
            });
//        }
    }

    private void startHomeActivity() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
    }

    private void startPrayersActivity() {
        GlobalConstants.log("Auth", prefsHelper.getName() + ": " + prefsHelper.getSignature());
        startActivity(new Intent(getApplicationContext(), PrayersActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
