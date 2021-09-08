import React from "react";
import "./Screenplays.module.css"
import CarouselContainer from "../Carousel/Carousel";

const Screenplays = (props) => {

    return <>

                <h2 className={" pb-0 text-danger text-center mb-5 pt-2"}>Award for Best Original Screenplay</h2>

        <table className="table table-bordered border-danger border-5 border-right-5 font-weight-bold">
            <thead>
            <tr>
                <th style={({fontSize: '20px'})}>Movie</th>

            </tr>
            </thead>
            <tbody>
            <tr>
                <td>{props.screenPlaysWinners.bestScreenPlays.map(m => (<p className="ml-5">{m.itemLabel}</p>))}</td>

            </tr>

            </tbody>
        </table>


    </>

};

export default Screenplays;
