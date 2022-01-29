package pl.sda.springwebmvc.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sda.springwebmvc.entity.BookEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@Primary
@Repository("jpaRepository")
public interface BookRepository extends JpaRepository<BookEntity, String> {

    List<BookEntity> findBookEntitiesByAuthors(String authors);

    int countByAuthors(String authors);

    Long deleteByPublicationYear(int publishingYear);

    Stream<BookEntity> readBookEntitiesByPriceGreaterThanEqual(BigDecimal limit);

    List<BookEntity> findDistinctTop5ByFirstEditionIsFalse();

    @Query("select b from BookEntity b where b.authors = ?1")
    List<BookEntity> booksByAuthors(String authors);
}
