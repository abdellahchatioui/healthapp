package com.healthapp.ui;

import com.healthapp.models.*;
import com.healthapp.repository.PatientRepository;
import com.healthapp.utils.Input;

import java.util.List;
public class Menu {

    public static Person person;
    public static void showMainMenu(List<Person> patients, PatientRepository repo) {
        Doctor doctor = Doctor.defaultDoctor();
        while (true) {
            System.out.println("\n===== CLINIC SYSTEM =====");
            System.out.println("1. Doctor login");
            System.out.println("2. Select patient");
            System.out.println("3. Add patient");
            System.out.println("4. Save & Exit");
            int choice = Input.readInt("Choose: ", 1, 4);

            switch (choice) {
                case 1 -> {
                    String user = Input.readString("Username: ");
                    String pass = Input.readString("Password: ");
                    if (doctor.authenticate(user, pass)) {
                        doctorMenu(patients, repo);
                    } else System.out.println("Invalid credentials.");
                }
                case 2 -> selectPatientMenu(patients, repo);
                case 3 -> addPatient(patients, repo);
                case 4 -> {
                    repo.saveAll(patients);
                    System.out.println("Saved. Goodbye!");
                    System.exit(0);
                }
            }
        }
    }

    private static void doctorMenu(List<Person> patients, PatientRepository repo) {
        while (true) {
            System.out.println("\n--- DOCTOR PANEL ---");
            System.out.println("1. View all patients");
            System.out.println("2. Edit a patient");
            System.out.println("3. Delete a patient");
            System.out.println("4. Back");
            int c = Input.readInt("Choose: ", 1, 4);

            switch (c) {
                case 1 -> {
                    int i=1;
                    for (Person p: patients) {
                        System.out.printf("%d) %s (Age %d)\n", i++, p.getFullName(), p.getAge());
                    }
                    System.out.println();
                }
                case 2 -> editPatient(patients, repo);
                case 3 -> deletePatient(patients, repo);
                case 4 -> { repo.saveAll(patients); return; }
            }
        }
    }

    private static void selectPatientMenu(List<Person> patients, PatientRepository repo){
        if (patients.isEmpty()) {
            System.out.println("No patients.");
            return;
        }
        int idx = choosePatient(patients);
        if (idx < 0) return;
        Person selected = patients.get(idx);
        patientOrgansMenu(selected, repo, patients);
    }

    private static int choosePatient(List<Person> patients) {
        int i = 1;
        for (Person p : patients) {
            System.out.printf("%d) %s (Age %d)\n", i++, p.getFullName(), p.getAge());
        }
        int choice = Input.readInt("Select patient number (0 cancel): ", 0, patients.size());
        if (choice == 0) return -1;
        return choice - 1;
    }

    private static void patientOrgansMenu(Person person, PatientRepository repo, List<Person> allPatients) {
        while (true) {
            System.out.println("\nPatient: " + person.getFullName());
            System.out.println("1. Eyes");
            System.out.println("2. Heart");
            System.out.println("3. Skin");
            System.out.println("4. Stomach");
            System.out.println("5. Back");
            int c = Input.readInt("Choose: ",1,5);
            switch (c) {
                case 1 -> person.getEyes().showDetails();
                case 2 -> person.getHeart().showDetails();
                case 3 -> person.getSkin().showDetails();
                case 4 -> person.getStomach().showDetails();
                case 5 -> { repo.saveAll(allPatients); return; }
            }
            // after each organ action, auto-save
            repo.saveAll(allPatients);
        }
    }

    private static void addPatient(List<Person> patients, PatientRepository repo) {
        System.out.println("\n--- Add Patient ---");
        String fn = Input.readString("First name: ");
        String ln = Input.readString("Last name: ");
        int age = Input.readInt("Age: ", 0, 150);

        // organ defaults (ask user)
        String eyeColor = Input.readString("Eye color (e.g. Blue): ");
        String eyeState = Input.readString("Eye condition (e.g. Short Sighted): ");
        int heartRate = Input.readInt("Heart rate (BPM): ", 20, 300);
        String heartCond = Input.readString("Heart condition: ");
        String skinColor = Input.readString("Skin color: ");
        String skinCond = Input.readString("Skin condition: ");
        String stomachStatus = Input.readString("Stomach status: ");
        boolean digesting = Input.readInt("Is digesting? (1 yes, 0 no): ", 0, 1) == 1;

        Eyes eyes = new Eyes("Eyes", eyeColor, eyeState, true);
        Heart heart = new Heart("Heart", heartRate, heartCond);
        Skin skin = new Skin("Skin", skinColor, skinCond);
        Stomach stomach = new Stomach("Stomach", stomachStatus, digesting);

        Person newP = new Person(fn, ln, age, eyes, heart, skin, stomach);
        patients.add(newP);
        repo.saveAll(patients);
        System.out.println("Patient added.");
    }   

    private static void editPatient(List<Person> patients, PatientRepository repo) {
        int idx = choosePatient(patients);
        if (idx < 0) return;
        Person p = patients.get(idx);
        System.out.println("Editing " + p.getFullName());
        String newFirst = Input.readString("New first name (enter to keep): ");
        if (!newFirst.isBlank()) p.setFirstName(newFirst);
        String newLast = Input.readString("New last name (enter to keep): ");
        if (!newLast.isBlank()) p.setLastName(newLast);
        int newAge = Input.readInt("New age (0 = keep): ", 0, 150);
        if (newAge != 0) p.setAge(newAge);
        repo.saveAll(patients);
    }

    private static void deletePatient(List<Person> patients, PatientRepository repo) {
        int idx = choosePatient(patients);
        if (idx < 0) return;
        Person p = patients.remove(idx);
        repo.saveAll(patients);
        System.out.println("Deleted " + p.getFullName());
    }
}
