import apiUtils from "../utils/apiUtils";

const awardsService = {

    getAward: (awardURI) => {
        return apiUtils.get(`/api/awards/search?award=${awardURI}`);
    },



};

export default awardsService;
