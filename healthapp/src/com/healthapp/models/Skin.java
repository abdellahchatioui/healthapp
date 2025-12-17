package com.healthapp.models;

import com.healthapp.ui.Menu;

public class Skin extends Organ {
    private String color;
    private String condition;

    public Skin(String name, String color, String condition) {
        super(name);
        this.color = color;
        this.condition = condition;
    }

    @Override
    public void showDetails() {
        System.out.println("--- Skin ---");
        System.out.println("Color: " + color);
        System.out.println("Condition: " + condition);

        // Menu.showMainMenu(Menu.person);
    }
}
