import React, {useState, useEffect} from "react";
import Pagination from "../Pagination/pagination";
import ActorDetails from "../ActorDetails/ActorDetails";
import {useHistory} from "react-router";
import {categories} from "../../constants/constants";
import {Link} from "react-router-dom";

const ActorsPagination = (props) => {
    const [showDetails, setShowDetails] = useState(false);
    const [actors, setActors] = useState(props.actors.slice(0, 8));
    const [currActor, setCurrActor] = useState();
    const history = useHistory();
    const [pagination, setPagination] = useState({
        page: 0,
        pageSize: 8,
        totalPages: props.actors ? props.actors.length / 10 : 0,
    });

    const getNewPage = (e) => {
        setActors(props.actors.slice(e.selected * pagination.pageSize, e.selected * pagination.pageSize + pagination.pageSize))
        setPagination({
            page: e.selected,
            pageSize: pagination.pageSize,
            totalPages: pagination.totalPages
        });
    }

    const fetchDataForActor = () => {
        props.getActorDetails(currActor.uri);
        props.setCategory(categories.actors);
        history.push("/actors");
    }

    if (showDetails) return <div id={"pdis"} className={"row mb-5"}>

        <ActorDetails actor={currActor}/>
        <a onClick={() => setShowDetails(false)}
           className={"btn btn-ws w-75 ml-auto mr-auto"}
           href={"#sp"}>Back</a>
        <Link onClick={() => fetchDataForActor()} to={"/actors"}
              className={"mt-3 w-75 ml-auto mr-auto btn btn-outline-ws"}>
            See all about {currActor.name ? currActor.name : "this actor"}</Link>
        <hr className={"w-75 ml-auto mr-auto mt-5"}/>
    </div>

    return <div className={"row mb-5 mt-3"}>

        {actors.map(p => {
            return <div className={"col-sm-6 col-md-4 col-lg-3 mb-5"}>
                <div className="card w-75 ml-auto mr-auto" style={{minHeight: "500px"}}>
                    <img src={p.thumbnail ? p.thumbnail : "../images/user.png"}
                         className="card-img-top"
                         alt="..."
                         style={{width: "100%", height: "150px"}}/>
                    <div className="card-body">
                        {p.name && <h5 className="card-title">{p.name}</h5>}
                        {!p.name && p.fullName && <h5>{p.fullName}</h5>}
                        <p className="card-text text-dark">{p.description && p.description.substr(0, 120)}</p>
                    </div>
                    <div className={"card-footer"}>
                        <a onClick={() => {
                            setCurrActor(p);
                            setShowDetails(true);
                        }}
                           href={"#pdis"}
                           className="btn btn-outline-ws">Read more</a>
                    </div>
                </div>
            </div>
        })}
        {pagination.totalPages > 1 && <Pagination
            page={pagination.page}
            totalPages={pagination.totalPages}
            getNewPage={getNewPage}
            showDetails={getNewPage}
            message="Нема актери"/>
        }
    </div>
}

export default ActorsPagination;
