<template>
  <div class="header-container">
    <div id="logo">
      <router-link v-bind:to="{ name: 'home' }"
        ><img src="../assets/flip-logo.png" alt="Flip" />
      </router-link>
    </div>
    <div id="links">
      <div id="home">
        <router-link
          v-bind:to="{ name: 'home' }"
          v-if="$store.state.token != ''"
          class="link"
          >Home
        </router-link>
      </div>
      <div id="logout">
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          class="link"
          >Logout
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "header-component",
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Bungee+Inline&family=Bungee+Shade&family=Fredoka+One&family=Montserrat&display=swap");
.header-container {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  justify-items: center;
  display: grid;
  grid-template-columns: 1fr 5fr;
  grid-template-areas: "logo links";
  border: none;
  border-bottom: solid 2px #b4b0ad;
  background-image: linear-gradient(white, #cad6ff);
  color: #464443;
}
img {
  height: 15vh;
  margin-top: -2vh;
  margin-bottom: -2vh;
}
#logo {
  grid-area: logo;
}
#links {
  font-family: "Fredoka One", Times, sans-serif;
  width: 95%;
  grid-area: links;
  display: flex;
  justify-items: space-between;
  margin-bottom: -3vh;
}
.link {
  text-decoration: none;
  font-size: 2.5vh;
  color: #464443;
}
.link:hover {
  color: purple;
}
#logout {
  margin-left: auto;
}
.link.router-link-exact-active {
  border-bottom: solid purple 3px;
}
</style>