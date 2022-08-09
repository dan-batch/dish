<template>
  <div id="login" class="text-center">
    <div class="header-img">
      <img src="../assets/Dish_Logo_Cropped.png" alt="dish logo" />
    </div>
    <div class="form-align">
      <form class="form-signin" @submit.prevent="login">
        <h1 class="h3 mb-3 font-weight-normal">
          Sign in to access your account
        </h1>
        <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
          Invalid email and password!
        </div>
        <div
          class="alert alert-success"
          role="alert"
          v-if="this.$route.query.registration"
        >
          Thank you for registering, please sign in.
        </div>
        <div class="label-text">
          <label for="email" class="sr-only">Email Address</label> <br />
        </div>
        <input
          type="text"
          id="email"
          class="form-control"
          placeholder="Email Address"
          v-model="user.email"
          required
          autofocus
        />
        <br />
        <div class="label-text">
          <label for="password" class="sr-only">Password</label> <br />
        </div>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <br />
        <div class="rtr-link">
          <router-link :to="{ name: 'register' }">Need an account?</router-link>
        </div>
        <div class="btn-ctr">
          <button class="submit-btn" type="submit">Log In</button>
        </div>
        <div class="btn-ctr">
          <button
            class="cancel-btn"
            v-on:click.prevent="resetForm"
            type="cancel"
          >
            Never Mind!
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        email: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
    resetForm() {
      this.user = {};
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400&display=swap");

img {
  max-width: 150px;
}

.header-img {
  display: flex;
  justify-content: center;
  align-items: center;
}

.form-align {
  display: flex;
  justify-content: center;
}

.rtr-link,
.btn-ctr {
  display: flex;
  justify-content: center;
}

.label-text {
  text-indent: 15px;
}

.submit-btn {
  border: none;
  border-radius: 16px;
  font-weight: 600;
  font-size: 18px;
  color: white;
  background-color: #9dcd5a;
  height: 32px;
  width: 125px;
}

.cancel-btn {
  border: none;
  border-radius: 16px;
  font-weight: 600;
  font-size: 18px;
  color: white;
  background-color: #f58634;
  height: 32px;
  width: 125px;
}
</style>