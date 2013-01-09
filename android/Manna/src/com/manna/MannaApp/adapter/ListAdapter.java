package com.manna.MannaApp.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.manna.MannaApp.list.ListItem;

public class ListAdapter extends ArrayAdapter<ListItem>  {

    public ListAdapter(Activity context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItem item = getItem(position);
        View view = ((Activity)getContext()).getLayoutInflater().inflate(item.getResourceId(), parent, false);
        item.populate(view);
        return view;
    }
}