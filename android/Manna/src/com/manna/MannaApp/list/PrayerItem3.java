package com.manna.MannaApp.list;

import android.view.View;
import android.widget.TextView;
import com.manna.MannaApp.R;
import com.manna.MannaApp.model.Prayer;

public class PrayerItem3 extends ListItem<Prayer> {
    @Override
    public ListItemType getType() {
        return ListItemType.PRAYER;
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
        text.setText(model.getContent());

        TextView commentButton = (TextView) view.findViewById(R.id.prayer_btn_comment);
        commentButton.setText(String.format("Comments(%d)", 1));
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView commentButton = (TextView) view.findViewById(R.id.prayer_btn_comment);
                commentButton.setText("Comments(X)");

            }
        });

        TextView prayButton = (TextView) view.findViewById(R.id.prayer_btn_pray);
        prayButton.setText(String.format("Prays(%d)", model.getTimesPrayed()));
        prayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.incTimesPrayed();
                TextView prayButton = (TextView) view.findViewById(R.id.prayer_btn_pray);
                prayButton.setEnabled(false);
                prayButton.setText(String.format("Prays(%d)", model.getTimesPrayed()));
            }
        });

        TextView timestamp = (TextView) view.findViewById(R.id.prayer_timestamp);
        timestamp.setText(String.format("Posted by %s %d minutes ago", model.getAuthor(), model.getTimestamp()));
    }
}
