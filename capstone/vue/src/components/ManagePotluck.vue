<template>
  <div>
    <form
      name="pluck-mgr"
      v-on:submit.prevent="createPotluck()"
      @reset="resetCategories()"
    >
      <div id="event-details">
        <div>
          <label for="pluckName">Name your event</label><br />
          <input
            type="text"
            name="pluckName"
            id="pluckName-input"
            v-model="potluckName"
            required
          />
        </div>
        <div>
          <label for="dateTime">Date &amp; Time</label><br />
          <input
            type="datetime-local"
            name="pluckDateTime"
            id="pluckDateTime-input"
            v-model="potluckDateTime"
            required
          />
        </div>
        <div>
          <label for="pluckLocation">Event location</label><br />
          <input
            type="text"
            name="pluckLocation"
            id="pluckLocation-input"
            v-model="potluckLocation"
            required
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

        <div id="dish-requirements">
          <h4>Choose categories &amp; limits:</h4>
          <div
            v-for="category in potluckCategories"
            :key="category.catId"
            class="list"
          >
            <label class="dish-name">{{ category.catName }}</label>
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
          <input
            type="submit"
            value="Save Changes"
            id="save"
            @click="validateEventName, validateCategorySelection"
          />
          <input type="reset" value="Cancel" id="cancel" />
        </div>
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
      cat.active = true;
      cat.limit = quantity;
    },
    selectBanner(bannerID) {
      this.selectedBanner = bannerID;
      this.$forceUpdate();
      console.log(bannerID);
    },
    resetCategories() {
      this.potluckCategories.forEach((c) => {
        c.active = false;
      });
    },
    createPotluck() {
      let newPotluck = {
        pluckName: this.potluckName,
        pluckPlace: this.potluckLocation,
        pluckTime: this.potluckDateTime,
        pluckDescription: this.potluckDescription,
      };
      potluckService
        .createPotluck(newPotluck)
        .then((r) => {
          this.potluckCategories.forEach((c) => {
            let newCat = {
              pluckId: r.data,
              catId: c.catId,
              limit: c.limit,
            };
            if (c.active) {
              potluckService.addCatToPluck(newCat).then((r) => {
                console.log(r.statusText);
              });
            }
          });
          alert("Successfully created event");
          this.$router.push("/");
        })
        .catch((e) => {
          alert(e.message);
        });
    },
    validateEventName() {
      let inputs = document.form.getElementById("pluckName-input");
      for (let index = 0; index < inputs.length; ++index) {
        if (inputs[index].value == null || inputs[index].value == "") {
          alert("Don't forget to name your event!");
          return false;
        }
      }
    },
    validateCategorySelection() {
      if (!this.anyChecked) {
        alert("Please select at least one category for your event!");
      } else this.createPotluck;
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
    anyChecked() {
      return this.potluckCategories.some((c) => c.active);
    },
  },
};
</script>

<style scoped>
@media only screen and (max-width: 768px) {
  #pluckName-input {
    background-color: white;
    border-radius: 15px;
    border-style: none;
    width: 20em;
    height: 3em;
    margin: 0.25em;
    grid-area: name;
  }

  #pluckDateTime-input {
    background-color: white;
    border-radius: 15px;
    border-style: none;
    width: 20em;
    height: 3em;
    margin: 0.25em;
    grid-area: when;
  }

  #pluckLocation-input {
    background-color: white;
    border-radius: 15px;
    border-style: none;
    width: 20em;
    height: 3em;
    margin: 0.25em;
    grid-area: location;
  }

  #pluckDescription-input {
    background-color: white;
    border-radius: 15px;
    border-style: none;
    width: 20em;
    height: 3em;
    margin: 0.25em;
    grid-area: description;
  }

  #dish-requirements {
    grid-area: dishes;
  }

  .list {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .dish-name {
    display: flex;
    flex-grow: 1;
  }
  select {
    background-color: white;
    border-radius: 15px;
    border-style: none;
    height: 3em;
    width: 5em;
    margin-left: 1em;
  }

  input[type="checkbox"] {
    margin-left: 1em;
    grid-area: checkbox;
  }

  #banner-image-selector {
    text-indent: 1.25em;
    grid-area: banner;
  }

  img {
    border-radius: 15px;
    width: 3em;
    height: 3em;
    margin: 0.25em;
    border-color: black;
    border-style: solid;
    border-width: 2px;
  }
  #save-or-cancel {
    grid-area: save-or-cancel;
  }

  #save {
    background-color: #9dcd5a;
    border-radius: 15px;
    border-style: none;
    width: 10em;
    height: 3em;
    margin: 0.25em;
  }

  input[type="submit"] {
    font-weight: 900;
    color: white;
  }

  input[type="reset"] {
    font-weight: 900;
    color: white;
  }
  #cancel {
    background-color: #f58634;
    border-radius: 15px;
    border-style: none;
    width: 10em;
    height: 3em;
    margin: 0.25em;
  }

  #event-details {
    display: grid;
    grid-template-columns: 1fr;
    row-gap: 20px;
    column-gap: 50px;
    align-items: center;
    justify-items: center;
    grid-template-areas:
      "name"
      "when"
      "location"
      "description"
      "dishes"
      "banner"
      "save-or-cancel";
  }
}

@media only screen and (min-width: 768px) {
  #pluckName-input {
    background-color: white;
    border-radius: 15px;
    border-style: none;
    width: 20em;
    height: 3em;
    margin: 0.25em;
    grid-area: name;
  }

  #pluckDateTime-input {
    background-color: white;
    border-radius: 15px;
    border-style: none;
    width: 20em;
    height: 3em;
    margin: 0.25em;
    grid-area: when;
  }

  #pluckLocation-input {
    background-color: white;
    border-radius: 15px;
    border-style: none;
    width: 20em;
    height: 3em;
    margin: 0.25em;
    grid-area: location;
  }

  #pluckDescription-input {
    background-color: white;
    border-radius: 15px;
    border-style: none;
    width: 20em;
    height: 3em;
    margin: 0.25em;
    grid-area: description;
  }

  #dish-requirements {
    grid-area: dishes;
  }

  .list {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .dish-name {
    display: flex;
    flex-grow: 1;
  }
  select {
    background-color: white;
    border-radius: 15px;
    border-style: none;
    height: 3em;
    width: 5em;
    margin-left: 1em;
  }

  input[type="checkbox"] {
    margin-left: 1em;
    grid-area: checkbox;
  }

  #banner-image-selector {
    text-indent: 1.25em;
    grid-area: banner;
  }

  img {
    border-radius: 15px;
    width: 3em;
    height: 3em;
    margin: 0.25em;
    border-color: black;
    border-style: solid;
    border-width: 2px;
  }
  #save-or-cancel {
    grid-area: save-or-cancel;
  }

  #save {
    background-color: #9dcd5a;
    border-radius: 15px;
    border-style: none;
    width: 10em;
    height: 3em;
    margin: 0.25em;
  }

  input[type="submit"] {
    font-weight: 900;
    color: white;
  }

  input[type="reset"] {
    font-weight: 900;
    color: white;
  }
  #cancel {
    background-color: #f58634;
    border-radius: 15px;
    border-style: none;
    width: 10em;
    height: 3em;
    margin: 0.25em;
  }

  #event-details {
    display: grid;
    grid-template-columns: 1fr 1fr;
    row-gap: 20px;
    column-gap: 50px;
    align-items: center;
    justify-items: center;
    grid-template-areas:
      "name             dishes"
      "when             dishes"
      "location         dishes"
      "description      banner"
      "save-or-cancel   banner";
  }
}

@media only screen and (min-width: 1200px) {
  #pluckName-input {
    background-color: white;
    border-radius: 15px;
    border-style: none;
    width: 20em;
    height: 3em;
    margin: 0.25em;
    grid-area: name;
  }

  #pluckDateTime-input {
    background-color: white;
    border-radius: 15px;
    border-style: none;
    width: 20em;
    height: 3em;
    margin: 0.25em;
    grid-area: when;
  }

  #pluckLocation-input {
    background-color: white;
    border-radius: 15px;
    border-style: none;
    width: 20em;
    height: 3em;
    margin: 0.25em;
    grid-area: location;
  }

  #pluckDescription-input {
    background-color: white;
    border-radius: 15px;
    border-style: none;
    width: 20em;
    height: 3em;
    margin: 0.25em;
    grid-area: description;
  }

  #dish-requirements-grid {
    font-weight: 900;
    grid-area: dishes;
  }

  .list {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .dish-name {
    display: flex;
    flex-grow: 1;
  }
  select {
    background-color: white;
    border-radius: 15px;
    border-style: none;
    height: 3em;
    width: 5em;
    margin-left: 1em;
  }

  input[type="checkbox"] {
    margin-left: 1em;
    grid-area: checkbox;
  }

  #banner-image-selector {
    text-indent: 1.25em;
    grid-area: banner;
  }

  img {
    border-radius: 15px;
    width: 3em;
    height: 3em;
    margin: 0.25em;
    border-color: black;
    border-style: solid;
    border-width: 2px;
  }
  #save-or-cancel {
    grid-area: save-or-cancel;
  }

  #save {
    background-color: #9dcd5a;
    border-radius: 15px;
    border-style: none;
    width: 10em;
    height: 3em;
    margin: 0.25em;
  }

  input[type="submit"] {
    font-weight: 900;
    color: white;
  }

  input[type="reset"] {
    font-weight: 900;
    color: white;
  }
  #cancel {
    background-color: #f58634;
    border-radius: 15px;
    border-style: none;
    width: 10em;
    height: 3em;
    margin: 0.25em;
  }

  #event-details {
    display: grid;
    grid-template-columns: 1fr 1fr;
    row-gap: 20px;
    column-gap: 50px;
    align-items: center;
    justify-items: center;
    grid-template-areas:
      "name             dishes"
      "when             dishes"
      "location         dishes"
      "description      banner"
      "save-or-cancel   banner";
  }
}
</style>