const USER_API_BASE_URL = "http://localhost:8082/user-service/users/"

function getUsers(){
    return axios.get(USER_API_BASE_URL);
}
function getUser(token){
    return axios.get('http://localhost:8082/user', {
            headers: {
                'Authorization': `${token.accessToken}` 
            }
        })
}
function getUserById(id){
    return axios.get('http://localhost:8082/user-service/userbyid?id=' + id)
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
function checkUser(loginInfo){
    return axios.get(USER_API_BASE_URL+loginInfo.username);
}