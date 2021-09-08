import React, {useState} from 'react';
import "./ActorDetails.css";
import {withRouter} from "react-router";
import Quotes from "./Quotes/Quotes";
import Awards from "./Awards/Awards"
import dateUtils from "../../utils/dateUtils";
import ActorMovie from "./ActorMovie/ActorMovie";

const ActorDetails = (props) => {
        const actor = props.actor;
        const [seeMore, setSeeMore] = useState(false);

        const checkActor = (p) => {
            return !(p.birthDate === null &&
                p.birthPlace === null &&
                p.comment === null &&
                p.deathDate === null &&
                p.deathPlace === null &&
                p.description === null &&
                p.fullName === null &&
                p.name === null &&
                p.yearsActive === null &&
                p.thumbnail === null);

        }


        if (actor !== null && !checkActor(actor)) {
            return (
                <div className={"blank_content"}>
                    <div className="container text-dark rounded-3 pd-container mx-auto" style={{opacity: "0.8"}}>
                        <h3 className={"p-5 text-light text-center"}>Actor not found!</h3>
                    </div>
                </div>
            )
        }

        const getDate = dateUtils.getDate;


        return (
            actor && actor.length !== 0 ?
                <div className="container text-dark rounded-3 pd-container my-3 mx-auto pb-100 pt-0" style={{opacity: "0.8"}}>
                    <div className={"text-center d-flex "}>
                        <div className={"mt-4"}>
                            <a href={actor.thumbnail} target={"_blank"}>
                                <img
                                    className=" mx-auto actor-image"
                                    style={{height: "300px", width: "300px"}}
                                    alt={actor.name}
                                    title={"Show image..."}
                                    src={actor.thumbnail ? actor.thumbnail : "/images/user.png"}
                                />
                            </a>
                            {actor.fullName &&
                            <h3 className={"text-light pb-0 text-danger"}><b className={"text-danger"}>{actor.fullName}</b></h3>}
                            {actor.name && <h4 className={"text-dark py-0 text-ws"}>({actor.name})</h4>}
                        </div>
                    </div>

                    <div className={"col-12 p-2"}>
                        {actor.yearsActive &&
                        <div className={"row"}>
                            <div className={"col-2 text-danger"} id="years">Years Active:</div>
                            <div className={"col-10"}><b>{actor.yearsActive}m</b></div>
                        </div>
                        }
                        {actor.birthPlace &&
                        <div className={"row"}>
                            <div className={"col-2 text-danger"} id="born">Born:</div>
                            <div className={"col-10"}>
                                <b>{actor.birthPlace}, {getDate(actor.birthDate).toDateString()} </b>
                                (age {new Date(Date.now()).getFullYear() - new Date(actor.birthDate).getFullYear()} years)
                            </div>
                        </div>
                        }
                        {actor.deathPlace &&
                        <div className={"row"}>
                            <div className={"col-2 text-danger"} id="death">Death:</div>
                            <div className={"col-10"}><b>{actor.deathPlace}, {getDate(actor.deathDate).toDateString()}</b>
                            </div>
                        </div>
                        }
                    </div>
                    {
                            actor.description && <div className={"col-12 p-2"}>
                                <div className={"row"}>
                                    <div className={"col-2 text-danger"} id="desc">
                                        Description:
                                    </div>
                                    <div className={"col-10"}>
                                        {actor.description}
                                    </div>
                                </div>
                            </div>

                    }

                        {actor.wins &&
                        <div className={"row"}>
                            <div className={"col-2 text-danger"} id="wins">Wins:</div>
                            <div className={"col-10"}><b>{actor.wins}m</b></div>
                        </div>
                        }

                        {actor.nominations &&
                        <div className={"row"}>
                            <div className={"col-2 text-danger"} id="nominations">Nominations:</div>
                            <div className={"col-10"}><b>{actor.nominations}m</b></div>
                        </div>
                        }

                    <hr className={"w-75  ml-auto mr-auto mt-5"}/>
                    {actor.actorQuotes && actor.actorQuotes.length !== 0&& <>
                        <Quotes quotes={actor.actorQuotes}/>
                        <hr className={"w-75 ml-auto mr-auto mt-5"}/>
                    </>
                    }

                    <hr className={"w-75 ml-auto mr-auto mt-5"} />
                    {actor.actorAw && actor.actorAw.length !== 0 && <>
                        <Awards awards={actor.actorAw}/>
                        <hr className={"w-75 ml-auto mr-auto mt-5"}/>
                    </>
                    }

                    <div className="filmovi">
                    {actor.movies && actor.movies.length !== 0 && <>
                        <h2 className={"text-danger text-center p-2"} id="film">Movies</h2>
                        {actor.movies.map(m => <ActorMovie movie={m}
                                                           getMovieDetails={props.getMovieDetails}/>)
                        }
                    </>}
                    </div>


                </div>
                :
                <div className={"blank_content"}>
                    <div className="container text-dark rounded-3 pd-container mx-auto mt-0" style={{opacity: "0.8"}}>
                        <img style={({ width: '90%'  })} src="/images/awards/actor.jpg" className="mt-0 ml-5"/>
                    </div>

                </div>
        );
    }
;

export default withRouter(ActorDetails);
