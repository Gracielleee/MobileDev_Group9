package com.example.mobiledev_unifime.model;

// Contact.java
public class Contact {
    private String name;
    private String phoneNumber;
    private String group; // e.g., "Family", "Work", "School"

    public Contact(String name, String phoneNumber, String group) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.group = group;
    }

    // Getters
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getGroup() {
        return group;
    }
}