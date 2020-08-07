package com.epam.winter_java_lab.dao.json.adapters;

import com.epam.winter_java_lab.entities.Transaction;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.epam.winter_java_lab.entities.constant.Constants.*;
import static com.epam.winter_java_lab.entities.constant.Constants.FIELD_ID;

public class TransactionAdapter implements JsonDeserializer<Transaction>, JsonSerializer<Transaction> {

    @Override
    public Transaction deserialize(JsonElement jsonElement, Type type,
                                   JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Transaction transaction = new Transaction();

        transaction.setId(jsonObject.get(FIELD_ID).getAsLong());
            String date = jsonObject.get(FIELD_DATE).getAsString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
            LocalDate localDate = LocalDate.parse(date, formatter);
        transaction.setDate(localDate);
        transaction.setCreditId(jsonObject.get(FIELD_CREDIT_ID).getAsLong());
            BigDecimal money = new BigDecimal(jsonObject.get(FIELD_MONEY).getAsLong());
        transaction.setMoney(money);
        return transaction;
    }

    @Override
    public JsonElement serialize(Transaction transaction,
                                 Type type, JsonSerializationContext jsonSerializationContext) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
        JsonObject jsonTransaction = new JsonObject();
        jsonTransaction.add(FIELD_ID, new JsonPrimitive( transaction.getId()));
        jsonTransaction.add(FIELD_DATE, new JsonPrimitive(formatter.format(transaction.getDate())));
        jsonTransaction.add(FIELD_CREDIT_ID, new JsonPrimitive(transaction.getCreditId()));
        jsonTransaction.add(FIELD_MONEY, new JsonPrimitive(transaction.getMoney().longValue()));
        return jsonTransaction;
    }
}
