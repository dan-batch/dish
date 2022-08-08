import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:3001"
});

export default {

    getUser(id) {
        return http.get(`/users/${id}`)
    },

    updateUser(id, updatedUser) {
        return http.put(`/users/${id}`, updatedUser);
    },

}
