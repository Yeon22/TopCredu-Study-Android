package com.example.test.project_c;

public class Contact {
    private int contactType;

    public Contact() { }

    public Contact(int contactType) {
        this.contactType = contactType;
    }
    public int getContactType() {
        return contactType;
    }

    public void setContactType(int contactType) {
        this.contactType = contactType;
    }
}
