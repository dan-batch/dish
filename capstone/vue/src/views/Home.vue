<template>
  <div class="home">
    <div class="body">
      <div class="header-img">
        <img
          src="../assets/Dish_Logo_Cropped.png"
          class="logo"
          alt="dish logo"
        />
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
@media only screen and (max-width: 767px) {
  .logo {
    height: 10em;
  }

  .header-img {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: transparent;
  }

  .well-container {
    display: flex;
    background-color: transparent;
    flex-direction: column;
    align-items: center;
    flex-grow: 1;
  }

  .well {
    background-color: white;
    border-radius: 15px;
    width: 20em;
    height: 3em;
    margin: 0.25em;
    display: flex;
    align-items: center;
    text-decoration: none;
    font-weight: 900;
  }

  .body {
    height: 90vh;
  }

  .bullet {
    height: 2em;
    width: 2em;
    margin-left: 5px;
  }
}

@media only screen and (min-width: 768) and (max-width: 1199px) {
  .logo {
    display: none;
  }

  .header-img {
    display: none;
    justify-content: center;
    align-items: center;
    background-color: transparent;
  }

  .well-container {
    display: flex;
    background-color: transparent;
    flex-direction: column;
    align-items: flex-end;
    flex-grow: 1;
    margin: 100px;
    margin-right: 150px;
  }

  .well {
    background-color: white;
    border-radius: 15px;
    width: 450px;
    height: 100px;
    margin: 5px;
    display: flex;
    align-items: center;
    text-decoration: none;
    font-weight: 900;
    font-size: 28px;
    text-shadow: 0px 1px, 1px 0px, 1px 1px;
  }

  .body {
    background-color: transparent;
    background-size: cover;
    background-position: center;
    background-attachment: fixed;
    display: flex;
    flex-direction: column;
    height: 90vh;
  }

  .bullet {
    height: 5px;
    width: 35px;
    margin-left: 5px;
  }
}

@media only screen and (min-width: 1200px) {
  .logo {
    height: 150px;
    display: none;
  }

  .header-img {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: transparent;
    /* grid-area: logo; */
  }

  .well-container {
    display: flex;
    background-color: transparent;
    flex-direction: column;
    align-items: flex-end;
    flex-grow: 1;
    margin: 17em;
    margin-right: 2em;
    grid-area: links;
  }

  .well {
    background-color: white;
    border-radius: 15px;
    width: 17em;
    height: 2em;
    margin: 5px;
    display: flex;
    align-items: center;
    text-decoration: none;
    font-weight: 900;
    font-size: 28px;
    text-shadow: 0px 1px, 1px 0px, 1px 1px;
  }

  .body {
    background-image: url("../assets/Home_Page_Background.png");
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    background-attachment: fixed;
    /* display: grid;
    grid-template-columns: 2fr 1fr;
    grid-template-areas:
      "empty  logo   empty"
      "empty  empty  links";
    gap: 10px; */
  }

  .bullet {
    height: 35px;
    width: 35px;
    margin-left: 5px;
  }
}
</style>