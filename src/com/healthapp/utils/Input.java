package com.healthapp.utils;

import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            try {
                String line = scanner.nextLine();
                if (line.isBlank()) {
                    // if user pressed Enter and min==0 maybe we treat as 0
                    if (min <= 0 && max >= 0) return 0;
                    System.out.println("Please enter a number.");
                    continue;
                }
                int value = Integer.parseInt(line.trim());
                if (value < min || value > max) {
                    System.out.println("Enter number between " + min + " and " + max);
                    continue;
                }
                return value;
            } catch (Exception e) {
                System.out.println("Invalid number!");
            }
        }
    }

    public static String readString(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }
}
