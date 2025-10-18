package com.example.mobiledev_unifime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class activity_main_frame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        // Hide the status bar
        hideSystemUI();

        // Show the contacts fragment by default
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, new activity_main_fragment_contacts())
                    .commit();
        }

        //FAB logic
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a dialog to add a note, contact, or reminder
                showPopupWindow(v);
            }
        });

        // Set up Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle item selection
                int itemId = item.getItemId();
                if (itemId == R.id.nav_contacts) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new activity_main_fragment_contacts()).commit();
                    return true;
                } else if (itemId == R.id.nav_notes) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new activity_main_fragment_notes()).commit();
                    return true;
                } else if (itemId == R.id.nav_schedule) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new activity_main_fragment_schedule()).commit();
                    return true;
                } else if (itemId == R.id.nav_tracker) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new activity_main_fragment_tracker()).commit();
                    return true;
                }
                return false;
            }
        });
    }

    private void showPopupWindow(View anchorView) {
        // Inflate the popup_layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.fab_popup, null);

        // Create the PopupWindow
        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        // Set the popup window to be focusable
        popupWindow.setFocusable(true);

        // Get the FAB's position
        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);
        int x = location[0] + anchorView.getWidth()/2 - popupWindow.getWidth()/2 - 380; // X-coordinate
        int y = location[1] - popupWindow.getHeight() - 635; // Y-coordinate

        // Show the popup window at the specified location
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, x, y);

        // Set up button click listeners within the popup
        Button addContactButton = popupView.findViewById(R.id.add_contact_btn);
        Button addNoteButton = popupView.findViewById(R.id.add_note_btn);
        Button addReminderButton = popupView.findViewById(R.id.add_reminder_btn);

        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle adding a new contact here
                Toast.makeText(activity_main_frame.this, "Add New Contact clicked", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle adding a new note here
                Toast.makeText(activity_main_frame.this, "Add New Note clicked", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                // Start the new activity
                Intent intent = new Intent(activity_main_frame.this, add_note_frame.class);
                startActivity(intent);
            }
        });

        addReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle adding a new reminder here
                Toast.makeText(activity_main_frame.this, "Add New Reminder clicked", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}


