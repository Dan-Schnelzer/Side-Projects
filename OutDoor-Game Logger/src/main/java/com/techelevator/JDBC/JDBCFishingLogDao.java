package com.techelevator.JDBC;

import com.techelevator.model.FishingLog;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JDBCFishingLogDao implements FishingLogDao {

    private JdbcTemplate jdbcTemplate;

    public JDBCFishingLogDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public FishingLog createFishingLog() {
        return null;
    }

    @Override
    public List<Integer> getLogIds(int userId) {
        return null;
    }

    @Override
    public FishingLog getFishingLogById(int logId) {
        return null;
    }

    @Override
    public List<FishingLog> getFishingLogsByUser(int userId) {
        List<FishingLog> fishingLogList = new ArrayList<>();
        String sql = "Select * from fishing_log WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId );
        while (results.next()){
            fishingLogList.add(mapResultsToFishingLog(results));
        }
        return fishingLogList;
    }


    private FishingLog mapResultsToFishingLog(SqlRowSet results){
        FishingLog fishingLog = new FishingLog();
        fishingLog.setUserId(results.getLong("user_id"));
        fishingLog.setLogDate(results.getString("log_date"));
        fishingLog.setLogLocation(results.getString("log_location"));
        fishingLog.setLogDescription(results.getString("log_description"));
        fishingLog.setImageURL(results.getString("images"));
        fishingLog.setBait(results.getString("bait"));
        fishingLog.setWeather(results.getString("weather"));
        fishingLog.setFishingTrip(results.getString("fishing_trip"));
        return fishingLog;
    }
}
