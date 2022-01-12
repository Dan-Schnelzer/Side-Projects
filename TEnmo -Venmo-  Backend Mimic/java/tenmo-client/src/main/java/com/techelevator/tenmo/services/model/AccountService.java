package com.techelevator.tenmo.services.model;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import io.cucumber.java.bs.A;
import org.apiguardian.api.API;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


public class AccountService {


    private String baseURL;
    private final RestTemplate restTemplate = new RestTemplate();
    private String authToken = null;

    public AccountService (String url){
        baseURL = url;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

    public BigDecimal getBalance(){
        BigDecimal balance = null;
        Account account = new Account();
        try{
            ResponseEntity<BigDecimal> response = restTemplate.exchange(baseURL + "/tenmo/accountbalance",
                    HttpMethod.GET, makeAuthEntity(), BigDecimal.class);
            balance = response.getBody();
        }catch (RestClientResponseException | ResourceAccessException e){
            System.out.println(e.getMessage());
        }
        return balance;

    }


    //HttpEntity Helper Methods
    private HttpEntity<Account> makeAccountEntity(Account account){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(account, headers);
    }

    private HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }



}
