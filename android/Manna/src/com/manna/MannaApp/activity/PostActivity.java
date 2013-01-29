package com.manna.MannaApp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.manna.MannaApp.GlobalConstants;
import com.manna.MannaApp.R;

public class PostActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final EditText text = (EditText) findViewById(R.id.post_text);

        TextView postButton = (TextView) findViewById(R.id.post_post);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalConstants.log("Post", "post button clicked: " + text.getText().toString());
            }
        });

    }


}
