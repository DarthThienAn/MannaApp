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

public class PostActivity extends Activity {

    ViewGroup mainLayout;
    LayoutInflater layoutInflater;
    ViewGroup avatar;
    View secView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final EditText text = (EditText) findViewById(R.id.post_text);
        avatar = (ViewGroup) findViewById(R.id.post_avatar);
        mainLayout = (ViewGroup) getWindow().getDecorView().getRootView();
        layoutInflater = getLayoutInflater();
        secView = new View(this);
        avatar.addView(secView);

        TextView postButton = (TextView) findViewById(R.id.post_post);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalConstants.log("Post", "post button clicked: " + text.getText().toString());
                String md5 = Security.getMD5(text.getText().toString());
                avatar.removeView(secView);
                secView = Security.getColorSignature(layoutInflater, mainLayout, md5);
                secView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                avatar.addView(secView);
            }
        });

    }


}
