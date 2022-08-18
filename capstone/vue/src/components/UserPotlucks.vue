<template>
  <div class="page-title">
    <h4>Your Potlucks</h4>
    <div class="potlucks">
      <div
        class="pluck"
        v-for="potluck in potlucks"
        v-bind:key="potluck.potluckID"
        v-on:click="viewPotluckDetails(potluck.potluckID)"
      >
        <div class="name">
          <p>{{ potluck.name }}</p>
        </div>
        <div class="description">
          <p>{{ potluck.description }}</p>
        </div>
        <div class="date-time">
          <p>{{ potluck.dateTime }}</p>
        </div>
        <div class="location">
          <p>{{ potluck.location }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "user-potlucks",
  props: ["potlucks", "potluckID"],
  methods: {
    viewPotluckDetails(potluckID) {
      this.$router.push(`/potluck/${potluckID}/dishes`);
    },
  },
};
</script>

<style>
</style>