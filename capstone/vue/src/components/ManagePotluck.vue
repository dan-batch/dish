<template>
  <div>
    <form v-on:submit.prevent="createPotluck()" v-on:reset.prevent="">
      <div id="event-details">
        <div>
          <label for="pluckName">Name your event</label><br />
          <input
            type="text"
            name="pluckName"
            id="pluckName-input"
            v-model="potluckName"
          />
        </div>
        <div>
          <label for="dateTime">Date &amp; Time</label><br />
          <input
            type="datetime-local"
            name="pluckDateTime"
            id="pluckDateTime-input"
            v-model="potluckDateTime"
          />
        </div>
        <div>
          <label for="pluckLocation">Event location</label><br />
          <input
            type="text"
            name="pluckLocation"
            id="pluckLocation-input"
            v-model="potluckLocation"
          />
        </div>
        <div>
          <label for="pluckDescription">Event description</label><br />
          <input
            type="text"
            name="pluckDescription"
            id="pluckDescription-input"
            v-model="potluckDescription"
          />
        </div>
      </div>
      <div id="dish-requirements">
        <h4>Choose categories &amp; limits:</h4>
        <div v-for="category in potluckCategories" :key="category.catId">
          <label>{{ category.catName }}</label>
          <input
            type="checkbox"
            :name="category.catId + '-checkbox'"
            :id="category.catId + '-checkbox'"
            :value="category.catId"
            v-model="category.active"
          />
          <select
            :name="category.catId + '-selector'"
            :id="category.catId + '-selector'"
            :disabled="!catIsSelected(category.catId)"
            v-model="category.limit"
          >
            <option
              v-for="quantity in catQuantities"
              :key="quantity"
              :value="quantity"
            >
              {{ quantity }}
            </option>
            <option value="11">any</option>
          </select>
        </div>
      </div>
      <div id="banner-image-selector">
        <h4>Choose event banner:</h4>
        <img
          v-for="image in bannerImages"
          :key="image.id"
          :src="image.url"
          :value="image.id"
          @click="selectBanner(image.id)"
        />
      </div>
      <div id="save-or-cancel">
        <input type="submit" value="Save Changes" />
        <input type="reset" value="Cancel" />
      </div>
    </form>
  </div>
</template>

<script>
import categoryService from "../services/CategoryService";
import potluckService from "../services/PotluckService";
export default {
  data() {
    return {
      potluckID: "",
      potluckName: "",
      potluckDescription: "",
      potluckLocation: "",
      potluckDateTime: "",
      potluckCategories: this.$store.state.categories,
      bannerImages: this.$store.state.bannerImages,
      selectedBanner: 1,
    };
  },
  mounted() {
    console.log("getPotluckCategories");
    categoryService
      .getAllCategories()
      .then((r) => {
        this.$store.commit("SET_CATEGORIES", r.data);
      })
      .catch((e) => {
        console.log(e.statusMessage);
      });
  },
  methods: {
    catIsSelected(catID) {
      return this.potluckCategories.find((c) => c.catId === catID).active;
    },
    selectCatQuantity(catID, quantity) {
      console.log("selectCatQuantity");
      let cat = this.potluckCategories.find((c) => c.catId === catID);
      cat.selected = true;
      cat.limit = quantity;
    },
    selectBanner(bannerID) {
      this.selectedBanner = bannerID;
      this.$forceUpdate();
      console.log(bannerID);
    },
    createPotluck() {
      let newPotluck = {
        pluckName: this.potluckName,
        pluckPlace: this.potluckLocation,
        pluckTime: this.potluckDateTime,
        pluckDescription: this.potluckDescription,
      };
      potluckService.createPotluck(newPotluck);
      // .then(r => {
      //   let newCat = {
      //     pluckId: r.data,
      //     catId =
      //   }
      //   potluckService.addCatToPluck(r.data)
      // });
    },
  },
  computed: {
    catQuantities() {
      let quantities = [];
      let maxQuantity = 10;
      for (let i = 1; i <= maxQuantity; i++) {
        quantities.push(i);
      }
      return quantities;
    },
  },
};
</script>

<style lang="scss" scoped>
</style>