import axios from "axios";

export default {
    getDish(id) {
        return axios.get(`/dish/dish${id}`)
    },

    updateDish(id, updatedDish) {
        return axios.put(`/dish/dish${id}`, updatedDish);
    },
}