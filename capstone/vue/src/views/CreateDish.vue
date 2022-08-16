<template>
  <div>
    <h1>Edit Your Dish</h1>
    <new-dish-form></new-dish-form>
  </div>
</template>

<script>
import NewDishForm from "@/components/NewDishForm";

export default {
  components: {
    NewDishForm,
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
