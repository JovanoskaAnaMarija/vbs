import React from "react";
import HeaderSectionTop from "./HeaderSectionTop";
import HeaderSectionSearch from "./HeaderSectionSearch";
import "./Header.css";
import {withRouter} from "react-router";
import NavbarComp from "../Navbar/NavbarComp";

const Header = (props) => {

    return (<div id={"header"} className={"opacity"}>
            <HeaderSectionTop/>
            <HeaderSectionSearch
                                 getActorDetails={props.getActorDetails}
                                 setCategory={props.setCategory}
                                 category={props.category}
                                 value={props.value}
                                 award={props.award}
                                 selectAward={props.selectAward}
                                 getMovieDetails={props.getMovieDetails}/>
        </div>
    );

}

export default withRouter(Header);
