package com.princeton.prayforme.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.R;
import com.princeton.prayforme.Security;
import com.princeton.prayforme.adapter.PrayerReplyAdapter;
import com.princeton.prayforme.asynctask.AsyncGet;
import com.princeton.prayforme.helper.SharedPrefsHelper;
import com.princeton.prayforme.helper.URLHelper;
import com.princeton.prayforme.model.Prayer;
import com.princeton.prayforme.model.PrayerReply;
import com.princeton.prayforme.model.Reply;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Random;

public class PrayerRepliesActivity extends Activity {

    List<Prayer> prayers;
//    List<PrayerReply> prayerReplies;
    ViewPager viewPager;
    PrayerReplyAdapter prayerReplyAdapter;
    SharedPrefsHelper prefsHelper;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        GlobalConstants.log(getLocalClassName(), "create");
        setContentView(R.layout.activity_prayerview);
        prefsHelper = new SharedPrefsHelper(getApplicationContext());

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("prayers");
        prayers =  bundle.getParcelableArrayList("prayers");
        int pos = intent.getIntExtra("pos", 0);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        prayerReplyAdapter = new PrayerReplyAdapter(PrayerRepliesActivity.this);
        viewPager.setAdapter(prayerReplyAdapter);
//        createDummyPrayers();

        populatePrayers();
        viewPager.setCurrentItem(pos);
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
        AsyncGet<Prayer> getPrayerTask = new AsyncGet<Prayer>(URLHelper.getPrayerURL(1), Prayer.class) {
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
}