package com.epam.winter.java.lab.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class FileService {

    public static List<String> readFile(String path) throws FileNotFoundException{
            List<String> expressionList = new ArrayList<>();

        Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                expressionList.add(line.trim()); //  trim the spaces at the beginning and end
            }
            scanner.close();
        return expressionList;
    }
}
