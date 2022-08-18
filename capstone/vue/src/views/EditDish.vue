<template>
  <div>
    <h1>Edit Dish</h1>
    <new-dish-form v-bind:dishId="parseInt($route.params.dishId)" />
  </div>
</template>

<script>
import NewDishForm from "@/components/NewDishForm.vue";

export default {
  components: {
    NewDishForm,
  },
};
</script>