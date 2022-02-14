<template>
  <div class="view">
    <nav>
      <router-link :to="{ name: 'home' }" class="nav-button"
        >View Your Cards</router-link
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
        <div id="form-background">
          <div id="form-container">
            <form>
              <h2>Edit Deck</h2>
              <label for="deck-name">Deck Name</label>
              <input
                type="text"
                id="deck-name"
                name="deck-name"
                placeholder="Deck Name"
                class="input"
                v-model="deck.deckName"
                required
              />
              <div id="buttons">
                <button
                  class="delete-button"
                  v-on:click.prevent="deleteDeck(deck.deckId)"
                >
                  Delete
                </button>
                <button class="save" v-on:click.prevent="editDeck">Save</button>
              </div>
            </form>
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
import FooterComponent from "../components/FooterComponent.vue";
import deckService from "../services/DeckService.js";

export default {
  components: {
    FooterComponent,
  },
  name: "edit-deck",
  data() {
    return {
      deck: {},
    };
  },
  methods: {
    editDeck() {
      const editedDeck = this.deck;
      this.$store.dispatch("EDIT_DECK", editedDeck);
    },
    deleteDeck(deckId) {
      if (window.confirm("Are you sure you want to delete?")) {
        this.$store.dispatch("DELETE_DECK", deckId);
        this.$router.push({ name: "my-decks" });
      }
    },
    retrieveDeck() {
      deckService
        .getDeckById(this.$route.params.id)
        .then((response) => {
          this.$store.commit("SET_DECK", response.data);
          this.deck = response.data;
          this.isLoading = false;
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert(
              "Deck not available. This deck may have been deleted or you have entered an invalid deck ID."
            );
            this.$router.push("/");
          }
        });
    },
  },
  computed: {},
  created() {
    this.retrieveDeck();
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
h2 {
  margin: 0vh 0vh 4vh 0vh;
  text-align: center;
  font-size: 4vh;
  color: #464443;
}
label {
  padding-left: 1.5vh;
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
#form-container {
  grid-area: card-container;
  min-height: 69vh;
  background-image: url("../assets/lighter-blue-green-background.png");
  padding: 4vh 4vh 4vh 4vh;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  align-content: space-between;
  gap: 7vh 3vh;
  overflow: auto;
}
#form-background {
  min-height: 70vh;
}
form {
  display: flex;
  flex-direction: column;
  padding: 10vh;
  width: 100%;
}
#main {
  grid-area: body;
  margin-top: 11vh;
  min-height: 69vh;
  align-items: flex-end;
}
button {
  border: none;
  color: white;
  background-color: rgba(0, 167, 88, 255);
  padding: 1.5vh 5vh;
  border-radius: 10px;
  text-decoration: none;
  cursor: pointer;
}
.save-button:hover {
  background-color: rgb(2, 131, 70);
}
#buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 4vh;
}
.delete-button {
  display: flex;
  justify-content: flex-start;
  gap: 4vh;
  background-color: red;
  cursor: pointer;
}
.delete-button:hover {
  background-color: rgb(218, 4, 4);
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
.input {
  width: 100%;
  right: 0;
  box-sizing: border-box;
  font-family: "Roboto", sans-serif;
  border: 2px solid #ccc;
  border-radius: 10px;
  font-size: 16px;
  background-color: white;
  padding: 12px 20px 12px 12px;
  margin: 1vh 3vh 2vh 0vh;
}
.input:focus {
  outline: none;
  border-color: rgba(0, 148, 255, 255);
}
::placeholder {
  color: grey;
}
.footer {
  grid-area: footer;
  z-index: 3;
}
</style>
