package spring.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ss.kovalevskiy
 * @version 1.0
 */
@Service("bookService")
@Repository //Класс является репозиторием
public class BookServiceImpl implements BookService {

    //    для обеспечения логгирования
    public static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    //    Для внедрения EntityManager мы используем аннотацию @Persistence Context, которая представляет собой
//    стандартную аннотацию JPA для внедрения диспетчера сущностей.
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addBook(Book book) {
        entityManager.persist(book);
//        добавляем информацию для логгирования
        logger.info("Book successfully saved. Book details: " + book);

    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        entityManager.merge(book);
//        добавляем информацию для логгирования
        logger.info("Book successfully updated. Book details: " + book);


    }

    @Override
    @Transactional
    public void removeBook(int id) {
//        загружаем книгу по id
        Book book = getBookById(id);
        Book mergedBook = entityManager.merge(book);
        entityManager.remove(mergedBook);

        logger.info("Book successfully removed. Book details: " + book);

    }

    @Override
    @Transactional
    public Book getBookById(int id) {
        TypedQuery<Book> query = entityManager.createNamedQuery("Book.findById", Book.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Book> listBooks() {

        List<Book> bookList = entityManager.createNamedQuery("Book.findAll", Book.class).getResultList();
        for (Book book : bookList) {
            logger.info("Book list " + book);
        }
        return bookList;
    }
}
