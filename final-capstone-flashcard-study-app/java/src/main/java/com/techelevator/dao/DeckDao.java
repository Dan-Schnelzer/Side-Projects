package com.techelevator.dao;

import com.techelevator.model.Deck;

import java.util.List;

public interface DeckDao {
    Deck getDeckByDeckId(int deckId);        // 1D. this pulls deck and its cards by deckId
    List<Deck> getDecksByUserId(int userId);      // 2D. this pulls all the decks that the user has made
    Deck createNewDeck(Deck deck);           // 3D. this will create a new deck
    List<Deck> getPublicDecks();        // 4D. this will get public decks
    void deleteDeck(int deckId);         // 5D. this will delete a deck
    Deck updateDeck(Deck deck);      // 6D. this will update an existing deck
    Deck submitDeck(Deck deck);      // 7D. this will set a deck to be submitted
    List<Deck> getSubmittedDecks();      // 8D. this will return a list of submitted decks
    Deck makeDeckPublic(Deck deck);      // 9D. this will make Decks public
}
