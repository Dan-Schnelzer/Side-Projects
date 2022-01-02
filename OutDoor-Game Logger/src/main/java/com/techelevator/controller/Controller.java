package com.techelevator.controller;


import com.techelevator.JDBC.FishingLogDao;
import com.techelevator.JDBC.HuntingLogDao;
import com.techelevator.JDBC.ScoutingReportDao;
import com.techelevator.model.FishingLog;
import com.techelevator.model.ScoutingReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/outdoorlogger")
@RestController
//@PreAuthorize("isAuthenticated()")     //this need activated once I figure how to get users and passwords working
public class Controller {
    @Autowired
    FishingLogDao fishingLogDao;

    @Autowired
    HuntingLogDao huntingLogDao;

    @Autowired
    ScoutingReportDao scoutingReportDao;


    @RequestMapping(path = "/fishinglog/{id}", method = RequestMethod.GET)
    public List<FishingLog> viewMyFishingLogs(@PathVariable("id") int userId){
        return fishingLogDao.getFishingLogsByUser(userId);
    }

}
