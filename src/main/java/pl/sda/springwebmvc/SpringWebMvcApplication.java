package pl.sda.springwebmvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.springwebmvc.model.Book;
import pl.sda.springwebmvc.service.BookService;

import java.math.BigDecimal;

@SpringBootApplication
public class SpringWebMvcApplication implements CommandLineRunner {
    private final BookService service;

    public SpringWebMvcApplication(BookService service) {
        this.service = service;
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
                            .publisher("Helion")
                            .build()
            );
        }
    }
}
