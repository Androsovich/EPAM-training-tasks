package com.epam.winter.java.lab.dao.card;

import com.epam.winter.java.lab.dao.AbstractJDBCDao;
import com.epam.winter.java.lab.model.SubscriptionCard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static com.epam.winter.java.lab.constants.Constants.*;

public class CardDaoImpl extends AbstractJDBCDao implements CardDao {

    @SuppressWarnings("unchecked")
    @Override
    protected SubscriptionCard parseResultSet(ResultSet resultSet) throws SQLException {
        final int id = resultSet.getInt(ID_CARD_COLUMN);
        final int userId = resultSet.getInt(ID_USER_CARD_COLUMN);
        final LocalDate createDate = resultSet.getDate(CREATE_DATE_COLUMN).toLocalDate();

        return new SubscriptionCard(id, userId, createDate);
    }

    @Override
    public SubscriptionCard getById(Integer id) {
        final String FIND_BY_ID = "select * from abonementcards where idCard = ?";
        return getById(FIND_BY_ID, id);
    }

    @Override
    public void save(SubscriptionCard card) {
        final int userId = card.getUserId();
        final LocalDate createDate = card.getCreateDate();
        final String SQL_INSERT = "insert into abonementcards (createDate, idUser ) values (?, ?)";
        executeUpdate(SQL_INSERT, createDate, userId);
    }

    @Override
    public void update(SubscriptionCard card) {
        final int id = card.getId();
        final int userId = card.getUserId();
        final LocalDate createDate = card.getCreateDate();
        final String SQL_UPDATE = "update abonementcards set createDate = ?, idUser = ? where idCard = ?";
        executeUpdate(SQL_UPDATE, createDate, userId, id);
    }

    @Override
    public void delete(SubscriptionCard card) {
        final String SQL_DELETE = "delete from abonementcards where idCard = ?";
        executeUpdate(SQL_DELETE, card.getId());
    }

    @Override
    public List<SubscriptionCard> getAll() {
        final String SQL_FIND_ALL = "select * from abonementcards";
        return getAll(SQL_FIND_ALL);
    }
}
