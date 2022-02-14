package com.techelevator.dao;

import com.techelevator.model.CardDecks;
import com.techelevator.model.Deck;

import java.util.List;

public interface CardDeckDao {
    void addCardsToDeck(List<CardDecks> cardDecks);           // 1CD. this will add card to deck
    void removeCardFromDeck(int deckId, int cardId);      // 2CD. this will remove a card from deck
    List<CardDecks> getCardDecksByCardId(int cardId); // 3CD. this will return CardDecks where cardId is cardId
}
