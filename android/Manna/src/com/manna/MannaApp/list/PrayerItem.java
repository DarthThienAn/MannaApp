package com.manna.MannaApp.list;

import android.view.View;
import android.widget.TextView;
import com.manna.MannaApp.R;
import com.manna.MannaApp.model.Prayer;

public class PrayerItem extends ListItem<Prayer> {
    @Override
    public ListItemType getType() {
        return ListItemType.PRAYER;
    }

    @Override
    public int getResourceId() {
        return R.layout.item_prayer;
    }

    @Override
    public void populate(View view) {
        TextView title = (TextView) view.findViewById(R.id.prayer_title);
        TextView text = (TextView) view.findViewById(R.id.prayer_text);
        final TextView footerText = (TextView) view.findViewById(R.id.prayer_footer);
        footerText.setText(String.format("Prayed for %s times", model.getTimesPrayed()));
        TextView prayButton = (TextView) view.findViewById(R.id.prayer_btn_pray);
        prayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setTimesPrayed(model.getTimesPrayed() + 1);
                TextView prayButton = (TextView) view.findViewById(R.id.prayer_btn_pray);
                prayButton.setEnabled(false);
                prayButton.setText("Prayed");
                footerText.setText(String.format("Prayed for %s times", model.getTimesPrayed()));
            }
        });

        title.setText(String.format("Posted by %s", model.getAuthor()));
        text.setText(model.getContent());
    }
}