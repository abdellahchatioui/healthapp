package com.healthapp.models;

import com.healthapp.ui.Menu;
import com.healthapp.utils.Input;

public class Stomach extends Organ {

    private String status;
    private boolean isDigesting;

    public Stomach(String name, String status, boolean isDigesting) {
        super(name);
        this.status = status;
        this.isDigesting = isDigesting;
    }

    @Override
    public void showDetails() {
        System.out.println("--- Stomach ---");
        System.out.println("Status: " + status);
        System.out.println("Digesting: " + (isDigesting ? "Yes" : "No"));

        if (!isDigesting) {
            System.out.println("\n1. Eat Food");
            System.out.println("2. Back");
            int choice = Input.readInt("Choose: ", 1, 2);

            if (choice == 1) {
                isDigesting = true;
                status = "Digesting food...";
                System.out.println("Stomach is now digesting.");
            }
        }
        // Menu.showMainMenu(Menu.person);
    }
}
