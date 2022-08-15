<template>
  <div>
    <h1>Create Your Event</h1>
    <manage-potluck @load="getCategoryOptions"></manage-potluck>
  </div>
</template>



<script>
import ManagePotluck from "../components/ManagePotluck.vue";
export default {
  methods: {},

  components: {
    ManagePotluck,
  },
};
</script>

<style lang="scss" scoped>
</style>