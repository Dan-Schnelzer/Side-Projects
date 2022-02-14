<template>
  <div class="view">
    <nav>
      <router-link :to="{ name: 'new-card' }" class="nav-button"
        >Create New Card</router-link
      >
      <router-link :to="{ name: 'my-decks' }" class="nav-button">
        View Your Decks</router-link
      >
      <router-link :to="{ name: 'public-decks' }" class="nav-button">
        View Public Decks</router-link
      >
      <router-link
        :to="{ name: 'review-decks' }"
        class="nav-button"
        v-if="this.$store.state.user.authorities[0].name === 'ROLE_ADMIN'"
      >
        Review Submitted Decks</router-link
      >
    </nav>
    <div id="main">
      <article>
        <div id="card-container">
          <div id="search">
            <select v-model="searchBy">
              <option value="tags">Search Tags</option>
              <option value="content">Search Card Content</option>
            </select>
            <input
              type="text"
              name="search"
              id="search-bar"
              v-model="filter"
              placeholder="Search"
            />
          </div>
          <div id="cards">
            <div id="card-list-empty" v-if="filteredCards.length == 0">
              <p class="no-cards">There are no cards to show</p>
            </div>
            <div
              class="card"
              v-for="(card, index) in filteredCards"
              v-bind:key="index"
            >
              <flashcard-component
                :front="card.frontOfCard"
                :back="card.backOfCard"
                class="flashcard-component"
              />
              <span id="tags">Tags: {{ card.tags }}</span>
              <div id="card-buttons">
                <router-link
                  class="card-button"
                  :to="{ name: 'edit-card', params: { id: card.cardId } }"
                  >Edit</router-link
                >
                <router-link
                  class="card-button"
                  :to="{ name: 'decks-with-card', params: { id: card.cardId } }"
                  >Decks with Card</router-link
                >
              </div>
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
import FlashcardComponent from "../components/FlashcardComponent.vue";
import FooterComponent from "../components/FooterComponent.vue";

export default {
  components: {
    FlashcardComponent,
    FooterComponent,
  },
  name: "home",
  data() {
    return {
      filter: "",
      searchBy: "tags",
      cards: [],
    };
  },
  computed: {
    filteredCards() {
      if (this.filter !== "") {
        if (this.searchBy === "tags") {
          return this.filterByTags();
        } else {
          return this.filterByContent();
        }
      } else {
        return this.$store.state.cards;
      }
    },
  },
  created() {
    this.$store.dispatch("LOAD_USERS_CARDS", this.$store.state.user.id);
  },
  methods: {
    filterByTags() {
      return this.$store.state.cards.filter((card) => {
        if (card.tags !== null) {
          return card.tags.toLowerCase().includes(this.filter.toLowerCase());
        }
      });
    },
    filterByContent() {
      return this.$store.state.cards.filter((card) => {
        if (card.tags !== null) {
          let isFrontMatch = card.frontOfCard
            .toLowerCase()
            .includes(this.filter.toLowerCase());
          let isBackMatch = card.backOfCard
            .toLowerCase()
            .includes(this.filter.toLowerCase());
          return isFrontMatch || isBackMatch;
        }
      });
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
    "nav body"
    "footer footer";
  column-gap: 10px;
}
p {
  color: #464443;
}
span {
  font-weight: bold;
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
#card-container {
  grid-area: card-container;
  min-height: 73vh;
  background-image: url("../assets/lighter-blue-green-background.png");
  display: flex;
  flex-direction: column;
  padding-top: 4vh;
}
#cards {
  padding: 4vh 4vh 4vh 4vh;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  align-content: space-between;
  gap: 7vh 3vh;
  overflow: auto;
}
#tags {
  padding: 2vh 0 2vh 0;
  justify-content: space-evenly;
  align-content: space-between;
  display: flex;
}
.card-button {
  border: none;
  color: white;
  background-color: rgba(0, 167, 88, 255);
  padding: 1.5vh 5vh;
  border-radius: 10px;
  text-decoration: none;
}
.card-button:hover {
  background-color: rgb(2, 131, 70);
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
  margin-top: 11vh;
  min-height: 73vh;
  align-items: flex-start;
}
#search {
  grid-area: "search";
  width: 49%;
  margin: 0vh auto 0vh auto;
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
select {
  width: 30%;
  padding: 12px 5px 12px 10px;
  margin: 1vh 0vh 2vh 0vh;
  background-color: #f8f7f6;
  border: 2px solid #ccc;
  border-radius: 10px 0px 0px 10px;
  color: grey;
  font-size: 16px;
}
select:focus {
  outline: none;
  border-color: rgba(0, 148, 255, 255);
}
option {
  color: grey;
  padding: 12px 10px 12px 10px;
  border-radius: 20px;
  font-size: 16px;
}
input[type="text"] {
  width: 70%;
  box-sizing: border-box;
  border: 2px solid #ccc;
  border-radius: 0px 10px 10px 0px;
  font-size: 16px;
  background-color: white;
  background-image: url("../assets/icons8-search-32.png");
  background-repeat: no-repeat;
  padding: 12px 20px 12px 40px;
  margin: 1vh 3vh 2vh 0vh;
}
input[name="search"]:focus {
  outline: none;
  border: solid 2px rgba(0, 148, 255, 255);
}
.flashcard-component {
  height: 24vh;
  width: 40vh;
}
#card-buttons {
  display: flex;
  justify-content: space-between;
}
.footer {
  grid-area: footer;
  z-index: 3;
}
.no-cards {
  font-size: 10vh;
  text-align: center;
}
</style>
