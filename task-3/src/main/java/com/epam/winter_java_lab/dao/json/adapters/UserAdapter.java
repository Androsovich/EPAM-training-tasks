package com.epam.winter_java_lab.dao.json.adapters;

import com.epam.winter_java_lab.entities.User;
import com.epam.winter_java_lab.entities.enums.Sex;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.epam.winter_java_lab.entities.constant.Constants.FIELD_ID;
import static com.epam.winter_java_lab.entities.constant.Constants.PATTERN_DATE;

public class UserAdapter implements JsonDeserializer<User>, JsonSerializer<User> {
    private final String FIELD_NAME = "name";
    private final String FIELD_SECOND_NAME = "secondName";
    private final String FIELD_SEX = "sex";
    private final String FIELD_BIRTHDAY = "birthday";

    @Override
    public User deserialize(JsonElement jsonElement, Type type,
                            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        User user = new User();
        String date = jsonObject.get(FIELD_BIRTHDAY).getAsString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
        LocalDate localDate = LocalDate.parse(date, formatter);

        user.setId(jsonObject.get(FIELD_ID).getAsLong());
        user.setName(jsonObject.get(FIELD_NAME).getAsString());
        user.setSecondName(jsonObject.get(FIELD_SECOND_NAME).getAsString());
        String sex = jsonObject.get(FIELD_SEX).getAsString();
        user.setSex(Sex.valueOf(sex));
        user.setBirthday(localDate);

        return user;
    }

    @Override
    public JsonElement serialize(User user, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonUser = new JsonObject();
        jsonUser.add(FIELD_ID, new JsonPrimitive(user.getId()));
        jsonUser.add(FIELD_NAME, new JsonPrimitive(user.getName()));
        jsonUser.add(FIELD_SECOND_NAME, new JsonPrimitive(user.getSecondName()));
        jsonUser.add(FIELD_SEX, new JsonPrimitive(user.getSex().toString()));
        jsonUser.add(FIELD_BIRTHDAY, new JsonPrimitive(user.getBirthday().toString()));
        return jsonUser;
    }
}
