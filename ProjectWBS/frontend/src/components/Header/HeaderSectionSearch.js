import React, {useState} from "react";
import "./Header.css";
import {useHistory, useLocation} from "react-router";
import {Link} from "react-router-dom";
import {categories, awards} from "../../constants/constants";

const HeaderSectionSearch = (props) => {
    const [searchActor, setSearchActor] = useState("");
    const [searchMovie, setSearchMovie] = useState("");
    const [inputValue, setInputValue] = useState("");

    let category = props.category ? props.category : categories.actors;
    const history = useHistory();

    const searchAboutCategory = (cat) => {
        if (cat === categories.actors) {
            setInputValue(searchActor);
        }
        if (cat === categories.movies) {
            setInputValue(searchMovie);
        }
        props.setCategory(cat);
        history.push("/" + cat.toLowerCase());
    }

    const setSearchString = (value) => {
        setInputValue(value);

        if (category === categories.actors) {
            setSearchActor(value);
            return;
        }
        if (category === categories.movies) {
            setSearchMovie(value);
        }
    }

    const search = (e) => {
        e.preventDefault()

        if (category === categories.actors) {
            props.getActorDetails(searchActor);
            history.push("/actors")
            return;
        }
        if (category === categories.movies) {
            props.getMovieDetails(searchMovie);
            history.push("/movies");
        }
        if (category === categories.awards) {
        }
    }

    const selectAward = (s) => {
        props.selectAward(s);
        history.push("/awards/details");
    }

    return (

        <div id={"header"} className="header_section">

            <div className="container p-2">
                <div className="containt_main w-100 ml-auto mr-auto">


            <nav className="navbar navbar-expand-lg  meni">
                <a className="navbar-brand" href="#" id="oscars">OSCARS</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                        aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul className="navbar-nav">
                        <li className="nav-item active">
                            <a className="nav-link"> <Link to={"/home"} id="home">HOME</Link>
                            <span className="sr-only">(current)</span></a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link">  <Link onClick={() => searchAboutCategory(categories.actors)} to={"/actors"} id="actors">ACTORS</Link></a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link"> <Link onClick={() => searchAboutCategory(categories.movies)} to={"/movies"} id="movies">MOVIES</Link></a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link"> <Link onClick={() => searchAboutCategory(categories.awards)} to={"/awards"} id="movies">ACADEMY AWARD</Link></a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link"> <Link to={"/about"} id="about">ABOUT</Link></a>

                        </li>
                        <li className="nav-item dropdown">
                            <a className="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id="dr">
                                WINNERS
                            </a>
                            <div className="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <a className="dropdown-item" href="#"></a>
                                <a className="dropdown-item"><Link to={"/bestActors"} id="bestActors"> BEST ACTORS</Link></a>
                                <a className="dropdown-item"><Link to={"/bestActress"} id="bestActress"> BEST ACTRESSES</Link></a>
                                <a className="dropdown-item" > <Link to={"/directors"} id="directors"> BEST DIRECTORS</Link></a>
                                <a className="dropdown-item" > <Link to={"/bestSupActors"} id="bestSupActor"> BEST SUPPORTING ACTORS</Link></a>
                                <a className="dropdown-item" > <Link to={"/bestSupActress"} id="bestSupActress"> BEST SUPPORTING ACTRESSES</Link></a>
                                <a className="dropdown-item" > <Link to={"/bestPictures"} id="bestPictures"> BEST MOVIES</Link></a>
                                <a className="dropdown-item" > <Link to={"/bestScreenplays"} id="bestScreenplays"> BEST ORIGINAL SCREENPLAYS</Link></a>

                            </div>
                        </li>
                    </ul>
                </div>



                <div className="main">

                    <form className="input-group">
                        <input
                            value={inputValue}
                            onChange={(el) => {
                                setSearchString(el.target.value);
                                console.log(el.target.value)
                            }
                            }
                            type="text"
                            className="form-control"
                            placeholder={"Search for " + category.substr(0, category.length - 1)}/>
                        <div className="input-group-append">
                            <button
                                onClick={(e) => search(e)}
                                className="btn btn-warning"
                                type="submit"
                                style={{backgroundColor: "#eeee", borderColor: "black"}}>
                                <i className="fa fa-search"/>
                            </button>
                        </div>
                    </form>

                </div>
            </nav>

        </div>
        </div>
        </div>



    );
}

export default HeaderSectionSearch;





