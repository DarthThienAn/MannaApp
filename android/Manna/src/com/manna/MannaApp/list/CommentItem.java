package com.manna.MannaApp.list;

import android.view.View;
import android.widget.TextView;
import com.manna.MannaApp.R;
import com.manna.MannaApp.model.Comment;

public class CommentItem extends ListItem<Comment> {
    @Override
    public ListItemType getType() {
        return ListItemType.COMMENT;
    }

    @Override
    public int getResourceId() {
        return R.layout.item_comment;
    }

    @Override
    public void populate(View view) {
        TextView title = (TextView) view.findViewById(R.id.comment_title);
        title.setText(model.getSubject());
        TextView text = (TextView) view.findViewById(R.id.comment_text);
        text.setText(model.getContent());
        TextView timestamp = (TextView) view.findViewById(R.id.comment_timestamp);
        timestamp.setText(String.format("Posted by %s %d minutes ago", model.getAuthor(), model.getTimestamp()));
    }
}
