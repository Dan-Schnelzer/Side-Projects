package com.techelevator.controller;

import com.techelevator.dao.CardDao;
import com.techelevator.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class CardController {

    @Autowired
    CardDao cardDao;

    // 1C. this gets card by Id
    @RequestMapping(path = "/cards/{cardId}/", method = RequestMethod.GET)
    public Card getCard(@PathVariable int cardId) {
        return cardDao.getCardByCardId(cardId);
    }

    // 2C. this gets list of cards by userId
    @RequestMapping(path = "/users/{userId}/cards/", method = RequestMethod.GET)
    public List<Card> getCardsList (@PathVariable int userId) {
        return cardDao.getListOfCardsByUserId(userId);
    }

    // 3C. this creates a card
    @RequestMapping(path = "/users/card/create/" , method = RequestMethod.POST)
    public Card createNewCard(@RequestBody Card card){
        return cardDao.createCard(card);
    }

    // 4C. this will update an existing card
    @RequestMapping(path = "/cards/update/{cardId}/", method = RequestMethod.PUT)
    public Card updateCard(@PathVariable int cardId , @RequestBody Card card){
        return cardDao.updateCard(card);
    }

    // 5C. this will delete a card
    @RequestMapping(path = "/users/card/{cardId}/", method = RequestMethod.DELETE)
    public void  deleteCard(@PathVariable int cardId){
       cardDao.deleteCard(cardId);
    }


}
