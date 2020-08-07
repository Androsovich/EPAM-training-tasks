package com.epam.winter_java_lab.dao.json;

import com.epam.winter_java_lab.dao.json.adapters.TransactionAdapter;
import com.epam.winter_java_lab.entities.Transaction;
import com.epam.winter_java_lab.entities.wraper.json.BankBranch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.epam.winter_java_lab.entities.constant.Constants.*;

public class JsonBankBranchDao extends AbstractJsonDao<BankBranch> {

    public JsonBankBranchDao(String filePath) {
        super(filePath);
    }

    @Override
    public void save(BankBranch branch) {
        final String INDENT = "  ";

         new GsonBuilder()
                .registerTypeAdapter(Transaction.class, new TransactionAdapter())
                .setPrettyPrinting()
                .create();

            try (Writer writer = Files.newBufferedWriter(Paths.get(getFilePath()))) {
                JsonWriter jsonWriter = new JsonWriter(writer);
                jsonWriter.setIndent(INDENT);
                jsonWriter.beginObject();
                jsonWriter.name(ARRAY_TRANSACTIONS_NAME);
                jsonWriter.beginArray();

                for (Transaction transaction : branch.getTransactions()) {
                    writeTransaction(jsonWriter, transaction);
                }
                jsonWriter.endArray();
                jsonWriter.endObject();
                jsonWriter.close();
            } catch (IOException e) {
                final String MESSAGE_EXCEPTION = "exception in method save in JsonBankBranchDao";
                throw new JsonDaoException(MESSAGE_EXCEPTION, e);
            }
    }

    @Override
    public BankBranch parse() {
        List<Transaction> transactions;
        try (Reader reader = Files.newBufferedReader(Paths.get(getFilePath()), StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Transaction.class, new TransactionAdapter())
                    .create();
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.beginObject();
            jsonReader.nextName();
            transactions = new ArrayList<>();
            getListFromArray(jsonReader, transactions, Transaction.class, gson);
            jsonReader.endObject();
            jsonReader.close();
        } catch (IOException e) {
            final String MESSAGE_EXCEPTION = "exception in method parse in JsonBankBranchDao";
            throw new JsonDaoException(MESSAGE_EXCEPTION, e);
        }
        return new BankBranch(transactions);
    }

    @Override
    public void clean() {
        try (JsonWriter writer = new JsonWriter(new FileWriter(getFilePath()))) {
            writer.beginObject();                   // {
            writer.name(ARRAY_TRANSACTIONS_NAME);   // "transactions" :
            writer.beginArray();                    // [
            writer.endArray();                       //]
            writer.endObject();                      // }
        } catch (IOException e) {
            final String MESSAGE_EXCEPTION = "exception in method clean in JsonBankBranchDao";
            throw new JsonDaoException(MESSAGE_EXCEPTION, e);
        }
    }

    private void writeTransaction(JsonWriter writer, Transaction transaction) throws IOException {
        writer.beginObject();
        writer.name(FIELD_ID).value(transaction.getId());
        writer.name(FIELD_DATE).value(transaction.getDate().toString());
        writer.name(FIELD_CREDIT_ID).value(transaction.getCreditId());
        writer.name(FIELD_MONEY).value(transaction.getMoney().longValue());
        writer.endObject();
    }
}


