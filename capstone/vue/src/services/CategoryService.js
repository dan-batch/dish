import axios from 'axios';

export default {

    getAllCategories() {
        return axios.get('/cat');
    },

    getPluckCategories(pluckID) {
        return axios.get(`/cat/pluck${pluckID}`)
    }

}
