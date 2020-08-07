package com.epam.winter.java.lab.dao.card;

import com.epam.winter.java.lab.dao.AbstractDao;
import com.epam.winter.java.lab.model.SubscriptionCard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CardDaoImpl extends AbstractDao<SubscriptionCard> implements CardDao {
    private final String COLUMN_ID_CARD = "idCard";
    private final String COLUMN_ID_USER = "idUser";
    private final String COLUMN_CREATE_DATE = "createDate";

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Resource
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Resource
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public SubscriptionCard getById(Integer id) {
        final String SQL_CARD_BY_ID = "select * from abonementcards where idCard = ?";
        RowMapper<SubscriptionCard> rowMapper = getRowMapper();
        return this.jdbcTemplate.queryForObject(SQL_CARD_BY_ID, new Object[]{id}, rowMapper);
    }

    @Override
    public int save(SubscriptionCard card) {
        final String TABLE_NAME = "abonementcards";
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(COLUMN_ID_CARD);
        return simpleJdbcInsert.executeAndReturnKey(getParameters(card)).intValue();
    }

    @Override
    public void update(SubscriptionCard card) {
        final String SQL_UPDATE_CARD = "update abonementcards SET idUser = ?, createDate = ? where idCard = ?";
        this.jdbcTemplate.update(SQL_UPDATE_CARD, card.getUserId(), card.getCreateDate(), card.getId());
    }

    @Override
    public void delete(SubscriptionCard card) {
        final String SQL_DELETE_CARD = "delete from abonementcards where idCard = ?";
        this.jdbcTemplate.update(SQL_DELETE_CARD, card.getId());
    }

    @Override
    public List<SubscriptionCard> getAll() {
        final String SQL_CARDS_GET = "select * from abonementcards";
        return this.jdbcTemplate.query(SQL_CARDS_GET, getRowMapper());
    }

    @Override
    public SubscriptionCard initObject(ResultSet rs) {
        SubscriptionCard card;
        try {
            card = new SubscriptionCard();
            card.setId(rs.getInt(COLUMN_ID_CARD));
            card.setUserId(rs.getInt(COLUMN_ID_USER));
            card.setCreateDate(rs.getDate(COLUMN_CREATE_DATE).toLocalDate());
        } catch (SQLException e) {
            throw new CardDaoException(e);
        }
        return card;
    }

    @Override
    public Map<String, Object> getParameters(SubscriptionCard card) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(COLUMN_ID_USER, card.getUserId());
        parameters.put(COLUMN_CREATE_DATE, card.getCreateDate());
        return parameters;
    }
}
