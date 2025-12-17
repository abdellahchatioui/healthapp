package com.healthapp.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.healthapp.models.Person;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository {

    private final Path filePath;
    private final Gson gson;

    public PatientRepository(String filename) {
        this.filePath = Path.of(filename);
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public List<Person> loadAll() {
        try {
            if (!Files.exists(filePath)) {
                return new ArrayList<>();
            }
            Reader reader = Files.newBufferedReader(filePath);
            Type listType = new TypeToken<List<Person>>() {}.getType();
            List<Person> list = gson.fromJson(reader, listType);
            reader.close();
            return list != null ? list : new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Failed to load patients: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean saveAll(List<Person> patients) {
        try {
            Writer writer = Files.newBufferedWriter(filePath);
            gson.toJson(patients, writer);
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("Failed to save patients: " + e.getMessage());
            return false;
        }
    }
}
