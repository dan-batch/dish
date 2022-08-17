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
const currentDish = JSON.parse(localStorage.getItem('dish'));
const currentUserDietaryRestrictions = JSON.parse(localStorage.getItem('dietaryRestrictions'));
const currentDishDietaryRestrictions = JSON.parse(localStorage.getItem('DishDietaryRestrictions'));
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
    dish: currentDish || {
      dishUserName: 'Carly',
      dishName: 'Spinach Puffs',
      servings: 10,
      dishDescription: "Healthy and delicious!"
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
    DishDietaryRestrictions: currentDishDietaryRestrictions || [
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
    categories: defaultCategories || [],
    bannerImages: [
      {
        id: 1,
        url: "https://bakingamoment.com/wp-content/uploads/2020/01/IMG_7173-white-bread-2-500x500.jpg"
      },
      {
        id: 2,
        url: "https://images.timberland.com/is/image/timberland/10361024-ALT4?wid=500&hei=500"
      },
      {
        id: 3,
        url: "https://veenaazmanov.com/wp-content/uploads/2020/04/Pizza-Margherita14-500x500.jpg"
      },
      {
        id: 4,
        url: "https://i.ebayimg.com/images/g/IV0AAOSwb4JiLOQ5/s-l500.jpg"
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
    SET_DISH(state, dish) {
      state.dish = dish;
      localStorage.setItem('dish', JSON.stringify(dish));
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
    SET_DISH_DIETARY_RESTRICTIONS(state, DishDietaryRestrictions) {
      state.DishDietaryRestrictions = DishDietaryRestrictions;
      localStorage.setItem('DishDietaryRestrictions', JSON.stringify(DishDietaryRestrictions));
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
    UPDATE_DISH_DIETARY_RESTRICTIONS(state, selectedRestrictions) {
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
    }
  }
})
