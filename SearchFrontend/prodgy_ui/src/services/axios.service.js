import axios from 'axios';
import CONSTANTS from '../constants/common.constants';
export default {
    get: async function (url, resposeType) {
        try {
            return axios({
                method: 'get',
                url:CONSTANTS.APIURL+url,
                responseType: resposeType
            }).then(function (response) {
                return response.data;
            });
        } catch(error) {
         console.log(error);

        }
    },
    post: async function (url,data, resposeType) {
        try {
            return axios({
                method: 'POST',
                url: CONSTANTS.APIURL + url,
                data: data,
                responseType: resposeType
            }).then(function (response) {
                return response;
            });
        } catch(error) {
         console.log(error);
         
        }
    }

}