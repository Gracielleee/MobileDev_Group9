package com.example.mobiledev_unifime;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mobiledev_unifime.model.Contact;

public class AdapterContactTabViewPager extends FragmentStateAdapter {
    private final Contact contact;

    public AdapterContactTabViewPager(@NonNull FragmentActivity fragmentActivity, Contact contact) {
        super(fragmentActivity);
        this.contact = contact;
        Log.d("AdapterContactTabViewPager", "Contact passed to adapter: " +
                (contact != null ? contact.getName() : "NULL"));
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d("AdapterContactTabViewPager", "Creating fragment at position: " + position);
        switch (position) {
            case 0:
                FragmentContactTabNote noteFragment = FragmentContactTabNote.newInstance(contact);
                Log.d("AdapterContactTabViewPager", "Created note fragment for: " +
                        (contact != null ? contact.getName() : "NULL"));
                return noteFragment;
            case 1:
                return new FragmentContactTabReminder();
            default:
                return FragmentContactTabNote.newInstance(contact);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}