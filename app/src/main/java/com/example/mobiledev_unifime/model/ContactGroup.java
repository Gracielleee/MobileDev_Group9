package com.example.mobiledev_unifime.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public enum ContactGroup implements Parcelable {
    FAMILY("Family"),
    CHURCH("Church"),
    HIKING("Hiking"),
    SCHOOL("School"),
    WORK("Work"),
    PROVINCE("Province");

    private final String displayName;

    ContactGroup(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(this.name()); // Write the name of the enum constant
    }

    // The CREATOR field to recreate the enum from a Parcel
    public static final Creator<ContactGroup> CREATOR = new Creator<ContactGroup>() {
        @Override
        public ContactGroup createFromParcel(Parcel in) {
            return ContactGroup.valueOf(in.readString()); // Recreate the enum from its name
        }

        @Override
        public ContactGroup[] newArray(int size) {
            return new ContactGroup[size]; // Array creation
        }
    };
}
