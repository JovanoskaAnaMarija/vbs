import React, {useState} from "react";
import {useHistory} from "react-router";

const ActorMovie = (props) => {
    const movie = props.movie;

    const [seeMore, setSeeMore] = useState(false);
    const history = useHistory();

    const getMovieDetails = () => {
        console.log("aaaaaaaaaaaaaaaa " + movie.uri.split("/")[movie.uri.split("/").length - 1]);
        props.getMovieDetails(movie.uri.split("/")[movie.uri.split("/").length - 1]);
        history.push("/movies");
    };

    return <div className="bg-ws-bg w-50 ml-auto mr-auto mb-4">
        <div className="card-body">
            <button className="btn btn-outline-ws btn-block"
                    onClick={() =>getMovieDetails()}>
            <h5 className="card-title ">{movie.label}</h5>
            </button>
            <p className="card-text text-dark">

            </p>

        </div>
    </div>
};

export default ActorMovie;
