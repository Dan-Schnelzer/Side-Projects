package com.techelevator.JDBC;

import com.techelevator.model.ScoutingReport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class JDBCScoutingReportDao implements ScoutingReportDao{

    private JdbcTemplate jdbcTemplate;

    public JDBCScoutingReportDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public ScoutingReport createScoutingReport() {
        return null;
    }

    @Override
    public List<Integer> getScoutReportIds(int userId) {
        return null;
    }

    @Override
    public ScoutingReport getScoutingReportById(int reportId) {
        return null;
    }

    @Override
    public List<ScoutingReport> getScoutingReportByUser(int userId) {
        return null;
    }
}
