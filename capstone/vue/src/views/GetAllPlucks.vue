<template>
  <div class="potluck">
    <h1>My Potlucks:</h1>
    <div class="link-well">
      <router-link
        tag="button"
        class="pluck"
        v-for="pluck in plucks"
        v-bind:key="pluck.pluckId"
        :to="{ name: 'potluck-dishes', params: { id: pluck.pluckId } }"
      >
        <div class="potluckName">
          {{ pluck.pluckName }}
        </div>
      </router-link>
    </div>
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

.pluck {
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 35px;
  background-color: #9dcd5a;
  border: none;
  margin: 25px;
  padding: 30px;
  font-weight: bold;
}

.potluckName {
  background-color: #9dcd5a;
  max-width: 500px;
  max-height: 100px;
}
.potluck {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.link-well {
  max-width: 500px;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>