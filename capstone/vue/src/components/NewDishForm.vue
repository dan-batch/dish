<template>
  <div class="new-dish-form">
    <form v-on:submit.prevent="saveDish()" class="newDishForm">
      <div class="dish-info">
        <div class="leftSide">
          <div class="username">
            <label for="username" id="username"
              >Tell 'em who's bringing this dish!</label
            ><br />
            <input
              type="text"
              name="username"
              id="username-input"
              v-model="username"
              required
            />
          </div>
          <div class="DishName">
            <label for="dishName" id="dishNameLabel">Name Your Dish:</label
            ><br />
            <input
              type="text"
              name="dishName"
              id="dishName-input"
              v-model="dish.dishName"
              required
            />
          </div>
          <div class="Servings">
            <label for="servings" id="servingsLabel">How Many Servings?</label
            ><br />
            <input
              type="number"
              name="servings"
              id="servings-input"
              v-model="dish.servings"
            />
          </div>
          <div class="dishDescription">
            <label for="dishDescription" id="dishDescriptionLabel"
              >Anything Else to Add?</label
            ><br />
            <textarea
              name="dishDescription"
              id="dishDescription-input"
              placeholder="Add details! List ingredients! Share your recipe!"
              rows="3"
              v-model="dish.dishDescription"
            />
          </div>
        </div>
      </div>
      <div class="restrictions">
        <h3 class="dietary-restrictions">This dish will be:</h3>
        <ul class="dietary-restriction-list">
          <li
            v-for="restriction in dish.dishRestrictions"
            :key="restriction.id"
          >
            <div class="restrictionBorder">
              <span
                :id="restriction.abbreviation + '-icon'"
                class="dietary-restriction-icon"
                >{{ restriction.abbreviation }}</span
              >
              <span class="dietary-restriction-name">{{
                restriction.name
              }}</span>
            </div>
            <input
              type="checkbox"
              :value="restriction.id"
              v-model="restriction.active"
            />
          </li>
        </ul>
      </div>
      <div class="buttonGrid">
        <input type="submit" id="submitButton" value="Save Changes" />
        <input type="reset" id="cancelButton" value="Discard Changes" />
      </div>
    </form>
  </div>
</template>

<script>
import dishService from "../services/DishService";
import dietaryRestrictionsService from "../services/DietaryRestrictionsService";
export default {
  props: ["catID"],
  name: "new-dish-form",
  data() {
    return {
      dish: {
        dishName: "",
        servings: null,
        dishDescription: "",
        dishCatId: this.$store.state.activeDish.dishCatId,
        dishPluckId: this.$route.params.id,
        dishUserId: this.$store.state.user.id,
      },
      username: this.$store.state.user.email,
      catName: this.$route.params.catName,
      dishRestrictions: [],
    };
  },
  created() {
    dietaryRestrictionsService.getRestrictionsList().then((r) => {
      this.dishRestrictions = r.data;
    });
  },
  methods: {
    saveDish() {
      dishService.addDish(this.dish);
      console.log("saveDish");
      this.$router.push({
        name: "potluck-dishes",
        params: { id: this.$route.params.id },
      });
    },
    cancelChanges() {
      console.log("cancelChanges");
    },
  },
  computed: {},
};
</script>

<style scoped>
.dish-info {
  grid-area: ga-left;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-items: right;
  padding-right: 50px;
}

.newDishForm {
  display: flex;
  align-items: center;
  justify-content: center;
}

.leftSide {
  margin-top: 0%;
  text-indent: 10px;
}

#username-input {
  border-radius: 10px;
  background-color: white;
  height: 40px;
  width: 225px;
  border: none;
  border-radius: 20px;
  margin: 5px;
}

input {
  padding: 0 10px 0 10px;
}

#dishName-input,
#dishUserName-input {
  border-radius: 10px;
  background-color: white;
  height: 40px;
  width: 225px;
  border: none;
  border-radius: 20px;
  margin: 5px;
}
#servings-input {
  border-radius: 10px;
  background-color: white;
  height: 40px;
  width: 225px;
  border: none;
  border-radius: 20px;
  margin: 5px;
}
#dishDescription-input {
  border-radius: 10px;
  background-color: white;
  height: 175px;
  width: 225px;
  border: none;
  border-radius: 20px;
  margin: 5px;
  padding: 10px;
}
.restrictions {
  grid-area: ga-Restrictions;
  padding-left: 50px;
}
ul {
  padding-left: 0%;
  padding-top: 0%;
}

li {
  list-style: none;
  display: flex;
}

.restrictionBorder {
  background-color: white;
  border-radius: 15px;
  width: 150px;
  height: 35px;
  margin: 5px;
  display: flex;
  align-items: flex-start;
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
.buttonGrid {
  grid-area: ga-buttonGrid;
  display: flex;
  align-items: center;
  justify-content: center;
}
#submitButton {
  background-color: #9dcd5a;
  border-radius: 15px;
  border-style: none;
  width: 10em;
  height: 3em;
  margin: 0.25em;
  color: white;
  font-weight: 900;
}
#cancelButton {
  background-color: #f58634;
  border-radius: 15px;
  border-style: none;
  width: 10em;
  height: 3em;
  margin: 0.25em;
  color: white;
  font-weight: 900;
}

@media only screen and (max-width: 768px) {
  .newDishForm {
    display: grid;
    grid-template-columns: 1fr;
    grid-template-areas:
      "ga-left"
      "ga-Restrictions"
      "ga-buttonGrid";
    justify-content: center;
    align-items: center;
  }
  .restrictions {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
}

@media only screen and (min-width: 768px) {
  .newDishForm {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-areas:
      "ga-left ga-Restrictions"
      "ga-buttonGrid ga-buttonGrid";
    justify-content: center;
    align-items: center;
  }
  .buttonGrid {
    grid-area: ga-buttonGrid;
    display: flex;
    justify-content: center;
    padding-bottom: 25px;
  }
}
</style>