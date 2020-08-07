package com.epam.winter_java_lab.dao.json;

import com.epam.winter_java_lab.dao.json.adapters.CreditAdapter;
import com.epam.winter_java_lab.dao.json.adapters.TransactionAdapter;
import com.epam.winter_java_lab.dao.json.adapters.UserAdapter;
import com.epam.winter_java_lab.entities.Credit;
import com.epam.winter_java_lab.entities.Transaction;
import com.epam.winter_java_lab.entities.User;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

abstract class AbstractJsonDao<T> implements JsonDao<T> {
    private String filePath;

    AbstractJsonDao(String filePath) {
        this.filePath = filePath;
    }

    String getFilePath() {
        return filePath;
    }

    public <V> void addListToJsonArray(String jsonArrayName, List<V> values, Class<V> clazz) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Transaction.class, new TransactionAdapter())
                .registerTypeAdapter(User.class, new UserAdapter())
                .registerTypeAdapter(Credit.class, new CreditAdapter())
                .create();
        JsonParser parser = new JsonParser();

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
            JsonElement element = parser.parse(reader);
            JsonObject jsonObject = element.getAsJsonObject();
            JsonArray jsonArray = findArray(jsonObject, jsonArrayName);

            for (V value : values) {
                JsonElement jsonElement = gson.toJsonTree(value, clazz);
                jsonArray.add(jsonElement);
            }
            save(gson.toJson(element));
        } catch (IOException e) {
            final String MESSAGE_EXCEPTION = "exception in method addListToJsonArray";
            throw new JsonDaoException(MESSAGE_EXCEPTION, e);
        }
    }

    <V> void getListFromArray(JsonReader jsonReader, List<V> list, Class<V> clazz, Gson gson) {
        try {
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                list.add(gson.fromJson(jsonReader, clazz));
            }
            jsonReader.endArray();
        } catch (IOException e) {
            final String MESSAGE_EXCEPTION = "exception in method getListFromArray";
            throw new JsonDaoException(MESSAGE_EXCEPTION, e);
        }
    }

    private JsonArray findArray(JsonObject jsonObject, String jsonArrayName) {
        JsonArray array = null;
        for (String members : jsonObject.keySet()) {
            JsonElement element = jsonObject.get(members);
            if (members.equals(jsonArrayName)) {
                if (element.isJsonArray()) {
                    array = (JsonArray) element;
                    break;
                }
                if (element.isJsonObject()) {
                    JsonObject object = element.getAsJsonObject();
                    if (object.isJsonArray()) {
                        array = object.getAsJsonArray(jsonArrayName);
                        break;
                    } else {
                        array = findArray(object, jsonArrayName);
                    }
                }
            }
        }
        return array;
    }

    public void save(String json) {
        try(  Writer writer = Files.newBufferedWriter(Paths.get(filePath), StandardCharsets.UTF_8)){
            JsonWriter jsonWriter = new JsonWriter(writer);
            jsonWriter.setIndent("  ");
            jsonWriter.jsonValue(json);
            jsonWriter.close();
        } catch (IOException e) {
            final String MESSAGE_EXCEPTION = "exception save json file";
            throw new JsonDaoException(MESSAGE_EXCEPTION, e);
        }
    }


}
