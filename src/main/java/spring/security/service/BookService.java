package spring.security.service;


import spring.security.model.Book;

import java.util.List;

/**
 * @author ss.kovalevskiy
 * @version 1.0
 */
public interface BookService {
    public void addBook(Book book);

    public void updateBook(Book book);

    public void removeBook(int id);

    public Book getBookById(int id);

    public List<Book> listBooks();
}
