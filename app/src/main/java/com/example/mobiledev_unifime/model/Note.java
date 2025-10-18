package com.example.mobiledev_unifime.model;

import java.util.List;

public class Note {
    private Contact contact;
    private String title;
    private String description;
    private int imageId;
    private String tag;

    public Note(Contact contact,  String title, String description, String tag) {
        this.contact = contact;
        this.description = description;
        this.imageId = contact.getImageId();
        this.tag = tag;
        this.title = title;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

