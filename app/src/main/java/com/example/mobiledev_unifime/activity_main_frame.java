package com.example.mobiledev_unifime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class activity_main_frame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle item selection
                switch (item.getItemId()) {
                    case R.id.nav_contacts:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new activity_main_fragment_contacts()).commit();
                        return true;
                    case R.id.nav_notes:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new NotesFragment()).commit();
                        return true;
                    case R.id.nav_schedule:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new ScheduleFragment()).commit();
                        return true;
                    case R.id.nav_tracker:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new TrackerFragment()).commit();
                        return true;
                }
                return false;
            }
        });
    }
}


