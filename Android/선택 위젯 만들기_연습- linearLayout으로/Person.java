package com.example.p428;

public class Person {
    int img;
    String id;
    String name;
    int age;

    public Person() {
    }

    public Person(int img, String id, String name, int age) {
        this.img = img;
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
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

    @Override
    public String toString() {
        return "Person{" +
                "img=" + img +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
