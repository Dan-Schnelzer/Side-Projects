<template>
  <div class="view">
    <nav>
      <router-link :to="{ name: 'new-deck' }" class="nav-button"
        >Create New Deck</router-link
      >
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
          <div id="card-list-empty" v-if="decks.length == 0">
            <p class="no-decks">There are no decks to review</p>
          </div>
          <div class="deck" v-for="deck in decks" v-bind:key="deck.id">
            <deck-component
              class="deck-card"
              :name="deck.deckName"
              :size="deck.cards.length"
              :createdBy="deck.userName"
              :id="deck.deckId"
            />
            <div class="deck-buttons">
              <button
                class="deck-button"
                id="reject"
                v-on:click.stop="review($event, deck.deckId)"
              >
                Reject
              </button>
              <button
                class="deck-button"
                v-on:click.stop="review($event, deck.deckId)"
              >
                Approve
              </button>
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
import DeckService from "../services/DeckService";

export default {
  components: {
    FooterComponent,
    DeckComponent,
  },
  name: "review-decks",
  data() {
    return {};
  },
  computed: {
    decks() {
      return this.$store.state.decks.filter((deck) => deck.submitted);
    },
  },
  created() {
    this.$store.dispatch("LOAD_SUBMITTED_DECKS");
  },
  methods: {
    review(event, deckId) {
      let deck = this.decks.find((deck) => deck.deckId === deckId);
      deck.submitted = false;

      if (event.target.id !== "reject") {
        deck.public = true;
      }

      DeckService.submitDeckToPublish(deck);
    },
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
p {
  color: #464443;
}
nav {
  grid-area: nav;
  padding-top: 184.5px;
  padding-bottom: 20px;
  overflow-x: hidden;
  background-image: url("../assets/lighter-blue-green-background.png");
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
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
  box-sizing: border-box;
  border: none;
  width: 78%;
}
.nav-button:hover {
  background-color: rgb(6, 102, 171);
}
#deck-container {
  min-height: 57vh;
  background-image: url("../assets/lighter-blue-green-background.png");
  padding: 10vh 0vh;
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
#reject {
  background-color: red;
}
#reject:hover {
  background-color: rgb(153, 3, 3);
}
.deck-button:hover {
  background-color: rgb(2, 131, 70);
}
.disabled {
  cursor: not-allowed;
  background-color: rgb(49, 92, 49);
}
.disabled:hover {
  background-color: rgb(49, 92, 49);
}
.deck-buttons {
  display: flex;
  justify-content: space-between;
}
#main {
  grid-area: body;
  margin-top: 11vh;
  min-height: 75vh;
  display: flex;
  flex-direction: column;
  align-items: stretch;
}
.no-decks {
  font-size: 7vh;
  text-align: center;
}
.footer {
  z-index: 3;
  grid-area: footer;
}
</style>
