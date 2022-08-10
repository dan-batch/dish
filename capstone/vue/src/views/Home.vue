<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <!-- Router Link is for Demo -->
    <router-link to="/myProfile">
      Click here to view your profile.
    </router-link>
  </div>
</template>

<script>
export default {
  name: "home",
};
</script>
