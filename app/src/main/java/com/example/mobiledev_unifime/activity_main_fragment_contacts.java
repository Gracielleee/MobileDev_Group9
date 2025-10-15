package com.example.mobiledev_unifime;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class activity_main_fragment_contacts extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main_fragment_contacts, container, false);

        // Set up Contact Group Recycler View
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_contactGroup);

        List<String> contactGroups = Arrays.asList("Family", "Church", "Hiking", "School", "Work", "Province");

        adapter_contactgroup adapter = new adapter_contactgroup(contactGroups, groupName -> {
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);

        // Add snap helper for snapping effect
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        return view;
    }
}