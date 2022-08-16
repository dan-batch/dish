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
        <div class="header">
          <h3>{{ dish.userName }}</h3>
        </div>
        <div class="dish-name">
          <h3>{{ dish.dishName }}</h3>
        </div>
        <div class="restrictions">
          <span class="restriction-list" :class="getDishRestrictions(dishID)">{{
            dish.DishID
          }}</span>
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
      this.$router.push(`/pluck/${this.boardID}/dish/${dishID}`);
    },
    getDishRestrictions(dishID) {
      let clazz = "";
      switch (tag) {
        case "Feature Request":
          clazz = "feature";
          break;
        case "Design":
          clazz = "design";
          break;
        case "Q&A":
          clazz = "qa";
          break;
      }
      return clazz;
    },
  },
};
</script>

<style>
</style>