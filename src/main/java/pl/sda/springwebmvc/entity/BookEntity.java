package pl.sda.springwebmvc.entity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(targetEntity = TagEntity.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<TagEntity> tags = new HashSet<>();
}
