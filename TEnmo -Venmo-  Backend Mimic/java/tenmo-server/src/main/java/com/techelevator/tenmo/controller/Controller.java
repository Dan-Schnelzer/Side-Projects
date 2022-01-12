package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.exception.InsufficientFundsException;
import com.techelevator.tenmo.model.*;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RequestMapping("/tenmo")
@RestController
@PreAuthorize("isAuthenticated()")
public class Controller {

    @Autowired
    AccountDao accountDao;
    @Autowired
    UserDao userDao;
    @Autowired
    TransferDao transferDao;

    @GetMapping("/accountbalance")
    public BigDecimal getAccountBalance(Principal principal) {
        return accountDao.getBalance(principal.getName());
    }

    @GetMapping("/allusers")
    public List<User> getUsers(Principal principal) {
        return userDao.findAll(principal.getName());
    }


    //@ResponseStatus(code= HttpStatus.BAD_REQUEST, message = "Insufficient Funds!!")
    @RequestMapping(path = "/balancetransfer", method = RequestMethod.POST)
    public TransferDTO createTransfer(@RequestBody TransferDTO transfers)  {
        try {
            return transferDao.createTransfer(transfers);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/viewMyTransfers/{id}" , method = RequestMethod.GET)
    public List<ViewTransfersDTO> viewMyTransfers (@PathVariable("id") int userId){
        return transferDao.getAllTransfersByID(userId);
    }

    @RequestMapping(path = "/transferDetails/{id}", method = RequestMethod.GET)
    public TransferDetailDTO getTranferDetails(@PathVariable("id") int transferId){
        return transferDao.findTransferByID(transferId);
    }

}
