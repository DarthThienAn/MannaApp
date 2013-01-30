package com.manna.MannaApp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.manna.MannaApp.GlobalConstants;
import com.manna.MannaApp.R;
import com.manna.MannaApp.adapter.ListAdapter;
import com.manna.MannaApp.list.RepliesItem;
import com.manna.MannaApp.list.PrayerItem3;
import com.manna.MannaApp.model.Reply;
import com.manna.MannaApp.model.Prayer;

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

        PrayerItem3 prayerItem = new PrayerItem3(RepliesActivity.this);
        Prayer prayer = new Prayer("Mark", "Thesis killing me", "Hey guys my thesis is due next week and I haven't started. please pray for me.", 2, 10, 10);
        prayerItem.setModel(prayer);
        adapter.add(prayerItem);


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
            RepliesItem repliesItem = new RepliesItem(RepliesActivity.this, reply);
            adapter.add(repliesItem);
        }
    }
}
