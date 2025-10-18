package com.example.mobiledev_unifime;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ActivityReminderAdd extends AppCompatActivity {

    private ImageButton backButton;
    private ImageButton checkButton;
    private Button addDateButton;
    private Button addTimeButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reminder_frame);

        backButton = findViewById(R.id.backButton);
        checkButton = findViewById(R.id.check_button);
        addDateButton = findViewById(R.id.add_date_btn);
        addTimeButton = findViewById(R.id.add_time_btn);

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

        //Set the date and time picker buttons logic
        addDateButton.setOnClickListener(v -> showDatePicker());
        addTimeButton.setOnClickListener(v -> showTimePicker());
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        addDateButton.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute1) -> {
                    // Convert to 12-hour format using SimpleDateFormat
                    Calendar selectedTime = Calendar.getInstance();
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    selectedTime.set(Calendar.MINUTE, minute1);

                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                    String formattedTime = sdf.format(selectedTime.getTime());

                    addTimeButton.setText(formattedTime);
                }, hour, minute, false);
        timePickerDialog.show();
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