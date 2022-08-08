<template>
  <div id="register" class="text-center">
    <div class="center">
      <img
        class="logo-header"
        src="../assets/Dish_Logo_Cropped.png"
        alt="dish logo"
      />
    </div>
    <div class="form-align">
      <form class="form-register" @submit.prevent="register">
        <h1 class="h3 mb-3 font-weight-normal">Create Your Account!</h1>
        <div class="alert alert-danger" role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <label for="email" class="sr-only">Email Address:</label> <br />
        <input
          type="text"
          id="email"
          class="form-control"
          placeholder="Email Address"
          v-model="user.email"
          required
          autofocus
        /><br />
        <label for="password" class="sr-only">Password:</label><br />
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        /><br />
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        /><br />
        <router-link class="link-text" :to="{ name: 'login' }"
          >Already have an account?</router-link
        ><br />
        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Register
        </button>
        <button class="btn-cancel" v-on:click.prevent="resetForm" type="cancel">
          Never mind!
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        email: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
    resetForm() {
      this.user = {};
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400&display=swap");

.logo-header {
  max-width: 100px;
}

.center {
  display: flex;
  justify-content: center;
  align-items: center;
}

.font-weight-normal {
  font-family: quicksand, sans-serif;
  display: flex;
  justify-content: center;
}

.sr-only,
.form-control,
.link-text {
  font-family: quicksand, sans-serif;
}

.btn {
  font-family: quicksand, sans-serif;
  font-weight: 700;
  color: white;
  background-color: #9dcd5a;
  border-radius: 15px;
  border: none;
  height: 30px;
}

.btn-cancel {
  font-family: quicksand, sans-serif;
  font-weight: 700;
  color: white;
  background-color: #f58634;
  border-radius: 15px;
  border: none;
  height: 30px;
}

.form-control {
  border-radius: 10px;
  background-color: white;
  height: 40px;
  width: 500px;
  border: none;
  border-radius: 20px;
  margin: 20px;
}

input::placeholder {
  text-indent: 10px;
}

.form-align {
  display: flex;
  justify-content: center;
}
</style>
