package com.princeton.prayforme.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.princeton.prayforme.R;
import com.princeton.prayforme.adapter.PrayerAdapter;
import com.princeton.prayforme.model.Prayer;

import java.util.List;
import java.util.Random;

public class PrayerViewActivity extends Activity {

    List<Prayer> prayers;
    ViewPager viewPager;
    PrayerAdapter prayerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayerview);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("prayers");
        prayers =  bundle.getParcelableArrayList("prayers");
        int pos = intent.getIntExtra("pos", 0);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        prayerAdapter = new PrayerAdapter(getLayoutInflater());
        viewPager.setAdapter(prayerAdapter);
//        createDummyPrayers();

        populatePrayers();
        viewPager.setCurrentItem(pos);
    }

    private void createDummyPrayers() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String content = "" + random.nextInt(100);
            prayers.add(new Prayer("misc", "subject", content, i, i + 1, i + 1));
        }
    }

    private void populatePrayers() {
        for (Prayer prayer : prayers) {
//            View item = getLayoutInflater().inflate(R.layout.item_prayerview, viewPager, false);
//            ((TextView) item.findViewById(R.id.prayer_text)).setText(prayer.getContent());
            prayerAdapter.addItem(prayer);
        }
    }
}