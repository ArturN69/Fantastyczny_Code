package pl.sda.springwebmvc.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.springwebmvc.entity.BookEntity;

@Primary
@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {
}
