<template>
  <div class="potluck-dishes">
    <div class="header-container">
      <h1 class="header">{{ potluck.pluckName }}</h1>
    </div>
    <div class="where-when">
      <h3 class="where">where: {{ potluck.pluckPlace }}</h3>
      <h3 class="when">when: {{ potluck.pluckTime.toDateString() }}</h3>
    </div>
    <div class="categories-description">
      <!-- Placeholders -->
      <!-- <div
        v-for="column in testCategoryColumns"
        :key="column"
        class="description-container"
      >
        <h4 class="description-header">description:</h4>
        <p class="description-body">
          Lorem ipsum dolor sit amet, consectetur adipisicing elit.
        </p>
        <p class="description-body">
          Eum numquam consequuntur perferendis reprehenderit.
        </p>
        <p class="description-body">
          Beatae quam temporibus non quod dicta doloremque ea necessitatibus?
        </p>
      </div> -->

      <dish-column
        class="dish-column"
        v-for="column in testCategoryColumns"
        :key="column"
        :title="'column #' + column"
      ></dish-column>

      <!-- Actual description -->
      <div class="description-container">
        <h4 class="description-header">description:</h4>
        <p class="description-body">
          {{ potluck.pluckDescription }}
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import potluckService from "../services/PotluckService";
import DishColumn from "../components/DishColumn";
export default {
  components: {
    DishColumn,
  },
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
  computed: {
    testCategoryColumns() {
      let testArray = [];
      for (let i = 1; i <= 5; i++) {
        testArray.push(i);
      }
      return testArray;
    },
  },
};
</script>

<style scoped>
.potluck-dishes {
  margin: 30px;
}
.header-container {
  display: grid;
  justify-content: center;
  height: 100px;
  border: 2px red solid;
  border-radius: 20px;
  background-image: url("data:image/jpeg;base64,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");
}
.header {
  padding: 0;
  background: transparent;
}
.where-when {
  display: flex;
  justify-content: space-between;
}
.categories-description {
  display: flex;
  flex-wrap: nowrap;
}
.description-container {
  margin: 10px;
  padding: 5px;
  border: 2px red solid;
  width: 16.6%;
}
.dish-column {
  width: 16.6%;
  border: 2px red solid;
}
.description-header {
  text-align: center;
}
.description-body {
  background-color: white;
  padding: 10px;
  border-radius: 20px;
}
</style>