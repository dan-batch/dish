import axios from 'axios';

export default {

    // list() {
    //     return axios.get('/dietaryRestrictions');
    // },

    // getForUser(id) {
    //     return axios.get(`/user/${id}/restrictions`)
    // },

    // updateForUser(id, restrictionIDList) {
    //     return axios.put(`/user/${id}/restrictions`, restrictionIDList);
    // },

    createPotluck(newPotluck) {
        return axios.post('/pluck/create', newPotluck)
    },

    addCatToPluck(newCat) {
        return axios.post('pluck/addCat', newCat)
    },

    getPotluck(pluckID) {
        return axios.get(`pluck/pluck${pluckID}`)
    },

    getPluckDishes(pluckID) {
        return axios.get(`dish/pluck${pluckID}`)
    },



}
