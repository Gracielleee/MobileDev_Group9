package com.example.mobiledev_unifime;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class FragmentScheduleMainFrame extends Fragment {

    private Button btnAll, btnReminders, btnKeyDates;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout
        View view = inflater.inflate(R.layout.main_fragment_schedule, container, false);

        // Initialize buttons
        btnAll = view.findViewById(R.id.btnAll);
        btnReminders = view.findViewById(R.id.btnReminders);
        btnKeyDates = view.findViewById(R.id.btnKeyDates);

        // Default selected button
        setActiveButton(btnAll);

        // Click listeners
        btnAll.setOnClickListener(v -> setActiveButton(btnAll));
        btnReminders.setOnClickListener(v -> setActiveButton(btnReminders));
        btnKeyDates.setOnClickListener(v -> setActiveButton(btnKeyDates));

        //Logic to open reminder items
        CardView eventCardView1 = view.findViewById(R.id.eventCard1);
        CardView eventCardView2 = view.findViewById(R.id.eventCard2);

        eventCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), ActivityReminderView2.class);
                intent1.putExtra("eventTitle", "Birthday Gift");
                intent1.putExtra("eventDate", "November 19, 2025");
                intent1.putExtra("eventTime", "8 PM");
                intent1.putExtra("contact1", R.drawable.lolavic);
                intent1.putExtra("contact2", R.drawable.kirby_flat);
                intent1.putExtra("contact3", R.drawable.mikee);
                startActivity(intent1);
            }
        });

        eventCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), ActivityReminderView.class);
                intent2.putExtra("eventTitle", "Family Outing @ local beach");
                intent2.putExtra("eventDate", "October 9, 2025");
                intent2.putExtra("eventTime", "10 AM - 2 PM");
                intent2.putExtra("contact1", R.drawable.kirby_flat);
                intent2.putExtra("contact2", R.drawable.mikee);
                intent2.putExtra("contact3", R.drawable.lolavic);
                startActivity(intent2);
            }
        });

        return view;
    }

    private void setActiveButton(Button activeButton) {
        // Reset all buttons first
        resetButton(btnAll);
        resetButton(btnReminders);
        resetButton(btnKeyDates);

        // Set the active one to orange
        activeButton.setBackgroundTintList(getResources().getColorStateList(R.color.primary));
        activeButton.setTextColor(getResources().getColorStateList(R.color.black));
    }

    private void resetButton(Button button) {
        // Set others back to gray and black text
        button.setBackgroundTintList(getResources().getColorStateList(android.R.color.darker_gray));
        button.setTextColor(getResources().getColor(R.color.black));
    }
}
