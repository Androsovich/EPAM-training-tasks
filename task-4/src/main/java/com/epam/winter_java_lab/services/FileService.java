package com.epam.winter_java_lab.services;

import com.epam.winter_java_lab.entiities.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService {
    private final static String PATH = "src/orders.txt";

    public static void saveFile(User user) {
        final String DELIMITER = ";";
        final String ORDER_START_STRING = "order : ";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {
            writer.append(String.valueOf(user.getId()))
                    .append(DELIMITER)
                    .append(user.getName())
                    .append(DELIMITER)
                    .append(user.getSecondName())
                    .append(DELIMITER)
                    .append(user.getBirthDay().toString())
                    .append(DELIMITER)
                    .append(user.getNumberPhone())
                    .append(DELIMITER)
                    .append(ORDER_START_STRING)
                    .append(user.getOrder().getDescription())
                    .append(DELIMITER)
                    .append(String.valueOf(user.getOrder().getCost()))
                    .append("\n")
                    .flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile() {
        try {
            if (!Files.notExists(Paths.get(PATH))) {
                Files.delete(Paths.get(PATH));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
