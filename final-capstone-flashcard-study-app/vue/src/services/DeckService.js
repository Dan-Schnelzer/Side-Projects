import axios from 'axios';

export default {

  getDecksByUser(userId) {
    return axios.get(`/users/deck/${userId}`);
  },
  getDeckById(deckId) {
    return axios.get(`/deck/${deckId}/`);
  },
  create(deck) {
    return axios.post("/users/decks/create/", deck);
  },
  deleteDeck(deckId){
    return axios.delete(`/users/decks/${deckId}/`);
  },
  edit(deck) {
    return axios.put(`/decks/update/${deck.deckId}/`, deck);
  },
  getPublicDecks() {
    return axios.get('/users/get/public/');
  },
  submitDeckToPublish(deck) {
    return axios.put(`/decks/submit/${deck.deckId}/`, deck);
  },
  getSubmittedDecks() {
    return axios.get('/users/get/submit/');
  }, 

}