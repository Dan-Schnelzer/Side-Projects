package com.techelevator.JDBC;

import com.techelevator.model.FishingLog;

import java.util.List;

public interface FishingLogDao {

    FishingLog createFishingLog();

    List<Integer> getLogIds(int userId);

    FishingLog getFishingLogById(int logId);

    List<FishingLog> getFishingLogsByUser(int userId);



}
