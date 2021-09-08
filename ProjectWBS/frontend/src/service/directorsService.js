import apiUtils from "../utils/apiUtils";

const directorsService = {



    getBestDirectors: () => {
        return apiUtils.get(`/api/winners/bestDirectors`);
    }

};

export default directorsService;
