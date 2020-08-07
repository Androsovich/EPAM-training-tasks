package com.epam.winter_java_lab;

import com.epam.winter_java_lab.services.FileService;
import com.epam.winter_java_lab.services.Menu;

public class MainClass {
    public static void main(String[] args) {
        FileService.deleteFile();
        Menu.startApp();
    }
}
