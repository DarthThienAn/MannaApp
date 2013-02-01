package com.princeton.prayforme.list;

import android.view.View;
import android.widget.TextView;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.R;
import com.princeton.prayforme.model.Prayer;

public class PrayerItem extends ListItem<Prayer> {

    public PrayerItem(Prayer model) {
        super(model);
    }

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
        final TextView title = (TextView) view.findViewById(R.id.prayer_title);
        final TextView text = (TextView) view.findViewById(R.id.prayer_text);
        final TextView footerText = (TextView) view.findViewById(R.id.prayer_footer);
        footerText.setText(String.format("Prayed for %d times", model.getTimesPrayedFor()));
        final TextView prayButton = (TextView) view.findViewById(R.id.prayer_btn_pray);
        prayButton.setEnabled(!model.isPrayedFor());
        prayButton.setText(model.isPrayedFor() ? "Prayed" : "Pray");
        prayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setTimesPrayedFor(model.getTimesPrayedFor() + 1);
                TextView prayButton = (TextView) view.findViewById(R.id.prayer_btn_pray);
                prayButton.setEnabled(false);
                model.setPrayedFor(true);
                prayButton.setText("Prayed");
                footerText.setText(String.format("Prayed for %d times", model.getTimesPrayedFor()));
            }
        });

        title.setText(String.format("Posted by %s", GlobalConstants.isNullOrEmpty(model.getPerson()) ? "Anonymous" : model.getPerson()));
        text.setText(model.getMessage());
    }
}
