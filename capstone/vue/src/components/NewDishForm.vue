<template>
  <form v-on:submit.prevent="submitForm" class="newDishForm">
    <div class="wholeDamnThing">
      <div class="leftSide">
        <div class="DishUserName">
          <label for="dishUserName" id="dishUserNameLabel"
            >Tell 'em who's bringing this dish!</label
          ><br />
          <input
            type="text"
            name="dishUserName"
            id="dishUserName-input"
            required
          />
        </div>
        <div class="DishName">
          <label for="dishName" id="dishNameLabel">Name Your Dish:</label><br />
          <input type="text" name="dishName" id="dishName-input" required />
        </div>
        <div class="Servings">
          <label for="servings" id="servingsLabel">How Many Servings?</label
          ><br />
          <input type="text" name="servings" id="servings-input" />
        </div>
        <div class="dishDescription">
          <label for="dishDescription" id="dishDescriptionLabel"
            >Anything Else to Add?</label
          ><br />
          <textarea
            name="dishDescription"
            id="dishDescription-input"
            placeholder=" Add details!
   List ingredients!
   Share your recipe!"
            rows="3"
          />
        </div>
      </div>
    </div>
    <div class="restrictions">
      <h3 class="dietary-restrictions">This dish will be:</h3>
      <ul class="dietary-restriction-list">
        <li v-for="restriction in dietaryRestrictions" :key="restriction.id">
          <div class="restrictionBorder">
            <span
              :id="restriction.abbreviation + '-icon'"
              class="dietary-restriction-icon"
              >{{ restriction.abbreviation }}</span
            >
            <span class="dietary-restriction-name">{{ restriction.name }}</span>
          </div>
          <input
            type="checkbox"
            :value="restriction.id"
            v-model="selectedRestrictions"
          />
        </li>
      </ul>
    </div>
    <div class="buttonGrid">
      <input type="submit" id="submitButton" value="Save Changes" />
      <input type="reset" id="cancelButton" value="Discard Changes" />
    </div>
  </form>
</template>

<script>
import dishService from "../services/DishService";
import dietaryRestrictionsService from "../services/DietaryRestrictionsService";
export default {
  name: "my-profile",
  data() {
    return {
      userName: this.$store.state.dish.userName,
      dishName: this.$store.state.dish.dishName,
      servings: this.$store.state.dish.servings,
      dietaryRestrictions: this.$store.state.dietaryRestrictions,
      selectedRestrictions: this.selectRestrictions(),
      description: this.$store.state.dish.description,
    };
  },

  methods: {
    saveProfileChanges() {
      let dishID = this.$store.state.dish.id;
      let updatedDish = {
        authorities: this.$store.state.dish.authorities,
        userName: this.userName,
        id: dishID,
        dishName: this.dishName,
        servings: this.servings,
        description: this.description,
      };
      updatedDish.authorities = this.$store.state.dish.authorities;
      dishService
        .updateDish(dishID, updatedDish)
        .then((response) => {
          if (response.status === 200) {
            this.saveDietaryChanges();
          }
        })
        .catch((e) => {
          alert(e.message + " update dish");
        });
      this.$store.commit(
        "UPDATE_DIETARY_RESTRICTIONS",
        this.selectedRestrictions
      );
      this.$store.commit("SET_DISH", updatedDish);
    },
    saveDietaryChanges() {
      let dishId = this.$store.state.user.id;
      dietaryRestrictionsService
        .updateForUser(dishId, this.selectedRestrictions)
        .then((r) => {
          alert(r.statusText + "SUCCESSFUL update to profile.");
        })
        .catch((e) => {
          alert(e.message);
        });
    },
    cancelProfileChanges() {
      this.userName = this.$store.state.dish.userName;
      this.dishName = this.$store.state.dish.dishName;
      this.selectedRestrictions = this.selectRestrictions();
      this.servings = this.$store.state.dish.servings;
      this.description = this.$store.state.dish.description;
      console.log("cancelDishChanges");
    },
    selectRestrictions() {
      let selected = [];
      this.$store.state.dietaryRestrictions.forEach((r) => {
        if (r.active) {
          selected.push(r.id);
        }
      });
      return selected;
    },
  },
  computed: {},
};
</script>

<style scoped>
.wholeDamnThing {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
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
.UserName {
  grid-area: "ga-UserName";
}

#userName-input {
  border-radius: 10px;
  background-color: white;
  height: 40px;
  width: 225px;
  border: none;
  border-radius: 20px;
  margin: 5px;
}
.DishName {
  grid-area: "ga-DishName";
}

#dishName-input {
  border-radius: 10px;
  background-color: white;
  height: 40px;
  width: 225px;
  border: none;
  border-radius: 20px;
  margin: 5px;
}
.Servings {
  grid-area: "ga-Servings";
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
.Description {
  grid-area: "ga-Description";
}
#description-input {
  border-radius: 10px;
  background-color: white;
  height: 225px;
  width: 225px;
  border: none;
  border-radius: 20px;
  margin: 5px;
}
.restrictions {
  grid-area: "ga-Restrictions";
}
ul {
  grid-area: ga-list;
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
  grid-area: "ga-ButtonGrid";
  display: flex;
  align-items: center;
  justify-content: center;
}
#submitButton {
  grid-area: "ga-submit";
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
  grid-area: "ga-cancel";
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
      "ga-UserName"
      "ga-DishName"
      "ga-Description"
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
      "ga-UserName  ga-Restrictions"
      "ga-DishName  ga-Restrictions"
      "ga-Description ga-Restrictions"
      "ga-buttonGrid  ga-buttonGrid";
    justify-content: center;
    align-items: center;
  }
  .buttonGrid {
    padding-left: 600px;
    padding-bottom: 25px;
  }
}
</style>