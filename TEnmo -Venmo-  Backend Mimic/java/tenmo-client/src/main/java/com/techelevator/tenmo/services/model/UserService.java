package com.techelevator.tenmo.services.model;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService {

    private String baseURL;
    private final RestTemplate restTemplate = new RestTemplate();
    private String authToken = null;

    public UserService (String url){
        baseURL = url;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

    public List<User> getAllUsers() {
        List<User> userList = null;
        try {
            ResponseEntity<User[]> response = restTemplate.exchange(baseURL + "/tenmo/allusers", HttpMethod.GET,
                    makeAuthEntity(), User[].class);
            userList =new ArrayList<>(Arrays.asList(response.getBody()));
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }


    //HttpEntity Helper Methods
    private HttpEntity<User> makeUserEntity(User user){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(user, headers);
    }

    private HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }


}
