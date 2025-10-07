package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("logs.txt", true));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Scanner scan = new Scanner(System.in);

        boolean isRunning = true;
        boolean isFirstRun = true;

        while (isRunning) {
            try {
                LocalDateTime localDate = LocalDateTime.now();
                String formattedDate = localDate.format(dateTimeFormatter);

                if (isFirstRun) {
                    writeLog(writer, formattedDate + " launch");
                    isFirstRun = false;
                }
                System.out.println("Enter a search term (X to exit):");
                String userChoice = scan.nextLine();

                if (userChoice.equalsIgnoreCase("x")) {
                    writeLog(writer, formattedDate + " exit");
                    isRunning = false;
                } else {
                    writeLog(writer, formattedDate + " search : " + userChoice);
                }
            } catch (Exception ex) {
                System.err.println("An error occurred: " + ex.getMessage());
            }
        }

        writer.close();
        scan.close();
    }
    public static void writeLog(BufferedWriter writer, String message) throws IOException {
        writer.write(message);
        writer.newLine();
    }
}
