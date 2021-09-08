import apiUtils from "../utils/apiUtils";

const screenPlaysService = {



    getBestScreenPlays: () => {
        return apiUtils.get(`/api/winners/bestScreenplays`);
    }

};

export default screenPlaysService;
