package com.princeton.prayforme.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.princeton.prayforme.R;

public class HomeActivity extends Activity {

    private Button announcementsButton;
    private Button prayersButton;
    private Button meetupButton;
    private Button settingsButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        announcementsButton = (Button) findViewById(R.id.home_btn_announcements);
        prayersButton = (Button) findViewById(R.id.home_btn_prayers);
        meetupButton = (Button) findViewById(R.id.home_btn_meetup);
        settingsButton = (Button) findViewById(R.id.home_btn_settings);

        announcementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AuthActivity.class));
            }
        });

        prayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PrayersActivity.class));
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });
    }
}
