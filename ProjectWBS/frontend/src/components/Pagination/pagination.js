import React from "react";
import ReactPaginate from "react-paginate";
import "./pagination.css";

const Pagination = (props) => {


    if (props.totalPages !== 0) {
        return (
            <ReactPaginate previousLabel={"Prev"}
                           nextLabel={"Next"}
                           breakLabel={<span className="gap"> . . . </span>}
                           breakClassName={"break-me"}
                           pageCount={props.totalPages}
                           marginPagesDisplayed={2}
                           pageRangeDisplayed={5}
                           pageClassName={"page-item active-item"}
                           pageLinkClassName={"page-link"}
                           previousClassName={"page-item"}
                           nextClassName={"page-item"}
                           previousLinkClassName={"page-link"}
                           nextLinkClassName={"page-link"}
                           forcePage={props.page}
                           onPageChange={props.getNewPage}
                           containerClassName={"pagination justify-content-center"}
                           activeClassName={"pagination-selected-item "}/>
        )
    }
    return <h3 className="mt-5 text-center display-4">{props.message}</h3>;

};

export default Pagination;
