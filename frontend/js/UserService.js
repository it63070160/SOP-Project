const USER_API_BASE_URL = "http://localhost:8082/user-service/users"

function getUsers(){
    return axios.get(USER_API_BASE_URL);
}
function createUser(userInfo){
    return axios.post(USER_API_BASE_URL, userInfo);
}