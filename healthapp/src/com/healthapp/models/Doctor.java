package com.healthapp.models;

public class Doctor {
    private String username;
    private String password; // in real app hash it!

    public Doctor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }

    // simple default admin
    public static Doctor defaultDoctor() {
        return new Doctor("admin", "admin"); // change password in real use
    }
}
