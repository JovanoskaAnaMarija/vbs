import React, {useState} from "react";
import "./Movies.css";
import dateUtils from "../../utils/dateUtils";
import ActorsPagination from "../Actors/ActorsPagination";

const Movies = (props) => {
    const movie = props.movie;
    const getDate = dateUtils.getDate;

    if (movie === null) {
        return (
            <div className={"blank_content"}>
                <div className="container text-dark rounded-3 pd-container mx-auto mt-0" style={{opacity: "0.8"}}>
                    <img style={({ width: '90%'  })} src="/images/awards/movie.jpg" className="mt-0"/>
                </div>

            </div>
        )
    }

    return (
        <div id={"movies"} className="container text-dark rounded-3 pd-container my-3 mx-auto text-left"
             style={{opacity: "0.8"}}>
            {movie.label && <>
                <div className={"col-12 text-center d-flex justify-content-center"}>
                    <div className={"mt-4 pt-3 pb-3"}>
                        <h2 className={" pb-0  border-3 text-danger"}>{movie.label} </h2>
                    </div>
                </div>
                <hr className={"w-75 ml-auto mr-auto mt-5"}/>
            </>}
            {movie.description &&
            <div className={"row my-1"}>
                <div className={"col-2 text-danger"}>
                    Descrtiption:
                </div>
                <div className={"col-10 font-weight-bold"}>
                    {movie.description}
                </div>
            </div>
            }
            {movie.budget &&
            <div className={"row my-1"}>
                <div className={"col-2 text-danger"}>
                    Budget:
                </div>
                <div className={"col-10 font-weight-bold"}>
                    {movie.budget}
                </div>
            </div>
            }
            {movie.cinematoGraphy &&
            <div className={"row my-1"}>
                <div className={"col-2 text-danger"}>
                    Cinematography:
                </div>
                <div className={"col-10 font-weight-bold"}>
                    {movie.cinematoGraphy}
                </div>
            </div>
            }
            {movie.director &&
            <div className={"row my-1"}>
                <div className={"col-2 text-danger"}>
                    Director:
                </div>
                <div className={"col-10 font-weight-bold"}>
                    {movie.director}
                </div>
            </div>
            }
            {movie.distributor &&
            <div className={"row my-1"}>
                <div className={"col-2 text-danger"}>
                    Distributor:
                </div>
                <div className={"col-10 font-weight-bold"}>
                    {movie.distributor}
                </div>
            </div>
            }
            {movie.musicComposer &&
        <div className={"row my-1"}>
            <div className={"col-2 text-danger"}>
                Music Composer:
            </div>
            <div className={"col-10 font-weight-bold"}>
                {movie.musicComposer}
            </div>
        </div>
             }
            {movie.country &&
            <div className={"row my-1"}>
                <div className={"col-2 text-danger"}>
                    Country:
                </div>
                <div className={"col-10 font-weight-bold"}>
                    {movie.country}
                </div>
            </div>
            }
            {movie.producers &&
            <div className={"row my-1"}>
                <div className={"col-2 text-danger"}>
                    Producers:
                </div>
                <div className={"col-10 font-weight-bold"}>
                    <ul className={"p-0"}>
                        {movie.producers.map(n => {
                            return <li>{n}</li>
                        })}
                    </ul>
                </div>
            </div>
            }
            {movie.productionCompanies &&
            <div className={"row my-1"}>
                <div className={"col-2 text-danger"}>
                    Production Companies:
                </div>
                <div className={"col-10 font-weight-bold"}>
                    <ul className={"p-0"}>
                        {movie.productionCompanies.map(n => {
                            return <li>{n}</li>
                        })}
                    </ul>
                </div>
            </div>
            }
            <hr className={"w-75 ml-auto mr-auto mt-5"}/>
            {movie.actors && movie.actors.length !== 0 && <>
                <h2 id={"sp"} className={"text-danger text-center p-2"}>Actors in this movie</h2>
                <ActorsPagination actors={movie.actors}
                                   getActorDetails={props.getActorDetails}
                                   setCategory={props.setCategory}/>
            </>}
        </div>
    );
};

export default Movies;
