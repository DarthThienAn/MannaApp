package com.manna.MannaApp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.manna.MannaApp.GlobalConstants;
import com.manna.MannaApp.R;
import com.manna.MannaApp.adapter.PrayerReplyAdapter;
import com.manna.MannaApp.model.Prayer;
import com.manna.MannaApp.model.PrayerReply;
import com.manna.MannaApp.model.Reply;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrayerRepliesActivity extends Activity {

    List<Prayer> prayers;
//    List<PrayerReply> prayerReplies;
    ViewPager viewPager;
    PrayerReplyAdapter prayerReplyAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        GlobalConstants.log(getLocalClassName(), "create");
        setContentView(R.layout.activity_prayerview);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("prayers");
        prayers =  bundle.getParcelableArrayList("prayers");
        int pos = intent.getIntExtra("pos", 0);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        prayerReplyAdapter = new PrayerReplyAdapter(getLayoutInflater());
        viewPager.setAdapter(prayerReplyAdapter);
//        createDummyPrayers();

        populatePrayers();
        viewPager.setCurrentItem(pos);
    }

    private void createDummyPrayers() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String content = "" + random.nextInt(100);
            prayers.add(new Prayer("misc", "subject", content, i, i + 1, i + 1));
        }
    }

    private void populatePrayers() {
        for (Prayer prayer : prayers) {
//            View item = getLayoutInflater().inflate(R.layout.item_prayerview, viewPager, false);
//            ((TextView) item.findViewById(R.id.prayer_text)).setText(prayer.getContent());
            PrayerReply prayerReply = new PrayerReply(prayer, createDummyReplies());
            prayerReplyAdapter.addItem(prayerReply);
        }
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