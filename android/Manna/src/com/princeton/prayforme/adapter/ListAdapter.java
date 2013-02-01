package com.princeton.prayforme.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.princeton.prayforme.list.ListItem;
import com.princeton.prayforme.list.ListItemType;

public class ListAdapter extends ArrayAdapter<ListItem>  {

    private Activity context;

    public ListAdapter(Activity context) {
        super(context, 0);
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType().ordinal();
    }

    @Override
    public int getViewTypeCount() {
        return ListItemType.values().length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ListItem item = getItem(position);
        if (view == null) {
            view = context.getLayoutInflater().inflate(item.getResourceId(), parent, false);
        }
//        else
//            GlobalConstants.log("ListAdapter", "convertview used");
//        GlobalConstants.log("ListAdapter", item.getType().ordinal());

        item.populate(view);
        return view;
    }
}