package pl.sda.springwebmvc.service;

import org.springframework.stereotype.Service;
import pl.sda.springwebmvc.entity.BookEntity;
import pl.sda.springwebmvc.model.Book;
import pl.sda.springwebmvc.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save(Book book) {
        bookRepository.save(BookEntity.builder()
                .title(book.getTitle())
                .authors(book.getAuthors())
                .firstEdition(book.getFirstEdition())
                .price(book.getPrice())
                .publisher(book.getPublisher())
                .isbn(book.getIsbn())
                .publicationYear(book.getPublishingYear())
                .build());
    }

    public boolean delete(String isbn) {
        return false;
    }

    public List<Book> findAll() {
        return null;
    }

    public Optional<Book> findById(String isbn) {
        return Optional.empty();
    }

    public boolean update(Book book) {
        return false;
    }
}
