package com.example.mobiledev_unifime;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityContactView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_view);

        // Set up the back button
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a Toast message
                Toast.makeText(ActivityContactView.this, "Back button clicked", Toast.LENGTH_SHORT).show();
                // This logic handles the back navigation
                finish(); // Finishes the current activity and returns to the previous one
            }
        });

    }
}
