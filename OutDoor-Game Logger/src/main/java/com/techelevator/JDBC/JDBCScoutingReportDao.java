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
    }   //1S this method creates a new Scout Report

    @Override
    public List<Long> getScoutReportIds(long userId) {
        return null;
    }   //2S. this method will bring a list of your scout report IDs for you to choose from

    @Override
    public ScoutingReport getScoutingReportById(long reportId) {
        return null;
    }   //3S. this method will bring up your report after you choose ID to view

    @Override
    public void deleteScoutReport(long logId) {
    }   //4S. this method will delete a scout report if you choose by report ID


    @Override
    public List<ScoutingReport> getScoutingReportByUser(long userId) {
        return null;
    }   //5S. this method lets you view all reports if you want by gathering all by userId
}
