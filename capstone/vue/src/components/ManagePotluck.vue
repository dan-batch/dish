<template>
  <div>
    <form action="" method="post">
      <div id="event-details">
        <div>
          <label for="pluckName">Name your event</label><br />
          <input type="text" name="pluckName" id="pluckName-input" />
        </div>
        <div>
          <label for="dateTime">Date &amp; Time</label><br />
          <input
            type="datetime-local"
            name="pluckDateTime"
            id="pluckDateTime-input"
          />
        </div>
        <div>
          <label for="pluckLocation">Event location</label><br />
          <input type="text" name="pluckLocation" id="pluckLocation-input" />
        </div>
        <div>
          <label for="pluckDescription">Event description</label><br />
          <input
            type="text"
            name="pluckDescription"
            id="pluckDescription-input"
          />
        </div>
      </div>
      <div id="dish-requirements">
        <h3>Choose categories and limits:</h3>
        <div v-for="category in categoryOptions" :key="category.cat_id">
          <label>{{ category.cat_name }}</label>
          <input
            type="checkbox"
            :name="category.cat_id + '-checkbox'"
            :id="category.cat_id + '-checkbox'"
            :value="category.cat_id"
            v-model="selectedCategories"
          />
          <select
            :name="category.cat_id + '-selector'"
            :id="category.cat_id + '-selector'"
            :disabled="!catIsSelected(category.cat_id)"
          >
            <option
              v-for="quantity in catQuantities"
              :key="quantity"
              :value="quantity"
            >
              {{ quantity }}
            </option>
          </select>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      potluckID: "",
      potluckName: "",
      potluckDescription: "",
      potluckLocation: "",
      selectedCategories: [],
      categoryOptions: this.$store.state.categories,
    };
  },
  methods: {
    catIsSelected(catID) {
      return this.selectedCategories.includes(catID);
    },
  },
  computed: {
    catQuantities() {
      let quantities = [];
      let maxQuantity = 10;
      for (let i = 1; i <= maxQuantity; i++) {
        quantities.push(i);
      }
      quantities.push("any");
      return quantities;
    },
  },
};
</script>

<style lang="scss" scoped>
</style>