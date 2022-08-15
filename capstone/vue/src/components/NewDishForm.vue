<template>
  <form v-on:submit.prevent="submitForm" class="newDishForm">
    <div>
      <div>
        <label for="userName">Tell 'em who's bringing this dish!</label><br />
        <input type="text" name="userName" id="userName-input" />
      </div>
      <div>
        <label for="dishName">Name Your Dish:</label><br />
        <input type="text" name="dishName" id="dishName-input" />
      </div>
      <div>
        <label for="servings">How Many Servings?</label><br />
        <input type="text" name="servings" id="servings-input" />
      </div>
      <div>
        <label for="details">Anything Else to Add?</label><br />
        <input
          type="text"
          name="userName"
          id="userName-input"
          placeholder="Add details, list ingredients, share recipe..."
        />
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
import dietaryRestrictionsService from "../services/DietaryRestrictionsService";
export default {
  data() {
    return {
      dietaryRestrictions: this.$store.state.dietaryRestrictions,
      selectedRestrictions: this.selectRestrictions(),
    };
  },
  methods: {
    saveDietaryChanges() {
      let dishId = this.$store.state.dish.id;
      dietaryRestrictionsService
        .updateForUser(dishId, this.selectedRestrictions)
        .then((r) => {
          alert(r.statusText + "SUCCESSFUL update to profile.");
        })
        .catch((e) => {
          alert(e.message);
        });
    },
  },
};
</script>

<style lang="scss" scoped>
</style>