package com.example.mobiledev_unifime;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledev_unifime.model.Contact;
import com.example.mobiledev_unifime.model.Note;

import java.util.ArrayList;
import java.util.List;

public class FragmentContactTabNote extends Fragment {

    private static final String ARG_CONTACT = "contact";
    private RecyclerView recyclerView;
    private AdapterNoteItem adapter;
    private List<Note> filteredNotes;

    public static FragmentContactTabNote newInstance(Contact contact) {
        FragmentContactTabNote fragment = new FragmentContactTabNote();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CONTACT, contact);
        Log.d("FragmentContactTabNote", "newInstance called with contact: " +
                (contact != null ? contact.getName() : "NULL"));
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_tab_note, container, false);

        // Get contact from arguments
        Contact contact = null;
        if (getArguments() != null) {
            contact = getArguments().getParcelable(ARG_CONTACT);
            Log.d("FragmentContactTabNote", "Contact received: " + (contact != null ? contact.getName() : "NULL"));
        } else {
            Log.e("FragmentContactTabNote", "No arguments bundle");
        }

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView_notes);
        if (recyclerView == null) {
            Log.e("FragmentContactTabNote", "RecyclerView is NULL");
            return view;
        }
        // Set up Grid Layout
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));

        // Get all contacts and notes
        List<Contact> allContacts = FragmentContactsMainFrame.createContacts();
        Log.d("FragmentContactTabNote", "Total contacts: " + allContacts.size());
        List<Note> allNotes = createAllNotes(allContacts);
        Log.d("FragmentContactTabNote", "Total notes created: " + allNotes.size());

        // Filter notes for this specific contact
        if (contact != null) {
            filteredNotes = filterNotesByContact(allNotes, contact.getName(), contact.getImageId());

            Log.d("FragmentContactTabNote", "Filtered notes: " + filteredNotes.size() +
                    " for contact: " + contact.getName() + " (imageId: " + contact.getImageId() + ")");

            // Debug: Print which contacts are in the notes
            for (Note note : allNotes) {
                if (note.getContact() != null) {
                    Log.d("FragmentContactTabNote", "Note contact: " + note.getContact().getName() +
                            " (imageId: " + note.getContact().getImageId() + ")");
                }
            }

        } else {
            filteredNotes = new ArrayList<>();
            Log.e("FragmentContactTabNote", "No contact data received");
        }

        // Set up the adapter with filtered notes
        adapter = new AdapterNoteItem(filteredNotes, getContext());
        recyclerView.setAdapter(adapter);

        Log.d("FragmentContactTabNote", "Adapter set with " + filteredNotes.size() + " notes");

        return view;
    }

    private List<Note> createAllNotes(List<Contact> allContacts) {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(allContacts.get(0), "Gardening", "Lola called — started gardening; tool hurts her hands. Gift idea: lightweight, ergonomic gardening tools", "Gift", "Date Created: Nov. 03, 2025"));
        notes.add(new Note(allContacts.get(2), "Hobby", "Journaling, Sketching, Yoga", "Likes", "Date Created: Dec. 30, 2024"));
        notes.add(new Note(allContacts.get(0), "Travel", "Antarctica, Korea, Japan", "Likes", "Date Created: Sep. 30, 2025"));
        notes.add(new Note(allContacts.get(2), "Pets", "cat gave birth to 3 orange kittens", "Pets", "Date Created: Oct. 10, 2025"));
        notes.add(new Note(allContacts.get(1), "Food", "Potato, tomato, carrot", "Likes", "Date Created: Jun. 18, 2025"));
        notes.add(new Note(allContacts.get(3), "Singers", "AURORA, Laufey, Beabadobee", "Likes", "Date Created: Oct. 11, 2025"));
        notes.add(new Note(allContacts.get(1), "Song", "Cappuccino Assassino", "Dislikes", "Date Created: Sep. 2, 2025"));
        notes.add(new Note(allContacts.get(2), "Singers", "Coldplay, OneRepublic", "Likes", "Date Created: Nov. 1, 2025"));
        notes.add(new Note(allContacts.get(3), "Travel", "New Zealand, Palawan, Batanes", "Likes", "Date Created: Sep. 10, 2025"));
        return notes;
    }

    private List<Note> filterNotesByContact(List<Note> allNotes, String contactName, int contactImageId) {
        List<Note> filtered = new ArrayList<>();
        for (Note note : allNotes) {
            if (note.getContact() != null &&
                    note.getContact().getName().equals(contactName) &&
                    note.getContact().getImageId() == contactImageId) {
                filtered.add(note);
            }
        }
        return filtered;
    }
}
