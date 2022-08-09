<template>
  <div class="my-profile">
    <nav></nav>
    <h2>My Profile</h2>
    <form v-on:submit.prevent="saveProfileChanges()">
      <h3>Personal Info</h3>
      <div>
        <img :src="this.$store.state.user.imageURL" alt="profile pic" />
        <label for="email">Image URL:</label>
        <input
          type="text"
          name="imageURL"
          id="imageURL"
          v-model="userImageURL"
        />
      </div>
      <label for="email">E-mail address:</label>
      <input type="email" name="email" id="email" v-model="userEmail" />
      <h3>My Dietary Restrictions</h3>
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
      <input type="submit" value="Save Changes" />
      <input type="button" value="Cancel" v-on:click="cancelProfileChanges()" />
    </form>
  </div>
</template>

<script>
// import userService from "../services/UserService";
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
      // };
      // userService.updateUser(userID, updatedUser).then((response) => {
      //   if (response.status === 200) {
      //     alert("Successful update made to profile.");
      //   }
      // });
      console.log(this.userEmail);
      this.$store.commit(
        "UPDATE_DIETARY_RESTRICTIONS",
        this.dietaryRestrictions
      );
      this.$store.commit("UPDATE_USER_EMAIL", this.userEmail);
      console.log(this.$store.state.user.email);
      this.$store.commit("UPDATE_USER_IMAGE_URL", this.userImageURL);
      console.log(this.$store.state.user.imageURL);
    },
    cancelProfileChanges() {
      //   this.$router.push("/");
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

<style lang="scss" scoped>
</style>