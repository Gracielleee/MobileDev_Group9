package com.example.mobiledev_unifime;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiledev_unifime.model.Contact;

public class ContactDetailActivity extends AppCompatActivity {

    private static final String TAG = "ContactDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);

        // Retrieve the contact object passed through the intent
        Contact contact = getIntent().getParcelableExtra("contact_data");

        // Initial UI setup
        if (contact != null) {
            initializeUI(contact);
        } else {
            Log.e(TAG, "No contact data received");
        }
    }

    private void initializeUI(Contact contact) {
        // Get all the views from your layout
        TextView contactName = findViewById(R.id.contact_name);
        TextView contactGroup = findViewById(R.id.contact_contactgroup);
        TextView contactBirthday = findViewById(R.id.contact_birthday);
        TextView contactDescription = findViewById(R.id.contact_description);
        ImageView profilePicture = findViewById(R.id.contact_profilePicture);
        ImageView hamburgerMenu = findViewById(R.id.hamburger);
        ImageView instagramIcon = findViewById(R.id.instagram);

        // Bind data to views
        Log.d(TAG, "Binding contact: " + contact.getName());

        contactName.setText(contact.getName());
        contactGroup.setText(contact.getGroup().getDisplayName());
        contactBirthday.setText(contact.getBirthday() != null ? contact.getBirthday() : "Birthday not set");
        contactDescription.setText(contact.getDescription() != null ? contact.getDescription() : "No description");
        profilePicture.setImageResource(contact.getImageId());

        // Hamburger menu click listener
        hamburgerMenu.setOnClickListener(v -> {
            Log.d(TAG, "Hamburger menu clicked");
            // TODO: Add menu functionality
        });

        // Instagram icon click listener
        instagramIcon.setOnClickListener(v -> {
            Log.d(TAG, "Instagram icon clicked");
            // TODO: Add Instagram profile link functionality
        });
    }
}
