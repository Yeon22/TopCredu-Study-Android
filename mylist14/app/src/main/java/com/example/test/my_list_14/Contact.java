package com.example.test.my_list_14;

public class Contact {
    private int photo;
    private String name;
    private String alias;
    private String message;

    public Contact(int photo, String name, String alias, String message) {
        this.photo = photo;
        this.name = name;
        this.alias = alias;
        this.message = message;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
