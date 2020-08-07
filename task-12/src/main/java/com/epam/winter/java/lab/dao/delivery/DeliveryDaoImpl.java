package com.epam.winter.java.lab.dao.delivery;

import com.epam.winter.java.lab.dao.AbstractDao;
import com.epam.winter.java.lab.model.Delivery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.epam.winter.java.lab.utills.DaoHelper.convertDate;

@Repository
public class DeliveryDaoImpl extends AbstractDao<Delivery> implements DeliveryDao {
    private final String COLUMN_ID_DELIVERY = "idextradition";
    private final String COLUMN_ID_BOOK = "idbook";
    private final String COLUMN_ID_CARD = "idcard";
    private final String COLUMN_EXTRADITION_DATE = "dateExtradition";
    private final String COLUMN_DELIVERY_DATE = "dateDelivery";

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
    public List<Delivery> getDeliveryByCard(Integer cardId) {
        final String SQL_DELIVERY_BOOK_BY_CARD_ID = "select * from extradition where idcard = ?";
        return jdbcTemplate.query(SQL_DELIVERY_BOOK_BY_CARD_ID, new Object[]{cardId}, getRowMapper());

    }

    @Override
    public Delivery getById(Integer id) {
        final String SQL_DELIVERY_BOOK_BY_ID = "select * from extradition where idextradition = ?";
        return this.jdbcTemplate.queryForObject(SQL_DELIVERY_BOOK_BY_ID, new Object[]{id}, getRowMapper());
    }

    @Override
    public int save(Delivery delivery) {
        final String TABLE_NAME = "extradition";
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(COLUMN_ID_DELIVERY);
        return simpleJdbcInsert.executeAndReturnKey(getParameters(delivery)).intValue();
    }

    @Override
    public void update(Delivery delivery) {
        final String SQL_UPDATE_DELIVERY_BOOK = "update extradition SET idbook = ?, idcard = ?," +
                " dateExtradition = ?, dateDelivery = ? where idextradition = ?";
        this.jdbcTemplate.update(SQL_UPDATE_DELIVERY_BOOK, delivery.getBookId(), delivery.getCardId(),
                delivery.getDateExtradition(), delivery.getDateDelivery(), delivery.getId());
    }

    @Override
    public void delete(Delivery delivery) {
        final String SQL_DELETE_DELIVERY_BOOK = "delete from extradition where idextradition = ?";
        this.jdbcTemplate.update(SQL_DELETE_DELIVERY_BOOK, delivery.getId());
    }

    @Override
    public List<Delivery> getAll() {
        final String SQL_DELIVERY_BOOKS_GET = "select * from extradition ";
        return this.jdbcTemplate.query(SQL_DELIVERY_BOOKS_GET, getRowMapper());
    }

    @Override
    public Delivery initObject(ResultSet rs) {
        Delivery delivery;
        try {
            delivery = new Delivery();
            delivery.setId(rs.getInt(COLUMN_ID_DELIVERY));
            delivery.setBookId(rs.getInt(COLUMN_ID_BOOK));
            delivery.setCardId(rs.getInt(COLUMN_ID_CARD));

            delivery.setDateExtradition(convertDate(rs.getDate(COLUMN_EXTRADITION_DATE)));
            delivery.setDateDelivery(convertDate(rs.getDate(COLUMN_DELIVERY_DATE)));
        } catch (SQLException e) {
            throw new DeliveryDaoException(e);
        }
        return delivery;
    }

    @Override
    public Map<String, Object> getParameters(Delivery delivery) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(COLUMN_ID_BOOK, delivery.getBookId());
        parameters.put(COLUMN_ID_CARD, delivery.getCardId());
        parameters.put(COLUMN_EXTRADITION_DATE, delivery.getDateExtradition());
        parameters.put(COLUMN_DELIVERY_DATE, delivery.getDateDelivery());
        return parameters;
    }
}
