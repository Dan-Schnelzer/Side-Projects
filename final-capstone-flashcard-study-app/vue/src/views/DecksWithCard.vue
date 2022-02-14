<template>
  <div class="view">
    <nav>
      <router-link :to="{ name: 'home' }" class="nav-button">
        View Your Cards</router-link
      >
      <router-link :to="{ name: 'my-decks' }" class="nav-button">
        View Your Decks</router-link
      >
      <router-link :to="{ name: 'public-decks' }" class="nav-button">
        View Public Decks</router-link
      >
    </nav>
    <div id="main">
      <article>
        <div id="deck-container">
          <div class="deck" v-for="deck in filteredDecks" v-bind:key="deck.id">
            <deck-component
              class="deck-card"
              :name="deck.deckName"
              :size="deck.cards.length"
              :createdBy="deck.userName"
              :id="deck.deckId"
            />
            <div class="deck-buttons">
              <router-link
                class="deck-button"
                :to="{ name: 'edit-deck', params: { id: deck.deckId } }"
                >Edit</router-link
              >
              <router-link
                class="deck-button"
                :to="{
                  name: 'study-session',
                  params: { deckId: deck.deckId },
                }"
                >Study</router-link
              >
            </div>
          </div>
        </div>
      </article>
    </div>
    <div class="footer">
      <footer-component />
    </div>
  </div>
</template>

<script>
import DeckComponent from "../components/DeckComponent.vue";
import FooterComponent from "../components/FooterComponent.vue";
import cardDeckService from "../services/CardDeckService.js";

export default {
  components: {
    FooterComponent,
    DeckComponent,
  },
  name: "decks-with-card",
  data() {
    return {
      cardDecks: [],
    };
  },
  computed: {
    filteredDecks() {
      let deckIds = this.cardDecks.map((cardDeck) => cardDeck.deckId);

      return this.$store.state.decks.filter((deck) => {
        return deckIds.includes(deck.deckId);
      });
    },
  },
  created() {
    this.$store.dispatch("LOAD_USERS_DECKS", this.$store.state.user.id);
    cardDeckService
      .getCardDecksByCardId(this.$route.params.id)
      .then((response) => {
        this.cardDecks = response.data;
      });
  },
};
</script>

<style scoped>
.view {
  min-height: 100vh;
  display: grid;
  grid-template-columns: 1fr 5fr;
  grid-template-areas:
    "nav body"
    "footer footer";
  column-gap: 10px;
}
nav {
  grid-area: nav;
  padding-top: 20vh;
  padding-bottom: 20px;
  overflow-x: hidden;
  background-image: url("../assets/lighter-blue-green-background.png");
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
}
#main {
  margin-top: 11vh;
  min-height: 69vh;
  align-items: flex-end;
}
#deck-container {
  min-height: 63vh;
  background-image: url("../assets/lighter-blue-green-background.png");
  padding: 10vh 4vh 4vh 4vh;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  align-content: space-between;
  gap: 7vh 0vh;
  overflow: auto;
}
.deck {
  width: 60vh;
}
.deck-card {
  width: 100%;
}
.deck-button {
  border: none;
  color: white;
  background-color: rgba(0, 167, 88, 255);
  padding: 1.5vh 5vh;
  border-radius: 10px;
  text-decoration: none;
}
.deck-button:hover {
  background-color: rgb(2, 131, 70);
}
.deck-buttons {
  display: flex;
  justify-content: space-between;
}
.nav-button {
  background-color: rgba(0, 148, 255, 255);
  color: #f7fafc;
  text-decoration: none;
  border-radius: 10px;
  padding: 30px;
  font-size: 18px;
  font-weight: bold;
  margin: 10px;
  text-align: center;
  cursor: pointer;
  width: 60%;
  justify-self: flex-end;
}
.nav-button:hover {
  background-color: rgb(6, 102, 171);
}
.footer {
  grid-area: footer;
  z-index: 3;
}
</style>
