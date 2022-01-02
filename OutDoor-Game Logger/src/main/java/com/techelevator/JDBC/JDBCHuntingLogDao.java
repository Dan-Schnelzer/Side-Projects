package com.techelevator.JDBC;

import com.techelevator.model.HuntingLog;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class JDBCHuntingLogDao implements HuntingLogDao{

    private JdbcTemplate jdbcTemplate;

    public JDBCHuntingLogDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public HuntingLog createHuntingLog() {
        return null;
    }

    @Override
    public List<Integer> getLogIds(int userId) {
        return null;
    }

    @Override
    public HuntingLog getHuntingLogById(int logId) {
        return null;
    }

    @Override
    public List<HuntingLog> getHuntingLogsByUser(int userId) {
        return null;
    }
}
