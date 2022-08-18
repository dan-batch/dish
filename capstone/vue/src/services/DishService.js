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
        return axios.get(`/dish/pluck${pluckId}`);
    },

    deleteDish(id) {
        return axios.delete(`/dish/${id}`);
    },

    addRestrictionToDish(restrictionId, dishId) {
        const newRestrictionDTO = {
            restrictionId: restrictionId,
            dishId: dishId
        };
        return axios.post('/dish/addRestriction', newRestrictionDTO);
    },

    getDishRestrictionIDs(dishID) {
        return axios.get(`/dish/${dishID}/restrictions`);
    }
}