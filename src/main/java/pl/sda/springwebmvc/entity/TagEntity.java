package pl.sda.springwebmvc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String label;

    private String description;

    @ManyToMany(mappedBy = "tags")
    private Set<BookEntity> books;
}
