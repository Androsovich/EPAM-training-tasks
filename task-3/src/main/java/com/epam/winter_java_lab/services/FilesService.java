package com.epam.winter_java_lab.services;


import com.epam.winter_java_lab.dao.json.JsonSettingDao;
import com.epam.winter_java_lab.entities.wraper.json.Setting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.epam.winter_java_lab.entities.constant.Constants.JSON_DB_SETTING;
import static com.epam.winter_java_lab.entities.constant.Constants.PATH_DIR;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;


public class FilesService {
    private final static Path pathDir = Paths.get(PATH_DIR);
    private final static String EXT_FILE = ".json";
    private final static String START_NAME_FILE = "db_";
    private final static String PATTERN_FILE = "[A-Za-z0-9]*";


    public static String getPath(String file) {
        return pathDir.resolve(file).toString();
    }

    public static List<String> getFilesBySetting(Setting setting, List<String> filesList){
        Optional<List<String>> department = Optional.ofNullable(setting.getUseDepartments());
        if (department.isPresent()) {
            filesList = filesList.stream()
                    .filter(x -> {
                        String s = x.substring(START_NAME_FILE.length(), x.length() - EXT_FILE.length());
                        return setting.getUseDepartments().stream().anyMatch(s1 -> s1.equals(s));
                    }).collect(Collectors.toList());
        }
        return filesList;
    }

    public static List<String> readWorkDir(String dbFile, String setting) {
        List<String> pathList = null;

        if (!(exists(getPath(dbFile)) || exists(getPath(setting))))
            throw new RuntimeException("no file setting or db");

        try (Stream<Path> paths = Files.walk(pathDir)) {
            pathList = paths.filter(Files::isRegularFile)
                    .map(x -> x.getFileName().toString())
                    .filter(x -> x.endsWith(EXT_FILE))
                    .filter(x -> x.startsWith(START_NAME_FILE))
                    .filter(x -> x.substring(START_NAME_FILE.length(), x.length() - EXT_FILE.length())
                            .matches(PATTERN_FILE))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathList;
    }

    public static Setting getSetting() {
        return new JsonSettingDao(getPath(JSON_DB_SETTING)).parse();
    }

    private static boolean exists(String fileName) {
        final Path path = Paths.get(fileName);
        return Files.exists(path, NOFOLLOW_LINKS) && !Files.isDirectory(path, NOFOLLOW_LINKS);
    }





}
