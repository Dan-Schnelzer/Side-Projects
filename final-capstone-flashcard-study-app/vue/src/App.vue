<template>
  <div id="app">
    <header>
      <header-component />
    </header>
    <div class="router-view">
      <router-view />
    </div>
  </div>
</template>

<script>
import HeaderComponent from "./components/HeaderComponent.vue";
export default {
  components: {
    HeaderComponent,
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap");
html,
body {
  /*background: #a66f5b url("./assets/pexels-fauxels-3184464.jpg") 0 0 no-repeat;*/
  background-size: 100vw;
  margin: 0px;
  height: 100%;
  font-family: "Roboto", sans-serif;
}
@import url("https://fonts.googleapis.com/css2?family=Bungee+Inline&family=Bungee+Shade&family=Fredoka+One&family=Montserrat&display=swap");

header {
  position: fixed;
  top: 0px;
  width: 100%;
  z-index: 2;
}
.router-view {
  z-index: 1;
}
</style>