<template>
  <div>
    <h1>Edit Your Dish</h1>
    <new-dish-form v-bind:cardID="parseInt($route.params.dishID)" />
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
