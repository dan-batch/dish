<template>
  <div class="potluck-dishes">
    <h1 id="name">{{ potluck.pluckName }}</h1>
    <h3 id="where">where: {{ potluck.pluckPlace }}</h3>
    <h3 id="when">when: {{ potluck.pluckTime.toDateString() }}</h3>
    <h4 id="description-header">description:</h4>
    <p id="description-body">{{ potluck.pluckDescription }}</p>
  </div>
</template>

<script>
import potluckService from "../services/PotluckService";
export default {
  components: {},
  data() {
    return {
      potluck: {
        pluckId: null,
        pluckName: "",
        pluckPlace: "",
        pluckTime: null,
        pluckDescription: "",
        pluckDishes: null,
        pluckCats: null,
      },
    };
  },
  created() {
    potluckService.getPotluck(this.$route.params.id).then((r) => {
      this.potluck = r.data;
      let pluckTime = new Date(this.potluck.pluckTime);
      this.potluck.pluckTime = pluckTime;
    });
  },
};
</script>

<style lang="scss" scoped>
</style>