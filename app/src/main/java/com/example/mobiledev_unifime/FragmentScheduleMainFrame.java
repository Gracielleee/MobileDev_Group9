package com.example.mobiledev_unifime;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class FragmentScheduleMainFrame extends Fragment {

    private Button btnAll, btnReminders, btnKeyDates;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout
        View view = inflater.inflate(R.layout.activity_main_fragment_schedule, container, false);

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

        return view;
    }

    private void setActiveButton(Button activeButton) {
        // Reset all buttons first
        resetButton(btnAll);
        resetButton(btnReminders);
        resetButton(btnKeyDates);

        // Set the active one to orange
        activeButton.setBackgroundTintList(getResources().getColorStateList(R.color.orange));
        activeButton.setTextColor(getResources().getColor(R.color.white));
    }

    private void resetButton(Button button) {
        // Set others back to gray and black text
        button.setBackgroundTintList(getResources().getColorStateList(android.R.color.darker_gray));
        button.setTextColor(getResources().getColor(R.color.black));
    }
}
