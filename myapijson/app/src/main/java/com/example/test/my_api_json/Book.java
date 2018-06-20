package com.example.test.my_api_json;

public class Book {
    private String img;
    private String title;
    private String price;
    private String author;

    public Book(String img, String title, String price, String author) {
        this.img = img;
        this.title = title;
        this.price = price;
        this.author = author;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
