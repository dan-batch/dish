<template>
  <div class="my-profile">
    <nav></nav>
    <h2>My Profile</h2>
    <form
      v-on:submit.prevent="saveProfileChanges()"
      v-on:reset="cancelProfileChanges()"
    >
      <h3 class="personal-info">Personal Info</h3>
      <div>
        <img src="" alt="profile pic" />
      </div>
      <label for="email" id="email-label">E-mail address:</label>
      <input
        type="email"
        name="email"
        id="email"
        v-bind:value="this.$store.state.user.email"
      />
      <h3 class="dietary-restrictions">My Dietary Restrictions</h3>
      <ul class="dietary-restriction-list">
        <li v-for="restriction in dietaryRestrictions" :key="restriction.id">
          <span class="dietary-restriction-icon">{{
            restriction.abbreviation
          }}</span>
          <span>{{ " | " + restriction.name }}</span>
          <input type="checkbox" v-model="restriction.active" />
        </li>
      </ul>
      <div class="buttonGrid">
        <input type="submit" id="submitButton" value="Save Changes" />
        <input type="reset" id="cancelButton" value="Cancel" />
      </div>
    </form>
  </div>
</template>

<script>
// import userService from "../services/UserService";
export default {
  name: "my-profile",
  data() {
    return {
      userEmail: this.$store.state.user.username,
      dietaryRestrictions: this.$store.state.dietaryRestrictions,
    };
  },
  methods: {
    saveProfileChanges() {
      //   let userID = this.$store.state.user.userID;
      //   const updatedUser = {
      //     username: this.email,
      //   };
      //   userService.updateUser(userID, updatedUser).then((response) => {
      //     if (response.status === 200) {
      //       this.$router.push("/");
      //     }
      //   });
      this.$store.commit(
        "UPDATE_DIETARY_RESTRICTIONS",
        this.dietaryRestrictions
      );
      //   this.$store.commit("UPDATE_USER_EMAIL", this.userEmail);
    },
    cancelProfileChanges() {
      //   this.$router.push("/");
      document.location.reload();
    },
  },
  computed: {},
};
</script>

<style>
h2 {
  position: fixed;
  width: 100%;
  text-align: center;
}

h2 {
  grid-area: ga-title;
}

.personal-info {
  grid-area: ga-infoTitle;
}

img {
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

.dietary-restrictions-list {
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

form {
  display: grid;
  grid-template-columns: 2fr 1fr 2fr;
  grid-template-areas:
    "ga-infoTitle   .  ga-restrictionsTitle"
    "ga-profilePic  .  ga-List"
    "ga-emailLabel  .  ga-List"
    "ga-emailInput  .  ga-List"
    ".  ga-buttonGrid .";
  align-items: center;
}

.buttonGrid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: "ga-submit  ga-cancel";
  align-items: center;
}
</style>