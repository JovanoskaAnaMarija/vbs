import React from "react";
import "./Awards.css";

const Awards = (props) => {
    const awards = props.awards;

    const renderAwards = () => {
        return awards.map((q, i) => {
            let activeClass = i === 0 ? "active" : "";
            return <div className={"carousel-item " + activeClass}>
                <h4 className="text-dark ml-5">{q}</h4>
            </div>;
        });
    }

    return (
        awards.length !== 0 ?
            <div className={"mt-5 mb-5"}>
                <h2 className={"text-danger text-center p-2"} id="awards">Awards</h2>
                <div id="carouselExampleControls" className="carousel slide" data-ride="carousel">
                    <div className="carousel-inner">
                        {renderAwards()}
                    </div>
                    <a className="carousel-control-prev" href="#carouselExampleControls" role="button"
                       data-slide="prev">
                        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span className="sr-only">Previous</span>
                    </a>
                    <a className="carousel-control-next" href="#carouselExampleControls" role="button"
                       data-slide="next">
                        <span className="carousel-control-next-icon" aria-hidden="true"></span>
                        <span className="sr-only">Next</span>
                    </a>
                </div>
            </div> : "");
}

export default Awards;
