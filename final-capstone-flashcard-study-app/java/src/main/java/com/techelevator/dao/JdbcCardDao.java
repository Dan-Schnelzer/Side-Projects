package com.techelevator.dao;

import com.techelevator.model.Card;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class    JdbcCardDao implements CardDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcCardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override               // 1C. this gets card by Id
    public Card getCardByCardId(int cardId) {
        Card card = new Card();
        String sql = " SELECT cards.card_id, cards.user_id, cards.front, cards.back, cards.card_tags, users.username\n" +
                " FROM cards\n" +
                "  JOIN users ON  cards.user_id = users.user_id\n" +
                "  WHERE cards.card_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cardId);
        if (results.next()) {
            card = mapRowToCard(results);
        }
            return card;
    }


    @Override         // 2C. this gets list of cards by userId
    public List<Card> getListOfCardsByUserId(int userId) {
        String sql = " SELECT cards.card_id,cards.user_id, cards.front, cards.back, cards.card_tags, users.username\n" +
                " FROM cards\n" +
                "  JOIN users ON  cards.user_id = users.user_id\n" +
                "  WHERE cards.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        List<Card> cardList = new ArrayList<>();
        while (results.next()) {
            cardList.add(mapRowToCard(results));
        }
        return cardList;
    }

    @Override         // 3C. this creates a card
    public Card createCard(Card card) {
        String sql = "INSERT INTO cards (front, back, user_id, card_tags) VALUES (?, ?, ?, ?) RETURNING card_id  ";
        int id = jdbcTemplate.queryForObject(sql,Integer.class, card.getFrontOfCard(), card.getBackOfCard(), card.getUserId(), card.getTags());
        Card retrievedCard = getCardByCardId(id);
        return retrievedCard;
    }


    @Override         // 4C. this will update an existing card
    public Card updateCard(Card card) {
        String sql = "UPDATE cards SET front = ?, back = ? , user_id = ?, card_tags = ? WHERE card_id = ?";
        jdbcTemplate.update(sql, card.getFrontOfCard(), card.getBackOfCard(), card.getUserId(), card.getTags(), card.getCardId());
        return card;
    }

    @Override        // 5C. this will delete a card
    public void deleteCard(int cardId) {
        String sql = "DELETE FROM card_deck WHERE card_id = ?";
        jdbcTemplate.update(sql, cardId);
        String sql2 = "DELETE FROM cards WHERE card_id = ?";
        jdbcTemplate.update(sql2, cardId);
    }


    // Helper Method for mapping/building card objects
    private Card mapRowToCard(SqlRowSet rowSet) {
        Card card = new Card();
        card.setCardId(rowSet.getInt("card_id"));
        card.setFrontOfCard(rowSet.getString("front"));
        card.setBackOfCard(rowSet.getString("back"));
        card.setUsername(rowSet.getString("username"));
        card.setTags(rowSet.getString("card_tags"));
        card.setUserId(rowSet.getInt("user_id"));
        return card;
    }
}
