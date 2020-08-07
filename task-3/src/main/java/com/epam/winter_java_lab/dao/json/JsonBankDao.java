package com.epam.winter_java_lab.dao.json;

import com.epam.winter_java_lab.dao.json.adapters.CreditAdapter;
import com.epam.winter_java_lab.dao.json.adapters.TransactionAdapter;
import com.epam.winter_java_lab.dao.json.adapters.UserAdapter;
import com.epam.winter_java_lab.entities.Credit;
import com.epam.winter_java_lab.entities.Transaction;
import com.epam.winter_java_lab.entities.User;
import com.epam.winter_java_lab.entities.wraper.json.Bank;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonBankDao extends AbstractJsonDao<Bank> {

    public JsonBankDao(String filePath) {
        super(filePath);
    }

    @Override
    public void save(Bank object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bank parse() {
        final List<Transaction> transactions;
        final List<User> users;
        final List<Credit> credits;
        final Bank bank;

        try (Reader reader = Files.newBufferedReader(Paths.get(getFilePath()), StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Transaction.class, new TransactionAdapter())
                    .registerTypeAdapter(User.class, new UserAdapter())
                    .registerTypeAdapter(Credit.class, new CreditAdapter())
                    .create();
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.beginObject();

            jsonReader.nextName();
            users = new ArrayList<>();
            getListFromArray(jsonReader, users, User.class, gson);

            jsonReader.nextName();
            credits = new ArrayList<>();
            getListFromArray(jsonReader, credits, Credit.class, gson);

            jsonReader.nextName();
            transactions = new ArrayList<>();
            getListFromArray(jsonReader, transactions, Transaction.class, gson);
            jsonReader.endObject();
            jsonReader.close();
            bank = new Bank(users, credits, transactions);
        } catch (IOException e) {
            final String MESSAGE_EXCEPTION = "exception in method parse in JsonBankDao";
            throw new JsonDaoException(MESSAGE_EXCEPTION, e);
        }
        return bank;
    }

    @Override
    public void clean() {
        throw new UnsupportedOperationException();
    }
}
