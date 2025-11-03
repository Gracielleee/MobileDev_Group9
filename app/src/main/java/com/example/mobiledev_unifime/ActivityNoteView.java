package com.example.mobiledev_unifime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiledev_unifime.model.Contact;
import com.google.android.material.snackbar.Snackbar;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityNoteView extends AppCompatActivity {
    private ImageButton backButton;
    private ImageButton checkButton;
    private EditText titleText;
    private EditText descriptionText;
    private CircleImageView contactImageView;
    private Button tagButton;
    private TextView dateCreated;
    private int imageId;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_note_frame);

        // Initialize views
        titleText = findViewById(R.id.title);
        descriptionText = findViewById(R.id.description);
        backButton = findViewById(R.id.backButton);
        checkButton = findViewById(R.id.check_button);
        contactImageView = findViewById(R.id.linked_contact);
        tagButton = findViewById(R.id.tag);
        dateCreated = findViewById(R.id.date_created);
        contact = getIntent().getParcelableExtra("Contact");


        // Get the intent that started this activity
        Intent intent = getIntent();

        // Retrieve data from the intent
        String title = intent.getStringExtra("TITLE");
        String description = intent.getStringExtra("DESC");
        String tag = intent.getStringExtra("Tag");       // Get the tag string
        imageId = intent.getIntExtra("IMAGE", -1); // Default to -1 if not found
        String date = intent.getStringExtra("Date");

        // Update UI with the data
        titleText.setText(title);
        descriptionText.setText(description);
        dateCreated.setText(date);

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

        //Set the Check Button logic
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomGreenToast(v, "Saved",250);
                finish();

            }
        });

        //Set the Linked Contact Image Button logic
        contactImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the contact information to the activity
                Intent intent = new Intent(ActivityNoteView.this, ActivityContactDetail.class);
                intent.putExtra("contact_data", contact);
                startActivity(intent);
                finish();

            }
        });
    }

    private void showCustomGreenToast(View view, String message, int durationInMillis) {
        LayoutInflater inflater = getLayoutInflater();
        View customView = inflater.inflate(R.layout.custom_toast_layout, null);

        TextView textView = customView.findViewById(R.id.message);
        textView.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setView(customView);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 100);
        toast.show();

        // Dismiss the Toast after the specified duration
        new Handler().postDelayed(toast::cancel, durationInMillis);
    }
}
