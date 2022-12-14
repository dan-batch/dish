import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));
const activeDish = JSON.parse(localStorage.getItem('activeDish'));
const dish = JSON.parse(localStorage.getItem('dish'));
const currentUserDietaryRestrictions = JSON.parse(localStorage.getItem('dietaryRestrictions'));
// const currentDishDietaryRestrictions = JSON.parse(localStorage.getItem('DishDietaryRestrictions'));
const defaultCategories = JSON.parse(localStorage.getItem('categories'));



if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {
      email: 'carly@user.com',
      imageURL: 'https://media-exp1.licdn.com/dms/image/C5603AQG11wYnSQ28ug/profile-displayphoto-shrink_800_800/0/1625681037290?e=1665619200&v=beta&t=YYcwiDfDxqJeZNyfPrxqKQMP5_9yZnLeiY1LxM85JfI',
      firstName: 'Carly',
      lastName: 'Trimboli',
    },
    dietaryRestrictions: currentUserDietaryRestrictions || [
      {
        id: 2001,
        name: "Dairy-free",
        abbreviation: "df",
        active: false
      }, {
        id: 2002,
        name: "Gluten-free",
        abbreviation: "gf",
        active: false
      }, {
        id: 2003,
        name: "Halal",
        abbreviation: "hl",
        active: false
      }, {
        id: 2004,
        name: "Kosher",
        abbreviation: "k",
        active: false
      }, {
        id: 2005,
        name: "Low-carb",
        abbreviation: "lc",
        active: false
      }, {
        id: 2006,
        name: "Nut-free",
        abbreviation: "nf",
        active: false
      }, {
        id: 2007,
        name: "Vegan",
        abbreviation: "vn",
        active: false
      }, {
        id: 2008,
        name: "Vegetarian",
        abbreviation: "v",
        active: false
      },

    ],
    activeDish: activeDish || {},
    dish: dish || {},
    categories: defaultCategories || [],
    bannerImages: [
      {
        id: 1,
        url: "https://cdn.discordapp.com/attachments/965635046734168084/1009516049122725898/confetti_ribbons.png"
      },
      {
        id: 2,
        url: "https://cdn.discordapp.com/attachments/965635046734168084/1009516049747681411/pink_bubbles.png"
      },
      {
        id: 3,
        url: "https://cdn.discordapp.com/attachments/965635046734168084/1009516050318102588/pool_party.png"
      },
      {
        id: 4,
        url: "https://cdn.discordapp.com/attachments/965635046734168084/1009516050796261436/scribble_dots.png"
      },
    ]
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_USER_DIETARY_RESTRICTIONS(state, dietaryRestrictions) {
      state.dietaryRestrictions = dietaryRestrictions;
      localStorage.setItem('dietaryRestrictions', JSON.stringify(dietaryRestrictions));
    },
    UPDATE_DIETARY_RESTRICTIONS(state, selectedRestrictions) {
      state.dietaryRestrictions.forEach((r) => {
        if (selectedRestrictions.includes(r.id)) {
          r.active = true;
        } else {
          r.active = false;
        }
      });
      localStorage.setItem('dietaryRestrictions', JSON.stringify(state.dietaryRestrictions));
    },
    SET_CATEGORIES(state, categories) {
      state.categories = categories;
      localStorage.setItem('categories', JSON.stringify(state.categories));
    },
    SET_ACTIVE_DISH(state, activeDish) {
      state.activeDish = activeDish;
      localStorage.setItem('activeDish', JSON.stringify(state.activeDish));

    },
    SET_DISH(state, dish) {
      state.dish = dish;
      localStorage.setItem('dish', JSON.stringify(state.dish));
    }
  }
})
