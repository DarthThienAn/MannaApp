package com.princeton.prayforme.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.princeton.prayforme.R;
import com.princeton.prayforme.model.Prayer;

import java.util.ArrayList;
import java.util.List;

public class PrayerAdapter extends PagerAdapter {

    private List<Prayer> prayers;
    LayoutInflater layoutInflater;

    public PrayerAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
        prayers = new ArrayList<Prayer>();
    }

    @Override
    public int getCount() {
        return prayers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.item_prayerview_old, container, false);
        TextView title = (TextView) view.findViewById(R.id.prayer_title);
        TextView text = (TextView) view.findViewById(R.id.prayer_text);
        title.setText(String.format("Posted by %s", prayers.get(position).getAuthor()));
        text.setText(prayers.get(position).getContent());

        container.addView(view);
        return view;
//     return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
//        super.destroyItem(container, position, object);
    }

    public void addItem(Prayer prayer) {
        prayers.add(prayer);
    }
}
