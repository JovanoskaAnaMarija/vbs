export const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080';
export const ACCESS_TOKEN = 'accessToken';
// export const AUTHORIZATION_HEADER = 'Authorization';
export const categories = {actors: "Actors", movies:  "Movies", awards:"Academy Award"};

export const awards = [
    {award: "Best Actors", URI: "Best Actor Academy Award winners", imageName: "23.jpg"},
    {award: "Best Actresses", URI: "Best Actress Academy Award winners", imageName: "23.jpg"},
    {award: "Best Directors", URI: "Best Directing Academy Award winners", imageName: "23.jpg"},
    // {award: "Best Picture", URI: "Best Picture Academy Award winners", imageName: "23.jpg"},
    // {award: "Best Original Screenplay", URI: "Films whose writer won the Best Original Screenplay Academy Award", imageName: "23.jpg"},
    {award: "Best Supporting Actor", URI: "Best Supporting Actor Academy Award winners", imageName: "23.jpg"},
    {award: "Best Supporting Actress", URI: "Best Supporting Actress Academy Award winners", imageName: "23.jpg"},
];
