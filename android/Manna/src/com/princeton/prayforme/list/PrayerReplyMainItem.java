package com.princeton.prayforme.list;

import android.view.View;
import android.widget.TextView;
import com.princeton.prayforme.R;
import com.princeton.prayforme.model.Prayer;

public class PrayerReplyMainItem extends ListItem<Prayer> {
    public PrayerReplyMainItem(Prayer model) {
        super(model);
    }

    @Override
    public ListItemType getType() {
        return ListItemType.PRAYERREPLYMAIN;
    }

    @Override
    public int getResourceId() {
        return R.layout.item_prayerreplymain;
    }

    @Override
    public void populate(View view) {
        TextView author = (TextView) view.findViewById(R.id.prayer_author);
        author.setText(model.getAuthor());
        TextView title = (TextView) view.findViewById(R.id.prayer_title);
        title.setText(model.getSubject());
        TextView text = (TextView) view.findViewById(R.id.prayer_text);
        text.setText(model.getContent());

        TextView prayButton = (TextView) view.findViewById(R.id.prayer_btn_pray);
        prayButton.setText(String.format("Prays(%d)", model.getTimesPrayed()));
        prayButton.setEnabled(!model.isPrayedFor());

        prayButton.setOnClickListener(prayOnClickListener);

        TextView timestamp = (TextView) view.findViewById(R.id.prayer_timestamp);
        timestamp.setText(String.format("Posted %d minutes ago", model.getTimestamp()));
    }

    private final View.OnClickListener prayOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            model.incTimesPrayed();
            TextView prayButton = (TextView) view.findViewById(R.id.prayer_btn_pray);
            prayButton.setEnabled(false);
            model.setPrayedFor(true);
            prayButton.setText(String.format("Prays(%d)", model.getTimesPrayed()));
        }
    };
}
