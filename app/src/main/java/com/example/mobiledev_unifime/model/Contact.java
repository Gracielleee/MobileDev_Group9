package com.example.mobiledev_unifime.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
    private String name;
    private int imageId;
    private ContactGroup group;
    private String birthday;
    private String description;

    public Contact(String name, int imageId, ContactGroup group, String birthday, String description) {
        this.name = name;
        this.imageId = imageId;
        this.group = group;
        this.birthday = birthday;
        this.description = description;
    }

    protected Contact(Parcel in) {
        name = in.readString();
        imageId = in.readInt();
        group = in.readParcelable(ContactGroup.class.getClassLoader());
        birthday = in.readString();
        description = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(imageId);
        dest.writeParcelable(group, flags);
        dest.writeString(birthday);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Getters
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

    public String getDescription() {
        return description;
    }
}
