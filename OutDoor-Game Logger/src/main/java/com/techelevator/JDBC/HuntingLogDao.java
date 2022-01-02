package com.techelevator.JDBC;

import com.techelevator.model.HuntingLog;

import java.util.List;

public interface HuntingLogDao {

    HuntingLog createHuntingLog();

    List<Integer> getLogIds(int userId);

    HuntingLog getHuntingLogById(int logId);

    List<HuntingLog> getHuntingLogsByUser(int userId);
}
