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

public class ContactDetailFragment extends Fragment {

    private static final String ARG_CONTACT_NAME = "contact_name";
    private static final String ARG_CONTACT_IMAGE = "contact_image";
    private static final String ARG_CONTACT_GROUP = "contact_group";

    public static ContactDetailFragment newInstance(Contact contact) {
        ContactDetailFragment fragment = new ContactDetailFragment();
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
        View view = inflater.inflate(R.layout.activity_contact_view, container, false);

        // Get all the views from your layout
        CircleImageView profilePicture = view.findViewById(R.id.contact_profilePicture);
        TextView contactName = view.findViewById(R.id.contact_name);
        TextView contactGroup = view.findViewById(R.id.contact_contactgroup);
        TextView contactBirthday = view.findViewById(R.id.contact_birthday);
        TextView contactDescription = view.findViewById(R.id.contact_description);
        ImageView hamburgerMenu = view.findViewById(R.id.hamburger);
        ImageView instagramIcon = view.findViewById(R.id.instagram);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);

        // Get data from arguments
        if (getArguments() != null) {
            String name = getArguments().getString(ARG_CONTACT_NAME);
            int imageId = getArguments().getInt(ARG_CONTACT_IMAGE);

            // Bind data to views
            Log.d("ContactDetail", "Binding contact: " + name);

            contactName.setText(name);
            profilePicture.setImageResource(imageId);

            // You can set these later with actual data from your Contact model
            // For now, using placeholder values
            contactBirthday.setText("Birthday not set");
            contactDescription.setText("No description");
        }

        // Hamburger menu click listener
        hamburgerMenu.setOnClickListener(v -> {
            Log.d("ContactDetail", "Hamburger menu clicked");
            // TODO: Add menu functionality
        });

        // Instagram icon click listener
        instagramIcon.setOnClickListener(v -> {
            Log.d("ContactDetail", "Instagram icon clicked");
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

        // Back button functionality - using system back
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new androidx.activity.OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        getParentFragmentManager().popBackStack();
                    }
                });

        return view;
    }
}

// ============================================
// IMPORTANT: Update your Contact Model
// ============================================
/*
Add birthday and description fields to your Contact class:

package com.example.mobiledev_unifime.model;

public class Contact {
    private String name;
    private int imageId;
    private ContactGroup group;
    private String birthday;      // ADD THIS
    private String description;   // ADD THIS

    public Contact(String name, int imageId, ContactGroup group, String birthday, String description) {
        this.name = name;
        this.imageId = imageId;
        this.group = group;
        this.birthday = birthday;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public ContactGroup getGroup() {
        return group;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
*/