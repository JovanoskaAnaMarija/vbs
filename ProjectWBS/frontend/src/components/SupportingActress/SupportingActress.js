import React from "react";
import "./SupportingActress.module.css";
import CarouselContainer from "../Carousel/Carousel";

const SupportingActress = (props) => {

    return <>
                <h2 className={" pb-0 text-danger text-center mb-5 pt-2"}>Award for Best Supporting Actress:</h2>
        <table className="table table-bordered border-danger border-5 border-right-5 font-weight-bold">
            <thead>
            <tr>
                <th style={({fontSize: '20px'})}>Supporting Actress</th>
                <th style={({fontSize: '20px'})}>Award</th>
                <th style={({fontSize: '20px'})}>Time</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>{props.actressSupWinners.bestSupportingActress.map(m => (<p className="ml-5">{m.itemLabel}</p>))}</td>
                <td>{props.actressSupWinners.bestSupportingActress.map(k => (<p className="ml-5">{k.awardLabel}</p>))}</td>
                <td>{props.actressSupWinners.bestSupportingActress.map(t => (<p className="ml-5">{t.time}</p>))}</td>
            </tr>
            </tbody>
        </table>


    </>

};

export default SupportingActress;
