<template>
  <div class="home">
    <div id="grid" class="body">
      <div class="header-img">
        <img
          src="../assets/Dish_Logo_Cropped.png"
          class="logo"
          alt="dish logo"
        />
      </div>
      <!-- Router Link is for Demo -->
      <div id="content">
        <div class="well-container">
          <router-link to="/myProfile" class="well"
            ><img src="../assets/bullet_point_arrow.png" class="bullet" />
            View & edit your user profile
          </router-link>
          <br />
          <router-link to="/createPotluck" class="well"
            ><img src="../assets/bullet_point_arrow.png" class="bullet" />Plan
            your next potluck event</router-link
          >
          <br />
          <router-link to="/pluck/user:userId" class="well"
            ><img src="../assets/bullet_point_arrow.png" class="bullet" />View
            your potlucks</router-link
          >
        </div>
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
@media only screen and (max-width: 768px) {
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

  #content {
    background-color: transparent;
    grid-area: ga-content;
  }
}

@media only screen and (min-width: 768px) {
  .logo {
    height: 20em;
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
    margin: 4em;
  }

  .well {
    background-color: white;
    border-radius: 15px;
    width: 475px;
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
    background-color: #f3fadc;
    display: flex;
    flex-direction: column;
    height: 90vh;
  }

  .bullet {
    height: 2em;
    width: 2em;
    margin-left: 5px;
  }

  #content {
    background-color: #f3fadc;
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
    margin-top: 7em;
    margin-bottom: 3em;
    margin-right: 2em;
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
  }

  .bullet {
    height: 35px;
    width: 35px;
    margin-left: 5px;
  }

  #content {
    background-color: transparent;
    grid-area: ga-content;
  }

  #grid {
    display: grid;
    grid-template-columns: 2fr 1fr 2fr;
    row-gap: 20px;
    column-gap: 50px;
    align-items: center;
    grid-template-areas:
      "ga-empty     ga-empty     ga-empty "
      "ga-empty     ga-empty     ga-content "
      "ga-empty     ga-empty     ga-empty";
  }
}
</style>