package pl.sda.springwebmvc.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="laptops")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LaptopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private int ram;

    private BigDecimal price;

    @Column(length = 20)
    private String cpu;
}
