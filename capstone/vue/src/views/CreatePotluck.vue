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

<style scoped>
h1 {
  display: flex;
  flex-grow: 1;
  justify-content: center;
}
</style>