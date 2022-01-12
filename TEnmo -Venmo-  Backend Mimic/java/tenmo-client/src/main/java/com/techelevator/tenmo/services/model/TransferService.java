package com.techelevator.tenmo.services.model;

import com.techelevator.tenmo.model.*;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransferService {

    private String baseURL;
    private final RestTemplate restTemplate = new RestTemplate();
    private String authToken = null;

    public TransferService (String url){
        baseURL = url;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

    public TransferDTO createTransfer(TransferDTO transfers){
        try{
            ResponseEntity<TransferDTO> response = restTemplate.exchange(baseURL + "/tenmo/balancetransfer",
                    HttpMethod.POST, makeTransferEntity(transfers), TransferDTO.class);
            transfers = response.getBody();
        }catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return transfers;
    }

    public List<ViewTransfersDTO> getMyTransfers(int userId){
        List<ViewTransfersDTO> myTransfers = null;
        try {
            ResponseEntity<ViewTransfersDTO[]> response = restTemplate.exchange(baseURL + "/tenmo/viewMyTransfers/" + userId, HttpMethod.GET,
           makeAuthEntity(),ViewTransfersDTO[].class );
            myTransfers = new ArrayList<>(Arrays.asList(response.getBody()));
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
    }
        return myTransfers;
    }

    public TransferDetailDTO getTransferDetails(int transferId){
        TransferDetailDTO transferDetailDTO = new TransferDetailDTO();
        try {
            ResponseEntity<TransferDetailDTO> respone = restTemplate.exchange(baseURL + "/tenmo/transferDetails/" + transferId,
                    HttpMethod.GET, makeAuthEntity(), TransferDetailDTO.class);
            transferDetailDTO = respone.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return transferDetailDTO;
    }

    //HttpEntity Helper Methods
    private HttpEntity<TransferDTO> makeTransferEntity(TransferDTO transfers){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(transfers, headers);
    }
    private HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }


}
