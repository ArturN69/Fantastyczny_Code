package pl.sda.springwebmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.springwebmvc.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, String> {
}
