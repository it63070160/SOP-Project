const BOOK_API_BASE_URL = "http://localhost:8082/books"

function getBooks(){
    return axios.get(BOOK_API_BASE_URL);
}
function deleteBook(){
    return axios.delete(BOOK_API_BASE_URL);
}