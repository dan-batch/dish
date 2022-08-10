import axios from 'axios';

export default {

    getUser(id) {
        return axios.get(`/users/${id}`)
    },

    updateUser(id, updatedUser) {
        return axios.put(`/users/${id}`, updatedUser);
    },

}
