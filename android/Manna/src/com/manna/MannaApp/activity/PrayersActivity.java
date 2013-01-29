package com.manna.MannaApp.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.manna.MannaApp.GlobalConstants;
import com.manna.MannaApp.R;
import com.manna.MannaApp.adapter.ListAdapter;
import com.manna.MannaApp.asynctask.AsyncGet;
import com.manna.MannaApp.list.ListItemType;
import com.manna.MannaApp.list.PrayerItem;
import com.manna.MannaApp.model.Prayer;
import com.manna.MannaApp.providers.RestTemplateProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrayersActivity extends Activity {

    ListView listview;
    int count = 0;

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
        String[] content = { "hello world", "I wish I had a pony", "Pray for my life", "I wish my sermons would write themselves", "Green eggs and ham"};
        int[] times = { 1, 2, 3, 2, 1 };

        for (int i = 0; i < 5; i++) {
            PrayerItem prayerItem = new PrayerItem();
            Prayer prayer = new Prayer(authors[i], content[i], times[i]);
            prayerItem.setModel(prayer);
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
            else if (adapter.getItem(i).getType() == ListItemType.PRAYER) {
                Intent intent = new Intent(getApplicationContext(), PrayerViewActivity.class);
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

    private Dialog createDialog(String titleString, String textString, String editHintString, String yesText, String noText) {
        final Dialog dialog = new Dialog(PrayersActivity.this, R.style.dialog_no_title_fill);
        dialog.setContentView(R.layout.prayers_dialog);

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final TextView text = (TextView) dialog.findViewById(R.id.dialog_text);
        final EditText edit = (EditText) dialog.findViewById(R.id.dialog_edit);
        final Button yes = (Button) dialog.findViewById(R.id.dialog_yes);
        final Button no = (Button) dialog.findViewById(R.id.dialog_no);

        title.setText(titleString);
        text.setText(textString);
        edit.setHint(editHintString);
        yes.setText(yesText);
        no.setText(noText);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrayerItem prayerItem = new PrayerItem();
                Prayer prayer = new Prayer("Myself", edit.getText().toString(), 0);
                prayerItem.setModel(prayer);
                prayers.add(prayer);
                adapter.add(prayerItem);
                dialog.dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        return dialog;
    }

    private void populate() {
        AsyncGet<String> task = new AsyncGet<String>("http://api.sonar.me/v1/users/505622cde4b04828a245d6a5/statuses?auth_token=yhjCcW0te5Hc7iLzH-aUSA", String.class);
//        String object = restTemplate.getForObject("http://api.sonar.me/users/self?auth_token=yhjCcW0te5Hc7iLzH-aUSA", String.class);
//        String object = restTemplate.getForObject("http://api.sonar.me/v1/users/505622cde4b04828a245d6a5/statuses?auth_token=yhjCcW0te5Hc7iLzH-aUSA", String.class);
        task.execute();
    }
}
