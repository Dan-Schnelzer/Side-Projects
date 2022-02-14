import axios from 'axios';

export default {

  getCardsByUser(userId) {
    return axios.get(`/users/${userId}/cards/`);
  },
  create(card) {
    return axios.post('/users/card/create/', card);
  },
  editCard(card) {
    return axios.put(`/cards/update/${card.cardId}/`, card);
  },
  getCardById(cardId) {
    return axios.get(`/cards/${cardId}/`);
  },
  deleteCard(cardId){
    return axios.delete(`/users/card/${cardId}/`);
  }

}
