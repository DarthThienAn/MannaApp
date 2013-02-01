package com.princeton.prayforme.list;

import android.view.View;
import android.widget.TextView;
import com.princeton.prayforme.R;

public class PrayerFooterItem extends ListItem<Integer> {

    public PrayerFooterItem(Integer model) {
        super(model);
    }

    @Override
    public ListItemType getType() {
        return ListItemType.PRAYERFOOTER;
    }

    @Override
    public int getResourceId() {
        return R.layout.item_prayer;
    }

    @Override
    public void populate(View view) {
        TextView footerText = (TextView) view.findViewById(R.id.prayer_footer);
        footerText.setText(String.format("Prayed for %s times", model));
    }
}
