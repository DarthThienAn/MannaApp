package com.princeton.prayforme.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.R;
import com.princeton.prayforme.Security;
import com.princeton.prayforme.adapter.ListAdapter;
import com.princeton.prayforme.list.ReplyItem;
import com.princeton.prayforme.list.PrayerItem3;
import com.princeton.prayforme.model.Reply;
import com.princeton.prayforme.model.Prayer;

import java.util.ArrayList;
import java.util.List;

public class RepliesActivity extends Activity {

    ListView listview;
    List<Reply> replies;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replies);

        listview = (ListView) findViewById(R.id.list_view);
        adapter = new ListAdapter(RepliesActivity.this);
        replies = new ArrayList<Reply>();
        listview.setAdapter(adapter);

//        Prayer prayer = new Prayer("Mark", "Thesis killing me", "Hey guys my thesis is due next week and I haven't started. please pray for me.", Security.getMD5("mynameismark"), 2, 10, 10);
//        PrayerItem3 prayerItem = new PrayerItem3(RepliesActivity.this, prayer);
//        adapter.add(prayerItem);


        createDummyReplies();

        TextView sendButton = (TextView) findViewById(R.id.send_btn);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.send_edit);
                GlobalConstants.log("Replies", editText.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.close();
        menu.clear();
        GlobalConstants.log("Replies", "menu");
        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        GlobalConstants.log("Replies", "prep");
        return false;
    }

    private void createDummyReplies() {
        String[] authors = { "Mark my name is really long", "Daniel", "David", "Joel", "Sam"};
        String[] subjects = { "You can do it", "We're here for you", "IMO", "that sucks", "prayin4u"};
        String[] content = { "I believe in u bro", "Hey how you all doing my name is Daniel and I'm looking to fight some lions next week, so if you can pray for that, it'd be great thanks!", "Pray for my life", "I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.", "Green eggs and ham"};
        long[] timestamp = { 1, 2, 3, 2, 1 };

        for (int i = 0; i < 5; i++) {
            Reply reply = new Reply(authors[i], subjects[i], content[i], timestamp[i]);
            replies.add(reply);
            ReplyItem replyItem = new ReplyItem(reply);
            adapter.add(replyItem);
        }
    }
}
