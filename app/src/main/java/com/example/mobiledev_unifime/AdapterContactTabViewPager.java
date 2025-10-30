package com.example.mobiledev_unifime;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AdapterContactTabViewPager extends FragmentStateAdapter {
    public AdapterContactTabViewPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragmentContactTabNote();
            case 1:
                return new FragmentContactTabReminder();
            default:
                return new FragmentContactTabNote();
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Number of tabs
    }
}