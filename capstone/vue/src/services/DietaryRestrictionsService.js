import axios from 'axios';

export default {

    list() {
        return axios.get('/dietaryRestrictions');
    },

    //getForUser returns array of objects with (restriction ID/boolean) pairs to determine if the restriction is active or inactive for this user
    getForUser(id) {
        return axios.get(`/user/${id}/restrictions`)
    },

    updateForUser(id, restrictionIDList) {
        return axios.put(`/user/${id}/restrictions`, restrictionIDList);
    },

}
