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