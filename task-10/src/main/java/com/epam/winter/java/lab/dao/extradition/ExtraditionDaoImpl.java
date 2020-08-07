package com.epam.winter.java.lab.dao.extradition;

import com.epam.winter.java.lab.dao.AbstractJDBCDao;
import com.epam.winter.java.lab.model.Extradition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static com.epam.winter.java.lab.constants.Constants.*;

public class ExtraditionDaoImpl extends AbstractJDBCDao implements ExtraditionDao {

    @SuppressWarnings("unchecked")
    @Override
    protected Extradition parseResultSet(ResultSet resultSet) throws SQLException {
        final int id = resultSet.getInt(ID_EXTRADITION_COLUMN);
        final int cardId = resultSet.getInt(ID_CARD_EXTRADITION_COLUMN);
        final int bookId = resultSet.getInt(ID_BOOK_EXTRADITION_COLUMN);
        final LocalDate dateExtradition = resultSet.getDate(EXTRADITION_DATE_COLUMN).toLocalDate();
        final LocalDate dateDelivery = resultSet.getDate(DELIVERY_DATE_COLUMN).toLocalDate();

        return new Extradition(id, cardId, bookId, dateExtradition, dateDelivery);
    }

    @Override
    public Extradition getById(Integer id) {
        final String FIND_BY_ID = "select * from extradition where idExtradition = ?";
        return getById(FIND_BY_ID, id);
    }

    @Override
    public void save(Extradition extradition) {
        final int cardId = extradition.getCardId();
        final int bookId = extradition.getBookId();
        final LocalDate dateExtradition = extradition.getDateExtradition();
        final LocalDate dateDelivery = extradition.getDateDelivery();
        final String SQL_INSERT = "insert into extradition (dateDelivery, dateExtradition, idbook, idcard ) values (?, ?, ?, ?)";
        executeUpdate(SQL_INSERT, dateDelivery, dateExtradition, bookId, cardId);
    }

    @Override
    public void update(Extradition extradition) {
        final int id = extradition.getId();
        final int cardId = extradition.getCardId();
        final int bookId = extradition.getBookId();
        final LocalDate dateExtradition = extradition.getDateExtradition();
        final LocalDate dateDelivery = extradition.getDateDelivery();
        final String SQL_UPDATE = "update extradition set dateDelivery = ?, dateExtradition = ?, idbook = ?,idcard = ? where idExtradition = ?";
        executeUpdate(SQL_UPDATE, dateDelivery, dateExtradition, bookId, cardId, id);
    }

    @Override
    public void delete(Extradition extradition) {
        final String SQL_DELETE = "delete from extradition where idExtradition = ?";
        executeUpdate(SQL_DELETE, extradition.getId());
    }

    @Override
    public List<Extradition> getAll() {
        final String SQL_FIND_ALL = "select * from extradition";
        return getAll(SQL_FIND_ALL);
    }
}
