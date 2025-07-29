package com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

public class OracleDao {

    private final NamedParameterJdbcTemplate template;

    public OracleDao(@Qualifier("h2JdbcTemplate") NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public int insertData(String id, String name) {
        String sql = "INSERT INTO van (van_id, van_name) VALUES (:id, :name)";
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        return template.update(sql, params);
    }

    public int updateData(String id, String name) {
        String sql = "UPDATE van SET van_name = :name WHERE van_id = :id";
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        return template.update(sql, params);
    }

    public int deleteData(String id) {
        String sql = "DELETE FROM van WHERE van_id = :id";
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        return template.update(sql, params);
    }

    public Map<String, Object> selectData(String id) {
        String sql = "SELECT * FROM van WHERE van_id = :id";
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        return template.queryForMap(sql, params);
    }
}
