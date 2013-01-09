package com.manna.MannaApp.list;

import android.view.View;
import android.widget.TextView;
import com.manna.MannaApp.R;

public class PrayerFooterItem extends ListItem<Integer> {
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
