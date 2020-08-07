package com.epam.winter_java_lab.dao.json.adapters;

import com.epam.winter_java_lab.dao.json.JsonDaoException;
import com.epam.winter_java_lab.entities.wraper.json.Setting;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.epam.winter_java_lab.entities.constant.Constants.PATTERN_DATE;

public class SettingDeserializer implements JsonDeserializer<Setting> {
    @Override
    public Setting deserialize(JsonElement jsonElement, Type type,
                               JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final String SETTINGS = "settings";
        final String FIELD_DATE_FROM = "dateFrom";
        final String FIELD_DATE_TO = "dateTo";
        final String FIELD_SORT_BY = "sortBy";
        final String FIELD_USE_DEPARTMENTS = "useDepartments";

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
        Setting.SettingBuilder builder = Setting.newBuilder();

        JsonElement element = jsonObject.get(SETTINGS);

        if (element.isJsonObject()) {
            String date;
            JsonObject object = element.getAsJsonObject();
            if (object.has(FIELD_DATE_FROM)) {
                date = object.get(FIELD_DATE_FROM).getAsString();
                builder.setDateFrom(LocalDate.parse(date, formatter));
            }

            if (object.has(FIELD_DATE_TO)) {
                date = object.get(FIELD_DATE_TO).getAsString();
                builder.setDateTo(LocalDate.parse(date, formatter));
            }

            if (!object.has(FIELD_SORT_BY)) {
                final String EXCEPTION_MESSAGE = "missing field sortBy";
                throw new JsonDaoException(EXCEPTION_MESSAGE);
            }
            String sortBy = object.get(FIELD_SORT_BY).getAsString();
            builder.setSortBy(sortBy);

            if (object.has(FIELD_USE_DEPARTMENTS)) {
                JsonArray array = object.getAsJsonArray(FIELD_USE_DEPARTMENTS);
                List<String> useDepartments = getListByJsonArray(array);
                builder.setUseDepartments(useDepartments);
            }
            parseNestedObjects(builder, object);
        }
        return builder.build();
    }

    private void parseNestedObjects(Setting.SettingBuilder builder, JsonObject jsonObject) {
        final String FIELD_SHOW_FOR = "showFor";
        final String FIELD_LIST_SHOW_FOR = "users";
        final String FIELD_TYPE_SHOW_FOR = "type";

        if (jsonObject.has(FIELD_SHOW_FOR)) {
            JsonElement element = jsonObject.get(FIELD_SHOW_FOR);
            if (element.isJsonObject()) {
                JsonObject object = element.getAsJsonObject();
                for (String member : object.keySet()) {
                    if (member.equals(FIELD_TYPE_SHOW_FOR)) {
                        builder.setShowFor(object.get(member).getAsString());
                    }
                    if (member.equals(FIELD_LIST_SHOW_FOR)) {
                        JsonArray array = object.get(member).getAsJsonArray();
                        List<String> showForList = getListByJsonArray(array);
                        builder.setShowForList(showForList);
                    }
                }
            }
        }
    }

    private List<String> getListByJsonArray(JsonArray array) {
        List<String> strings = null;
        if (Objects.nonNull(array)) {
            strings = new ArrayList<>();

            for (JsonElement jsonElement : array) {
                if (jsonElement.isJsonPrimitive()) {
                    strings.add(jsonElement.getAsString());
                }
            }
        }
        return strings;
    }
}
