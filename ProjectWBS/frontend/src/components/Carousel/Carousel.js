import React from 'react';
import {Carousel} from 'react-bootstrap';



const CarouselContainer = () => {
    return (
        <Carousel controls={false} fade={true} pause={false}>
            <Carousel.Item interval={2000}>

                <img
                    className="d-block w-75 align-content-center "
                    src="/images/1.jpg"
                    alt="First slide"  height="550"
                    style={({ marginLeft: '200px' })}
                />
                <Carousel.Caption>
                    {/*<h3>First slide label</h3>*/}
                    {/*<p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>*/}
                </Carousel.Caption>
            </Carousel.Item >
            <Carousel.Item interval={2000}>
                <img
                    className="d-block w-75 align-content-center "
                    src="/images/2.jpg"
                    alt="Second slide"  height="550"
                    style={({ marginLeft: '200px' }) }
                />

                <Carousel.Caption>
                    {/*<h3>Second slide label</h3>*/}
                    {/*<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>*/}
                </Carousel.Caption>
            </Carousel.Item>
            <Carousel.Item interval={2000}>
                <img
                    className="d-block w-75 align-content-center "
                    src="/images/3.jpeg"
                    alt="Third slide"  height="550"
                    style={ ({ marginLeft: '200px' }) }
                />



                <Carousel.Caption>
                    {/*<h3>Third slide label</h3>*/}
                    {/*<p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>*/}
                </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item interval={2000}>
                <img
                    className="d-block w-75 align-content-center"
                    src="/images/5.jpg"
                    alt="Second slide"  height="550"
                    style={({ marginLeft: '200px' }) }
                />

                <Carousel.Caption>
                    {/*<h3>Second slide label</h3>*/}
                    {/*<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>*/}
                </Carousel.Caption>
            </Carousel.Item>
        </Carousel>
    )
}
export default CarouselContainer;
