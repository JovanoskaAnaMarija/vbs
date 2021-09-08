import React from "react";
import "./Act.module.css";
import CarouselContainer from "../Carousel/Carousel";
import ActorsPagination from "../Actors/ActorsPagination";

const Act = (props) => {
    return <>

                <h2 className={" pb-0 text-danger text-center mb-5 pt-2"}>Award for Best Actor:</h2>
        <table class="table table-bordered border-danger border-5 border-right-5 font-weight-bold">
            <thead>
            <tr>
                <th style={({ fontSize: '20px' })}>Actor</th>
                <th style={({ fontSize: '20px' })}>Award</th>
                <th style={({ fontSize: '20px' })}>Time</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>{props.actorWinners.bestActors.map(m =>(<p className="ml-5">{m.itemLabel}</p>))}</td>
                <td>{props.actorWinners.bestActors.map(k => (<p className="ml-5">{k.awardLabel}</p>))}</td>
                <td>{props.actorWinners.bestActors.map(t => (<p className="ml-5">{t.time}</p>))}</td>
            </tr>

            </tbody>
        </table>

    </>

};

export default Act;
