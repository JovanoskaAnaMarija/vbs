import apiUtils from "../utils/apiUtils";

const actressService = {


    getBestActress: () => {
        return apiUtils.get(`/api/winners/bestActress`);
    }

};

export default actressService;
