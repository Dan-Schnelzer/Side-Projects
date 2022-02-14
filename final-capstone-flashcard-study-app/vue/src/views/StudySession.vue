<template>
  <div class="view">
    <nav>
      <button class="shuffle nav-button" v-on:click.stop="shuffleDeck(cards)">
        Shuffle Deck
      </button>
      <router-link :to="{ name: 'results' }" class="nav-button">
        End Study Session</router-link
      >
    </nav>
    <div id="main">
      <article>
        <div id="study-container">
          <div class="cards">
            <flashcard-component
              :front="frontOfCard"
              :back="backOfCard"
              class="flashcard-component"
            />
          </div>
          <div class="study-buttons" v-on:click.stop="markIsCorrect($event)">
            <button class="study-button" id="mark-incorrect">Incorrect</button>
            <span>
              {{ currentCardIndex + 1 }} /
              {{ this.$store.state.deck.cards.length }}
            </span>
            <button
              class="study-button"
              id="mark-correct"
              v-on:click.stop="markIsCorrect($event)"
            >
              Correct
            </button>
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
import deckService from "../services/DeckService.js";

export default {
  components: {
    FooterComponent,
    FlashcardComponent,
  },
  name: "study-session",
  data() {
    return {
      deck: this.$store.state.deck,
      cards: [],
      isLoading: true,
      currentCardIndex: 0,
    };
  },
  methods: {
    markIsCorrect(event) {
      if (event.target.tagName === "BUTTON") {
        const maxIndex = this.$store.state.deck.cards.length - 1;

        if (event.target.id === "mark-incorrect") {
          let cardState = {
            index: this.currentCardIndex,
            isCorrect: false,
          };
          this.$store.commit("MARK_CARD_ISCORRECT", cardState);
        } else if (event.target.id === "mark-correct") {
          let cardState = {
            index: this.currentCardIndex,
            isCorrect: true,
          };
          this.$store.commit("MARK_CARD_ISCORRECT", cardState);
        }

        document.querySelector(".flashcard").classList.remove("is-flipped");
        document
          .querySelector(".card__face--back")
          .classList.remove("show-answer");

        if (this.currentCardIndex + 1 > maxIndex) {
          this.$router.push({ name: "results" });
        }

        this.currentCardIndex++;
      }
    },
    retrieveDeck() {
      deckService
        .getDeckById(this.$route.params.deckId)
        .then((response) => {
          this.$store.commit("SET_DECK", response.data);
          this.deck = response.data;
          this.cards = response.data.cards;
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
    shuffleDeck(cards) {
      for (let i = cards.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        const temp = cards[i];
        cards[i] = cards[j];
        cards[j] = temp;
      }

      this.cards = cards;
      this.currentCardIndex = 1;
      this.currentCardIndex = 0;
    },
  },
  computed: {
    frontOfCard() {
      if (this.cards[this.currentCardIndex] === undefined) {
        return "undefined";
      } else {
        return this.cards[this.currentCardIndex].frontOfCard;
      }
    },
    backOfCard() {
      if (this.cards[this.currentCardIndex] === undefined) {
        return "undefined";
      } else {
        return this.cards[this.currentCardIndex].backOfCard;
      }
    },
  },
  created() {
    this.retrieveDeck();
  },
};
</script>

<style scoped>
h1 {
  color: #464443;
  margin: 0vh auto 0vh 3vh;
  font-size: 4vh;
}
h3 {
  margin: 0vh auto 0vh 3vh;
  color: #464443;
  font-size: 3vh;
}
.view {
  min-height: 100vh;
  display: grid;
  grid-template-columns: 1fr 5fr;
  grid-template-areas:
    "nav body"
    "footer footer";
  column-gap: 10px;
}
.study-button {
  border: none;
  color: white;
  font-weight: bold;
  background-color: rgba(0, 167, 88, 255);
  padding: 1.5vh 5vh;
  border-radius: 10px;
  text-decoration: none;
}
.study-button:hover {
  background-color: rgb(2, 131, 70);
}
#mark-incorrect {
  background-color: red;
}
#mark-incorrect:hover {
  background-color: rgb(168, 2, 2);
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
#main {
  grid-area: body;
  margin-top: 11vh;
  min-height: 73vh;
  display: flex;
  flex-direction: column;
  align-items: stretch;
}
#study-container {
  min-height: 69vh;
  background-image: url("../assets/lighter-blue-green-background.png");
  padding: 4vh 0vh 4vh 0vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5vh 0vh;
}
.cards {
  align-self: center;
}
.study-buttons {
  width: 75vh;
  display: flex;
  justify-content: space-between;
}
.flashcard-component {
  height: 45vh;
  width: 75vh;
}
.footer {
  z-index: 3;
  grid-area: footer;
}
</style>
