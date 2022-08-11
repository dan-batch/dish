<template>
  <div class="home">
    <body>
      <div class="header-img">
        <img
          src="../assets/Dish_Logo_Cropped.png"
          alt="dish logo"
          class="logo"
        />
      </div>

      <!-- Router Link is for Demo -->
      <router-link to="/myProfile" class="well">
        View & edit your user profile
      </router-link>
      <br />
      <router-link to="/events" class="well"
        >Plan your next potluck event</router-link
      >
      <br />
      <router-link to="/upcoming_events" class="well"
        >View upcoming potluck events</router-link
      >
      <br />
      <router-link to="/past_events" class="well"
        >View past potluck events</router-link
      >
    </body>
  </div>
</template>

<script>
export default {
  name: "home",
};
</script>

<style scoped>
.logo {
  max-width: 150px;
}

.header-img {
  grid-area: ga-logo;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: transparent;
}

.well {
  background-color: white;
  border-radius: 15px;
  width: 250px;
  height: 50px;
  margin: 5px;
  display: flex;
  align-items: center;
  text-decoration: none;
  font-weight: 900;
}

body {
  background-image: url("../assets/Home_Page_Background.png");
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  background-attachment: fixed;
  height: 100hv;
}
</style>