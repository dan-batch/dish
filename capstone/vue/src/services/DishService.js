import axios from "axios";

export default {
    getDish(id) {
        return axios.get(`/dish/dish${id}`)
    },

    addDish(newDish) {
        return axios.post(`/dish/create`, newDish)
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