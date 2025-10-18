package com.example.mobiledev_unifime;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobiledev_unifime.model.Contact;
import com.example.mobiledev_unifime.model.Note;

import java.util.ArrayList;
import java.util.List;

public class FragmentNoteMainFrame extends Fragment {

    private RecyclerView recyclerView;
    private AdapterNoteItem adapter;
    private List<Note> noteList;
    private List<Contact> allContacts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main_fragment_notes, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView_notes);

        // Set the LayoutManager to GridLayoutManager
        int numberOfColumns = 2; // Adjust the number of columns as needed
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));

        // Initialize allContacts
        allContacts = FragmentContactsMainFrame.createContacts();

        // Prepare your data
        noteList = new ArrayList<>();
        noteList.add(new Note(allContacts.get(1), "Travel", "Antarctica, Korea, Japan", "Like"));
        noteList.add(new Note(allContacts.get(2), "Hobby", "Journaling, Sketching, Yoga", "Like"));
        noteList.add(new Note(allContacts.get(0), "Travel", "Antarctica, Korea, Japan", "Like"));

        // Set up the adapter
        adapter = new AdapterNoteItem(noteList, getContext());
        recyclerView.setAdapter(adapter);

        // Log for debugging
        Log.d("FragmentNoteMainFrame", "Number of notes: " + noteList.size());

        return view;
    }

}