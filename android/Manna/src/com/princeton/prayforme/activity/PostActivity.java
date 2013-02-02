package com.princeton.prayforme.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.R;
import com.princeton.prayforme.Security;
import com.princeton.prayforme.asynctask.AsyncGet;
import com.princeton.prayforme.asynctask.AsyncPost;
import com.princeton.prayforme.helper.SharedPrefsHelper;
import com.princeton.prayforme.helper.URLHelper;
import com.princeton.prayforme.model.PostResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PostActivity extends Activity {

    ViewGroup mainLayout;
    ViewGroup avatar;
    View secView;
    SharedPrefsHelper prefsHelper;

    ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final EditText text = (EditText) findViewById(R.id.post_text);
        final EditText subject = (EditText) findViewById(R.id.post_subject);
        avatar = (ViewGroup) findViewById(R.id.post_avatar);
        mainLayout = (ViewGroup) getWindow().getDecorView().getRootView();

        prefsHelper = new SharedPrefsHelper(getApplicationContext());

        String name = prefsHelper.getName();
        String signature = prefsHelper.getSignature();

        TextView author = (TextView) findViewById(R.id.post_author);
        author.setText(GlobalConstants.isNullOrEmpty(name) ? "Anonymous" : name);

        String md5 = Security.getMD5(name + signature);
        secView = Security.getColorSignature(getLayoutInflater(), mainLayout, md5);
        secView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        avatar.addView(secView);

        TextView postButton = (TextView) findViewById(R.id.post_post);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalConstants.log("Post", "post: " + subject.getText().toString() + ": " + text.getText().toString());

                if (GlobalConstants.isNullOrEmpty(subject.getText().toString())) {
                    Toast.makeText(PostActivity.this, R.string.post_subject_toast, Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (GlobalConstants.isNullOrEmpty(text.getText().toString())) {
                    Toast.makeText(PostActivity.this, R.string.post_message_toast, Toast.LENGTH_SHORT).show();
                    return;
                }

                AsyncPost<PostResponse> task = new AsyncPost<PostResponse>(URLHelper.prayerURL(), URLHelper.postPrayer(prefsHelper.getSignature(), prefsHelper.getName(), subject.getText().toString(), text.getText().toString()), PostResponse.class) {
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loadingDialog = ProgressDialog.show(PostActivity.this, "",
                                "Loading. Please wait...", true);
                    }

                    @Override
                    protected void onPostExecute(PostResponse s) {
                        super.onPostExecute(s);
                        GlobalConstants.log("Post", "post: successful");
                        if (loadingDialog != null) loadingDialog.dismiss();
                        finish();
                    }

                };
                task.execute();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadingDialog != null) loadingDialog.dismiss();
    }
}
