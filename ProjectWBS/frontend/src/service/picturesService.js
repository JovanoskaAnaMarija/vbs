import apiUtils from "../utils/apiUtils";

const picturesService = {


    getBestPictures: () => {
        return apiUtils.get(`/api/winners/bestPictures`);
    }

};

export default picturesService;
