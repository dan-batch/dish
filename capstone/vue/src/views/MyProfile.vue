<template>
  <div class="my-profile">
    <nav></nav>
    <h2>My Profile</h2>
    <form
      v-on:submit.prevent="saveProfileChanges()"
      v-on:reset="cancelProfileChanges()"
    >
      <h3>Personal Info</h3>
      <div>
        <img src="" alt="profile pic" />
      </div>
      <label for="email">E-mail address:</label>
      <input
        type="email"
        name="email"
        id="email"
        v-bind:value="this.$store.state.user.username"
      />
      <h3>My Dietary Restrictions</h3>
      <ul class="dietary-restriction-list">
        <li v-for="restriction in dietaryRestrictions" :key="restriction.id">
          <span class="dietary-restriction-icon">{{
            restriction.abbreviation
          }}</span>
          <span>{{ " | " + restriction.name }}</span>
          <input type="checkbox" v-model="restriction.active" />
        </li>
      </ul>
      <input type="submit" value="Save Changes" />
      <input type="reset" value="Cancel" />
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

<style lang="scss" scoped>
</style>