<template>
  <div class="column">
    <h2>{{ title }}</h2>
    <div class="dishes">
      <div
        class="dish"
        v-for="dish in dishes"
        v-bind:key="dish.id"
        v-on:click="viewDishDetails(dish.id)"
      >
        <div class="username">
          <h3>{{ dish.userName }}</h3>
        </div>
        <div class="dish-name">
          <h3>{{ dish.dishName }}</h3>
        </div>
        <div class="servings">
          <h2>Servings: {{ dish.servings }}</h2>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "dish-column",
  props: ["title", "dishes", "dishID"],
  methods: {
    viewDishDetails(dishID) {
      this.$router.push(`/pluck/${this.dishID}/dish/${dishID}`);
    },
  },
};
</script>

<style>
</style>