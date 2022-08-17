<template>
  <div>
    <h2>{{ dish.userName }} is bringing:</h2>
    <h1>{{ dish.dishName }}</h1>
    <p>{{ dish.servings }} Servings</p>
    <p>{{ dish.details }}</p>
    <h3 class="dietary-restrictions">This dish will be:</h3>
    <ul class="dietary-restriction-list">
      <li v-for="restriction in selectedRestrictions" :key="restriction.id">
        <div class="restrictionBorder">
          <span
            :id="restriction.abbreviation + '-icon'"
            class="dietary-restriction-icon"
            >{{ restriction.abbreviation }}</span
          >
          <span class="dietary-restriction-name">{{ restriction.name }}</span>
        </div>
      </li>
    </ul>
    <router-link
      tag="button"
      :to="{ name: 'CreateDish', params: { dishID: $route.params.dishID } }"
      class="btn editDish"
      >Edit Dish</router-link
    >

    <button class="btn deleteDish" v-on:click="deleteDish">Delete Dish</button>
  </div>
</template>

<script>
export default {
  name: "dish-detail",
  data() {
    return {
      userName: this.$store.state.dish.userName,
      dishName: this.$store.state.dish.dishName,
      servings: this.$store.state.dish.servings,
      dietaryRestrictions: this.$store.state.dietaryRestrictions,
      selectedRestrictions: this.selectRestrictions(),
      details: this.$store.state.dish.details,
    };
  },
  methods: {
    selectRestrictions() {
      let selected = [];
      this.$store.state.DishDietaryRestrictions.forEach((r) => {
        if (r.active) {
          selected.push(r.id);
        }
      });
      return selected;
    },
  },
};
</script>

<style scoped>
</style>