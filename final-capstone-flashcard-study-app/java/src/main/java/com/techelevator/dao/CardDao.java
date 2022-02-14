package com.techelevator.dao;

import com.techelevator.model.Card;

import java.util.List;

public interface CardDao {

    public Card getCardByCardId(int cardId);                 // 1C. this gets card by Id
    public List<Card> getListOfCardsByUserId(int userId);           // 2C. this gets list of cards by userId
    public Card createCard(Card card);                    // 3C. this creates a card
    public Card updateCard(Card card);                    // 4C. this will update an existing card
    void deleteCard(int cardId);                        // 5C. this will delete a card
}
