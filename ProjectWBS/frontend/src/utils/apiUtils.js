import axios from "../custom-axios/axios";

const apiUtils = {
    get: (url) => {
        return axios.get(url);
    },

    post: (url, body) => {
        const data = JSON.stringify(body);
        return axios.post(url, data, {
            headers: {
                'content-type': 'application/json'
            }
        });
    },

};

export default apiUtils;
