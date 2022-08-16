<template>
  <div class="container">
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
          <div
            class="alert alert-danger"
            role="alert"
            v-if="registrationErrors"
          >
            {{ registrationErrorMsg }}
          </div>
          <div class="form-label-text">
            <label for="email" class="sr-only">Email Address:</label> <br />
          </div>
          <input
            type="text"
            id="email"
            class="form-control"
            placeholder="Email Address"
            v-model="user.email"
            required
            autofocus
          /><br />
          <div class="form-label-text">
            <label for="password" class="sr-only">Password:</label><br />
          </div>
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
          <div class="link-ctr">
            <router-link class="link-text" :to="{ name: 'login' }"
              >Already have an account?</router-link
            ><br />
          </div>
          <div class="btn-ctr">
            <button class="btn btn-lg btn-primary btn-block" type="submit">
              Register
            </button>
          </div>
          <div class="btn-ctr">
            <button
              class="btn-cancel"
              v-on:click.prevent="resetForm"
              type="cancel"
            >
              Never Mind!
            </button>
          </div>
        </form>
      </div>
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
        role: "USER",
      },
      registrationErrors: false,
      registrationErrorMsg: "Uh-oh! There were problems registering this user.",
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
      this.registrationErrorMsg =
        "Uh-oh! There were problems registering this user.";
    },
    resetForm() {
      this.user = {};
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400&display=swap");

.logo-header {
  max-width: 150px;
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
  padding: 0px;
  margin: 0px;
}

.sr-only,
.form-control,
.link-text {
  font-family: quicksand, sans-serif;
}

.btn {
  font-family: quicksand, sans-serif;
  border-radius: 16px;
  font-weight: 600;
  font-size: 18px;
  color: white;
  background-color: #9dcd5a;
  border-radius: 15px;
  border: none;
  height: 30px;
  width: 125px;
}

.btn-cancel {
  font-family: quicksand, sans-serif;
  border-radius: 16px;
  font-weight: 600;
  font-size: 18px;
  color: white;
  background-color: #f58634;
  border-radius: 15px;
  border: none;
  height: 30px;
  width: 125px;
}

.form-control {
  border-radius: 10px;
  background-color: white;
  height: 40px;
  width: 500px;
  border: none;
  border-radius: 20px;
  margin: 5px;
}

input::placeholder {
  text-indent: 10px;
}

.form-align {
  display: flex;
  justify-content: center;
}

.btn-ctr,
.link-ctr {
  display: flex;
  justify-content: center;
  margin: 10px;
}

.form-label-text {
  text-indent: 15px;
}

input {
  text-indent: 10px;
}

.alert-danger {
  text-align: center;
  color: #f58634;
}

@media screen and (max-width: 768px) {
  .container {
    display: flex;
    flex-direction: column;
  }

  form {
    display: flex;
    flex-direction: column;
    max-width: 80vw;
    justify-content: center;
  }

  .form-control {
    display: flex;
    max-width: 100%;
  }
}
</style>
