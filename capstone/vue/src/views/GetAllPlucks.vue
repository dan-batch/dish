<template>
  <div>
    <h1>My Potlucks:</h1>
    <router-link
      class="pluck"
      v-for="pluck in plucks"
      v-bind:key="pluck.pluckId"
      :to="{ name: 'potluck-dishes', params: { id: pluck.pluckId } }"
    >
      <div class="potluckName">
        <p>{{ pluck.pluckName }}</p>
      </div>
    </router-link>
  </div>
</template>

<script>
import PotluckService from "../services/PotluckService";
export default {
  name: "get-all-plucks",
  data() {
    return {
      plucks: null,
    };
  },
  created() {
    PotluckService.getAllPlucks().then((r) => {
      this.plucks = r.data;
    });
  },
  methods: {
    viewPluck(potluckId) {
      this.$router.push(`potluck/${potluckId}/dishes`);
    },
  },
};
</script>

<style scoped>
h1 {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>