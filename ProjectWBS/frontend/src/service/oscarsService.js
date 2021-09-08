import apiUtils from "../utils/apiUtils";

const oscarsService = {



    getOscarsInfo: () => {
        return apiUtils.get(`/api/oscars/all`);
    }

};

export default oscarsService;
