<template>
  <div class="footer-container">
    <h1>
      Brought to you by <span id="blue">StudyBlue</span
      ><span id="green">Green</span>
    </h1>
    <h1 id="practice">Practice Makes Permanent</h1>
    <div id="nav">
      <router-link id="about-us" v-bind:to="{ name: 'about-us' }" class="link"
        >About Us</router-link
      >
      <!-- empty router link, we can decide later to make a About us page if we would like -->
    </div>
  </div>
</template>

<script>
export default {
  name: "footer-component",
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Bungee+Inline&family=Bungee+Shade&family=Fredoka+One&family=Montserrat&display=swap");
.footer-container {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: none;
  border-top: solid 2px #b4b0ad;
  background-image: linear-gradient(#c4d2ff, rgb(214, 207, 255));
  background-image: ("./assets/cool-background(1).png");
  font-size: 1.5vh;
  color: #464443;
}
h1 {
  margin-left: 2vh;
  font-family: "Fredoka One", Times, sans-serif;
}
#blue {
  font-family: "Bungee Inline", Times, sans-serif;
  color: rgba(0, 148, 255, 255);
}
#green {
  font-family: "Bungee Inline", Times, sans-serif;
  color: rgba(0, 167, 88, 255);
}
#about-us {
  font-family: "Fredoka One", Times, sans-serif;
  margin-right: 2vh;
  font-size: 2.5vh;
  text-decoration: none;
  color: #464443;
}
#about-us:hover {
  color: purple;
}
.router-link-exact-active {
  border-bottom: solid purple 3px;
}
#practice {
  color: purple;
  font-family: "Bungee Shade", Times, sans-serif;
}
</style>