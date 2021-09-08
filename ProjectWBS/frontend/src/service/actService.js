import apiUtils from "../utils/apiUtils";

const actService = {


    getBestActors: () => {
        return apiUtils.get(`/api/winners/bestActors`);
    }

};

export default actService;
