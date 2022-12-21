const ORDER_API_BASE_URL = "http://localhost:8082/order-service/orders/"

function getOrders(){
    return axios.get(ORDER_API_BASE_URL);
}
function editOrder(orderInfo){
    return axios.put(ORDER_API_BASE_URL, orderInfo);
}
function deleteOrder(orderId){
    console.log(orderId.bookList)
    return axios.delete(ORDER_API_BASE_URL, {data: orderId});
}
function getUserOrders(userId){
    return axios.get(ORDER_API_BASE_URL+userId);
}
function addNewOrder(orderInfo){
    return axios.post(ORDER_API_BASE_URL, orderInfo)
}
function userGetOrders(userId){
    return axios.get(ORDER_API_BASE_URL + "orderbybuyer?id=" + userIId)
}
// function deleteOrder(){
//     return axios.delete(ORDER_API_BASE_URL);
// }