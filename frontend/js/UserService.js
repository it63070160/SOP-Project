const USER_API_BASE_URL = "http://localhost:8082/user-service/users/"

function getUsers(){
    return axios.get(USER_API_BASE_URL);
}
function createUser(userInfo){
    return axios.post(USER_API_BASE_URL, userInfo);
}
function editUser(userInfo){
    return axios.put(USER_API_BASE_URL, userInfo);
}
function deleteUser(userId){
    return axios.delete(USER_API_BASE_URL, { data: userId });
}