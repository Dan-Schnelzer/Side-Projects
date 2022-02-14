package com.techelevator.dao;

import com.techelevator.model.CardDecks;
import com.techelevator.model.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.RowSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCardDeckDao implements CardDeckDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcCardDeckDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override         // 1CD. this will add card to deck
    public void addCardsToDeck(List<CardDecks> cardDecks) {
        String sql = "INSERT INTO card_deck (deck_id, card_id) VALUES (?,?)";

        for (CardDecks cardDeck : cardDecks) {
            jdbcTemplate.update(sql, cardDeck.getDeckId(), cardDeck.getCardId());
        }
    }

    @Override       // 2CD. this will remove a card from deck
    public void removeCardFromDeck(int deckId, int cardId) {
        String sql = "DELETE FROM card_deck WHERE deck_id = ? AND card_id = ?";
        jdbcTemplate.update(sql, deckId, cardId);
    }

    @Override      // 3CD. this will return CardDecks where cardId is cardId
    public List<CardDecks> getCardDecksByCardId(int cardId) {
        String sql = "SELECT * " +
                "FROM card_deck " +
                "WHERE card_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cardId);

        List<CardDecks> cardDecks = new ArrayList<>();
        while (results.next()) {
            cardDecks.add(mapCardDecksToRowSet(results));
        }
        return cardDecks;
    }

    private CardDecks mapCardDecksToRowSet(SqlRowSet rowSet) {
        CardDecks cardDecks = new CardDecks();
        cardDecks.setCardId(rowSet.getInt("card_id"));
        cardDecks.setDeckId(rowSet.getInt("deck_id"));
        return cardDecks;
    }
}
