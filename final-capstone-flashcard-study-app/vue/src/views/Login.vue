<template>
  <div id="login-screen">
    <div id="login-container">
      <div id="login" class="text-center">
        <form class="form-signin" @submit.prevent="login">
          <h1 class="h3 mb-3 font-weight-normal">Sign In</h1>
          <div
            class="alert alert-danger"
            role="alert"
            v-if="invalidCredentials"
          >
            Invalid username and password!
          </div>
          <div
            class="alert alert-success"
            role="alert"
            v-if="this.$route.query.registration"
          >
            Thank you for registering, please sign in.
          </div>
          <div id="username-container">
            <label for="username" class="sr-only">Username</label>
            <input
              type="text"
              id="username"
              class="form-control"
              placeholder="Type your username"
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
              placeholder="Type your password"
              v-model="user.password"
              required
            />
            <router-link :to="{ name: 'register' }" id="register"
              >Need an account?</router-link
            >
          </div>
          <button type="submit" v-on:click.prevent="login">Sign in</button>
        </form>
      </div>
    </div>
    <div class="footer">
      <footer-component />
    </div>
  </div>
</template>

<script>
import FooterComponent from "../components/FooterComponent.vue";
import authService from "../services/AuthService";

export default {
  name: "login",
  components: { FooterComponent },
  data() {
    return {
      user: {
        username: "",
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
#login-screen {
  display: flex;
  flex-direction: column;
}
div#login-container {
  width: 100%;
  height: 68vh;
  margin: 20vh 0px 0px 0px;
}
div#login {
  box-shadow: 10px 10px 5px rgba(0, 0, 0, 0.26);
  border-radius: 20px;
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
#register {
  align-self: end;
  text-decoration: none;
  font-size: 2vh;
  color: rgba(0, 148, 255, 255);
}
.register:hover {
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