const AUTH_API_BASE_URL = "http://localhost:8082/auth/"

function getToken(userInfo){
    return axios.post(AUTH_API_BASE_URL + "login", userInfo);
}