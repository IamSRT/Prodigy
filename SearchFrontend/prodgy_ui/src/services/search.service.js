import Axios_Service from './axios.service'
export default {
    getSearchData: async function (searchQuery) {
        var response = await Axios_Service.get("/search/GetProducts?searchTerm=" + searchQuery, "application/json");
        console.log(response);
        return response;
    },
    getQuerySuggestions: async function(searchTerm){
        var response = await Axios_Service.get("/search/GetSuggestions?searchTerm=" + searchTerm, "application/json");
        return response;
    }
}