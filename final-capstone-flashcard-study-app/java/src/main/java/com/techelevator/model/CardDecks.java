package com.techelevator.model;

import org.springframework.data.relational.core.sql.In;

public class CardDecks {
    private int cardId;
    private int deckId;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }
}
