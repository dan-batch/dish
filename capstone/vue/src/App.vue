<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }" class="homeButton"
        >Home</router-link
      >
      &nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        class="logoutButton"
        >Logout</router-link
      >
    </div>
    <router-view style="margin-bottom: 50px" />
    <footer>
      ©2022 Potluck Planner | Tech Elevator Capstone | Dan Batchelder - Robyn
      Florentz - Conor Smith - Bob Weeks
    </footer>
  </div>
</template>

<style >
</style>
