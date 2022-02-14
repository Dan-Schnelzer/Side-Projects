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
        <div id="form-container">
          <form>
            <h2>Edit Card</h2>
            <label for="frontOfCard">Question</label>
            <input
              type="text"
              id="frontOfCard"
              name="frontOfCard"
              placeholder="Question"
              class="input"
              v-model="card.frontOfCard"
              required
            />
            <label for="backOfCard">Answer</label>
            <textarea
              id="backOfCard"
              name="backOfCard"
              rows="5"
              cols="50"
              class="input"
              v-model="card.backOfCard"
              placeholder="Answer"
              required
            ></textarea>
            <label for="tags">Tags</label>
            <input
              type="text"
              id="tags"
              name="tags"
              placeholder="Tags"
              class="input"
              v-model="card.tags"
              required
            />
            <div id="buttons">
              <button
                class="delete-button"
                v-on:click.prevent="deleteCard(card.cardId)"
              >
                Delete
              </button>
              <button class="save-button" v-on:click.prevent="editCard">
                Save
              </button>
            </div>
          </form>
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
import cardService from "../services/CardService.js";

export default {
  components: {
    FooterComponent,
  },
  name: "edit-card",
  data() {
    return {
      card: {},
    };
  },
  methods: {
    editCard() {
      const editedCard = this.card;
      this.$store.dispatch("EDIT_CARD", editedCard);
    },
    deleteCard(cardId) {
      if (window.confirm("Are you sure you want to delete?")) {
        this.$store.dispatch("DELETE_CARD", cardId);
        this.$router.push({ name: "home" });
      }
    },
  },
  computed: {},
  created() {
    cardService.getCardById(this.$route.params.id).then((response) => {
      this.card = response.data;
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
  min-height: 69vh;
  background-image: url("../assets/lighter-blue-green-background.png");
  padding: 4vh 4vh 4vh 4vh;
}
form {
  display: flex;
  flex-direction: column;
  padding: 5vh 10vh;
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
#main {
  grid-area: body;
  margin-top: 11vh;
  min-height: 75vh;
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
