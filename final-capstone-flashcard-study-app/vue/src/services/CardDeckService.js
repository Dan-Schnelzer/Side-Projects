import axios from 'axios';

export default {

    addCards(cardDecks) {
        return axios.post("/decks/add/", cardDecks);
    },
    getCardDecksByCardId(cardId) {
        return axios.get(`/cards/${cardId}/card-decks`);
    },
    removeCard(deckId, cardId) {
        return axios.delete(`/decks/${deckId}/cards/${cardId}`);
    },

}
