package pl.sda.springwebmvc.entity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class BookEntity {
    @Id
    @EqualsAndHashCode.Include
    private String isbn;

    private String title;

    private String authors;

    private BigDecimal price;

    private String publisher;

    private int publicationYear;

    private boolean firstEdition;

    @CreationTimestamp
    private Timestamp created;
}
