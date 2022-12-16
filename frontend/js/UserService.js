const USER_API_BASE_URL = "http://localhost:8080/users"

function getUsers(){
    return axios.get(USER_API_BASE_URL);
}
function createUser(userInfo){
    return axios.post(USER_API_BASE_URL, userInfo);
}