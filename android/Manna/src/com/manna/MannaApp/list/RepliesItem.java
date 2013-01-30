package com.manna.MannaApp.list;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.manna.MannaApp.R;
import com.manna.MannaApp.model.Reply;

public class RepliesItem extends ListItem<Reply> {
    public RepliesItem(Context context) {
        super(context);
    }

    public RepliesItem(Context context, Reply model) {
        super(context, model);
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
        TextView text = (TextView) view.findViewById(R.id.reply_text);
        text.setText(model.getContent());
        TextView timestamp = (TextView) view.findViewById(R.id.reply_timestamp);
        timestamp.setText(String.format("Posted by %s %d minutes ago", model.getAuthor(), model.getTimestamp()));
    }
}
