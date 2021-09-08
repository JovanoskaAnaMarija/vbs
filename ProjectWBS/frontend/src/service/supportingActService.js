import apiUtils from "../utils/apiUtils";

const supportingActService = {



    getBestSupActors: () => {
        return apiUtils.get(`/api/winners/bestSupActors`);
    }

};

export default supportingActService;
