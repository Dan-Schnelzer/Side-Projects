import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import NewCard from '../views/NewCard.vue'
import MyDecks from '../views/MyDecks.vue'
import EditCard from '../views/EditCard.vue'
import DeckDetails from '../views/DeckDetails.vue'
import StudySession from '../views/StudySession.vue'
import SessionResults from '../views/SessionResults.vue'
import NewDeck from '../views/NewDeck.vue'
import AddCard from '../views/AddCard.vue'
import EditDeck from '../views/EditDeck.vue'
import DecksWithCard from '../views/DecksWithCard.vue'
import NewCardAddToDeck from '../views/NewCardAddToDeck.vue'
import PublicDecks from '../views/PublicDecks.vue'
import ReviewDecks from '../views/ReviewDecks.vue'
import AboutUs from '../views/AboutUs.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/new-card",
      name: "new-card",
      component: NewCard,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/my-decks",
      name: "my-decks",
      component: MyDecks,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/public-decks",
      name: "public-decks",
      component: PublicDecks,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/review-decks",
      name: "review-decks",
      component: ReviewDecks,
      meta: {
        requiresAuth: true,
        adminOnly: true
      }
    },
    {
      path: "/new-deck",
      name: "new-deck",
      component: NewDeck,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/decks/:id",
      name: "deck-details",
      component: DeckDetails,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/edit-deck/:id",
      name: "edit-deck",
      component: EditDeck,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/edit-card/:id",
      name: "edit-card",
      component: EditCard,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/study-session/:deckId",
      name: "study-session",
      component: StudySession,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/results",
      name: "results",
      component: SessionResults,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/decks/:id/add-card",
      name: "add-card",
      component: AddCard,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/decks/:id/new-card",
      name: "new-card-to-deck",
      component: NewCardAddToDeck,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/cards/:id/decks",
      name: "decks-with-card",
      component: DecksWithCard,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/about-us",
      name: "about-us",
      component: AboutUs,
      meta: {
        requiresAuth: false
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  const adminOnly = to.matched.some(x => x.meta.adminOnly);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }

  if (adminOnly && store.state.user.authorities[0].name != "ROLE_ADMIN") {
    next("/"); 
  } else {
    next();
  }
});

export default router;
