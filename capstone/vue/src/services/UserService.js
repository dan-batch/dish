import axios from 'axios';

export default {

    getUser(id) {
        return axios.get(`/user/${id}`)
    },

    updateUser(id, updatedUser) {
        return axios.put(`/user/${id}`, updatedUser);
    },

}
