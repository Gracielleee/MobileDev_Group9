package com.example.mobiledev_unifime;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobiledev_unifime.model.Contact;
import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentContactView extends Fragment {

    private static final String ARG_CONTACT_NAME = "contact_name";
    private static final String ARG_CONTACT_IMAGE = "contact_image";
    private static final String ARG_CONTACT_GROUP = "contact_group";

    public static FragmentContactView newInstance(Contact contact) {
        FragmentContactView fragment = new FragmentContactView();
        Bundle args = new Bundle();
        args.putString(ARG_CONTACT_NAME, contact.getName());
        args.putInt(ARG_CONTACT_IMAGE, contact.getImageId());
        args.putString(ARG_CONTACT_GROUP, contact.getGroup().getDisplayName());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate your existing layout
        View view = inflater.inflate(R.layout.view_contact_frame, container, false);

        // Get all the views from your layout
        CircleImageView profilePicture = view.findViewById(R.id.contact_profilePicture);
        TextView contactName = view.findViewById(R.id.contact_name);
        TextView contactGroup = view.findViewById(R.id.contact_contactgroup);
        TextView contactBirthday = view.findViewById(R.id.contact_birthday);
        TextView contactDescription = view.findViewById(R.id.contact_description);
        ImageView hamburgerMenu = view.findViewById(R.id.hamburger);
        ImageView backButton = view.findViewById(R.id.backButton);
        ImageView instagramIcon = view.findViewById(R.id.instagram);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager);


        // Get data from arguments
        if (getArguments() != null) {
            String name = getArguments().getString(ARG_CONTACT_NAME);
            int imageId = getArguments().getInt(ARG_CONTACT_IMAGE);

            // Bind data to views
            Log.d("ContactDetail", "Binding contact: " + name);

            contactName.setText(name);
            profilePicture.setImageResource(imageId);

            contactBirthday.setText("Birthday not set");
            contactDescription.setText("No description");
        }

        // Hamburger menu click listener
        hamburgerMenu.setOnClickListener(v -> {
            Log.d("ContactDetail", "Hamburger menu clicked");
            // TODO: Add menu functionality
        });

        backButton.setOnClickListener(v ->{
            Log.d("ContactDetail", "Back button clicked");
        });

        // Instagram icon click listener
        instagramIcon.setOnClickListener(v -> {
            Log.d("ContactDetail", "Instagram icon clicked");
            getParentFragmentManager().popBackStack();
            // TODO: Add Instagram profile link functionality
        });

        // TabLayout click listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tabName = tab.getText().toString();
                Log.d("TabLayout", "Tab selected: " + tabName);
                // TODO: Switch between Notes and Reminders
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

//        // Back button functionality - using system back
//        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
//                new androidx.activity.OnBackPressedCallback(true) {
//                    @Override
//                    public void handleOnBackPressed() {
//                        getParentFragmentManager().popBackStack();
//                    }
//                });

        return view;
    }
}
