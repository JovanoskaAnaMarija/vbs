import React, {useEffect, useState} from "react";
import ActorsService from "../../service/actorsService";

const Actors = () => {

    const [currActor, setCurrActor] = useState([]);
    useEffect(() => {
        ActorsService.getActor("Meryl")
            .then(response => {
                setCurrActor(response.data);
            }).catch(err => {
            console.log("Error in Actors component!");
        });
    }, []);


    return (
        <h1 className={"text-primary"}>  Actors component</h1>
    )
};

export default Actors;
