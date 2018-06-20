package com.example.test.my_list_15;

public class Contact {
    // 고객의 실제정보
    private int imageId;
    private String surname;
    private String name;
    private String email;

    // 사용할 디자인 정보
    private int contactType;

    public Contact() { }

    public Contact(int imageId, String surname, String name, String email,
                   int contactType) {
        this.imageId = imageId;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.contactType = contactType;
    }

    public int getImageId() {
        return imageId;
    }

    public Contact setImageId(int imageId) {
        this.imageId = imageId;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Contact setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getName() {
        return name;
    }

    public Contact setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Contact setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getContactType() {
        return contactType;
    }

    public Contact setContactType(int contactType) {
        this.contactType = contactType;
        return this;
    }

}
