package com.techelevator.tenmo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (code = HttpStatus.BAD_REQUEST, reason = "Insufficient Funds!")
public class InsufficientFundsException extends Exception{
 //   private static long serialVersionUID = 1L;

    public InsufficientFundsException(){
        super("Insufficient Funds!");
    }
}
