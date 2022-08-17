<template>
  <div class="potluck-dishes">
    <div class="header-container" :style="cssVars">
      <h1 class="header">{{ potluck.pluckName }}</h1>
    </div>
    <div class="where-when">
      <h3 class="where">where: {{ potluck.pluckPlace }}</h3>
      <h3 class="when">when: {{ potluck.pluckTime.toDateString() }}</h3>
    </div>
    <div class="categories-description">
      <!-- Dishes -->

      <dish-column
        class="dish-column"
        v-for="category in pluckCats"
        :key="category.catId"
        :title="category.catName"
        :dishes="pluckDishes.filter((d) => d.dishCatId === category.catId)"
        :limit="category.limit"
      ></dish-column>

      <!-- Description -->
      <div class="description-container">
        <h4 class="description-header">description:</h4>
        <p class="description-body">
          {{ potluck.pluckDescription }}
        </p>
      </div>
    </div>
    <form>
      <p><label for="guest-list">Guest List</label></p>
      <textarea id="guests" name="guest-list" rows="8" columns="200">
To invite guests, enter their email addresses here, separated by a comma: "," </textarea
      >
      <div>
        <input type="email" id="emailButton" value="Email Guests" />
        <input type="reset" id="resetButton" value="Reset Form" />
      </div>
    </form>
  </div>
</template>

<script>
import potluckService from "../services/PotluckService";
import categoryService from "../services/CategoryService";
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
        pluckTime: "",
        pluckDescription: "",
        pluckImageURL: "",
      },
      pluckDishes: [],
      pluckCats: [],
      categories: this.$store.state.categories,
    };
  },
  beforeCreate() {
    potluckService.getPotluck(this.$route.params.id).then((r) => {
      console.log("got response");
      this.potluck = r.data;
      let pluckTime = new Date(this.potluck.pluckTime);
      this.potluck.pluckTime = pluckTime;
    });

    potluckService.getPluckDishes(this.$route.params.id).then((d) => {
      this.pluckDishes = d.data;
    });

    categoryService.getPluckCategories(this.$route.params.id).then((c) => {
      this.pluckCats = c.data;
    });
  },
  computed: {
    cssVars() {
      return {
        "--headerImage": "url(" + this.potluck.pluckImageURL + ")",
      };
    },
  },
  methods: {},
};
</script>

<style scoped>
@media only screen and (max-width: 768px) {
  .potluck-dishes {
    margin: 30px;
  }
  .header-container {
    display: grid;
    justify-content: center;
    height: 100px;
    border: 2px #6b3e0a solid;
    border-radius: 20px;
    background-image: var(--headerImage);
  }
  .header {
    padding: 0;
    background: transparent;
  }
  .where-when {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  }
  .categories-description {
    display: flex;
    flex-direction: column;
  }
  .description-container {
    margin: 10px;
    padding: 5px;
    width: 16.6%;
  }
  .dish-column {
    width: 16.6%;
    margin: 10px;
    padding: 5px;
  }
  .description-header {
    text-align: center;
  }
  .description-body {
    background-color: white;
    padding: 10px;
    border-radius: 20px;
    width: 16em;
  }

  p {
    background-color: transparent;
    font-weight: 900;
  }

  textarea {
    width: 22em;
    background-color: white;
  }

  #emailButton {
    border: none;
    border-radius: 16px;
    font-weight: 600;
    font-size: 18px;
    color: white;
    background-color: #9dcd5a;
    height: 32px;
    width: 125px;
    text-align: center;
  }

  #resetButton {
    border: none;
    border-radius: 16px;
    font-weight: 600;
    font-size: 18px;
    color: white;
    background-color: #f58634;
    height: 32px;
    width: 125px;
  }
}

@media only screen and (min-width: 768px) {
  .potluck-dishes {
    margin: 30px;
  }
  .header-container {
    display: grid;
    justify-content: center;
    height: 100px;
    border: 2px #6b3e0a solid;
    border-radius: 20px;
    background-image: var(--headerImage);
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
    width: 16.6%;
  }
  .dish-column {
    width: 16.6%;
    margin: 10px;
    padding: 5px;
  }
  .description-header {
    text-align: center;
  }
  .description-body {
    background-color: white;
    padding: 10px;
    border-radius: 20px;
  }

  p {
    background-color: transparent;
    font-weight: 900;
  }

  textarea {
    width: 50em;
    background-color: white;
  }

  #emailButton {
    border: none;
    border-radius: 16px;
    font-weight: 600;
    font-size: 18px;
    color: white;
    background-color: #9dcd5a;
    height: 32px;
    width: 125px;
    text-align: center;
  }

  #resetButton {
    border: none;
    border-radius: 16px;
    font-weight: 600;
    font-size: 18px;
    color: white;
    background-color: #f58634;
    height: 32px;
    width: 125px;
  }
}
</style>