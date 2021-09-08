import React from "react";
import "./Directors.module.css";
import CarouselContainer from "../Carousel/Carousel";

const Directors = (props) => {

    return <>
                <h2 className={" pb-0 text-danger text-center mb-5 pt-2"}>Award for Best Director:</h2>
        <table className="table table-bordered border-danger border-5 border-right-5 font-weight-bold">
            <thead>
            <tr>
                <th style={({fontSize: '20px'})}>Director</th>
                <th style={({fontSize: '20px'})}>Award</th>
                <th style={({fontSize: '20px'})}>Time</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>{props.directorWinners.bestDirectors.map(m => (<p className="ml-5">{m.itemLabel}</p>))}</td>
                <td>{props.directorWinners.bestDirectors.map(k => (<p className="ml-5">{k.awardLabel}</p>))}</td>
                <td>{props.directorWinners.bestDirectors.map(t => (<p className="ml-5">{t.time}</p>))}</td>
            </tr>

            </tbody>
        </table>


    </>

};

export default Directors;
