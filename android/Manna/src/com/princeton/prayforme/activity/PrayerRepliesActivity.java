package com.princeton.prayforme.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.R;
import com.princeton.prayforme.adapter.PrayerReplyAdapter;
import com.princeton.prayforme.asynctask.AsyncGet;
import com.princeton.prayforme.asynctask.AsyncPost;
import com.princeton.prayforme.helper.SharedPrefsHelper;
import com.princeton.prayforme.helper.URLHelper;
import com.princeton.prayforme.model.*;

import java.util.ArrayList;
import java.util.List;

public class PrayerRepliesActivity extends Activity {

    List<Prayer> prayers;
//    List<PrayerReply> prayerReplies;
    ViewPager viewPager;
    PrayerReplyAdapter prayerReplyAdapter;
    SharedPrefsHelper prefsHelper;

    ProgressDialog loadingDialog;
    EditText sendEdit;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        GlobalConstants.log(getLocalClassName(), "create");
        setContentView(R.layout.activity_prayerview);
        prefsHelper = new SharedPrefsHelper(getApplicationContext());

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(GlobalConstants.KEY_PRAYERS);
        prayers =  bundle.getParcelableArrayList(GlobalConstants.KEY_PRAYERS);
        int pos = intent.getIntExtra(GlobalConstants.KEY_POSITION, 0);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        prayerReplyAdapter = new PrayerReplyAdapter(PrayerRepliesActivity.this);
        viewPager.setAdapter(prayerReplyAdapter);
//        createDummyPrayers();

        populatePrayers();
        viewPager.setCurrentItem(pos);

        int post = viewPager.getCurrentItem();
//        prayerReplyAdapter.getItem(viewPager.getCurrentItem()).getPrayer().get;

        TextView sendBtn = (TextView) findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(postOnClickListener);
        sendEdit = (EditText) findViewById(R.id.send_edit);
    }

//    private void createDummyPrayers() {
//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            String content = "" + random.nextInt(100);
//            prayers.add(new Prayer("misc", "subject", content, Security.getMD5("name" + i), i, i + 1, i + 1));
//        }
//    }

    private void populatePrayers() {
        GlobalConstants.log("PRA", "populate");
        AsyncGet<Prayer> getPrayerTask = new AsyncGet<Prayer>(URLHelper.getPrayerURL(19), Prayer.class) {
            @Override
            protected void onPostExecute(Prayer s) {
                super.onPostExecute(s);
                GlobalConstants.log("PRA", "success: " + s);
                if (s != null)
                    prayerReplyAdapter.addItem(new PrayerReply(s, createDummyReplies()));
            }
        };

        getPrayerTask.execute();

//        Prayer prayer1 = getPrayerTask.getResult();
//        EventListener listener = new EventListener() {
//
//        };
//        prayerReplyAdapter.addItem(new PrayerReply(prayer1, createDummyReplies()));

            PrayerReply prayerReply = new PrayerReply(prayers.get(0), createDummyReplies());
            prayerReplyAdapter.addItem(prayerReply);


//        for (Prayer prayer : prayers) {
//            PrayerReply prayerReply = new PrayerReply(prayer, createDummyReplies());
//            prayerReplyAdapter.addItem(prayerReply);
//        }
    }

    private List<Reply> createDummyReplies() {
        String[] authors = { "Mark my name is really long", "Daniel", "David", "Joel", "Sam"};
        String[] subjects = { "You can do it", "We're here for you", "IMO", "that sucks", "prayin4u"};
        String[] content = { "I believe in u bro", "Hey how you all doing my name is Daniel and I'm looking to fight some lions next week, so if you can pray for that, it'd be great thanks!", "Pray for my life", "I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.", "Green eggs and ham"};
        long[] timestamp = { 1, 2, 3, 2, 1 };

        List<Reply> replies = new ArrayList<Reply>();

        for (int i = 0; i < 5; i++) {
            Reply reply = new Reply(authors[i], subjects[i], content[i], timestamp[i]);
            replies.add(reply);
        }

        return replies;
    }

    final View.OnClickListener postOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AsyncPost<ReplyPostResponse> task = new AsyncPost<ReplyPostResponse>(URLHelper.replyURL(), URLHelper.postReply(prefsHelper.getSignature(), prefsHelper.getName(), 1, " ", sendEdit.getText().toString()), ReplyPostResponse.class) {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    GlobalConstants.log("Replies", "pre");
                        loadingDialog = ProgressDialog.show(PrayerRepliesActivity.this, "",
                                "Loading. Please wait...", true);
                }

                @Override
                protected void onPostExecute(ReplyPostResponse s) {
                    super.onPostExecute(s);
                    GlobalConstants.log("Replies", "post");
                    if (loadingDialog != null) loadingDialog.dismiss();

                    sendEdit.setText("");
                    Toast.makeText(PrayerRepliesActivity.this, "Reply made successfully!", Toast.LENGTH_SHORT).show();
                }

            };
            task.execute();

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadingDialog != null) loadingDialog.dismiss();
    }
}