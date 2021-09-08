import React, {useState} from 'react';
import ActorsPagination from "../Actors/ActorsPagination";
import Actors from "../Actors/Actors";
import ActorPerson from "../ActorPerson/ActorPerson";

const AwardDetails = (props) => {
    const award = props.award;

    const [seeMore, setSeeMore] = useState(false);


    if (award === null) return <div>
         Select academy award!
    </div>



    return (<div className={"container text-light row rounded-3 pd-container my-3 mx-auto"}>
        <div className={"col-12 text-center d-flex justify-content-center my-2"}>
            <div className={"mb-5"}>
                {award.label && <h2 className={"text-light pb-3 my-2"}><b
                    className={"border-bottom  text-danger"}>{award.label.toUpperCase()}</b>
                </h2>
                }
            </div>

        </div>

        <div className={"col-12"}>
            {award.actors && award.actors.length !== 0 && <>
                <ActorPerson actors={award.actors}
                                   getActorDetails={props.getActorDetails}
                                   setCategory={props.setCategory}/></>}
        </div>
    </div>);
};

export default AwardDetails;
