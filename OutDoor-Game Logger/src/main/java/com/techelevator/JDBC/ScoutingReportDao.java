package com.techelevator.JDBC;

import com.techelevator.model.ScoutingReport;

import java.util.List;

public interface ScoutingReportDao {

    ScoutingReport createScoutingReport();

    List<Integer> getScoutReportIds(int userId);

    ScoutingReport getScoutingReportById(int reportId);

    List<ScoutingReport> getScoutingReportByUser(int userId);
}
