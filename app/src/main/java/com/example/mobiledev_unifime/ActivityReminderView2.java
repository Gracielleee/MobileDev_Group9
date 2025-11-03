package com.example.mobiledev_unifime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityReminderView2 extends AppCompatActivity {
    private ImageButton backButton;
    private Button checkButton;

    private TextView titleText;
    private TextView dateText;
    private TextView timeText;

    private CircleImageView contact1;
    private CircleImageView contact2;
    private CircleImageView contact3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_reminder_frame2);

        // Initialize views
        backButton = findViewById(R.id.backButton);
        checkButton = findViewById(R.id.check_button);

        titleText = findViewById(R.id.title);
        dateText = findViewById(R.id.date_txt_btn);
        timeText = findViewById(R.id.time_txt_btn);

        contact1 = findViewById(R.id.contact_1);

        // Get the intent that started this activity
        Intent intent = getIntent();

        // Retrieve data from the intent using the correct keys
        String eventTitle = intent.getStringExtra("eventTitle");
        String eventDate = intent.getStringExtra("eventDate");
        String eventTime = intent.getStringExtra("eventTime");
        int contactImage1 = intent.getIntExtra("contact1", -1);

        // Update UI with the data
        titleText.setText(eventTitle);
        dateText.setText(eventDate);
        timeText.setText(eventTime);

        // Set the images if they exist
        if (contactImage1 != -1) {
            contact1.setImageResource(contactImage1);
        }


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
                showCustomGreenToast(v, "Completed",250);
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
