package com.manna.MannaApp.list;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.manna.MannaApp.R;
import com.manna.MannaApp.model.Reply;

public class ReplyItem extends ListItem<Reply> {
    public ReplyItem(Reply model) {
        super(model);
    }

    @Override
    public ListItemType getType() {
        return ListItemType.REPLY;
    }

    @Override
    public int getResourceId() {
        return R.layout.item_reply;
    }

    @Override
    public void populate(View view) {
        TextView title = (TextView) view.findViewById(R.id.reply_title);
        title.setText(model.getSubject());
//        title.setVisibility(View.GONE);
        TextView text = (TextView) view.findViewById(R.id.reply_text);
        text.setText(model.getContent());
        TextView timestamp = (TextView) view.findViewById(R.id.reply_timestamp);
        timestamp.setText(String.format("Posted by %s %d minutes ago", model.getAuthor(), model.getTimestamp()));
    }
}
