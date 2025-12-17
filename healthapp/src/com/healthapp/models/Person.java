package com.healthapp.models;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    private Eyes eyes;
    private Heart heart;
    private Skin skin;
    private Stomach stomach;



    public Person(String firstName, String lastName, int age,
                  Eyes eyes, Heart heart, Skin skin, Stomach stomach) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.eyes = eyes;
        this.heart = heart;
        this.skin = skin;
        this.stomach = stomach;
    }

    public Eyes getEyes() { return eyes; }
    public Heart getHeart() { return heart; }
    public Skin getSkin() { return skin; }
    public Stomach getStomach() { return stomach; }

    public String getFullName() {return firstName + " " + lastName;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;};
    public void setLastName(String lastName) {this.lastName = lastName;}

    public void setAge(int age) {this.age = age;}
    public int getAge() { return age; }
}
