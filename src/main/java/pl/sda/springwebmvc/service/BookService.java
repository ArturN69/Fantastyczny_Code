package pl.sda.springwebmvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.springwebmvc.entity.BookEntity;
import pl.sda.springwebmvc.model.Book;
import pl.sda.springwebmvc.repository.BookRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        bookRepository.deleteById(isbn);
        return true;
    }

    public List<Book> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(mapFromEntity())
                .collect(Collectors.toList());
    }

    private Function<BookEntity, Book> mapFromEntity() {
        return entity ->
                Book.builder()
                        .isbn(entity.getIsbn())
                        .title(entity.getTitle())
                        .publisher(entity.getPublisher())
                        .authors(entity.getAuthors())
                        .price(entity.getPrice())
                        .firstEdition(entity.isFirstEdition())
                        .publishingYear(entity.getPublicationYear())
                        .build();
    }

    public Optional<Book> findById(String isbn) {
        return bookRepository.findById(isbn)
                .map(mapFromEntity());
    }
    //Dodanie @Transactional powoduje umieszczenie metody między
    //em.getTransaction().begin() i em.getTransaction().commit()
    //więc zmiana w encji zarządzanej jest utrwalana w bazie
    //nie jest konieczny save(entity)!!!
    @Transactional
    public boolean update(Book book) {
        final Optional<BookEntity> optionalBook = bookRepository.findById(book.getIsbn());
        if (optionalBook.isPresent()){
            BookEntity entity = optionalBook.get();
            entity.setAuthors(book.getAuthors());
            entity.setTitle(book.getTitle());
            entity.setFirstEdition(book.getFirstEdition());
            //Przykład rollback - zgloszenie wyjątku typu RuntimeException
            if (book.getPublishingYear() == LocalDate.now().getYear()) {
                entity.setPublicationYear(book.getPublishingYear());
            } else {
                throw new RuntimeException("Książka nie spełnia reguł! Możesz modyfikować tylko książki z br!");
            }
            entity.setPublisher(book.getPublisher());
            return true;
        } else {
            return false;
        }
    }

    public List<Book> findBooksByAuthors(String authors){
        return bookRepository.findBookEntitiesByAuthors(authors)
                .stream()
                .map(mapFromEntity())
                .collect(Collectors.toList());
    }

    public int countBookByAuthors(String authors){
        return bookRepository.countByAuthors(authors);
    }

    @Transactional
    public List<Book> findBooksByPriceGreaterOrEquals15(){
        return bookRepository.readBookEntitiesByPriceGreaterThanEqual(new BigDecimal("15"))
                .map(mapFromEntity())
                .collect(Collectors.toList());
    }

    @Transactional
    public Long deleteBooksByPublicationYear(int publicationYear){
        return bookRepository.deleteByPublicationYear(publicationYear);
    }
}
