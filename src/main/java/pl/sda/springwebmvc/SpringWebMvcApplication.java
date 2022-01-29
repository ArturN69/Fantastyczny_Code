package pl.sda.springwebmvc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.springwebmvc.entity.BookEntity;
import pl.sda.springwebmvc.model.Book;
import pl.sda.springwebmvc.model.MemoryBookRepository;
import pl.sda.springwebmvc.repository.BookRepository;
import pl.sda.springwebmvc.service.BookService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringWebMvcApplication implements CommandLineRunner {
    private final BookService service;
    private final BookRepository repository;

    @PersistenceContext
    private EntityManager em;

    public SpringWebMvcApplication(BookService service, BookRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringWebMvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (service.findById("978-83-246-2084-5").isEmpty()) {
            service.save(
                    Book.builder()
                            .price(new BigDecimal("11.80"))
                            .publishingYear(2020)
                            .isbn("978-83-246-2084-5")
                            .authors("Bloch")
                            .title("Effective Java")
                            .publisher("Pearson")
                            .tagLabels(Set.of("programming", "java"))
                            .firstEdition(true)
                            .build()
            );
        }
        if (service.findById("978-83-246-7762-7").isEmpty()) {
            service.save(
                    Book.builder()
                            .authors("Adam Nowak")
                            .title("Java")
                            .isbn("978-83-246-7762-7")
                            .price(new BigDecimal("20.5"))
                            .firstEdition(false)
                            .publishingYear(2000)
                            .tagLabels(Set.of("spring", "java"))
                            .publisher("Helion")
                            .build()
            );
        }
        System.out.println(service.findAll());
        //testBuildInQueries();
        //jpqlQueries();
        //testNativeQueries();
    }

    void testBuildInQueries(){
        //final Stream<BookEntity> stream = repository.readBookEntitiesByPriceGreaterThanEqual(new BigDecimal("15"));
        System.out.println(service.findBooksByPriceGreaterOrEquals15());
        //pobrany z kontekstu em nie udostępnia transakcji !!!

        //em.getTransaction().begin();

        //to można zrobić tylko w metodzie z @Transactional

        //long deleted = repository.deleteByPublicationYear(2000);
        //em.getTransaction().commit();
        //System.out.println("usunięto : " + deleted);

        final Long deleted = service.deleteBooksByPublicationYear(2000);
        System.out.println(deleted);
        System.out.println(repository.findAll());
    }

    void jpqlQueries(){
        System.out.println(repository.booksByAuthors("Bloch"));
        int updated = service.updateFirstEdition("Java", 2000);
        System.out.println("Liczba zmienionych książek: " + updated);
        System.out.println(service.findAll());
    }

    void testNativeQueries(){
        service.insertBook("345-78-89-3344", "Nowe", 2021);
        System.out.println(service.findAll());
    }
}
