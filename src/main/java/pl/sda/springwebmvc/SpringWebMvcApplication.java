package pl.sda.springwebmvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.springwebmvc.entity.User;
import pl.sda.springwebmvc.model.Book;
import pl.sda.springwebmvc.repository.BookRepository;
import pl.sda.springwebmvc.repository.UserRepository;
import pl.sda.springwebmvc.service.BookService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Set;

@SpringBootApplication
public class SpringWebMvcApplication implements CommandLineRunner {
    private final BookService service;
    private final BookRepository repository;
    private final UserRepository users;

    @PersistenceContext
    private EntityManager em;

    public SpringWebMvcApplication(BookService service, BookRepository repository, UserRepository users) {
        this.service = service;
        this.repository = repository;
        this.users = users;
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
        if (!users.existsById(1L)){
            users.save(
                    User.builder()
                            .email("ewa@op.pl")
                            .enabled(true)
                            .password("$2a$12$DxhouXpOTZqu5NSv9h0y0e3NFbKANXFKNmG1z/1D5yeWkIUEIoHRe")
                            .firstName("Ewa")
                            .lastName("Kowal")
                            .authority("ROLE_ADMIN")
                            .build()
            );
        }
        if (!users.existsById(2L)){
            users.save(
                    User.builder()
                            .email("adam@op.pl")
                            .enabled(true)
                            .password("$2a$12$DxhouXpOTZqu5NSv9h0y0e3NFbKANXFKNmG1z/1D5yeWkIUEIoHRe")
                            .firstName("Adam")
                            .lastName("Nowak")
                            .authority("ROLE_USER")
                            .build()
            );
        }

        //System.out.println(service.findAll());
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
