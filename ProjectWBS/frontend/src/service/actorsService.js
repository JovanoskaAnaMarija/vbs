import apiUtils from "../utils/apiUtils";

const ActorsService = {

    getActor: (name) => {
        return apiUtils.get(`/api/actors/search?actor=${name}`);
    },

    getActorByURI: (uri) => {
        return apiUtils.get(`/api/actors/search?uri=${uri}`);
    }
};

export default ActorsService;
