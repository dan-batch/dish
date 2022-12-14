import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import MyProfile from '../views/MyProfile'
import CreatePotluck from '../views/CreatePotluck'
import CreateDish from '../views/CreateDish'
import PotluckDishes from '../views/PotluckDishes'
// import UserPotlucks from '../views/UserPotlucks'
import store from '../store/index'
// import DishService from '../services/DishService'
import Dish from '../views/Dish'
import EditDish from '../views/EditDish'
import GetAllPlucks from '../views/GetAllPlucks'



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
      path: '/myProfile',
      name: 'my-profile',
      component: MyProfile,
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
      path: "/createPotluck",
      name: "create-potluck",
      component: CreatePotluck,
      meta: {
        requiresAuth: false
      },
    },
    {
      path: '/dish/:dishId/edit',
      name: "EditDish",
      component: EditDish
    },
    {
      path: "/potluck/:id/createDish",
      name: "create-dish",
      component: CreateDish,
      meta: {
        requiresAuth: false
      },
    },
    {
      path: '/potluck/:id/dishes',
      name: 'potluck-dishes',
      component: PotluckDishes,
    },
    {
      path: '/potluck',
      name: 'get-all-plucks',
      component: GetAllPlucks,
    },
    {
      path: '/dish/:dishId',
      name: 'dish',
      component: Dish,
    },
    // {
    //   path: '/pluck/user:userId',
    //   name: 'potlucks-by-user',
    //   component: UserPotlucks,
    // }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
