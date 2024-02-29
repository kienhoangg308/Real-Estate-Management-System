package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.mapper.ResultsetMapper;
import com.laptrinhjavaweb.repository.custom.JdbcRepository;
import com.laptrinhjavaweb.utils.ConnectionUtils;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SimpleJdbcRepository<T> implements JdbcRepository {

    private Class<T> tClass;

    public SimpleJdbcRepository() {
        tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    @Override
    public T findById(Long id) {
        List<T> results = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            conn = ConnectionUtils.getConnection();
            stmt = conn.createStatement();
            String tableName = null;
            if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
                Table table = tClass.getAnnotation(Table.class);
                tableName = table.name();
            }
            String sql = "select * from " + tableName + "";
            rs = stmt.executeQuery(sql);

//			rs = stmt.executeQuery(sql.toString());
            ResultsetMapper<T> resultsetMapper = new ResultsetMapper<>();
            return resultsetMapper.mapRow(rs, tClass).size() > 0 ? results.get(0) : null;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                conn.close();
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
