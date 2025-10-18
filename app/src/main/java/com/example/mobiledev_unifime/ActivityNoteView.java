package com.example.mobiledev_unifime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityNoteView extends AppCompatActivity {
    private ImageButton backButton;
    private EditText titleText;
    private EditText descriptionText;
    private CircleImageView contactImageView;
    private Button tagButton;
    private int imageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_note_frame);

        // Initialize views
        titleText = findViewById(R.id.title);
        descriptionText = findViewById(R.id.description);
        backButton = findViewById(R.id.backButton);
        contactImageView = findViewById(R.id.linked_contact);
        tagButton = findViewById(R.id.tag); // Initialize the Button

        // Get the intent that started this activity
        Intent intent = getIntent();

        // Retrieve data from the intent
        String title = intent.getStringExtra("TITLE");
        String description = intent.getStringExtra("DESC");
        String tag = intent.getStringExtra("Tag");       // Get the tag string
        imageId = intent.getIntExtra("IMAGE", -1); // Default to -1 if not found

        // Update UI with the data
        titleText.setText(title);
        descriptionText.setText(description);

        // Set the image if it exists
        if (imageId != -1) {
            contactImageView.setImageResource(imageId);
        }

        // Set the button text from the tag
        tagButton.setText(tag);

        // Set the Back Button logic
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
