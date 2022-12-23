const BOOK_API_BASE_URL = "http://localhost:8082/book-service/books/"

function getBooks(){
    return axios.get(BOOK_API_BASE_URL);
}
function editBook(bookInfo){
    return axios.put(BOOK_API_BASE_URL, bookInfo);
}
function deleteBook(bookId){
    return axios.delete(BOOK_API_BASE_URL, {data: bookId});
}
function getUserBooks(userId){
    return axios.get(BOOK_API_BASE_URL+userId);
}
function addNewBook(bookInfo){
    return axios.post(BOOK_API_BASE_URL, bookInfo)
}
// function getBookById(bookId){
//     return axios.get(BOOK_API_BASE_URL+"bookbybookid?id="+bookId)
// }
function getBookById(bookId){
    return axios.get("http://localhost:8089/bookName/"+bookId)
}