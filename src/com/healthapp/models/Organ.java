package com.healthapp.models;

public abstract class Organ {
    private String name;

    public Organ(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public abstract void showDetails();
}
