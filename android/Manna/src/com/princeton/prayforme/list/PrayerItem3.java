package com.princeton.prayforme.list;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.R;
import com.princeton.prayforme.activity.RepliesActivity;
import com.princeton.prayforme.model.Prayer;

public class PrayerItem3 extends ListItem<Prayer> {
    private Context context;

    public PrayerItem3(Context context, Prayer model) {
        super(model);
        this.context = context;
    }

    @Override
    public ListItemType getType() {
        return ListItemType.PRAYER3;
    }

    @Override
    public int getResourceId() {
        return R.layout.item_prayer3;
    }

    @Override
    public void populate(View view) {
        TextView title = (TextView) view.findViewById(R.id.prayer_title);
        title.setText(model.getSubject());
        TextView text = (TextView) view.findViewById(R.id.prayer_text);
        text.setText(model.getMessage());

        TextView commentButton = (TextView) view.findViewById(R.id.prayer_btn_reply);
        commentButton.setText(String.format("Replies(%d)", model.getTimesReplied()));
        commentButton.setOnClickListener(repliesOnClickListener);

        TextView prayButton = (TextView) view.findViewById(R.id.prayer_btn_pray);
        prayButton.setText(String.format("Prays(%d)", model.getTimesPrayedFor()));
        prayButton.setEnabled(!model.isPrayedFor());

        prayButton.setOnClickListener(prayOnClickListener);

        TextView timestamp = (TextView) view.findViewById(R.id.prayer_timestamp);
        timestamp.setText(String.format("Posted by %s on %s", GlobalConstants.isNullOrEmpty(model.getPerson()) ? "Anonymous" : model.getPerson(), model.getTimestamp()));
    }

    private final View.OnClickListener repliesOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            context.startActivity(new Intent(context.getApplicationContext(), RepliesActivity.class));
//                TextView commentButton = (TextView) view.findViewById(R.id.prayer_btn_comment);
//                commentButton.setText("Comments(X)");
        }
    };

    private final View.OnClickListener prayOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            model.incTimesPrayed();
            TextView prayButton = (TextView) view.findViewById(R.id.prayer_btn_pray);
            prayButton.setEnabled(false);
            model.setPrayedFor(true);
            prayButton.setText(String.format("Prays(%d)", model.getTimesPrayedFor()));
        }
    };
}
