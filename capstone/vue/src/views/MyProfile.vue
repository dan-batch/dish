<template>
  <div class="my-profile">
    <div class="header-img">
      <img src="../assets/Dish_Logo_Cropped.png" alt="dish logo" />
    </div>
    <nav></nav>
    <h2>My Profile</h2>
    <form
      v-on:submit.prevent="saveProfileChanges()"
      v-on:reset.prevent="cancelProfileChanges()"
    >
      <h3 class="personal-info">Personal Info:</h3>
      <div>
        <img :src="this.$store.state.user.imageURL" alt="profile pic" />
      </div>
      <div class="urlEntry">
        <label for="imageURL" class="urlLabel">Image URL:</label>
        <input
          type="text"
          name="imageURL"
          id="imageURL"
          class="urlInput"
          v-model="userImageURL"
        />
      </div>
      <div class="emailEntry">
        <label for="email" id="email-label">E-mail address:</label>
        <input type="email" name="email" id="email" v-model="userEmail" />
      </div>
      <h3 class="dietary-restrictions">My Dietary Restrictions:</h3>
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
      <div class="buttonGrid">
        <input type="submit" id="submitButton" value="Save Changes" />
        <input type="reset" id="cancelButton" value="Never Mind!" />
      </div>
    </form>
  </div>
</template>

<script>
import userService from "../services/UserService";
import dietaryRestrictionsService from "../services/DietaryRestrictionsService";
export default {
  name: "my-profile",
  data() {
    return {
      userEmail: this.$store.state.user.email,
      userImageURL: this.$store.state.user.imageURL,
      dietaryRestrictions: this.$store.state.dietaryRestrictions,
      selectedRestrictions: this.selectRestrictions(),
    };
  },

  methods: {
    saveProfileChanges() {
      let userID = this.$store.state.user.id;
      const updatedUser = {
        id: this.userID,
        email: this.userEmail,
        imageURL: this.userImageURL,
      };
      userService
        .updateUser(userID, updatedUser)
        .then((response) => {
          if (response.status === 200) {
            this.saveDietaryChanges();
          }
        })
        .catch((e) => {
          alert(e.message);
        });
      // this.saveDietaryChanges();
      this.$store.commit(
        "UPDATE_DIETARY_RESTRICTIONS",
        this.selectedRestrictions
      );
      this.$store.commit("UPDATE_USER_EMAIL", this.userEmail);
      console.log(this.$store.state.user.email);
      this.$store.commit("UPDATE_USER_IMAGE_URL", this.userImageURL);
      console.log(this.$store.state.user.imageURL);
    },
    saveDietaryChanges() {
      let userID = this.$store.state.user.id;
      dietaryRestrictionsService
        .updateForUser(userID, this.selectedRestrictions)
        .then((r) => {
          alert(r.statusText + "SUCCESSFUL update to profile.");
        })
        .catch((e) => {
          alert(e.message);
        });
    },
    cancelProfileChanges() {
      this.userEmail = this.$store.state.user.email;
      this.userImageURL = this.$store.state.user.imageURL;
      this.selectedRestrictions = this.selectRestrictions();
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
img {
  max-width: 150px;
}

.header-img {
  grid-area: ga-logo;
  display: flex;
  justify-content: center;
  align-items: center;
}

h2 {
  width: 100%;
  text-align: center;
  font-size: 35px;
}

h2 {
  grid-area: ga-title;
}

form {
  grid-area: ga-formGrid;
  padding-left: 50px;
  padding-right: 50px;
  align-items: center;
}

.personal-info {
  grid-area: ga-infoTitle;
}

.profile-pic {
  grid-area: ga-profilePic;
  display: flex;
  justify-content: center;
  align-items: center;
}

.urlEntry {
  grid-area: ga-urlEntry;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.urlInput {
  border-radius: 10px;
  background-color: white;
  height: 40px;
  width: 500px;
  border: none;
  border-radius: 20px;
  margin: 5px;
}

.urlLabel {
  text-indent: 15px;
}

#email {
  border-radius: 10px;
  background-color: white;
  height: 40px;
  width: 500px;
  border: none;
  border-radius: 20px;
  margin: 5px;
}

#email-label {
  text-indent: 15px;
}

.emailEntry {
  grid-area: ga-emailEntry;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

input {
  text-indent: 10px;
}

.dietary-restrictions {
  grid-area: ga-restrictionsTitle;
}

ul {
  grid-area: ga-list;
  padding-left: 0%;
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
  background-color: white;
  border: 1px solid black;
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
  margin-bottom: 50px;
}

#submitButton {
  padding-left: 0%;
  grid-area: ga-submit;
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

#cancelButton {
  grid-area: ga-cancel;
  padding-left: 0%;
  border-radius: 16px;
  border-style: none;
  font-weight: 600;
  font-size: 18px;
  color: white;
  height: 32px;
  width: 125px;
  background-color: #f58634;
}

.my-profile {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
    "ga-logo      ga-logo"
    " ga-title    ga-title"
    "ga-formGrid  ga-formGrid";
  column-gap: 50px;
}

form {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
    "ga-infoTitle   ga-restrictionsTitle"
    "ga-profilePic  ga-list"
    "ga-urlEntry    ga-list"
    "ga-emailEntry  ga-list"
    "ga-buttonGrid ga-buttonGrid";
  justify-items: center;
}

.buttonGrid {
  display: grid;
  grid-template-columns: 3fr 1fr 1fr 3fr;
  column-gap: 30px;
  grid-template-areas: ". ga-submit  ga-cancel  .";
}

img {
  max-width: 150px;
}
</style>

<style>
#df-icon {
  background-color: #faef36;
  color: #6b3e0a;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  border: none;
  margin-left: 5px;
  text-align: center;
  margin-top: 6px;
  font-weight: bold;
}

#gf-icon {
  background-color: #75aa00;
  color: #6b3e0a;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  border: none;
  margin-left: 5px;
  text-align: center;
  margin-top: 6px;
  font-weight: bold;
}

#hl-icon {
  background-color: #d44f02;
  color: #6b3e0a;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  border: none;
  margin-left: 5px;
  text-align: center;
  margin-top: 6px;
  font-weight: bold;
}

#k-icon {
  background-color: #baeffa;
  color: #6b3e0a;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  border: none;
  margin-left: 5px;
  text-align: center;
  margin-top: 6px;
  font-weight: bold;
}

#lc-icon {
  background-color: #ffbd59;
  color: #6b3e0a;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  border: none;
  margin-left: 5px;
  text-align: center;
  margin-top: 6px;
  font-weight: bold;
}

#nf-icon {
  background-color: #e86201;
  color: #6b3e0a;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  border: none;
  margin-left: 5px;
  text-align: center;
  margin-top: 6px;
  font-weight: bold;
}

#vn-icon {
  background-color: #7ed957;
  color: #6b3e0a;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  border: none;
  margin-left: 5px;
  text-align: center;
  margin-top: 6px;
  font-weight: bold;
}

#v-icon {
  background-color: #498a0c;
  color: #6b3e0a;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  border: none;
  margin-left: 5px;
  text-align: center;
  margin-top: 6px;
  font-weight: bold;
}
</style>