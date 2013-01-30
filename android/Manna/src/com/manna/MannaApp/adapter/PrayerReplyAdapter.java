package com.manna.MannaApp.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.manna.MannaApp.GlobalConstants;
import com.manna.MannaApp.R;
import com.manna.MannaApp.list.ListItem;
import com.manna.MannaApp.list.PrayerItem;
import com.manna.MannaApp.list.PrayerItem3;
import com.manna.MannaApp.list.ReplyItem;
import com.manna.MannaApp.model.PrayerReply;
import com.manna.MannaApp.model.Reply;

import java.util.ArrayList;
import java.util.List;

public class PrayerReplyAdapter extends PagerAdapter {

    private List<PrayerReply> prayerReplies;
    LayoutInflater layoutInflater;

    public PrayerReplyAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
        prayerReplies = new ArrayList<PrayerReply>();
    }

    @Override
    public int getCount() {
        return prayerReplies.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup view = (ViewGroup) layoutInflater.inflate(R.layout.item_prayerview, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        ListAdapter listAdapter = new ListAdapter((Activity) layoutInflater.getContext());
        listView.setAdapter(listAdapter);


        PrayerItem prayerItem = new PrayerItem(prayerReplies.get(position).getPrayer());
        listAdapter.add(prayerItem);
//        ViewGroup prayerView = (ViewGroup) layoutInflater.inflate(prayerItem.getResourceId(), view, false);
//        prayerItem.populate(prayerView);
//        listView.addView(prayerView);


        for (Reply reply : prayerReplies.get(position).getReplies()) {
            ReplyItem replyItem = new ReplyItem(reply);
//            View replyView = layoutInflater.inflate(replyItem.getResourceId(), view, false);
//            replyItem.populate(replyView);
//            listView.addView(replyView);
            listAdapter.add(replyItem);
        }

        final EditText editText = (EditText) view.findViewById(R.id.send_edit);

        TextView sendButton = (TextView) view.findViewById(R.id.send_btn);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalConstants.log("Replies", editText.getText().toString());
            }
        });

        container.addView(view);
        return view;
//     return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
//        super.destroyItem(container, position, object);
    }

    public void addItem(PrayerReply prayerReply) {
        prayerReplies.add(prayerReply);
    }
}
