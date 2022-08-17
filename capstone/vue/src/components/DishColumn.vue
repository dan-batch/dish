<template>
  <div class="column">
    <h4>{{ title }}</h4>
    <div class="dishes">
      <div
        class="dish"
        v-for="dish in dishes"
        v-bind:key="dish.dishId"
        v-on:click="viewDishDetails(dish.dishId)"
      >
        <div class="username">
          <p>{{ dish.username }}</p>
        </div>
        <div class="dish-name">
          <p>{{ dish.dishName }}</p>
        </div>
        <div class="servings">
          <p>Servings: {{ dish.servings }}</p>
        </div>
      </div>
    </div>
    <router-link to="/createDish" v-if="limit > dishes.length"
      >Add dish</router-link
    >
  </div>
</template>

<script>
export default {
  name: "dish-column",
  props: ["title", "dishes", "dishID", "categoryID", "limit"],
  methods: {
    viewDishDetails(dishID) {
      this.$router.push(`/dish/${dishID}`);
    },
  },
};
</script>

<style>
@media only screen and (max-width: 768px) {
  .dishes {
    display: flex;
    flex-flow: row wrap;
    flex-grow: 1;
    width: 18em;
  }
}
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

h4 {
  text-align: center;
}
</style>