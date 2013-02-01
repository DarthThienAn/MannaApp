package com.princeton.prayforme.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.princeton.prayforme.R;
import com.princeton.prayforme.Security;
import com.princeton.prayforme.adapter.ListAdapter;
import com.princeton.prayforme.asynctask.AsyncGet;
import com.princeton.prayforme.helper.SharedPrefsHelper;
import com.princeton.prayforme.list.*;
import com.princeton.prayforme.model.Prayer;

import java.util.ArrayList;
import java.util.List;

public class PrayersActivity extends Activity {

    ListView listview;
    List<Prayer> prayers;
    ListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayers);

        adapter = new ListAdapter(PrayersActivity.this);

        prayers = new ArrayList<Prayer>();

        listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(onItemClickListener);

        drawPrayers();
/*
        Button addButton = (Button) findViewById(R.id.prayers_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrayerItem prayerItem = new PrayerItem();
                Prayer prayer = new Prayer("New", "prayer #" + count, count);
                prayerItem.setModel(prayer);
                adapter.add(prayerItem);
                prayers.add(prayer);

//                View item = getLayoutInflater().inflate(R.layout.item_prayer, prayerContainer, false);
//                ((TextView) item.findViewById(R.id.prayer_text)).setText("" + count);
//                item.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        startActivity(new Intent(getApplicationContext(), PrayerViewActivity.class));
//                        count++;
//                    }
//                });
//                prayerContainer.addView(item);
                count++;
            }
        });

        Button postButton = (Button) findViewById(R.id.prayers_post);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = createDialog("Post a prayer", "Some text", "Enter your prayer here...", "Post", "Cancel");
                dialog.show();
            }
        });
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_prayers, menu);

        menu.findItem(R.id.menu_post).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
//                Dialog dialog = createDialog("Post a prayer", "Some text", "Enter your prayer here...", "Post", "Cancel");
//                dialog.show();
                startActivity(new Intent(getApplicationContext(), PostActivity.class));
                return true;
            }
        });
        menu.findItem(R.id.menu_settings).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                populate();
//                startActivity(new Intent(getApplicationContext(), RepliesActivity.class));
                return true;
            }
        });
        menu.findItem(R.id.menu_logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                SharedPrefsHelper prefsHelper = new SharedPrefsHelper(getApplicationContext());
                prefsHelper.clear();
                startActivity(new Intent(getApplicationContext(), AuthActivity.class));
                finish();
                return true;
            }
        });
        return true;
    }

    private void drawPrayers() {
//        for (Prayer prayer : prayers) {
//            View item = getLayoutInflater().inflate(R.layout.item_prayer, prayerContainer, false);
//            ((TextView) item.findViewById(R.id.prayer_text)).setText(prayer.getContent());
//            item.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    count++;
//                }
//            });
//            prayerContainer.addView(item);
//            count++;
//        }

        String[] authors = { "Mark my name is really long", "Daniel", "David", "Joel", "Sam"};
        String[] subject = { "Need prayer for thesis", "Tough times", "Finals", "Busy week", "Need a friend"};
        String[] content = { "hello world", "Hey how you all doing my name is Daniel and I'm looking to fight some lions next week, so if you can pray for that, it'd be great thanks!", "Pray for my life", "I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.I wish my sermons would write themselves for me.", "Green eggs and ham"};
        String[] signatures = { "Need prayer for thesis", "Tough times", "Finals", "Busy week", "Need a friend"};
        int[] times = { 1, 2, 3, 2, 1 };
        int[] replies = { 0, 2, 4, 1, 3 };

//        for (int i = 0; i < 5; i++) {
//            Prayer prayer = new Prayer(authors[i], subject[i], content[i], Security.getMD5(signatures[i]), times[i], replies, "timestamp");
//            PrayerItem prayerItem = new PrayerItem(prayer);
//            adapter.add(prayerItem);
//            prayers.add(prayer);
//        }
        for (int i = 0; i < 5; i++) {
            Prayer prayer = new Prayer(authors[i], subject[i], content[i], Security.getMD5(signatures[i]), times[i], replies, "timestamp");
            PrayerItem2 prayerItem = new PrayerItem2(PrayersActivity.this, prayer);
            adapter.add(prayerItem);
            prayers.add(prayer);
        }
        for (int i = 0; i < 5; i++) {
            Prayer prayer = new Prayer(authors[i], subject[i], content[i], Security.getMD5(signatures[i]), times[i], replies, "timestamp");
            PrayerItem3 prayerItem = new PrayerItem3(PrayersActivity.this, prayer);
            adapter.add(prayerItem);
            prayers.add(prayer);
        }
    }

    final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (adapter.getItem(i).getType() == ListItemType.PRAYER) {
                Intent intent = new Intent(getApplicationContext(), PrayerViewActivity.class);
                intent.putExtra("pos", i);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("prayers", (ArrayList<Prayer>) prayers);
                intent.putExtra("prayers", bundle);
                //            Parcelable[] data = new Parcelable[1];
                //            intent.putExtra("prayers", );
                startActivity(intent);
            }
            else if (adapter.getItem(i).getType() == ListItemType.PRAYER2) {
                Intent intent = new Intent(getApplicationContext(), PrayerRepliesActivity.class);
                intent.putExtra("pos", i);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("prayers", (ArrayList<Prayer>) prayers);
                intent.putExtra("prayers", bundle);
                //            Parcelable[] data = new Parcelable[1];
                //            intent.putExtra("prayers", );
                startActivity(intent);
            }
            else if (adapter.getItem(i).getType() == ListItemType.PRAYER3) {
                Intent intent = new Intent(getApplicationContext(), PrayerRepliesActivity.class);
                intent.putExtra("pos", i);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("prayers", (ArrayList<Prayer>) prayers);
                intent.putExtra("prayers", bundle);
                //            Parcelable[] data = new Parcelable[1];
                //            intent.putExtra("prayers", );
                startActivity(intent);
            }
        }
    };

//    private Dialog createDialog(String titleString, String textString, String editHintString, String yesText, String noText) {
//        final Dialog dialog = new Dialog(PrayersActivity.this, R.style.dialog_no_title_fill);
//        dialog.setContentView(R.layout.prayers_dialog);
//
//        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
//        final TextView text = (TextView) dialog.findViewById(R.id.dialog_text);
//        final EditText edit = (EditText) dialog.findViewById(R.id.dialog_edit);
//        final Button yes = (Button) dialog.findViewById(R.id.dialog_yes);
//        final Button no = (Button) dialog.findViewById(R.id.dialog_no);
//
//        title.setText(titleString);
//        text.setText(textString);
//        edit.setHint(editHintString);
//        yes.setText(yesText);
//        no.setText(noText);
//
//        yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Prayer prayer = new Prayer("Myself", "MY SUBJECT", edit.getText().toString(), Security.getMD5("SIGNATURE"), 0, 1, 1);
//                PrayerItem prayerItem = new PrayerItem(prayer);
//                prayers.add(prayer);
//                adapter.add(prayerItem);
//                dialog.dismiss();
//            }
//        });
//        no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.setCancelable(true);
//        return dialog;
//    }

    private void populate() {
        AsyncGet<String> task = new AsyncGet<String>("http://api.sonar.me/v1/users/505622cde4b04828a245d6a5/statuses?auth_token=yhjCcW0te5Hc7iLzH-aUSA", String.class);
//        String object = restTemplate.getForObject("http://api.sonar.me/users/self?auth_token=yhjCcW0te5Hc7iLzH-aUSA", String.class);
//        String object = restTemplate.getForObject("http://api.sonar.me/v1/users/505622cde4b04828a245d6a5/statuses?auth_token=yhjCcW0te5Hc7iLzH-aUSA", String.class);
        task.execute();
    }
}
