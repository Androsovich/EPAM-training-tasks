package com.epam.winter.java.lab.dao.user;

import com.epam.winter.java.lab.dao.AbstractDao;
import com.epam.winter.java.lab.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private final String COLUMN_ID_USER = "iduser";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_SURNAME = "surname";
    private final String COLUMN_ADDRESS = "adress";
    private final String COLUMN_PHONE = "phone";

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
    public User getById(Integer id) {
        final String SQL_USER_BY_ID = "select * from users where iduser = ?";
        return this.jdbcTemplate.queryForObject(SQL_USER_BY_ID, new Object[]{id}, getRowMapper());
    }

    @Override
    public int save(User user) {
        final String TABLE_NAME = "users";
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(this.dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(COLUMN_ID_USER);
        return simpleJdbcInsert.executeAndReturnKey(getParameters(user)).intValue();
    }

    @Override
    public void update(User user) {
        final String SQL_UPDATE_USER = "update users SET name = ?, surname = ?,adress = ?,phone = ? where idUser = ?";
        this.jdbcTemplate.update(SQL_UPDATE_USER, user.getName(), user.getSurName(),
                user.getAddress(), user.getPhone(), user.getId());
    }

    @Override
    public void delete(Integer userId) {
        final String SQL_DELETE_USER = "delete  from users where idUser = ?";
        this.jdbcTemplate.update(SQL_DELETE_USER, userId);
    }

    @Override
    public void delete(User user) {
        delete(user.getId());
    }

    @Override
    public List<User> getAll() {
        final String SQL_USERS_GET = "select * from users ";
        return this.jdbcTemplate.query(SQL_USERS_GET, getRowMapper());
    }

    @Override
    public User getUserByCardId(Integer cardId) {
        final String SQL_USER_BY_CARD = "select * from users where idUser = (select idUser " +
                "from abonementcards where idCard = ?)";
        return this.jdbcTemplate.queryForObject(SQL_USER_BY_CARD, new Object[]{cardId}, getRowMapper());
    }

    @Override
    public User initObject(ResultSet rs) {
        User user;
        try {
            user = new User();
            user.setId(rs.getInt(COLUMN_ID_USER));
            user.setName(rs.getString(COLUMN_NAME));
            user.setSurName(rs.getString(COLUMN_SURNAME));
            user.setAddress(rs.getString(COLUMN_ADDRESS));
            user.setPhone(rs.getString(COLUMN_PHONE));
        } catch (SQLException e) {
            throw new UserDaoException(e);
        }
        return user;
    }

    @Override
    public Map<String, Object> getParameters(User user) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(COLUMN_NAME, user.getName());
        parameters.put(COLUMN_SURNAME, user.getSurName());
        parameters.put(COLUMN_ADDRESS, user.getAddress());
        parameters.put(COLUMN_PHONE, user.getPhone());
        return parameters;
    }
}
