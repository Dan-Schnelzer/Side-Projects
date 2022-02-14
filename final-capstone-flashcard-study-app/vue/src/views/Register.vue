<template>
  <div id="register-screen">
    <div id="register-container">
      <div id="register" class="text-center">
        <form class="form-register" @submit.prevent="register">
          <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
          <div
            class="alert alert-danger"
            role="alert"
            v-if="registrationErrors"
          >
            {{ registrationErrorMsg }}
          </div>
          <div id="username-container">
            <label for="username" class="sr-only">Username</label>
            <input
              type="text"
              id="username"
              class="form-control"
              placeholder="Username"
              v-model="user.username"
              required
              autofocus
            />
          </div>
          <div id="password-container">
            <label for="password" class="sr-only">Password</label>
            <input
              type="password"
              id="password"
              class="form-control"
              placeholder="Password"
              v-model="user.password"
              required
            />
            <input
              type="password"
              id="confirmPassword"
              class="form-control"
              placeholder="Confirm Password"
              v-model="user.confirmPassword"
              required
            />
            <router-link :to="{ name: 'login' }" id="login"
              >Have an account?</router-link
            >
          </div>
          <button class="btn btn-lg btn-primary btn-block" type="submit">
            Register
          </button>
        </form>
      </div>
    </div>
    <div class="footer">
      <footer-component />
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import FooterComponent from "../components/FooterComponent.vue";

export default {
  name: "register",
  components: { FooterComponent },
  data() {
    return {
      user: {
        username: "",
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
  },
};
</script>

<style scoped>
h1 {
  text-align: center;
  font-size: 7vh;
  color: #464443;
}
label {
  padding-left: 1vh;
  font-size: 2.5vh;
  color: #464443;
}
#register-screen {
  display: flex;
  flex-direction: column;
}
div#register-container {
  width: 100%;
  height: 68vh;
  margin: 20vh 0px 0px 0px;
}
div#register {
  box-shadow: 10px 10px 5px rgba(0, 0, 0, 0.26);
  border-radius: 20px;
  border: none;
  margin: auto;
  width: 60%;
  background-image: url("../assets/lighter-blue-green-background.png");
}
#username-container {
  display: flex;
  flex-direction: column;
  gap: 5px;
}
#password-container {
  display: flex;
  flex-direction: column;
  gap: 5px;
}
input {
  padding: 1.4vh;
  border: none;
  border-radius: 10px;
}
input:focus {
  outline: none;
  border: solid 2px rgba(0, 148, 255, 255);
}
#login {
  align-self: end;
  text-decoration: none;
  font-size: 2vh;
  color: rgba(0, 148, 255, 255);
}
.login:hover {
  background-color: rgb(6, 102, 171);
}
form {
  display: flex;
  flex-direction: column;
  padding: 0px 50px;
  gap: 20px;
}
button {
  margin-bottom: 50px;
  padding: 1vh;
  color: white;
  background-color: rgba(0, 148, 255, 255);
  font-size: 2.5vh;
  border: none;
  border-radius: 10px;
}
button:hover {
  background-color: rgb(6, 102, 171);
}
</style>