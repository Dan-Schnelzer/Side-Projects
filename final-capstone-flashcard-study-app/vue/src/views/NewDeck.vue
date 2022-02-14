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
    <article>
      <div id="form-background">
        <div id="form-container">
          <div class="form">
            <h2>Create a new Deck</h2>
            <label for="frontOfCard">Deck Name</label>
            <input
              type="text"
              id="deckName"
              name="deckName"
              placeholder="Deck Name"
              class="input"
              v-model="newDeck.deckName"
              required
            />
            <div id="save-buttons">
              <button id="save" v-on:click.prevent="createDeck">Save</button>
              <button id="save-and-new" v-on:click.prevent="createDeckAndReset">
                Save and New
              </button>
            </div>
          </div>
        </div>
      </div>
    </article>
    <div class="footer">
      <footer-component />
    </div>
  </div>
</template>

<script>
import FooterComponent from "../components/FooterComponent.vue";

export default {
  components: {
    FooterComponent,
  },
  name: "new-deck",
  data() {
    return {
      newDeck: {
        deckName: "",
        userId: this.$store.state.user.id,
      },
    };
  },
  methods: {
    createDeck() {
      const newDeck = {
        ...this.newDeck,
      };

      this.$store.dispatch("CREATE_NEW_DECK", newDeck);
    },
    createDeckAndReset() {
      const newDeck = {
        ...this.newDeck,
      };

      this.$store.dispatch("CREATE_NEW_DECK_SAVE_AND_NEW", newDeck);
      this.newDeck = {
        deckName: "",
        userId: this.$store.state.user.id,
      };
    },
  },
  computed: {},
  created() {},
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
  grid-area: body;
  min-height: 77vh;
  background-image: url("../assets/lighter-blue-green-background.png");
}
#form-background {
  min-height: 70vh;
  margin-top: 11vh;
}
.form {
  display: flex;
  flex-direction: column;
  padding: 10vh;
}
button {
  border: none;
  color: white;
  background-color: rgba(0, 167, 88, 255);
  padding: 1.5vh 5vh;
  border-radius: 10px;
}
button:hover {
  background-color: rgb(2, 131, 70);
}
#save-buttons {
  display: flex;
  justify-content: flex-end;
  gap: 4vh;
  margin-top: 4vh;
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
