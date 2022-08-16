import axios from "axios";

export default {
    getDish(id) {
        return axios.get(`/dish/dish${id}`)
    },

    addDish() {
        return axios.post(`/dish/create`)
    },

    updateDish(id, updatedDish) {
        return axios.put(`/dish/dish${id}`, updatedDish);
    },

    getDishByPluck(pluckId) {
        return axios.get(`/dish/pluck${pluckId}`)
    },

    deleteDish(id) {
        return axios.delete(`/dish/${id}`);
    }
}