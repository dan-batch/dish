<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <router-view />
    <footer>
      ©2022 Potluck Planner | Tech Elevator Capstone | Dan Batchelder - Robyn
      Florentz - Conor Smith - Bob Weeks
    </footer>
  </div>
</template>
