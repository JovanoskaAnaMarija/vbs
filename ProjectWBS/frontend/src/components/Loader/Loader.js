import React from "react";
import "./loader.css";

const Loader = () => {

    return <div className="loader_main">
        <div className="loader"/>
        <p className={"text-center text-danger font-weight-bold mt-3"}>Loading...</p>
    </div>;
}

export default Loader;
