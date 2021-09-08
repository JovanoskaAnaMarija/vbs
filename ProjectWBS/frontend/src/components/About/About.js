import React from "react";
import "./About.module.css";
import CarouselContainer from "../Carousel/Carousel";

const About = (props) => {

    return <>

        <div className={""}>
            <div className={"bg-ws mb-5"}/>
            <div className={"home-container container text-dark rounded-3 py-3  mx-auto pb-100 rounded-3"}>
                {/*<h2 className={" pb-0 text-danger text-center"}>What is Academy Awards?</h2>*/}
                {/*<p>{props.oscarsBaseInfo.label}</p>*/}
                <img style={({ width: '90%'  })} src="/images/awards/about.jpg" className="mt-0  mb-3"/>
                <p style={({color:'#d39e00'})} className="font-weight-bold">ABOUT ACADEMY AWARDS</p>
                <p>{props.oscarsBaseInfo.comment}</p>
                <p style={({color:'#d39e00'})} className="font-weight-bold">DESCRIPTION</p>
                <p>{props.oscarsBaseInfo.description}</p>
                <p style={({color:'#d39e00'})} className="font-weight-bold">YEAR</p>
                <p>{props.oscarsBaseInfo.year}</p>
                <p style={({color:'#d39e00'})} className="font-weight-bold">COUNTRY</p>
                <p>{props.oscarsBaseInfo.country}</p>
                <p style={({color:'#d39e00'})} className="font-weight-bold">PRESENTER</p>
                <p>{props.oscarsBaseInfo.presenter}</p>

            </div>
        </div>


    </>

};

export default About;
