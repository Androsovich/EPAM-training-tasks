package com.epam.winter.java.lab.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class AbstractDao<T> {

   public RowMapper<T> getRowMapper() {
       return  (rs, rowNum) -> initObject(rs);
   }

   public abstract T initObject(ResultSet rs) throws SQLException;

   public abstract Map<String, Object> getParameters(T t);
}
