<template>
  <div class="column">
    <h4>{{ title }}</h4>
    <div class="dishes">
      <div
        class="dish"
        v-for="dish in dishes"
        v-bind:key="dish.id"
        v-on:click="viewDishDetails(dish.id)"
      >
        <div class="username">
          <p>{{ dish.userName }}</p>
        </div>
        <div class="dish-name">
          <p>{{ dish.dishName }}</p>
        </div>
        <div class="servings">
          <p>Servings: {{ dish.servings }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "dish-column",
  props: ["title", "dishes", "dishID", "categoryID"],
  methods: {
    viewDishDetails(dishID) {
      this.$router.push(`/pluck/${this.dishID}/dish/${dishID}`);
    },
  },
};
</script>

<style>
.dish {
  background-color: white;
  padding: 5px;
  border-radius: 20px;
  margin-bottom: 10px;
}
p {
  background-color: white;
}
.servings p {
  font-size: 0.8rem;
}
</style>