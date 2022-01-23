package pl.sda.springwebmvc.model;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class BookRepository {

    private Map<String, Book> books = new HashMap<>();

    public BookRepository() {
        final Book book1 = Book.builder()
                .authors("Adam Nowak")
                .title("Java")
                .isbn("978-83-246-7762-7")
                .price(new BigDecimal("20.5"))
                .firstEdition(false)
                .publishingYear(2000)
                .publisher("Helion")
                .build();
        save(book1);
        save(Book.builder()
                .price(new BigDecimal("11.80"))
                .publishingYear(2020)
                .isbn("978-83-246-2084-5")
                .authors("Bloch")
                .title("Effective Java")
                .publisher("Pearson")
                .firstEdition(true)
                .build());
    }

    public void save(Book book) {
        books.put(book.getIsbn(), book);
    }

    public boolean delete(String isbn){
//        if (books.remove(isbn)!=null) {
//            return true;
//        }
//        else {
//            return false;
//        }
        return books.remove(isbn) != null ? true : false;
    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    public Optional<Book> findById(String isbn){
        return Optional.ofNullable(books.get(isbn));
    }

    public boolean update(Book book){
        final Optional<Book> bookOptional = findById(book.getIsbn());
        if (bookOptional.isPresent()){
            Book original = bookOptional.get();
            original.setTitle(book.getTitle());
            original.setAuthors(book.getAuthors());
            original.setPublisher(book.getPublisher());
            original.setFirstEdition(book.getFirstEdition());
            original.setPublisher(book.getPublisher());
            return true;
        } else {
            return false;
        }
    }
}
