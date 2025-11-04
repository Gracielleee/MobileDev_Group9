package com.example.mobiledev_unifime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledev_unifime.model.Contact;
import com.example.mobiledev_unifime.model.ContactGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentContactsMainFrame extends Fragment {

    private AdapterContactItem adapterContact;
    private static List<Contact> allContacts;
    private RecyclerView recyclerViewContact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_contacts, container, false);

        if (allContacts == null) {
            allContacts = createContacts();
        }

        // Get references to RecyclerViews
        RecyclerView recyclerViewContactGroup = view.findViewById(R.id.recyclerView_contactGroup);
        recyclerViewContact = view.findViewById(R.id.recyclerView_contacts);


        // Setup RecyclerViews
        setupGroupRecyclerView(recyclerViewContactGroup);
        setupContactRecyclerView(recyclerViewContact);

        // Retrieve and filter by last selected group
        SharedPreferences preferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String lastSelectedGroupName = preferences.getString("last_selected_group", ContactGroup.FAMILY.name()); // Default to FAMILY

        // Ensure this conversion is safe
        try {
            ContactGroup lastSelectedGroup = ContactGroup.valueOf(lastSelectedGroupName);
            filterContacts(lastSelectedGroup); // Filter contacts based on last selected group
        } catch (IllegalArgumentException e) {
            Log.e("ContactGroupError", "Invalid ContactGroup retrieved: " + lastSelectedGroupName);
            filterContacts(ContactGroup.FAMILY); // Fall back to default
        }

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String lastSelectedGroupName = preferences.getString("last_selected_group", null);
        filterContacts(lastSelectedGroupName != null ? ContactGroup.valueOf(lastSelectedGroupName) : ContactGroup.FAMILY);
    }

    @Override
    public void onPause() {
        super.onPause();
        hideKeyboard();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerViewContact = null;
        adapterContact = null;
    }

    private void setupGroupRecyclerView(RecyclerView recyclerView) {
        List<ContactGroup> contactGroups = Arrays.asList(
                ContactGroup.FAMILY,
                ContactGroup.CHURCH,
                ContactGroup.HIKING,
                ContactGroup.SCHOOL,
                ContactGroup.PROVINCE
        );

        AdapterContactGroup groupAdapter = new AdapterContactGroup(contactGroups, group -> {
            Log.d("GroupClick", "Group clicked: " + group.getDisplayName());
            filterContacts(group);

            // Store the last selected group
            SharedPreferences preferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("last_selected_group", group.name());
            editor.apply();
            Log.d("GroupSave", "Saved group: " + group.name());
        });

        recyclerView.setAdapter(groupAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
    }

    private void setupContactRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Pass the context and initialize the adapter
        adapterContact = new AdapterContactItem(getContext(), new ArrayList<>());

        // Set click listener to navigate to detail fragment
        adapterContact.setOnContactClickListener(contact -> {
            Log.d("ContactClick", "Contact clicked: " + contact.getName());
            navigateToContactDetail(contact);
        });

        recyclerView.setAdapter(adapterContact);
        Log.d("ContactRecyclerView", "Contact RecyclerView initialized");
    }

    public static List<Contact> createContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Lola Vic", R.drawable.lolavic, ContactGroup.FAMILY, "November 30, 1940", "Dancer"));
        contacts.add(new Contact("Kirby", R.drawable.kirby_flat, ContactGroup.CHURCH, "December 25, 1999", "Church member"));
        contacts.add(new Contact("Mikee", R.drawable.mikee, ContactGroup.HIKING, "February 3, 2000", "Adventure seeker"));

        contacts.add(new Contact("Jhun-jhun", R.drawable.jhun, ContactGroup.HIKING, "April 1, 1998", "Experienced hiker"));
        contacts.add(new Contact("Mai-Mai", R.drawable.smiski, ContactGroup.SCHOOL, "November 11, 2003", "Future Android Developer"));
        contacts.add(new Contact("Tita Baby", R.drawable.tita_baby, ContactGroup.FAMILY, "September 4, 1985", "Lola's cousin"));
        contacts.add(new Contact("Luzviminda", R.drawable.labubu, ContactGroup.PROVINCE, "June 30, 2003", "Labubu enjoyer"));
        contacts.add(new Contact("Brownie", R.drawable.brownie, ContactGroup.FAMILY, "October 9, 2002", "Foodie"));
        Log.d("Contacts", "Created contacts: " + contacts.size());

        for (Contact contact : contacts) {
            Log.d("ContactsList", "Contact: " + contact.getName() + " | Group: " + contact.getGroup().getDisplayName() + " | Birthday: " + contact.getBirthday());
        }

        return contacts; // Return the created list of contacts
    }

    private void filterContacts(ContactGroup selectedGroup) {
        if (selectedGroup == null || allContacts == null || adapterContact == null) {
            Log.w("FilterDebug", "Cannot filter: null parameters");
            return;
        }

        List<Contact> filteredContacts = new ArrayList<>();

        Log.d("FilterDebug", "Filtering contacts for group: " + selectedGroup.getDisplayName());

        for (Contact contact : allContacts) {
            if (contact.getGroup() == selectedGroup) {
                filteredContacts.add(contact);
                Log.d("FilterDebug", "✓ Contact added: " + contact.getName());
            }
        }

        adapterContact.updateContacts(filteredContacts);
        Log.d("FilteredContacts", "Filtered " + filteredContacts.size() + " contacts");
    }

//    private void navigateToContactDetail(Contact contact) {
//        ContactDetailFragment detailFragment = ContactDetailFragment.newInstance(contact);
//
//        getParentFragmentManager().beginTransaction()
//                .replace(R.id.contact_profileCard, detailFragment)
//                .addToBackStack(null)
//                .commit();
//    }

    private void navigateToContactDetail(Contact contact) {
        Intent intent = new Intent(getContext(), ActivityContactDetail.class);
        intent.putExtra("contact_data", contact);
        startActivity(intent);
    }


    private void hideKeyboard() {
        View view = getView();
        if (view != null) {
            android.view.inputmethod.InputMethodManager imm =
                    (android.view.inputmethod.InputMethodManager) getContext()
                            .getSystemService(android.content.Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}