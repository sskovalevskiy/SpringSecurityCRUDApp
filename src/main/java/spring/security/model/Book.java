package spring.security.model;

/**
 * @author ss.kovalevskiy
 * @version 1.0
 */
//Для привязки к БД
import javax.persistence.*;


@Entity                //сообщаем, что данный класс является сущностью
@Table(name = "books") //указываем, с какой таблицей он будет связан
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "select b from Book b"),
        @NamedQuery(name = "Book.findById", query = "select b from Book b where b.id = :id")
})
public class Book {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //Генерируемое значение
    private int id;

    @Column(name = "BOOK_TITLE")
    private String bookTitle;

    @Column(name = "BOOK_AUTHOR")
    private String bookAuthor;

    @Column(name = "BOOK_PRICE")
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", price=" + price +
                '}';
    }
}
