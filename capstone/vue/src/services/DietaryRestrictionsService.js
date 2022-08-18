import axios from 'axios';

export default {

    getRestrictionsList() {
        return axios.get('/dish/dietaryRestrictions');
    },

    getForUser(id) {
        return axios.get(`/user/${id}/restrictions`)
    },

    updateForUser(id, restrictionIDList) {
        return axios.put(`/user/${id}/restrictions`, restrictionIDList);
    },

}
