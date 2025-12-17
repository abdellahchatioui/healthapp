package com.healthapp.models;

import com.healthapp.ui.Menu;
import com.healthapp.utils.Input;

public class Heart extends Organ {

    private int heartRate;
    private String condition;

    public Heart(String name, int heartRate, String condition) {
        super(name);
        this.heartRate = heartRate;
        this.condition = condition;
    }

    @Override
    public void showDetails() {
        System.out.println("--- Heart ---");
        System.out.println("Rate: " + heartRate + " BPM");
        System.out.println("Condition: " + condition);

        System.out.println("\n1. Change Heart Rate");
        System.out.println("2. Back");

        int choice = Input.readInt("Choose: ", 1, 2);

        if (choice == 1) {
            heartRate = Input.readInt("Enter new heart rate: ", 30, 200);
            System.out.println("Heart rate updated!");
        }

        // Menu.showMainMenu(Menu.person);
    }
}
