package com.example.p500;

public class Item {
    String id;
    String name;
    int age;
    String img;
    String img2;

    public Item() {
    }

    public Item(String id, String name, int age, String img) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.img = img;
    }

    public Item(String id, String name, int age, String img, String img2) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.img = img;
        this.img2 = img2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }
}
