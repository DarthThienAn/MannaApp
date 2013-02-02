package com.princeton.prayforme.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.R;
import com.princeton.prayforme.Security;
import com.princeton.prayforme.asynctask.AsyncPost;
import com.princeton.prayforme.asynctask.AsyncPut;
import com.princeton.prayforme.helper.SharedPrefsHelper;
import com.princeton.prayforme.helper.URLHelper;
import com.princeton.prayforme.model.PrayerPostResponse;

public class PostActivity extends Activity {

    ViewGroup mainLayout;
    ViewGroup avatar;
    View signatureView;
    SharedPrefsHelper prefsHelper;
    int prayerId;

    ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final EditText subject = (EditText) findViewById(R.id.post_subject);
        final EditText text = (EditText) findViewById(R.id.post_text);

        Intent intent = getIntent();
        String subjectSeed = intent.getStringExtra(GlobalConstants.KEY_POST_SUBJECT);
        String textSeed = intent.getStringExtra(GlobalConstants.KEY_POST_TEXT);
        prayerId = intent.getIntExtra(GlobalConstants.KEY_POST_ID, -1);

        if (!GlobalConstants.isNullOrEmpty(subjectSeed))
            subject.setText(subjectSeed);
        if (!GlobalConstants.isNullOrEmpty(textSeed))
            text.setText(textSeed);

        avatar = (ViewGroup) findViewById(R.id.post_avatar);
        mainLayout = (ViewGroup) getWindow().getDecorView().getRootView();

        prefsHelper = new SharedPrefsHelper(getApplicationContext());

        String name = prefsHelper.getName();
        String signature = prefsHelper.getSignature();

        TextView author = (TextView) findViewById(R.id.post_author);
        author.setText(GlobalConstants.isNullOrEmpty(name) ? "Anonymous" : name);

        String md5 = Security.getMD5(name + signature);
        signatureView = Security.getColorSignature(getLayoutInflater(), mainLayout, md5);
        signatureView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        avatar.addView(signatureView);

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

                AsyncTask task;

                if (prayerId > 0) {
                    task = new AsyncPut(URLHelper.prayerURL(), URLHelper.postPrayer(prayerId, prefsHelper.getSignature(), prefsHelper.getName(), subject.getText().toString(), text.getText().toString())) {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            loadingDialog = ProgressDialog.show(PostActivity.this, "",
                                    "Loading. Please wait...", true);
                        }

                        @Override
                        protected void onPostExecute(Void s) {
                            super.onPostExecute(s);
                            postSuccess();
                        }
                    };
                }
                else {
                    task = new AsyncPost<PrayerPostResponse>(URLHelper.prayerURL(), URLHelper.postPrayer(prefsHelper.getSignature(), prefsHelper.getName(), subject.getText().toString(), text.getText().toString()), PrayerPostResponse.class) {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            loadingDialog = ProgressDialog.show(PostActivity.this, "",
                                    "Loading. Please wait...", true);
                        }

                        @Override
                        protected void onPostExecute(PrayerPostResponse s) {
                            super.onPostExecute(s);
                            postSuccess();
                        }

                    };
                }

                task.execute();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadingDialog != null) loadingDialog.dismiss();
    }

    private void postSuccess() {
        GlobalConstants.log("Post", "post: successful");
        if (loadingDialog != null) loadingDialog.dismiss();
        Toast.makeText(PostActivity.this, "Post success!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
