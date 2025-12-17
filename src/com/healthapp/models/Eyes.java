package com.healthapp.models;

import com.healthapp.ui.Menu;
import com.healthapp.utils.Input;

public class Eyes extends Organ {

    private String color;
    private String visionState;
    private boolean isOpen;

    public Eyes(String name, String color, String visionState, boolean isOpen) {
        super(name);
        this.color = color;
        this.visionState = visionState;
        this.isOpen = isOpen;
    }

    @Override
    public void showDetails() {
        System.out.println("--- " + getName() + " ---");
        System.out.println("Color: " + color);
        System.out.println("Vision: " + visionState);
        System.out.println("State: " + (isOpen ? "Open" : "Closed"));

        System.out.println("\n1. Toggle Eye State");
        System.out.println("2. Change Color");
        System.out.println("3. Back");

        int choice = Input.readInt("Choose: ", 1, 3);

        switch(choice) {
            case 1:
                isOpen = !isOpen;
                System.out.println("Eyes are now " + (isOpen ? "Open" : "Closed"));
                break;
            case 2:
                color = Input.readString("New Eye Color: ");
                break;
            case 3:
                return; // 👈 just go back
        }
    }

}
