package com.epam.winter_java_lab.dao.json;

import com.epam.winter_java_lab.dao.json.adapters.SettingDeserializer;
import com.epam.winter_java_lab.entities.wraper.json.Setting;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSettingDao extends AbstractJsonDao<Setting> {

    public JsonSettingDao(String filePath) {
        super(filePath);
    }

    @Override
    public void save(Setting object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Setting parse() {
        Setting setting;
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Setting.class, new SettingDeserializer())
                .setPrettyPrinting()
                .setLenient()
                .create();
        try (Reader reader = Files.newBufferedReader(Paths.get(getFilePath()), StandardCharsets.UTF_8)) {
            setting = gson.fromJson(reader, Setting.class);
        } catch (IOException e) {
            final String MESSAGE_EXCEPTION = "exception in method parse in JsonSettingDao";
            throw new JsonDaoException(MESSAGE_EXCEPTION, e);
        }
        return setting;
    }

    @Override
    public void clean() {
        throw new UnsupportedOperationException();
    }
}
