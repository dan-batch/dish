import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:3001"
});

export default {

    list() {
        return http.get('/dietaryRestrictions');
    },

    //getForUser returns array of objects with (restriction ID/boolean) pairs to determine if the restriction is active or inactive for this user
    getForUser(id) {
        return http.get(`/users/${id}/dietaryRestrictions`)
    },

    updateForUser(id, restrictions) {
        return http.put(`/users/${id}/dietaryRestrictions`, restrictions);
    },

}
