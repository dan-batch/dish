<template>
  <div class="all">
    <h2>{{ dish.username }} is bringing:</h2>
    <h1>{{ dish.dishName }}</h1>
    <h3>Serving {{ dish.servings }}</h3>
    <h3 class="description">"{{ dish.dishDescription }}"</h3>
    <h3 class="dietary-restrictions">This dish will be:</h3>
    <ul class="dietary-restriction-list">
      <li v-for="restriction in dishDietaryRestrictions" :key="restriction.id">
        <span
          :id="restriction.abbreviation + '-icon'"
          class="dietary-restriction-icon"
          >{{ restriction.abbreviation }}</span
        >
        <span class="dietary-restriction-name">{{ restriction.name }}</span>
      </li>
    </ul>
    <div class="buttons">
      <router-link
        tag="button"
        :to="{ name: 'EditDish', params: { dishId: $route.params.dishId } }"
        class="btn-editDish"
        >Edit Dish</router-link
      >
      <button class="btn-deleteDish" v-on:click="deleteDish">
        Delete Dish
      </button>
    </div>
  </div>
</template>

<script>
import dishService from "../services/DishService";
import restrictionService from "../services/DietaryRestrictionsService";
export default {
  name: "dish-detail",
  data() {
    return {
      dishDietaryRestrictions: [],
    };
  },
  methods: {
    retrieveDish() {
      dishService
        .getDish(this.$route.params.dishId)
        .then((response) => {
          this.$store.commit("SET_DISH", response.data);
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert("Dish not available. This dish may have been deleted.");
            this.$router.push({ name: "Home" });
          }
        });
    },
    deleteDish() {
      if (
        confirm(
          "Are you sure you want to delete this dish? This action cannot be undone."
        )
      ) {
        dishService
          .deleteDish(this.dish.dishId)
          .then((response) => {
            if (response.status === 200) {
              alert("Dish successfully deleted");
              this.$router.push(`/potluck/${this.potluck.pluckId}/dishes`);
            }
          })
          .catch((error) => {
            if (error.response) {
              this.errorMsg =
                "Error deleting dish. Response received was '" +
                error.response.statusText +
                "'.";
            } else if (error.request) {
              this.errorMsg =
                "Error deleting dish. Server could not be reached.";
            } else {
              this.errorMsg =
                "Error deleting dish. Request could not be created.";
            }
          });
      }
    },
  },
  created() {
    this.retrieveDish();
  },
  mounted() {
    restrictionService.getRestrictionsList().then((list) => {
      dishService
        .getDishRestrictionIDs(this.$store.state.dish.dishId)
        .then((d) => {
          list.data.forEach((r) => {
            if (d.data.includes(r.id)) {
              this.dishDietaryRestrictions.push(r);
            }
          });
        });
    });
    console.log("created()");
  },
  computed: {
    dish() {
      return this.$store.state.dish;
    },
  },
};
</script>

<style scoped>
.all {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.description {
  font-style: italic;
}

.btn-editDish {
  grid-area: "ga-edit";
  margin-right: 7px;
  border-radius: 16px;
  border-style: none;
  font-weight: bold;
  font-size: 18px;
  color: white;
  background-color: #9dcd5a;
  height: 32px;
  width: 140px;
  align-items: center;
}

.btn-deleteDish {
  grid-area: "ga-delete";
  border-radius: 16px;
  border-style: none;
  font-weight: 600;
  font-size: 18px;
  color: white;
  height: 32px;
  width: 140px;
  background-color: #f58634;
}

.dietary-restriction-icon {
  width: 20px;
  border-radius: 50%;
  margin-left: 5px;
  text-align: center;
  margin-top: 6px;
}

.dietary-restriction-name {
  background-color: white;
  padding-left: 5px;
  margin-top: 6px;
  font-weight: bold;
}
</style>