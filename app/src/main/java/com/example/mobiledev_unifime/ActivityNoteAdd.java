package com.example.mobiledev_unifime;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class ActivityNoteAdd extends AppCompatActivity {

    private ImageButton backButton;
    private ImageButton checkButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_frame);

        backButton = findViewById(R.id.backButton);
        checkButton = findViewById(R.id.check_button);


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