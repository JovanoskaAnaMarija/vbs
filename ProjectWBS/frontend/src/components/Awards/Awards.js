import React, {useState} from "react";
import {awards} from "../../constants/constants";
import "./Award.css";
import {useHistory} from "react-router";
const Awards = (props) => {
    const [selectedAward, setSelectedAward] = useState({award: "Choose academy award"});
    const history = useHistory();

    function selectAward(s) {
        props.selectAward(s);
        history.push("/awards/details");
    }

    return (<div id={"awards"} className={"container"}>
        {

            <div className={"row pb-100 mb-5"}>
                {
                    awards.map(award => {
                        return <div onClick={() => selectAward(award)} className={"col-sm-4 col-md-2"}>
                            <div className={"award-item bg-white my-2"}>
                                <img
                                    className={"rounded-3"}
                                    src={"images/awards/" + award.imageName}
                                    alt={"img"}
                                    width={"100px"} height={"100px"}/><br/>
                                {award.award}
                            </div>

                        </div>
                    })
                }
            </div>

        }
    </div>);
};

export default Awards;
