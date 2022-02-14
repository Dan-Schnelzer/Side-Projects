<template>
  <div class="deck">
    <router-link
      class="deck-link"
      :to="{ name: 'deck-details', params: { id: id } }"
    >
      <div class="card-deck">
        <p class="deck-name">{{ name }}</p>
        <p class="deck-size">{{ size }} cards in deck</p>
        <p class="created-by">
          Created by <span class="bold">{{ createdBy }}</span>
        </p>
      </div>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "deck-component",
  props: ["name", "size", "createdBy", "id"],
};
</script>

<style scoped>
.card-deck {
  background: white;
  border: solid 1px black;
  border-radius: 10px;
  padding: 30px;
  font-size: 18px;
  margin-bottom: 3vh;
}
.deck-link {
  text-decoration: none;
  color: #464443;
}
.deck-name {
  font-size: 3vh;
  font-weight: bold;
}
.deck-size {
  font-size: 2.2vh;
}
.created-by {
  font-size: 2.3vh;
}
.bold {
  font-weight: bold;
}
</style>