package com.princeton.prayforme.activity;

import android.app.Activity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final EditText text = (EditText) findViewById(R.id.post_text);
        avatar = (ViewGroup) findViewById(R.id.post_avatar);
        mainLayout = (ViewGroup) getWindow().getDecorView().getRootView();

        prefsHelper = new SharedPrefsHelper(getApplicationContext());

        String name = prefsHelper.getName();
        String signature = prefsHelper.getSignature();

        TextView author = (TextView) findViewById(R.id.post_author);
        author.setText(name == null ? "Anonymous" : name);

        String md5 = Security.getMD5(name + signature);
        secView = Security.getColorSignature(getLayoutInflater(), mainLayout, md5);
        secView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        avatar.addView(secView);

        TextView postButton = (TextView) findViewById(R.id.post_post);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalConstants.log("Post", "post button clicked: " + text.getText().toString());
                AsyncPost<String> task = new AsyncPost<String>("http://api.sonar.me/v1/users/505622cde4b04828a245d6a5/statuses", buildParams(new ArrayList<NameValuePair>()), String.class);
                task.execute();
//                String md5 = Security.getMD5(text.getText().toString());
//                avatar.removeView(secView);
//                secView = Security.getColorSignature(layoutInflater, mainLayout, md5);
//                secView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                avatar.addView(secView);
            }
        });

    }


    public String buildParams(List<NameValuePair> params) {
        NameValuePair token = new BasicNameValuePair("auth_token", "yhjCcW0te5Hc7iLzH-aUSA");
        params.add(token);
        params.add(new BasicNameValuePair("locale", Locale.getDefault().toString()));
        params.add(new BasicNameValuePair("status", "!"));
        return URLEncodedUtils.format(params, "utf-8");
    }
}
