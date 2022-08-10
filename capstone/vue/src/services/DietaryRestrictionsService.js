import axios from 'axios';

export default {

    list() {
        return axios.get('/dietaryRestrictions');
    },

    //getForUser returns array of objects with (restriction ID/boolean) pairs to determine if the restriction is active or inactive for this user
    getForUser(id) {
        return axios.get(`/users/${id}/dietaryRestrictions`)
    },

    updateForUser(id, restrictions) {
        return axios.put(`/users/${id}/dietaryRestrictions`, restrictions);
    },

}
