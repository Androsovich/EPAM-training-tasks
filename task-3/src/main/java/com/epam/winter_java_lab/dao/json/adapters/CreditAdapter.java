package com.epam.winter_java_lab.dao.json.adapters;

import com.epam.winter_java_lab.entities.Credit;
import com.epam.winter_java_lab.entities.enums.Periods;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.epam.winter_java_lab.entities.constant.Constants.*;
import static com.epam.winter_java_lab.entities.constant.Constants.FIELD_MONEY;

public class CreditAdapter implements JsonDeserializer<Credit>, JsonSerializer<Credit> {
    private final String FIELD_USER_ID = "userId";
    private final String FIELD_PERIOD = "period";
    private final String FIELD_RATE = "rate";

    @Override
    public Credit deserialize(JsonElement jsonElement, Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Credit.CreditBuilder builder = Credit.newBuilder();

        String date = jsonObject.get(FIELD_DATE).getAsString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
        LocalDate localDate = LocalDate.parse(date, formatter);

        builder.setId(jsonObject.get(FIELD_ID).getAsLong());
        builder.setUserId(jsonObject.get(FIELD_USER_ID).getAsLong());
        builder.setDate(localDate);
        String period = jsonObject.get(FIELD_PERIOD).getAsString();
        builder.setPeriods(Periods.valueOf(period));
        BigDecimal money = new BigDecimal(jsonObject.get(FIELD_MONEY).getAsLong());
        builder.setMoney(money);
        BigDecimal rate = BigDecimal.valueOf(jsonObject.get(FIELD_RATE).getAsDouble());
        builder.setRate(rate);

        return builder.build();
    }

    @Override
    public JsonElement serialize(Credit credit, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonCredit = new JsonObject();
        jsonCredit.add(FIELD_ID, new JsonPrimitive( credit.getId()));
        jsonCredit.add(FIELD_USER_ID, new JsonPrimitive( credit.getUserId()));
        jsonCredit.add(FIELD_DATE, new JsonPrimitive(credit.getDate().toString()));
        jsonCredit.add(FIELD_PERIOD, new JsonPrimitive(credit.getPeriods().toString()));
        jsonCredit.add(FIELD_MONEY, new JsonPrimitive(credit.getMoney().longValue()));
        jsonCredit.add(FIELD_RATE, new JsonPrimitive(credit.getRate().doubleValue()));
        return jsonCredit;
    }
}
