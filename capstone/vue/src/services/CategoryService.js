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

    getAllCategories() {
        return axios.get('/cat');
    },

    getPluckCategories(pluckID) {
        return axios.get(`/cat/pluck${pluckID}`)
    }

}
