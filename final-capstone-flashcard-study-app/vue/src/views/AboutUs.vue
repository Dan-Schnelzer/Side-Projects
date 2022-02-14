<template>
  <div class="view">
    <div id="main">
      <article>
        <div id="card-container">
          <div id="cards">
            <div
              class="card"
              v-for="teammate in teammates"
              v-bind:key="teammate.id"
            >
              <div class="scene">
                <div class="flashcard" v-on:click.stop="flip($event)">
                  <div class="card__face card__face--front">
                    <p class="name">{{ teammate.fullName }}</p>
                    <img
                      :src="
                        teammate.name === 'dan'
                          ? dan
                          : teammate.name === 'anna'
                          ? anna
                          : teammate.name === 'sean'
                          ? sean
                          : jamie
                      "
                    />
                    <a v-bind:href="teammate.linkedinUrl">LinkedIn</a>
                  </div>
                  <div class="card__face card__face--back">
                    <p class="content">{{ teammate.bio }}</p>
                  </div>
                </div>
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
import FooterComponent from "../components/FooterComponent.vue";
import danHeadshot from "@/assets/dan-headshot.jpg";
import annaHeadshot from "@/assets/anna-headshot.jpg";
import jamieHeadshot from "@/assets/jamie-headshot.jpg";
import seanHeadshot from "@/assets/crypto-sean.jpg";

export default {
  components: {
    FooterComponent,
  },
  name: "home",
  data() {
    return {
      teammates: this.$store.state.teammates,
      dan: danHeadshot,
      anna: annaHeadshot,
      jamie: jamieHeadshot,
      sean: seanHeadshot,
    };
  },
  computed: {},
  methods: {
    flip(event) {
      if (event.target.tagName === "DIV") {
        event.target.parentElement.classList.toggle("is-flipped");
      }
      if (event.target.tagName === "P") {
        event.target.parentElement.parentElement.classList.toggle("is-flipped");
      }
    },
  },
};
</script>

<style scoped>
.view {
  min-height: 100vh;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas:
    "body"
    "footer";
}
img {
  height: 20vh;
  width: 20vh;
  object-fit: cover;
  border-radius: 50%;
}
.name {
  margin: 0;
}
a {
  display: block;
  color: rgba(0, 148, 255, 255);
}
#card-container {
  height: 75vh;
  background-image: url("../assets/lighter-blue-green-background.png");
  padding: 4vh;
  display: flex;
  justify-content: center;
  padding-bottom: 8vh;
}
#cards {
  width: 92%;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  align-content: space-between;
  gap: 7vh;
  padding-bottom: 4vh;
}
#main {
  grid-area: body;
  min-height: 75vh;
  margin-top: 11vh;
}
.flashcard-component {
  height: 24vh;
  width: 40vh;
}
.footer {
  z-index: 3;
  grid-area: footer;
}
.card {
  height: 36vh;
  width: 60vh;
}
.scene {
  width: 100%;
  height: 100%;
  perspective: 600px;
}
.flashcard {
  width: 100%;
  height: 100%;
  transition: transform 1s;
  transform-style: preserve-3d;
  border-radius: 20px;
  margin: auto;
  background-image: linear-gradient(to bottom, rgb(255, 255, 255), #e4e0dd);
}
.content {
  padding: 0vh 4vh;
}
.card__face {
  position: absolute;
  height: 100%;
  width: 100%;
  backface-visibility: hidden;
  text-align: center;
  font-size: 2.2vh;
}
.card__face--front {
  background: white;
  color: #464443;
  border: solid 1px black;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 2vh;
  align-items: center;
  box-shadow: 10px 10px 5px rgba(0, 0, 0, 0.26);
}
.card__face--back {
  background: white;
  color: #464443;
  border: solid 1px black;
  border-radius: 10px;
  display: flex;
  justify-content: space-around;
  align-items: center;
  transform: rotateY(180deg);
  font-size: 1.2vw;
  box-shadow: 10px 10px 5px rgba(0, 0, 0, 0.26);
}
.is-flipped {
  transform: rotateY(180deg);
}
</style>
