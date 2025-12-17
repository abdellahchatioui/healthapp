package com.healthapp;

import com.healthapp.models.*;
import com.healthapp.repository.PatientRepository;
import com.healthapp.ui.Menu;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Load patients from disk (or create default if none)
        PatientRepository repo = new PatientRepository("patients.json");
        List<Person> patients = repo.loadAll();

        // If empty, create a default patient to demo
        if (patients.isEmpty()) {
            Eyes eyes = new Eyes("Eyes", "Blue", "Short Sighted", true);
            Heart heart = new Heart("Heart", 80, "Normal");
            Skin skin = new Skin("Skin", "White", "Normal");
            Stomach stomach = new Stomach("Stomach", "Hungry", false);

            Person p = new Person("Abdellah", "Chatioui", 21, eyes, heart, skin, stomach);
            patients.add(p);
            repo.saveAll(patients);
        }

        // Show main menu (will allow doctor login and patient selection)
        Menu.showMainMenu(patients, repo);
    }
}
