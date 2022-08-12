<template>
  <div class="home">
    <div class="body">
      <div class="header-img logo">
        <!-- <img
          src="../assets/Dish_Logo_Cropped.png"
          class="logo"
          alt="dish logo"
        /> -->
      </div>
      <!-- Router Link is for Demo -->
      <div class="well-container">
        <router-link to="/myProfile" class="well"
          ><img src="../assets/bullet_point_arrow.png" class="bullet" />
          View & edit your user profile
        </router-link>
        <br />
        <router-link to="/plan_event" class="well"
          ><img src="../assets/bullet_point_arrow.png" class="bullet" />Plan
          your next potluck event</router-link
        >
        <br />
        <router-link to="/upcoming_events" class="well"
          ><img src="../assets/bullet_point_arrow.png" class="bullet" />View
          upcoming potluck events</router-link
        >
        <br />
        <router-link to="/past_events" class="well"
          ><img src="../assets/bullet_point_arrow.png" class="bullet" />View
          past potluck events</router-link
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "home",
};
</script>

<style scoped>
.logo {
  height: 150px;
  grid-area: ga-logo;
}

.header-img {
  /* display: flex;
  justify-content: center;
  align-items: center; */
  background-color: transparent;
}

.well-container {
  display: flex;
  background-color: transparent;
  flex-direction: column;
  align-items: flex-end;
  flex-grow: 1;
}

.well {
  background-color: white;
  border-radius: 15px;
  width: 300px;
  height: 50px;
  margin: 5px;
  display: flex;
  align-items: center;
  text-decoration: none;
  font-weight: 900;
  margin-right: 100px;
  grid-area: ga-links;
}

.body {
  background-image: url("../assets/Home_Page_Background.png");
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  background-attachment: fixed;
  /* display: flex;
  flex-direction: column; */
  height: 90vh;
}

.bullet {
  height: 35px;
  width: 35px;
  margin-left: 5px;
}

@media screen and (max-width: 1024px) {
  .home {
    height: 100%;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    row-gap: 20px;
    column-gap: 35px;
    align-items: center;
    grid-template-areas:
      "ga-empty    ga-logo   ga-empty"
      "ga-empty    ga-empty  ga-links";
  }
}

@media screen and (max-width: 745px) {
  #main-grid {
    display: grid;
    grid-template-columns: 1fr;
    row-gap: 20px;
    justify-content: center;
    align-items: center;
    grid-template-areas:
      "ga-logo"
      "ga-links";
  }
}
</style>