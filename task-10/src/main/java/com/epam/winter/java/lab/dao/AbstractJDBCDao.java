package com.epam.winter.java.lab.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.epam.winter.java.lab.dao.utills.ConnectorDb.getConnection;

public abstract class AbstractJDBCDao {
    private Connection connection;

    protected <T> List<T> getAll(String sql) {
        List<T> objects;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            objects = new ArrayList<>();
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T object = parseResultSet(resultSet);
                objects.add(object);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(connection, statement, resultSet);
        }
        return objects;
    }

    protected <T> T getById(String sql, int key) {
        T entity = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            final int INDEX_ID = 1;
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(INDEX_ID, key);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entity = parseResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(connection, statement, resultSet);
        }
        return entity;
    }

    protected void executeUpdate(String sql, Object... values) {
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setValues(statement, values);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(connection, statement, null);
        }
    }

    private void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException {
        if (Objects.isNull(values) || Objects.isNull(preparedStatement)) {
            return;
        }
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
    }

    protected void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("CONNECTION_CLOSE_EXCEPTION" + e);
        }
    }

    protected abstract <T> T parseResultSet(ResultSet resultSet) throws SQLException;
}
