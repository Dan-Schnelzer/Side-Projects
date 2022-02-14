<template>
  <div class="scene">
    <div class="flashcard" v-on:click.stop="flip($event)">
      <div class="card__face card__face--front">
        <p class="content">Question: {{ this.front }}</p>
      </div>
      <div class="card__face card__face--back">
        <p class="content">Answer: {{ this.back }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "flashcard-component",
  props: ["front", "back"],
  methods: {
    flip(event) {
      if (event.target.tagName === "DIV") {
        event.target.parentElement.classList.toggle("is-flipped");
        let cardBacks = document.querySelectorAll(".card__face--back");
        for (let i = 0; i < cardBacks.length; i++) {
          cardBacks[i].classList.add("show-answer");
        }
      }
      if (event.target.tagName === "P") {
        event.target.parentElement.parentElement.classList.toggle("is-flipped");
        let cardBacks = document.querySelectorAll(".card__face--back");
        for (let i = 0; i < cardBacks.length; i++) {
          cardBacks[i].classList.add("show-answer");
        }
      }
    },
  },
};
</script>

<style scoped>
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
  justify-content: space-around;
  align-items: center;
  box-shadow: 10px 10px 5px rgba(0, 0, 0, 0.26);
}
.card__face--back {
  background: white;
  color: white;
  border: solid 1px black;
  border-radius: 10px;
  display: flex;
  justify-content: space-around;
  align-items: center;
  transform: rotateY(180deg);
  font-size: 1.2vw;
  box-shadow: 10px 10px 5px rgba(0, 0, 0, 0.26);
}
.show-answer {
  color: #464443;
}
.is-flipped {
  transform: rotateY(180deg);
}
</style>