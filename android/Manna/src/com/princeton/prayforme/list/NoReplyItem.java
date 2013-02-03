package com.princeton.prayforme.list;

import android.view.View;
import com.princeton.prayforme.R;

public class NoReplyItem extends ListItem<Void> {
    public NoReplyItem() {
        super(null);
    }

    @Override
    public ListItemType getType() {
        return ListItemType.NOREPLY;
    }

    @Override
    public int getResourceId() {
        return R.layout.item_noreply;
    }

    @Override
    public void populate(View view) {
    }
}
