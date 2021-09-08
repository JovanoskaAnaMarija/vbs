import apiUtils from "../utils/apiUtils";

const supportingActressService = {


    getBestSupActress: () => {
        return apiUtils.get(`/api/winners/bestSupActress`);
    }

};

export default supportingActressService;
