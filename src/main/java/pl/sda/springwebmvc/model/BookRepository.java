package pl.sda.springwebmvc.model;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository {

    private Map<String, Book> books = new HashMap<>();

    public BookRepository() {
        final Book book1 = Book.builder()
                .authors("Adam Nowak")
                .title("Java")
                .isbn("11")
                .price(new BigDecimal("20.5"))
                .firstEdition(false)
                .publishingYear(2000)
                .publisher("Helion")
                .build();
        save(book1);
        save(Book.builder()
                .price(new BigDecimal("11.80"))
                .publishingYear(2020)
                .isbn("24")
                .authors("Bloch")
                .title("Effective Java")
                .publisher("Pearson")
                .firstEdition(true)
                .build());
    }

    public void save(Book book) {
        books.put(book.getIsbn(), book);
    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }
}
