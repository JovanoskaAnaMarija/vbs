import apiUtils from "../utils/apiUtils";

const movieService = {

    getMovie: (movieName) => {
        return apiUtils.get(`/api/movies/search?movie=${movieName}`);
    }

};

export default movieService;
