<template>
  <div class="my-profile">
    <nav></nav>
    <h2>My Profile</h2>
    <form
      v-on:submit.prevent="saveProfileChanges()"
      v-on:reset.prevent="cancelProfileChanges()"
    >
      <h3 class="personal-info">Personal Info</h3>
      <div>
        <img :src="this.$store.state.user.imageURL" alt="profile pic" />
      </div>
      <label for="imageURL">Image URL:</label>
      <input type="text" name="imageURL" id="imageURL" v-model="userImageURL" />

      <label for="email" id="email-label">E-mail address:</label>
      <input type="email" name="email" id="email" v-model="userEmail" />
      <h3 class="dietary-restrictions">My Dietary Restrictions</h3>
      <ul class="dietary-restriction-list">
        <li v-for="restriction in dietaryRestrictions" :key="restriction.id">
          <span class="dietary-restriction-icon">{{
            restriction.abbreviation
          }}</span>
          <span>{{ " | " + restriction.name }}</span>
          <input
            type="checkbox"
            :value="restriction.id"
            v-model="selectedItems"
          />
        </li>
      </ul>
      <!-- </div> -->
      <div class="buttonGrid">
        <input type="submit" id="submitButton" value="Save Changes" />
        <input type="reset" id="cancelButton" value="Cancel" />
      </div>
    </form>
  </div>
</template>

<script>
// import userService from "../services/UserService";
// import dietaryRestrictionsService from "../services/DietaryRestrictionsService";
export default {
  name: "my-profile",
  data() {
    return {
      userEmail: this.$store.state.user.email,
      userImageURL: this.$store.state.user.imageURL,
      dietaryRestrictions: this.$store.state.dietaryRestrictions,
      selectedItems: this.selectRestrictions(),
    };
  },

  methods: {
    saveProfileChanges() {
      // let userID = this.$store.state.user.userID;
      // const updatedUser = {
      //   email: this.email,
      //   imageURL: this.imageURL,
      // };
      // userService.updateUser(userID, updatedUser).then((response) => {
      //   if (response.status === 200) {
      //     this.saveDietaryChanges();
      //   }
      // });
      this.$store.commit(
        "UPDATE_DIETARY_RESTRICTIONS",
        this.dietaryRestrictions
      );
      this.$store.commit("UPDATE_USER_EMAIL", this.userEmail);
      console.log(this.$store.state.user.email);
      this.$store.commit("UPDATE_USER_IMAGE_URL", this.userImageURL);
      console.log(this.$store.state.user.imageURL);
    },
    // saveDietaryChanges() {
    //   dietaryRestrictionService
    //     .updateForUser(userID, this.dietaryRestrictions)
    //     .then((r) => {
    //       if (r.status === 200) {
    //         alert("Successful Update to Profile.");
    //       }
    //     });
    // },
    cancelProfileChanges() {
      this.userEmail = this.$store.state.user.email;
      this.userImageURL = this.$store.state.user.imageURL;
      this.selectedItems = this.selectRestrictions();
      console.log("cancelProfileChanges");
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
h2 {
  width: 100%;
  text-align: center;
}

h2 {
  grid-area: ga-title;
}

form {
  grid-area: ga-formGrid;
}

.personal-info {
  grid-area: ga-infoTitle;
}

.profile-pic {
  grid-area: ga-profilePic;
}

#email-label {
  grid-area: ga-emailLabel;
}

#email {
  grid-area: ga-emailInput;
}

.dietary-restrictions {
  grid-area: ga-restrictionsTitle;
}

ul {
  grid-area: ga-list;
}

.buttonGrid {
  grid-area: ga-buttonGrid;
}

#submitButton {
  grid-area: ga-submit;
}

#cancelButton {
  grid-area: ga-cancel;
}

.my-profile {
  display: grid;
  grid-template-columns: 2fr 1fr 2fr;
  row-gap: 20px;
  column-gap: 50px;
  grid-template-areas:
    ".          ga-title    ."
    "ga-formGrid  ga-formGrid ga-formGrid"
    ".  ga-buttonGrid .";
  align-items: center;
}

form {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas:
    "ga-infoTitle   .  ga-restrictionsTitle"
    "ga-profilePic  .  ga-List"
    "ga-emailLabel  .  ga-List"
    "ga-emailInput  .  ga-List";
}

.buttonGrid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: "ga-submit  ga-cancel";
  align-items: center;
}
</style>