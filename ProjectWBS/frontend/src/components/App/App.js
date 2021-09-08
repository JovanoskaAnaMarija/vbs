import React from "react";
import {Redirect, Route, Switch} from "react-router";

import 'bootstrap/dist/css/bootstrap.css';
import './App.css';
import ActorDetails from "../ActorDetails/ActorDetails";
import ActorsService from "../../service/actorsService";
import Header from "../Header/Header";
import {categories, awards} from "../../constants/constants";

import movieService from "../../service/movieService";
import Loader from "../Loader/Loader";
import Home from "../Home/Home";
import Directors from "../Directors/Directors";
import About from "../About/About";
import Movies from "../Movies/Movies";
import directorsService from "../../service/directorsService";
import oscarsService from "../../service/oscarsService";
import actressService from "../../service/actressService";
import actService from "../../service/actService";
import Act from "../Act/Act";
import Actress from "../Actress/Actress";
import supportingActService from "../../service/supportingActService";
import supportingActressService from "../../service/supportingActressService";
import SupportingActor from "../SupportingActor/SupportingActor";
import SupportingActress from "../SupportingActress/SupportingActress";
import picturesService from "../../service/picturesService";
import screenPlaysService from "../../service/screenplaysService";
import Pictures from "../Pictures/Pictures";
import Screenplays from "../Screenplays/Screenplays";
import awardsService from "../../service/awardsService";
import AwardDetails from "../Awards/AwardDetails";
import Awards from "../Awards/Awards";
class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoading: false,

            currActor: [],
            currMovie: null,

            currAward:null,
            currCategory: null,
            selectedAward: {award: "Choose academy award"},
            oscarsBaseInfo: { label: "", comment: "", description: "", year:"", country:"", presenter:""},
            directorWinners:{bestDirectors: [{ item: "" }, { itemLabel: "" },{ awardLabel: "" }, { time: "" } ]},
            actorWinners:{bestActors: [{ item: "" }, { itemLabel: "" },{ awardLabel: "" }, { time: "" } ]},
            actressWinners:{bestActress: [{ item: "" }, { itemLabel: "" },{ awardLabel: "" }, { time: "" } ]},
            actorSupWinners:{bestSupportingActors: [{ item: "" }, { itemLabel: "" },{ awardLabel: "" }, { time: "" } ]},
            actressSupWinners:{bestSupportingActress: [{ item: "" }, { itemLabel: "" },{ awardLabel: "" }, { time: "" } ]},

            pictureWinners:{bestPictures: [{ item: "" }, { itemLabel: "" },{ awardLabel: "" }, { time: "" } ]},
            screenPlaysWinners:{bestScreenPlays: [{ item: "" }, { itemLabel: "" },{ awardLabel: "" }, { time: "" } ]},

        };
    }

    componentDidMount() {
        oscarsService.getOscarsInfo()
            .then(response => {
                this.setState({ oscarsBaseInfo: response.data });
            })
            .catch(err => console.log(err));

        directorsService.getBestDirectors()
            .then(response => {
                this.setState({ directorWinners: response.data });
            })
            .catch(err => console.log(err));

        actService.getBestActors()
            .then(response => {
                this.setState({ actorWinners: response.data });
            })
            .catch(err => console.log(err));
        actressService.getBestActress()
            .then(response => {
                this.setState({ actressWinners: response.data });
            })
            .catch(err => console.log(err));

        supportingActService.getBestSupActors()
            .then(response => {
                this.setState({ actorSupWinners: response.data });
            })
            .catch(err => console.log(err));

        supportingActressService.getBestSupActress()
            .then(response => {
                this.setState({ actressSupWinners: response.data });
            })
            .catch(err => console.log(err));


        picturesService.getBestPictures()
            .then(response => {
                this.setState({ picturesWinners: response.data });
            })
            .catch(err => console.log(err));

        screenPlaysService.getBestScreenPlays()
            .then(response => {
                this.setState({ screenPlaysWinners: response.data });
            })
            .catch(err => console.log(err));
    }

    getMovieDetails = (movieName) => {
        this.setState({isLoading: true, currCategory: categories.movies});
        movieService.getMovie(movieName)
            .then(response => {
                this.setState({currMovie: response.data, isLoading: false});
            }).catch(err => {
            console.log("Error while fetching movie data!");
            this.setState({isLoading: false});
        });
    }

    getActorDetails = (name) => {
        this.setState({isLoading: true, currCategory: categories.actors});
        ActorsService.getActor(name)
            .then(response => {
                this.setState({currActor: response.data, isLoading: false});
                console.log(response.data);
            }).catch(err => {
            console.log("Error in Actors component!");
            this.setState({isLoading: false, currActor: null});
        });
    }

    getActorDetailsByURI = (uri) => {
        this.setState({isLoading: true, currCategory: categories.actors});
        ActorsService.getActorByURI(uri)
            .then(response => {
                this.setState({currActor: response.data, isLoading: false});
                console.log(response.data);
                console.log(this.state.currCategory, "hereee")

            }).catch(err => {
            console.log("Error in Actors component!");
            this.setState({isLoading: false});
        });
        console.log(this.state.currCategory, "hereee")
    }
    selectAward = (award) => {
        this.setState({isLoading: true, selectedAward: award, currCategory: categories.awards});
        awardsService.getAward(award.URI)
            .then(response => {
                console.log(response.data);
                this.setState({currAward: response.data, isLoading: false});
            })
            .catch(err => {
                console.log("Err ", err.message)
                this.setState({isLoading: false});
            });
    }


    selectCategory = (cat) => {
        this.setState({currCategory: cat})
    }


    render() {

        return <div className={"h-auto"}>
            <Header
                    getActorDetails={this.getActorDetails}
                    category={this.state.currCategory}
                    setCategory={(cat) => {
                        this.setState({currCategory: cat})
                    }}
                    currActor={this.state.currActor}
                    award={this.state.selectedAward}
                    selectAward={this.selectAward}
                    getMovieDetails={this.getMovieDetails}/>
            {this.state.isLoading && <Loader/>}
            <Switch>
                <Route path={"/"} exact>
                    <Redirect to={"/home"}/>
                </Route>
                <Route path={"/home"} exact>
                    <Home oscarsBaseInfo={this.state.oscarsBaseInfo}/>
                </Route>

                <Route path={"/directors"} exact>
                    <div className={"blank_content text-center"}>
                        {this.state.isLoading || <Directors directorWinners={this.state.directorWinners}/>}
                    </div>
                </Route>
                <Route path={"/bestActors"} exact>
                    <div className={"blank_content text-center"}>
                        {this.state.isLoading  || <Act actorWinners={this.state.actorWinners}/>}
                    </div>
                </Route>

                <Route path={"/bestActress"} exact>
                    <div className={"blank_content text-center"}>
                        {this.state.isLoading || <Actress actressWinners={this.state.actressWinners}/>}
                    </div>
                </Route>

                <Route path={"/bestSupActors"} exact>
                    <div className={"blank_content text-center"}>
                        {this.state.isLoading || <SupportingActor actorSupWinners={this.state.actorSupWinners}/>}
                    </div>
                </Route>
                <Route path={"/bestSupActress"} exact>
                    <div className={"blank_content text-center"}>
                        {this.state.isLoading  ||  <SupportingActress actressSupWinners={this.state.actressSupWinners}/>}
                    </div>
                </Route>

                <Route path={"/bestPictures"} exact>
                    <div className={"blank_content text-center"}>
                        {this.state.isLoading  || <Pictures picturesWinners={this.state.picturesWinners}/>}
                    </div>
                </Route>

                <Route path={"/bestScreenplays"} exact>
                    <div className={"blank_content text-center"}>
                        {this.state.isLoading  || <Screenplays screenPlaysWinners={this.state.screenPlaysWinners}/>}
                    </div>
                </Route>


                <Route path={"/actors"} exact>
                    {this.state.isLoading || <ActorDetails actor={this.state.currActor}
                                                            getMovieDetails={this.getMovieDetails}/>}
                </Route>
                <Route path={"/movies"} exact>
                    <div className={"blank_content text-center"}>
                        {this.state.isLoading || <Movies movie={this.state.currMovie}
                                                        getActorDetails={this.getActorDetailsByURI}
                                                        setCategory={(cat) => this.setState({currCategory: cat})}/>}
                    </div>
                </Route>

                <Route path={"/awards/details"} exact>
                    {this.state.isLoading || <AwardDetails award={this.state.currAward}
                                                           getActorDetails={this.getActorDetailsByURI}
                                                           setCategory={(cat) => this.setState({currCategory: cat})}/>}
                </Route>
                <Route path={"/awards"}>
                    <div className={"blank_content text-center"}>
                        <Awards selectAward={this.selectAward}/>
                    </div>
                </Route>

                <Route path={"/about"} exact>
                    <div className={"blank_content text-center"}>
                        <About oscarsBaseInfo={this.state.oscarsBaseInfo}/>
                    </div>
                </Route>
                <Redirect to={"/home"}/>
            </Switch>

        </div>;
    }

}

export default App;
