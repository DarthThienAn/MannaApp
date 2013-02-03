package com.princeton.prayforme.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.R;
import com.princeton.prayforme.adapter.ListAdapter;
import com.princeton.prayforme.adapter.PrayerReplyAdapter;
import com.princeton.prayforme.asynctask.AsyncGet;
import com.princeton.prayforme.asynctask.AsyncPost;
import com.princeton.prayforme.helper.SharedPrefsHelper;
import com.princeton.prayforme.helper.URLHelper;
import com.princeton.prayforme.list.ListItemType;
import com.princeton.prayforme.list.NoReplyItem;
import com.princeton.prayforme.list.PrayerReplyMainItem;
import com.princeton.prayforme.list.ReplyItem;
import com.princeton.prayforme.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PrayerReplyActivity extends Activity {

    List<Reply> replies;
    ListView listview;
    ListAdapter listAdapter;
    SharedPrefsHelper prefsHelper;

    ProgressDialog loadingDialog;
    EditText sendEdit;

    Prayer prayer;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        GlobalConstants.log(getLocalClassName(), "create");
        setContentView(R.layout.activity_prayerreply);
        prefsHelper = new SharedPrefsHelper(getApplicationContext());

        listview = (ListView) findViewById(R.id.list_view);
        listAdapter = new ListAdapter(PrayerReplyActivity.this);
        listview.setAdapter(listAdapter);

        Intent intent = getIntent();
        prayer = intent.getParcelableExtra(GlobalConstants.KEY_PRAYER);
        PrayerReplyMainItem prayerItem = new PrayerReplyMainItem(PrayerReplyActivity.this, prayer);
        listAdapter.add(prayerItem);

        replies = new ArrayList<Reply>();
        getReplies();

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

    private void getReplies() {
        GlobalConstants.log("PRA", "getRepliesList");
        int[] replyIds = prayer.getRepliesList();
        if ((replyIds == null) || (replyIds.length < 1)) {
            GlobalConstants.log("PRA", "no replies");
            listAdapter.add(new NoReplyItem());
        }
        else if (replyIds.length == 1) {
            AsyncGet<Reply> getReplyTask = new AsyncGet<Reply>(URLHelper.getReplyURL(replyIds), Reply.class) {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    GlobalConstants.log("PRA", "recent pre");
                    loadingDialog = ProgressDialog.show(PrayerReplyActivity.this, "",
                            "Loading. Please wait...", true);
                }

                @Override
                protected void onPostExecute(Reply reply) {
                    super.onPostExecute(reply);
                    GlobalConstants.log("PRA", "success: " + reply);
                    listAdapter.add(new ReplyItem(reply));


                    if (loadingDialog != null) loadingDialog.dismiss();
                }
            };
            getReplyTask.execute();
        }
        else {
            AsyncGet<Reply[]> getRepliesTask = new AsyncGet<Reply[]>(URLHelper.getReplyURL(replyIds), Reply[].class) {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    GlobalConstants.log("PRA", "recent pre");
                    loadingDialog = ProgressDialog.show(PrayerReplyActivity.this, "",
                            "Loading. Please wait...", true);
                }

                @Override
                protected void onPostExecute(Reply[] resultReplies) {
                    super.onPostExecute(resultReplies);
                    GlobalConstants.log("PRA", "success: " + Arrays.toString(resultReplies));
                    for (Reply reply : resultReplies) {
                        listAdapter.add(new ReplyItem(reply));
                    }

                    if (loadingDialog != null) loadingDialog.dismiss();
                }
            };
            getRepliesTask.execute();
        }
    }

    private List<Reply> createDummyReplies() {
        String[] authors = { "Mark my name is really long", "Daniel", "David", "Joel", "Sam"};
        String[] subjects = { "You can do it", "We're here for you", "IMO", "that sucks", "prayin4u"};
        String[] content = { "I believe in u bro", "Hey how you all doing my name is Daniel and I'm looking to fight some lions next week, so if you can pray for that, it'd be great thanks!", "Pray for my life", "I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.", "Green eggs and ham"};
        String[] timestamp = { "0", "0", "0", "0", "0" };

        List<Reply> replies = new ArrayList<Reply>();

        for (int i = 0; i < 5; i++) {
            Reply reply = new Reply(authors[i], content[i], timestamp[i]);
            replies.add(reply);
        }

        return replies;
    }

    final View.OnClickListener postOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (GlobalConstants.isNullOrEmpty(sendEdit.getText().toString())) {
                GlobalConstants.log("Replies", "empty comment; return");
                return;
            }

            AsyncPost<ReplyPostResponse> task = new AsyncPost<ReplyPostResponse>(URLHelper.replyURL(), URLHelper.postReply(prefsHelper.getSignature(), prefsHelper.getName(), prayer.getId(), " ", sendEdit.getText().toString()), ReplyPostResponse.class) {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    GlobalConstants.log("Replies", "pre");
                    loadingDialog = ProgressDialog.show(PrayerReplyActivity.this, "",
                            "Loading. Please wait...", true);
                }

                @Override
                protected void onPostExecute(ReplyPostResponse s) {
                    super.onPostExecute(s);
                    GlobalConstants.log("Replies", "post");
                    if (loadingDialog != null) loadingDialog.dismiss();
                    Reply reply = new Reply(prefsHelper.getName(), sendEdit.getText().toString(), new Date(System.currentTimeMillis()).toString());
                    if (listAdapter.getItem(listAdapter.getCount() - 1).getType() == ListItemType.NOREPLY)
                        listAdapter.remove(listAdapter.getItem(listAdapter.getCount() - 1));
                    listAdapter.add(new ReplyItem(reply));

                    sendEdit.setText("");
                    Toast.makeText(PrayerReplyActivity.this, "Reply made successfully!", Toast.LENGTH_SHORT).show();
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