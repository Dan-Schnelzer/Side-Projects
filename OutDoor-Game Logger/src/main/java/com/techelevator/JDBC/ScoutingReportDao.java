package com.techelevator.JDBC;

import com.techelevator.model.ScoutingReport;

import java.util.List;

public interface ScoutingReportDao {

    ScoutingReport createScoutingReport();           //1S

    List<Long> getScoutReportIds(long userId);            //2S

    ScoutingReport getScoutingReportById(long reportId);       //3S

    void deleteScoutReport(long logId);                //4S

    List<ScoutingReport> getScoutingReportByUser(long userId);         //5S
}
